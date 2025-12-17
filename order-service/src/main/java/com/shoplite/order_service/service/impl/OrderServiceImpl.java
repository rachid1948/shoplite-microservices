package com.shoplite.order_service.service.impl;

import com.shoplite.order_service.client.ProductClient;
import com.shoplite.order_service.domain.entity.OrderItem;
import com.shoplite.order_service.domain.enums.OrderStatus;
import com.shoplite.order_service.dto.request.OrderCreateRequestDto;
import com.shoplite.order_service.dto.request.OrderRequestDto;
import com.shoplite.order_service.dto.request.OrderUpdateRequestDto;
import com.shoplite.order_service.dto.response.OrderResponseDto;
import com.shoplite.order_service.domain.entity.Order;
import com.shoplite.order_service.dto.search.OrderSearchCriteria;
import com.shoplite.order_service.exception.OrderNotFoundException;
import com.shoplite.order_service.repository.OrderRepository;
import com.shoplite.order_service.service.OrderService;
import com.shoplite.order_service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;




import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    @Override
    public OrderResponseDto createOrder(OrderCreateRequestDto dto) {

        // 1) créer l'entité Order (sans items si tu veux)
        Order order = new Order();
        order.setCustomerId(dto.customerId());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderNumber("ORD-" + UUID.randomUUID());

        // 2) construire les items enrichis (snapshot)
        List<OrderItem> items = dto.items().stream().map(it -> {
            var product = productClient.getProductById(it.productId()); // Feign

            OrderItem item = new OrderItem();
            item.setProductId(it.productId());
            item.setProductName(product.name());
            item.setUnitPrice(product.price());

            item.setQuantity(it.quantity());

            BigDecimal lineTotal = product.price().multiply(BigDecimal.valueOf(it.quantity()));
            item.setLineTotal(lineTotal);

            item.setOrder(order); // si relation ManyToOne
            return item;
        }).toList();

        order.setItems(items);

        // 3) totalAmount = somme des lineTotal (qui ne sont plus null)
        BigDecimal totalAmount = items.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        // 4) save
        Order saved = orderRepository.save(order);
        return OrderMapper.toResponseDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return OrderMapper.toResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setStatus(newStatus);

        // Si tu as un champ updatedAt avec @PreUpdate, il sera mis à jour automatiquement
        order = orderRepository.save(order);

        return OrderMapper.toResponseDto(order);
    }


    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrders(OrderSearchCriteria criteria, Pageable pageable) {

        Page<Order> ordersPage;

        if (criteria.status() != null) {
            ordersPage = orderRepository.findByStatus(criteria.status(), pageable);
        } else {
            ordersPage = orderRepository.findAll(pageable);
        }

        return ordersPage.map(OrderMapper::toResponseDto);
    }



    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long id, OrderUpdateRequestDto dto) {
        // 1) Récupérer la commande ou exception
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // 2) Mettre à jour les infos client
        order.setCustomerId(dto.customerId());
        // (on ne touche pas au status ni au orderNumber ici)

        // 3) Recréer la liste des items à partir du DTO
        List<OrderItem> newItems = dto.items().stream()
                .map(itemDto -> OrderMapper.toEntity(itemDto, order))
                .toList();

        // 4) Remplacer les anciens items par les nouveaux
        order.getItems().clear();
        order.getItems().addAll(newItems);

        // 5) Recalculer le total de la commande
        BigDecimal totalAmount = newItems.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        // 6) Sauvegarder + renvoyer le DTO réponse
        Order saved = orderRepository.save(order);
        return OrderMapper.toResponseDto(saved);
    }
}
