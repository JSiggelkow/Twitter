CREATE TABLE Tweet
(
    id        BIGSERIAL PRIMARY KEY,
    text      TEXT,
    video     TEXT,
    image     TEXT,
    userId    BIGINT REFERENCES users (id),
    retweetId BIGINT REFERENCES Tweet (id),
    createdAt TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT at_least_on_not_null CHECK ( (text IS NOT NULL) OR
                                            (video IS NOT NULL) OR
                                            (image IS NOT NULL))
)
