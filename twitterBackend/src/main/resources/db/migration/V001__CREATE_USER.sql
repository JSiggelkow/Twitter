CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) UNIQUE  NOT NULL,
    password TEXT                NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL DEFAULT NOW()
)