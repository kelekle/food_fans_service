package com.star.foodfans.service;

import javax.servlet.http.HttpServletRequest;

public interface FriendrangeService {

    String addFriend(HttpServletRequest request, Integer friendId);
    String deleteFriend(HttpServletRequest request, Integer friendId);

}
