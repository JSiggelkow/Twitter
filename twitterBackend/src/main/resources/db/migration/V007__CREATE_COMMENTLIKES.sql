CREATE TABLE commentLikes
(
    userId   BIGINT REFERENCES users (id),
    commentId  BIGINT REFERENCES comment (id),
    likedAt TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (userId, commentId)
)
