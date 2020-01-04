package com.star.foodfans.service;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {

    String sendMessage(HttpServletRequest request, String msg, String to);

    String readMessage(HttpServletRequest request);

    String deleteMessage(HttpServletRequest request,String msgId);

}
