-- Inserimento prodotti (2 per ogni categoria con ID fissi)

-- BOOKS (ID: 1-2)
INSERT INTO product (id, name, price, description, type) VALUES (1, 'Libro Java', 29.90, 'Un manuale completo su Java', 'BOOKS');
INSERT INTO product (id, name, price, description, type) VALUES (2, 'Libro C++', 29.90, 'Un manuale completo su C++', 'BOOKS');

-- GARDEN (ID: 3-4)
INSERT INTO product (id, name, price, description, type) VALUES (3, 'Set da Giardino', 59.99, 'Sedie e tavolo in legno per esterni', 'GARDEN');
INSERT INTO product (id, name, price, description, type) VALUES (4, 'Attrezzi da Giardinaggio', 34.50, 'Kit completo di attrezzi per il giardino', 'GARDEN');

-- ELECTRONICS (ID: 5-6)
INSERT INTO product (id, name, price, description, type) VALUES (5, 'Cuffie Wireless', 99.90, 'Cuffie Bluetooth con cancellazione rumore', 'ELECTRONICS');
INSERT INTO product (id, name, price, description, type) VALUES (6, 'Smartphone Android', 299.99, 'Smartphone con display HD e doppia fotocamera', 'ELECTRONICS');

-- CLOTHING (ID: 7-8)
INSERT INTO product (id, name, price, description, type) VALUES (7, 'Maglietta Estiva', 19.90, 'Maglietta in cotone 100% traspirante', 'CLOTHING');
INSERT INTO product (id, name, price, description, type) VALUES (8, 'Jeans Classici', 49.90, 'Jeans in denim blu scuro taglio classico', 'CLOTHING');

-- OTHER (ID: 9-10)
INSERT INTO product (id, name, price, description, type) VALUES (9, 'Borsa da Viaggio', 79.90, 'Borsa resistente per viaggi e weekend', 'OTHER');
INSERT INTO product (id, name, price, description, type) VALUES (10, 'Orologio da Polso', 129.99, 'Orologio analogico con cinturino in pelle', 'OTHER');

-- Relazioni prodotti simili (accoppiati per categoria)
-- BOOKS: Libro Java <-> Libro C++
INSERT INTO product_similar (product_id, similar_id) VALUES (1, 2);
INSERT INTO product_similar (product_id, similar_id) VALUES (2, 1);
-- GARDEN: Set da Giardino <-> Attrezzi da Giardinaggio
INSERT INTO product_similar (product_id, similar_id) VALUES (3, 4);
INSERT INTO product_similar (product_id, similar_id) VALUES (4, 3);
-- ELECTRONICS: Cuffie Wireless <-> Smartphone Android
INSERT INTO product_similar (product_id, similar_id) VALUES (5, 6);
INSERT INTO product_similar (product_id, similar_id) VALUES (6, 5);
-- CLOTHING: Maglietta Estiva <-> Jeans Classici
INSERT INTO product_similar (product_id, similar_id) VALUES (7, 8);
INSERT INTO product_similar (product_id, similar_id) VALUES (8, 7);

-- OTHER: Borsa da Viaggio <-> Orologio da Polso
INSERT INTO product_similar (product_id, similar_id) VALUES (9, 10);
INSERT INTO product_similar (product_id, similar_id) VALUES (10, 9);

-- UTENTI (con tutti i campi obbligatori)
INSERT INTO users (id, username, email, password, first_name, last_name, role, enabled, created_at) VALUES (1, 'Valerio', 'valerio@prodottobello.com', '$2a$10$/NxylownsxOMdl6zjhFkmOAGukyVWcweh.cwhi7wq1yaq.FcMo9G.', 'Valerio', 'Admin', 'ADMIN', true, NOW());
INSERT INTO users (id, username, email, password, first_name, last_name, role, enabled, created_at) VALUES (2, 'Riccardo', 'riccardo@email.com', '$2a$10$6KTe5Mj8EIL0WBTII3RZR.X1J3HqAuPDjtF6FS8ExuKBLiXM1vDMS', 'Riccardo', 'Utente', 'USER', true, NOW());

-- Aggiorna la sequence 
SELECT setval('users_seq', (SELECT MAX(id) FROM users));
SELECT setval('product_seq', (SELECT MAX(id) FROM product));
