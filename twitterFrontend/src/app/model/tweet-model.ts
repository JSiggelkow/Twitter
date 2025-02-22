export interface TweetModel {
  id: string;
  text: string;
  video: string;
  image: string;
  username: string;
  retweetedByUsername: string;
  retweetID: string;
  createdAt: string;
  countLikes: string;
  countRetweets: string;
  countComments: string;
  isLiked: boolean;
  isRetweeted: boolean;
}
