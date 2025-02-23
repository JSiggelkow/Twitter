import {QuoteModel} from './quote-model';

export interface SocialInteractionsModel {
  tweetId: string,
  quote: QuoteModel,
  countComments: number,
  countRetweets: number,
  countLikes: number,
  viewsCount: number,
  isRetweeted: boolean,
  isLiked: boolean,
  isSaved: boolean
}
