\c shoplite_customer_db;

CREATE TABLE IF NOT EXISTS customer (
                                        id BIGSERIAL PRIMARY KEY,
                                        first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE,
    phone      VARCHAR(30),
    address    VARCHAR(255),
    active     BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
    );

-- IDs forcés pour que tes orders utilisent customerId=1 et customerId=2
INSERT INTO customer (id, first_name, last_name, email, phone, address, active, created_at, updated_at) VALUES
                                                                                                            (1, 'Imane',   'Saidi',     'imane.saidi@shoplite.ma',     '+212600000002', 'Agdal, Rabat',      TRUE, NOW(), NOW()),
                                                                                                            (2, 'Yassine', 'Ait Omar',  'yassine.aitomar@shoplite.ma', '+212600000003', 'Guéliz, Marrakech', TRUE, NOW(), NOW()),
                                                                                                            (3, 'Sara',    'El Amrani', 'sara.elamrani@shoplite.ma',   '+212600000004', 'Hay Riad, Rabat',   TRUE, NOW(), NOW())
    ON CONFLICT (email) DO NOTHING;

SELECT setval(pg_get_serial_sequence('customer','id'), (SELECT COALESCE(MAX(id), 1) FROM customer));
