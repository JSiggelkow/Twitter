CREATE TABLE tweetLikes
(
    userId   BIGINT REFERENCES users (id),
    tweetId  BIGINT REFERENCES tweet (id),
    likedAt TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (userId, tweetId)
)
