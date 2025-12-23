\c shoplite_order_db;

-- ⚠️ "order" est un mot réservé SQL, donc on utilise "orders" (souvent Hibernate fait pareil)
CREATE TABLE IF NOT EXISTS orders (
                                      id BIGSERIAL PRIMARY KEY,
                                      order_number VARCHAR(255) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_amount NUMERIC(12,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS order_item (
                                          id BIGSERIAL PRIMARY KEY,
                                          order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price NUMERIC(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    line_total NUMERIC(12,2) NOT NULL
    );

-- Order #1
INSERT INTO orders (id, order_number, customer_id, status, total_amount, created_at, updated_at) VALUES
    (1, 'ORD-000001', 1, 'PENDING', 379.80, NOW(), NOW())
    ON CONFLICT (order_number) DO NOTHING;

INSERT INTO order_item (order_id, product_id, product_name, unit_price, quantity, line_total) VALUES
    (1, 1, 'Bavette Angus', 189.90, 2, 379.80);

-- Order #2
INSERT INTO orders (id, order_number, customer_id, status, total_amount, created_at, updated_at) VALUES
    (2, 'ORD-000002', 2, 'PENDING', 449.70, NOW(), NOW())
    ON CONFLICT (order_number) DO NOTHING;

INSERT INTO order_item (order_id, product_id, product_name, unit_price, quantity, line_total) VALUES
                                                                                                  (2, 1, 'Bavette Angus', 189.90, 1, 189.90),
                                                                                                  (2, 2, 'Rumsteak',      129.90, 2, 259.80);

SELECT setval(pg_get_serial_sequence('orders','id'), (SELECT COALESCE(MAX(id), 1) FROM orders));
SELECT setval(pg_get_serial_sequence('order_item','id'), (SELECT COALESCE(MAX(id), 1) FROM order_item));
