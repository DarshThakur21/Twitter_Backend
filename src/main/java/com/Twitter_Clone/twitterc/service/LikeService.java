package com.Twitter_Clone.twitterc.service;

import com.Twitter_Clone.twitterc.exception.TweetException;
import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.Like;
import com.Twitter_Clone.twitterc.model.User;

import java.util.List;

public interface LikeService {

//    1 it is used for liking the post also for unliking
    public Like likeTweet(Long tweetId, User user) throws UserException, TweetException;

//2 gives the list of all the likes done on the post in tweet
    public List<Like> getAllLikes(Long tweetId) throws TweetException;


}
