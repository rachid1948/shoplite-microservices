\c shoplite_product_db;

CREATE TABLE IF NOT EXISTS product (
                                       id BIGSERIAL PRIMARY KEY,
                                       name VARCHAR(150) NOT NULL,
    sku VARCHAR(100) NOT NULL UNIQUE,
    price NUMERIC(10,2) NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
    description VARCHAR(500),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

-- IDs forcés pour que tes orders utilisent productId=1 et productId=2
INSERT INTO product (id, name, sku, price, quantity_in_stock, description, created_at, updated_at) VALUES
                                                                                                       (1, 'Bavette Angus',   'BAV-ANG-001', 189.90, 50, 'Bavette Angus premium', NOW(), NOW()),
                                                                                                       (2, 'Rumsteak',        'RUM-001',     129.90, 40, 'Rumsteak classique',     NOW(), NOW()),
                                                                                                       (3, 'Entrecôte Angus', 'ENT-ANG-002', 249.90, 30, 'Entrecôte tendre et juteuse', NOW(), NOW())
    ON CONFLICT (sku) DO NOTHING;

SELECT setval(pg_get_serial_sequence('product','id'), (SELECT COALESCE(MAX(id), 1) FROM product));
