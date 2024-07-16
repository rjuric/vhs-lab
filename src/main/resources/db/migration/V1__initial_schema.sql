CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles TEXT
);

CREATE TABLE vhs (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(120) NOT NULL
);

CREATE TABLE rental (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    returned_at TIMESTAMP,
    user_id BIGINT,
    vhs_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (vhs_id) REFERENCES vhs(id)
);