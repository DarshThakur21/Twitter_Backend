package com.Twitter_Clone.twitterc.controller;

import com.Twitter_Clone.twitterc.dto.LikeDto;
import com.Twitter_Clone.twitterc.dto.mapper.LikeDtoMapper;
import com.Twitter_Clone.twitterc.exception.TweetException;
import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.Like;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.service.LikeService;
import com.Twitter_Clone.twitterc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private UserService userService;



    @Autowired
    private LikeService likeService;


    @PostMapping("/{tweetId}/likes")
    public ResponseEntity<LikeDto> likeTweet(@PathVariable Long tweetId ,@RequestHeader("Authorization") String jwt)
    throws UserException , TweetException
    {
        User user=userService.findUserProfileByJwt(jwt);

        Like like=likeService.likeTweet(tweetId,user);

        LikeDto likeDto = LikeDtoMapper.toLikeDto(like,user);

        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
    }



    @PostMapping ("/tweet/{tweetId}")
    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long tweetId , @RequestHeader("Authorization") String jwt)
            throws UserException , TweetException
    {
        User user=userService.findUserProfileByJwt(jwt);

        List<Like> likes =likeService.getAllLikes(tweetId);

        List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(likes, user);

        return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);
    }


}
