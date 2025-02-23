import {TweetModel} from './tweet-model';

export interface StatusModel {
  parent: TweetModel,
  comments: TweetModel[]
}
