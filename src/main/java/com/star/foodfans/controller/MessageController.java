package com.star.foodfans.controller;

import com.star.foodfans.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageController {

    @Autowired
    @Qualifier(value = "MessageService")
    private MessageService messageService;

    @RequestMapping(value = "/send_message", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest request, String msg, String to){
        return messageService.sendMessage(request, msg, to);
    }

    @RequestMapping(value = "/read_message", method = RequestMethod.GET)
    public String readMessage(HttpServletRequest request){
        return messageService.readMessage(request);
    }

    @RequestMapping(value = "/delete_message", method = RequestMethod.POST)
    public String readMessage(HttpServletRequest request, String msgId){
        return messageService.deleteMessage(request, msgId);
    }

}
