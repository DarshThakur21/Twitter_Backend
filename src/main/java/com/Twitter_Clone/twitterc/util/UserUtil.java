package com.Twitter_Clone.twitterc.util;

import com.Twitter_Clone.twitterc.model.User;

public class UserUtil {

    public static final boolean isReqUser (User reqUser, User reqUser2){
        return reqUser.getId().equals(reqUser2.getId());
    }

    public static final boolean isFollowedByReqUser (User reqUser, User user2){
         return reqUser.getFollowing().contains(user2);
    }
}
