ALTER TABLE tweet RENAME TO post;

ALTER TABLE post ADD COLUMN commentOn BIGINT REFERENCES post (id);

ALTER TABLE save DROP CONSTRAINT save_tweetid_fkey;
ALTER TABLE save RENAME COLUMN tweetId TO postId;
ALTER TABLE save ADD CONSTRAINT save_postid_fkey FOREIGN KEY (postId) REFERENCES post (id);

ALTER TABLE retweet DROP CONSTRAINT retweet_tweetid_fkey;
ALTER TABLE retweet RENAME COLUMN tweetId TO postId;
ALTER TABLE retweet ADD CONSTRAINT retweet_postid_fkey FOREIGN KEY (postId) REFERENCES post (id);

ALTER TABLE tweetLikes DROP CONSTRAINT tweetlikes_tweetid_fkey;
ALTER TABLE tweetLikes RENAME COLUMN tweetId TO postId;
ALTER TABLE tweetLikes ADD CONSTRAINT tweetlikes_postid_fkey FOREIGN KEY (postId) REFERENCES post (id);

ALTER TABLE tweetlikes RENAME TO postLikes;

DROP TABLE commentlikes;

DROP TABLE comment;

