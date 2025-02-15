CREATE TABLE save (
    userId BIGINT REFERENCES users (id),
    tweetId BIGINT REFERENCES tweet (id),
    savedAT TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (userId, tweetId)
)