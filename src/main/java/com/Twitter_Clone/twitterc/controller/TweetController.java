package com.Twitter_Clone.twitterc.controller;


import com.Twitter_Clone.twitterc.dto.TweetDto;
import com.Twitter_Clone.twitterc.service.TweetService;
import com.Twitter_Clone.twitterc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    public ResponseEntity<TweetDto>





}
