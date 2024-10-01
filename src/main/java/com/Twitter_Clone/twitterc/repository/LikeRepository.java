package com.Twitter_Clone.twitterc.repository;

import com.Twitter_Clone.twitterc.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

//    checking if the like for the tweet exist
    @Query("SELECT l FROM Like l WHERE l.user.id=:userId AND l.tweet.id=:tweetId")
    public Like isLikeExist(@Param("userId") Long userId,@Param("tweetId") Long tweetId);


//    this method will list out all the users who liked the tweet that is likes for the tweet
    @Query("SELECT l FROM Like l WHERE l.tweet.id=:tweetId")
    public List<Like> findByTweetId(@Param("tweetId")Long tweetId);

}
