package com.Twitter_Clone.twitterc.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "id")
    private User user;

    private  String content;
    private String image;
    private String video;

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @OneToMany
    private List<Tweet> replyTweets=new ArrayList<>();

    @ManyToMany
    private List<User> retweetUser=new ArrayList<>();

    @ManyToOne
    private Tweet replyFor;

    private boolean isReply;
    private boolean isTweet;



}
