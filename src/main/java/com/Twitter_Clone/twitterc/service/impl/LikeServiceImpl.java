package com.Twitter_Clone.twitterc.service.impl;

import com.Twitter_Clone.twitterc.exception.TweetException;
import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.Like;
import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.repository.LikeRepository;
import com.Twitter_Clone.twitterc.repository.TweetRepository;
import com.Twitter_Clone.twitterc.service.LikeService;
import com.Twitter_Clone.twitterc.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Like likeTweet(Long tweetId, User user) throws UserException, TweetException {
        Like isLikeExist=likeRepository.isLikeExist(user.getId(), tweetId);


        if(isLikeExist!=null){
            likeRepository.deleteById(isLikeExist.getId());  //unliking the likes on the tweet
            return isLikeExist;
        }
        Tweet tweet=tweetService.findById(tweetId);

        Like like=new Like();

        like.setTweet(tweet);
        like.setUser(user);

        Like savedLike=likeRepository.save(like);

        tweet.getLikes().add(savedLike);
        tweetRepository.save(tweet);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {
        Tweet tweet=tweetService.findById(tweetId);

        List<Like> likes=likeRepository.findByTweetId(tweetId);
        return likes;






    }
}
