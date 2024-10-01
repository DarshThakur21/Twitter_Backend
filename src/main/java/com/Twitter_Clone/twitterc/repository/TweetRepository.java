package com.Twitter_Clone.twitterc.repository;

import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository  extends JpaRepository<Tweet,Long> {

    //this will help detecting whether the tweet is replied as comment or is stand along tweet



//this method helps in finding the post as tweet

    List<Tweet> findAllByIsTweetTrueOrderByCreatedAtDesc();



//this will give the times the user has tweeted the post
    List<Tweet> findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user,Long userId);


//    it will give the list of tweets a particular user have liked
    List<Tweet> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT t FROM TWEET t JOIN t.likes l WHERE l.user.id=:userId")
    List<Tweet> findByLikesUser_id( Long userId);

}
