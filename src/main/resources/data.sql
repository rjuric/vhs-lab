INSERT INTO users (created_at, updated_at, email, password)
SELECT *
FROM (
    VALUES
        (NOW(), NOW(), 'user1@example.com', '$2a$10$vmQtY5Evl1OlywgTfQm3O.5tPtki1jwv1pWP8l/EtiisXBoLwR9.C'),
        (NOW(), NOW(), 'user2@example.com', '$2a$10$.UBHeCDlzFDyZXZGMYT2uux4X5VxLvdyUNhPtoAXF7eDv1W0T2Z3O'),
        (NOW(), NOW(), 'user3@example.com', '$2a$10$rudIfng.2lClKLeeMN5/q.pSHJTtSuae7A6NnXOx.SstPOmHVM/4W')
    )
WHERE NOT EXISTS (
    SELECT 1
    FROM users
    WHERE email LIKE 'user%@example.com'
);

INSERT INTO vhs (created_at, updated_at, description, name)
SELECT *
FROM (
    VALUES
        (NOW(), NOW(), 'Frodo, a hobbit, embarks on a quest to destroy a powerful ring, joined by a diverse fellowship facing many dangers.', 'The Lord of the Rings - The Fellowship od the Ring'),
        (NOW(), NOW(), 'Frodo and Sam journey to Mordor while Aragorn, Legolas, and Gimli aid Rohan against Saruman''s forces in epic battles.', 'The Lord of the Rings - The Two Towers'),
        (NOW(), NOW(), 'Frodo and Sam reach Mount Doom, while Aragorn leads the final battle against Sauron''s forces to save Middle-earth.', 'The Lord of the Rings - The Return of the King')
    )
WHERE NOT EXISTS (
    SELECT 1
    FROM vhs
    WHERE name LIKE 'The Lord of the Rings - %'
);

INSERT INTO rental (created_at, updated_at, start_date, end_date, returned_at, user_id, vhs_id)
SELECT *
FROM (
    VALUES
        (NOW(), NOW(), NOW(), NOW() + INTERVAL '7d', NULL::timestamp, 1, 3),
        (NOW(), NOW(), NOW() - INTERVAL '4d', NOW() - INTERVAL '1d', NOW() - INTERVAL '1d', 1, 2),
        (NOW(), NOW(), NOW() - INTERVAL '8d', NOW() - INTERVAL '5d', NOW() - INTERVAL '6d', 1, 1),
        (NOW(), NOW(), NOW() + INTERVAL '8d', NOW() + INTERVAL '9d', NULL::timestamp, 2, 3),
        (NOW(), NOW(), NOW() + INTERVAL '8d', NOW() + INTERVAL '9d', NULL::timestamp, 2, 2),
        (NOW(), NOW(), NOW() + INTERVAL '8d', NOW() + INTERVAL '9d', NULL::timestamp, 2, 1)
    )
WHERE NOT EXISTS (
    SELECT 1
    FROM rental
    INNER JOIN users ON users.id = rental.user_id
    WHERE users.email LIKE 'user%@example.com'
);
