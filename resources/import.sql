-- Inserimento prodotti (2 per ogni categoria con ID fissi)

-- BOOKS (ID: 1-2)
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (1, 'Libro Java', 56.90, 'Un manuale completo su Java', 'BOOKS', 'https://m.media-amazon.com/images/I/41AtD30pfGL._SY445_SX342_ML2_.jpg', 'https://www.amazon.it/nuovo-Java-completa-programmazione-moderna/dp/882039930X/ref=asc_df_882039930X?mcid=2eb6eb354f253b53ae6570f386f7d784&tag=googshopit-21&linkCode=df0&hvadid=700814440063&hvpos=&hvnetw=g&hvrand=11748694937746669955&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9181232&hvtargid=pla-941840108540&psc=1&hvocijid=11748694937746669955-882039930X-&hvexpln=0');
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (2, 'Libro C++', 46.55, 'Un manuale completo su C++', 'BOOKS', 'https://m.media-amazon.com/images/I/41QsY6R7SMS._SY445_SX342_ML2_.jpg', 'https://www.amazon.it/Fondamenti-programmazione-Algoritmi-strutture-oggetti/dp/8838699372/ref=asc_df_8838699372?mcid=2742c16e75a8342788028aa5d8d5ed38&tag=googshopit-21&linkCode=df0&hvadid=700814440063&hvpos=&hvnetw=g&hvrand=1968785440035819061&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9181232&hvtargid=pla-1392114532015&psc=1&hvocijid=1968785440035819061-8838699372-&hvexpln=0');

