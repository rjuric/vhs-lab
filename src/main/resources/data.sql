INSERT INTO users (created_at, updated_at, email, password, roles)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'user1@example.com', '$2a$10$vmQtY5Evl1OlywgTfQm3O.5tPtki1jwv1pWP8l/EtiisXBoLwR9.C', ARRAY['USER']),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'user2@example.com', '$2a$10$.UBHeCDlzFDyZXZGMYT2uux4X5VxLvdyUNhPtoAXF7eDv1W0T2Z3O', ARRAY['USER']),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'user3@example.com', '$2a$10$rudIfng.2lClKLeeMN5/q.pSHJTtSuae7A6NnXOx.SstPOmHVM/4W', ARRAY['USER','ADMIN']);

INSERT INTO vhs (created_at, updated_at, description, name)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Frodo, a hobbit, embarks on a quest to destroy a powerful ring, joined by a diverse fellowship facing many dangers.', 'The Lord of the Rings - The Fellowship od the Ring'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Frodo and Sam journey to Mordor while Aragorn, Legolas, and Gimli aid Rohan against Saruman''s forces in epic battles.', 'The Lord of the Rings - The Two Towers'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Frodo and Sam reach Mount Doom, while Aragorn leads the final battle against Sauron''s forces to save Middle-earth.', 'The Lord of the Rings - The Return of the King');

INSERT INTO rental (created_at, updated_at, start_date, end_date, returned_at, user_id, vhs_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '7' DAY, NULL, 1, 3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP - INTERVAL '4' DAY, CURRENT_TIMESTAMP - INTERVAL '1' DAY, CURRENT_TIMESTAMP - INTERVAL '1' DAY, 1, 2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP - INTERVAL '8' DAY, CURRENT_TIMESTAMP - INTERVAL '5' DAY, CURRENT_TIMESTAMP - INTERVAL '6' DAY, 1, 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '8' DAY, CURRENT_TIMESTAMP + INTERVAL '9' DAY, NULL, 2, 3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '8' DAY, CURRENT_TIMESTAMP + INTERVAL '9' DAY, NULL, 2, 2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '8' DAY, CURRENT_TIMESTAMP + INTERVAL '9' DAY, NULL, 2, 1);