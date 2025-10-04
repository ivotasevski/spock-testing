CREATE TABLE outbox
(
    id      BIGSERIAL PRIMARY KEY,
    payload TEXT NOT NULL
);