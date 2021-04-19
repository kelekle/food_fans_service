package com.star.foodfans.controller;

import com.star.foodfans.service.FriendrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FriendrangeController {

    @Autowired
    @Qualifier(value = "FriendrangeService")
    private FriendrangeService friendrangeService;

    @RequestMapping(value = "/add_friend", method = RequestMethod.POST)
    public String register(HttpServletRequest request, String friendId){
        return friendrangeService.follow(request, Integer.valueOf(friendId));
    }

    @RequestMapping(value = "/delete_friend", method = RequestMethod.POST)
    public String deleteFriend(HttpServletRequest request, String friendId){
        return friendrangeService.cancelFollow(request, Integer.valueOf(friendId));
    }

    @RequestMapping(value = "/get_personal_friends", method = RequestMethod.GET)
    public String getPersonalFriend(HttpServletRequest request){
        return friendrangeService.getPersonalFriends(request);
    }

}
