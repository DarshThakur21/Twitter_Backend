package com.Twitter_Clone.twitterc.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    private String fullName;
    private String email;
    private String image;
    private String location;
    private String website;
    private String dob;
    private String mobileno;
    private String backgroudImage;
    private String bio;

    private boolean  req_user;

    private boolean login_with_google;

    private List<UserDto> followers=new ArrayList<>();
    private List<UserDto> followings=new ArrayList<>();

    private boolean followed;

    private boolean isVerified;




}
