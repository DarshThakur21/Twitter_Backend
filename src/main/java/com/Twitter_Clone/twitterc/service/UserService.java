package com.Twitter_Clone.twitterc.service;

import com.Twitter_Clone.twitterc.exception.UserException;
import com.Twitter_Clone.twitterc.model.User;

import java.util.List;
//import jdk.jshell.spi.ExecutionControl;

public interface UserService {

//1
    public User findUserById(Long userId) throws UserException;
//2
    public User findUserProfileByJwt(String jwt) throws UserException;

//3
    public User updateUser (Long userId,User user) throws UserException;

//4 follow and unfollow user methods
    public User followUser(Long userId,User user) throws UserException;

//5 user search
    public List<User> searchUser(String query);



}
