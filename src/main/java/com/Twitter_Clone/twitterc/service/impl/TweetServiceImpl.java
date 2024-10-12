package com.Twitter_Clone.twitterc.service.impl;

import com.Twitter_Clone.twitterc.exception.TweetException;
import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.repository.TweetRepository;
import com.Twitter_Clone.twitterc.request.TweetReplyRequest;
import com.Twitter_Clone.twitterc.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;



    @Override
    public Tweet createTweet(Tweet req, User user) throws UserException {
        Tweet tweet=new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setImage(req.getImage());
        tweet.setUser(user);
        tweet.setReply(false); //as it is not replying tweet and more of a creating a new post
        tweet.setTweet(true);
        tweet.setVideo(req.getVideo());


        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllTweet() {

        return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
    }


//    retweeting the tweet from the comment section
    @Override
    public Tweet retweet(Long tweetId, User user) throws UserException, TweetException {
        Tweet tweet=findById(tweetId);//look for this if you face issue
        if(tweet.getRetweetUser().contains(user)){
            tweet.getRetweetUser().remove(user);
        }else{
            tweet.getRetweetUser().add(user);
        }

        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findById(Long tweetId) throws TweetException {

        Tweet tweet=tweetRepository.findById(tweetId)
                .orElseThrow(()->new TweetException("tweet not found with id "+tweetId));

        return tweet;
    }

    @Override
    public void deleteTweetById(Long tweetId, Long userId) throws TweetException, UserException {
        Tweet tweet=findById(tweetId);
        if(!userId.equals(tweet.getUser().getId())){
            throw new UserException("you cant delete other user tweets");
        }
        tweetRepository.deleteById(tweet.getId());

    }

    @Override
    public Tweet removeFromRetweet(Long tweetId, User user) throws TweetException, UserException {


        return null;
    }

    @Override
    public Tweet createdReply(TweetReplyRequest req, User user) throws TweetException {


        Tweet replyFor=findById(req.getTweetId());

        Tweet tweetReply =new Tweet();//created a reply object to the tweet
        tweetReply.setContent(req.getContent());
        tweetReply.setCreatedAt(LocalDateTime.now());
        tweetReply.setImage(req.getImage());
        tweetReply.setUser(user);
        tweetReply.setReply(true);
        tweetReply.setTweet(false);//as it is not replying tweet and more of a creating a new post
        tweetReply.setReplyFor(replyFor);

        Tweet savedReply=tweetRepository.save(tweetReply);

//        tweetReply.getReplyTweets().add(savedReply);
        replyFor.getReplyTweets().add(savedReply);
        tweetRepository.save(replyFor);

        return  replyFor;


    }

    @Override
    public List<Tweet> getUserTweet(User user) {
        return tweetRepository.findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
    }

    @Override
    public List<Tweet> findByLikesContainsUser(User user) {
        return  tweetRepository.findByLikesUser_id(user.getId());
    }
}