-- GARDEN (ID: 3-4)
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (3, 'Set da Giardino', 129.00, 'Sedie e tavolo in legno per esterni', 'GARDEN', 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcR57AVP9HC5Pk9DUFQzFjiY-gAyC1-GjObC8oSmBW0wMkn9f8ckPF3ox1Oi81e03uS-_Cd6be02ipj1F9om7cv--bZSo17BUd8fGdYAJrxb-AzkECLwL4aTwQ', 'https://www.leroymerlin.it/prodotti/salotto-da-giardino-arona-antracite-con-cuscini-in-poliestere-grigio-per-4-persone-93430171.html');
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (4, 'Attrezzi da Giardinaggio', 84.99, 'Kit completo di attrezzi per il giardino', 'GARDEN', 'https://i.ebayimg.com/images/g/OxAAAOSwGEZoOG7F/s-l1600.webp', 'https://www.ebay.it/itm/405908834771?chn=ps&norover=1&mkevt=1&mkrid=724-166974-047061-3&mkcid=2&mkscid=101&itemid=405908834771&targetid=2304343365564&device=c&mktype=pla&googleloc=9181232&poi=&campaignid=22806971262&mkgroupid=183502053580&rlsatarget=pla-2304343365564&abcId=10391313&merchantid=5605261501&geoid=9181232&gad_source=1&gad_campaignid=22806971262&gbraid=0AAAAACadmUkNMeNZ5KyT-PaXSq0RY6qEy&gclid=EAIaIQobChMIjtf1h6L0jwMVqUBBAh1q7y9UEAQYASABEgK9GfD_BwE');

-- ELECTRONICS (ID: 5-6)
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (5, 'Cuffie Wireless', 20.89, 'Cuffie Bluetooth con cancellazione rumore', 'ELECTRONICS', 'https://m.media-amazon.com/images/I/71DgSG37O9L.__AC_SX300_SY300_QL70_ML2_.jpg', 'https://www.amazon.it/Bluetooth-Riproduzione-Wireless-Modalit%C3%A0-Microfono/dp/B0BXCNW9Q5/ref=asc_df_B0BXCNW9Q5?mcid=017f963219413c13ba0b29785cdef8ab&tag=googshopit-21&linkCode=df0&hvadid=700948657610&hvpos=&hvnetw=g&hvrand=16784316973406347975&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9181232&hvtargid=pla-2245833634313&hvocijid=16784316973406347975-B0BXCNW9Q5-&hvexpln=0&th=1');
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (6, 'Smartphone Android', 117.99, 'Smartphone con display HD e doppia fotocamera', 'ELECTRONICS', 'https://m.media-amazon.com/images/I/61pc4vR8BoL.__AC_SX300_SY300_QL70_ML2_.jpg', 'https://www.amazon.it/Smartphone-Batteria-Resistenza-espandibile-Versione/dp/B0DKTJ22QN/ref=asc_df_B0DKTJ22QN?mcid=a83f553436dc37b79507ef87ff853c32&tag=googshopit-21&linkCode=df0&hvadid=722673707223&hvpos=&hvnetw=g&hvrand=2774191931699693759&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9181232&hvtargid=pla-2384894823802&psc=1&hvocijid=2774191931699693759-B0DKTJ22QN-&hvexpln=0');

-- CLOTHING (ID: 7-8)
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (7, 'Maglietta Estiva', 2.81, 'Maglietta in cotone 100% traspirante', 'CLOTHING', 'https://img.kwcdn.com/thumbnail/s/ce62e63422e6b631606c90d850a59f95_0820edc830e1.jpg?imageView2/2/w/264/q/70/format/webp', 'https://www.temu.com/ul/kuiper/un9.html?subj=goods-un&_bg_fs=1&_p_jump_id=894&_x_vst_scene=adg&goods_id=601099865935982&sku_id=17593648173107&adg_ctx=a-7f004714~c-9fe46bc3~f-6d6e9d2e&_x_ads_sub_channel=shopping&_p_rfs=1&_x_ns_prz_type=3&_x_ns_sku_id=17593648173107&_x_ns_gid=601099865935982&mrk_rec=1&_x_ads_channel=google&_x_gmc_account=5583678549&_x_login_type=Google&_x_ns_gg_lnk_type=adr&_x_ads_account=4059011744&_x_ads_set=22760837905&_x_ads_id=187222569772&_x_ads_creative_id=762179200204&_x_ns_source=g&_x_ns_gclid=EAIaIQobChMIyYKKv6L0jwMVcDkGAB1Vrzo8EAQYASABEgIxQPD_BwE&_x_ns_placement=&_x_ns_match_type=&_x_ns_ad_position=&_x_ns_product_id=5583678549-17593648173107&_x_ns_target=&_x_ns_devicemodel=&_x_ns_wbraid=Ck0KCQjwrc7GBhDTARI8AH9xqP-LdEMeQotk-hrRTp_s1dmusJn4HO4vt_PbOggY6gXxNleRBiLrqpgBNuTxanMiSYVBhpKVAOWbGgIPww&_x_ns_gbraid=0AAAAAo4mICGZ4TYO7faZLR0xDY6wQG8-i&_x_ns_targetid=pla-2427297419932&gad_source=1&gad_campaignid=22760837905&gbraid=0AAAAAo4mICGZ4TYO7faZLR0xDY6wQG8-i&gclid=EAIaIQobChMIyYKKv6L0jwMVcDkGAB1Vrzo8EAQYASABEgIxQPD_BwE');
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (8, 'Jeans Classici', 10.55, 'Jeans in denim blu scuro taglio classico', 'CLOTHING', 'https://img.kwcdn.com/thumbnail/s/926c380f0e10cc6a532cc21a08086130_3dbd57c8efac.jpg?imageView2/2/w/264/q/70/format/webp', 'https://www.temu.com/ul/kuiper/un9.html?subj=goods-un&_bg_fs=1&_p_jump_id=894&_x_vst_scene=adg&goods_id=601099575117469&sku_id=17592446264422&adg_ctx=a-67caa13e~c-d14445bf~f-6d6e9d2e&_x_ads_sub_channel=shopping&_p_rfs=1&_x_ns_prz_type=-1&_x_ns_sku_id=17592446264422&_x_ns_gid=601099575117469&mrk_rec=1&_x_ads_channel=google&_x_gmc_account=5583678549&_x_login_type=Google&_x_ns_gg_lnk_type=adr&_x_ads_account=4059011744&_x_ads_set=22789922144&_x_ads_id=177578207250&_x_ads_creative_id=763620512075&_x_ns_source=g&_x_ns_gclid=EAIaIQobChMI3e3u06L0jwMV3paDBx2drCRfEAQYASABEgKtI_D_BwE&_x_ns_placement=&_x_ns_match_type=&_x_ns_ad_position=&_x_ns_product_id=5583678549-17592446264422&_x_ns_target=&_x_ns_devicemodel=&_x_ns_wbraid=Ck0KCQjwrc7GBhDTARI8AH9xqP9HH27KOv13pQXJyvhaXBtF4yFkHV15y5i29NucrMKDhHDe3GnAfTgt5mjcSTZjQ6Y-Lv9utMuTGgJxmQ&_x_ns_gbraid=0AAAAAo4mICGtSSruAxOjQGhVCpeF0Mjpk&_x_ns_targetid=pla-2431746910545&gad_source=1&gad_campaignid=22789922144&gbraid=0AAAAAo4mICGtSSruAxOjQGhVCpeF0Mjpk&gclid=EAIaIQobChMI3e3u06L0jwMV3paDBx2drCRfEAQYASABEgKtI_D_BwE');

-- OTHER (ID: 9-10)
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (9, 'Borsa da Viaggio', 14.99, 'Borsa resistente per viaggi e weekend', 'OTHER', 'https://m.media-amazon.com/images/I/519t5N8rOLL.__AC_SX300_SY300_QL70_ML2_.jpg', 'https://www.amazon.it/Viaggio-Pieghevole-Capacit%C3%A0-Impermeabile-Campeggio/dp/B09B9N57V9/ref=asc_df_B09B9N57V9?mcid=3cb0ad27836a3e6e8d69cffd60ed8378&tag=googshopit-21&linkCode=df0&hvadid=700853099016&hvpos=&hvnetw=g&hvrand=2930000571097450999&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9181232&hvtargid=pla-1426916398926&psc=1&hvocijid=2930000571097450999-B09B9N57V9-&hvexpln=0');
INSERT INTO product (id, name, price, description, type, image_url, buy_link) VALUES (10, 'Orologio da Polso', 289.00, 'Orologio analogico con cinturino in pelle', 'OTHER', 'https://www.holzkern.com/media/catalog/product/1/_/1_210918_q_hanks_fa_32_copy.jpg', 'https://www.holzkern.com/it/naturalist-marmo-grigio.html?utm_source=shopping&utm_medium=cpc&utm_campaign=it-generic-watches-css&gad_source=1&gad_campaignid=20656599050&gbraid=0AAAAADfsqs-KUeqsNIjTM3IwO_HbHjxgi&gclid=EAIaIQobChMIhM6k9KL0jwMVfqeDBx2UQCn6EAQYASABEgLpT_D_BwE');

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

