package com.Twitter_Clone.twitterc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String fullName;
        private String location;
        private String website;
        private String dob;
        private String email;
        private String password;
        private String mobileno;
        private String image;
        private String backgroundImage;
        private String bio;
        private boolean req_user;
        private boolean login_with_google;

        @JsonIgnore
        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
        private List<Tweet> tweets=new ArrayList<>();

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
        private List<Like> likes=new ArrayList<>();


//        this is for the blue batch
        @Embedded
        private Varification varification;

//this is the setting for followers and follwing function

        @JsonIgnore
        @ManyToMany
        private List<User> followers=new ArrayList<>();

        @JsonIgnore
        @ManyToMany
        private List<User> following=new ArrayList<>();


}

