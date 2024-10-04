package com.Twitter_Clone.twitterc.dto.mapper;

import com.Twitter_Clone.twitterc.dto.UserDto;
import com.Twitter_Clone.twitterc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    public static UserDto toUserDto(User user){
        UserDto userDto=new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setImage(user.getImage());


        userDto.setBackgroudImage(user.getBackgroundImage());
        userDto.setBackgroudImage(user.getBackgroundImage());
        userDto.setWebsite(user.getWebsite());
        userDto.setLocation(user.getLocation());
        userDto.setBio(user.getBio());
        userDto.setDob(user.getDob());

        userDto.setFollowers(toUserDtos(user.getFollowers()));
        userDto.setFollowings(toUserDtos(user.getFollowing()));

        userDto.setLogin_with_google(user.isLogin_with_google());
//        userDto.setVerified(false);



        return userDto;
    }

    private static List<UserDto> toUserDtos(List<User> followers) {
        List<UserDto> userDtos=new ArrayList<>();
            for(User user: followers){

                UserDto userDto=new UserDto();


                userDto.setId(user.getId());
                userDto.setEmail(user.getEmail());
                userDto.setFullName(user.getFullName());
                userDto.setImage(user.getImage());


                userDtos.add(userDto);
            }

        return userDtos;
    }
}
