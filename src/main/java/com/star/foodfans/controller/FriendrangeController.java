package com.star.foodfans.controller;

import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.service.FriendrangeService;
import com.star.foodfans.service.FriendrangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FriendrangeController {

    @Autowired
    private FriendrangeServiceImpl friendrangeService;

    @RequestMapping(value = "/add_friend", method = RequestMethod.POST)
    public String register(HttpServletRequest request, String friendId){
        return friendrangeService.addFriend(request, Integer.valueOf(friendId));
    }

    @RequestMapping(value = "/delete_friend", method = RequestMethod.POST)
    public String deleteFriend(HttpServletRequest request, String friendId){
        return friendrangeService.deleteFriend(request, Integer.valueOf(friendId));
    }

    @RequestMapping(value = "/get_personal_friends", method = RequestMethod.GET)
    public String getPersonalFriend(HttpServletRequest request){
        return friendrangeService.getPersonalFriends(request);
    }

}
