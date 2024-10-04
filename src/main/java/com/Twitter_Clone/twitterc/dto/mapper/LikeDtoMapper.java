package com.Twitter_Clone.twitterc.dto.mapper;

import com.Twitter_Clone.twitterc.dto.LikeDto;
import com.Twitter_Clone.twitterc.dto.TweetDto;
import com.Twitter_Clone.twitterc.dto.UserDto;
import com.Twitter_Clone.twitterc.model.Like;
import com.Twitter_Clone.twitterc.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {


    public  static LikeDto toLikeDto(Like like, User reqUser){

        UserDto userDto=UserDtoMapper.toUserDto(like.getUser());
        UserDto reqUserDto=UserDtoMapper.toUserDto(reqUser);
        TweetDto tweetDto=TweetDtoMapper.toTweetDto(like.getTweet(),reqUser);

        LikeDto likeDto=new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setTweet(tweetDto);
        likeDto.setUser(userDto);



        return likeDto;


    }

    public static List<LikeDto> toLikeDtos(List<Like> likes,User reqUser){
        List<LikeDto> likeDtos=new ArrayList<>();

        for(Like like:likes){
            UserDto userDto=UserDtoMapper.toUserDto(like.getUser());
            TweetDto tweetDto=TweetDtoMapper.toTweetDto(like.getTweet(),reqUser);

            LikeDto likeDto=new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setTweet(tweetDto);
            likeDto.setUser(userDto);
            likeDtos.add(likeDto);

        }
        return likeDtos;


    }
}
