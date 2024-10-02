package com.Twitter_Clone.twitterc.service;

import com.Twitter_Clone.twitterc.exception.TweetException;
import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.request.TweetReplyRequest;


import java.util.List;


//@Service
public interface TweetService {

//  1
    public Tweet createTweet(Tweet req, User user) throws UserException;

//2 it will give you the tweets and post you see in the homepage
    public List<Tweet> findAllTweet();

// 3   creating method for retweeting on what tweet user is retweeting
    public Tweet retweet(Long tweetId,User user)throws  UserException, TweetException;


// 4 finding  tweet by using the tweet id
    public Tweet findById(Long tweetId) throws TweetException;


//    5
    public void deleteTweetById(Long tweetId,Long userId) throws TweetException,UserException;

//  6  removing the tweet from the retweet or the comment section
    public Tweet removeFromRetweet(Long tweetId,User user)throws TweetException,UserException;

//7 creating a reply for comment section
public Tweet createdReply(TweetReplyRequest req, User user)throws TweetException;

//8 finding particular user tweet (finding out all the tweet user profile have
    public List<Tweet> getUserTweet(User user);

// 9 gives the tweets liked by user
    public List<Tweet> findByLikesContainsUser(User user);




}
