CREATE TABLE retweet
(
    userId   BIGINT REFERENCES users (id),
    tweetId  BIGINT REFERENCES tweet (id),
    retweetedAt TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (userId, tweetId)
)
