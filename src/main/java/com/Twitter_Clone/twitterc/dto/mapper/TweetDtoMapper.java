package com.Twitter_Clone.twitterc.dto.mapper;

import com.Twitter_Clone.twitterc.dto.TweetDto;
import com.Twitter_Clone.twitterc.dto.UserDto;
import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.util.TweetUtil;

import java.util.ArrayList;
import java.util.List;

public class TweetDtoMapper {
    public static TweetDto toTweetDto(Tweet tweet, User reqUser){

        UserDto userDto=UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked= TweetUtil.isLikedByReqUser(reqUser,tweet);

        boolean isRetweeted=TweetUtil.isRetweetedByReqUser(reqUser,tweet);

        List<Long> retweetUserId=new ArrayList<>();
        for(User user: tweet.getRetweetUser()){
            retweetUserId.add(user.getId());
        }

          TweetDto tweetDto=new TweetDto();

        tweetDto.setId(tweet.getId());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setImage(tweet.getImage());
        tweetDto.setTotalLike(tweet.getLikes().size());
        tweetDto.setTotalReplies(tweet.getReplyTweets().size());
        tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDto.setUser(userDto);
        tweetDto.setLiked(isLiked);
        tweetDto.setRetweet(isRetweeted);

        tweetDto.setRetweetUsersId(retweetUserId);
        tweetDto.setReplyTweets(toTweetDtos(tweet.getReplyTweets(),reqUser));

        tweetDto.setVideo(tweet.getVideo());

        return tweetDto;
    }


        public static List<TweetDto> toTweetDtos(List<Tweet> tweets,User reqUser){
        List<TweetDto > tweetDtos=new ArrayList<>();

        for(Tweet tweet:tweets){
            TweetDto tweetDto=toReplyTweetDto(tweet,reqUser);
            tweetDtos.add(tweetDto);
        }
        return tweetDtos;
        }

    private static TweetDto toReplyTweetDto(Tweet tweet, User reqUser) {


        UserDto userDto=UserDtoMapper.toUserDto(tweet.getUser());

        boolean isLiked= TweetUtil.isLikedByReqUser(reqUser,tweet);

        boolean isRetweeted=TweetUtil.isRetweetedByReqUser(reqUser,tweet);

        List<Long> retweetUserId=new ArrayList<>();
        for(User user: tweet.getRetweetUser()){
            retweetUserId.add(user.getId());
        }
        TweetDto tweetDto=new TweetDto();

        tweetDto.setId(tweet.getId());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setCreatedAt(tweet.getCreatedAt());
        tweetDto.setImage(tweet.getImage());
        tweetDto.setTotalLike(tweet.getLikes().size());
        tweetDto.setTotalReplies(tweet.getReplyTweets().size());
        tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
        tweetDto.setUser(userDto);
        tweetDto.setLiked(isLiked);
        tweetDto.setRetweet(isRetweeted);

        tweetDto.setRetweetUsersId(retweetUserId);
        tweetDto.setVideo(tweet.getVideo());

        return tweetDto;
    }


}
