package com.Twitter_Clone.twitterc.dto;

import com.Twitter_Clone.twitterc.model.Tweet;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class TweetDto {
    private Long id;
    private String content;
    private String image;
    private String video;
    private UserDto user;
    private LocalDateTime createdAt;
    private int totalLike;
    private int totalReplies;
    private int totalRetweets;

    private boolean isLiked;

    private boolean isRetweet;

    private List<Long> retweetUsersId;
    private List<TweetDto> replyTweets;


}
