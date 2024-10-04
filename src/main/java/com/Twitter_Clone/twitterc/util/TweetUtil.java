package com.Twitter_Clone.twitterc.util;

import com.Twitter_Clone.twitterc.model.Like;
import com.Twitter_Clone.twitterc.model.Tweet;
import com.Twitter_Clone.twitterc.model.User;

public class TweetUtil

{

    public final static boolean isLikedByReqUser(User reqUser , Tweet tweet){
          for(Like like:tweet.getLikes()){
              if(like.getUser().getId().equals(reqUser.getId())){
                  return true;
              }
          }
          return false;
    }
    public static final boolean isRetweetedByReqUser(User reqUser,Tweet tweet){

        for(User user:tweet.getRetweetUser()){
            if(user.getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }

}
