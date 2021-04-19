package com.star.foodfans.service;

import javax.servlet.http.HttpServletRequest;

public interface FriendrangeService {

    String follow(HttpServletRequest request, Integer friendId);
    String cancelFollow(HttpServletRequest request, Integer friendId);
    String getPersonalFriends(HttpServletRequest request);

}
