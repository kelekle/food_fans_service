package com.star.foodfans.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.star.foodfans.dao.MessageDao;
import com.star.foodfans.dao.UserDao;
import com.star.foodfans.entity.Message;
import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Override
    public String sendMessage(HttpServletRequest request, String msg, String to) {
        JSONObject object = new JSONObject();
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        Message message = new Message();
        message.setCanread(1);
        message.setContent(msg);
        message.setIsread(0);
        message.setReceive(Integer.valueOf(to));
        message.setSender(uid);
        message.setTimestamp(System.currentTimeMillis());
        if(messageDao.insert(message) > 0){
            object.put("result", "success");
            object.put("msg", "发送失败");
        }else {
            object.put("result", "fail");
            object.put("msg", "发送成功");
        }
        return object.toJSONString();
    }

    @Override
    public String readMessage(HttpServletRequest request) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        List<Message> messages = messageDao.selectOnesMessage(uid);
        JSONArray array = new JSONArray();
        for(Message message:messages){
            JSONObject object = new JSONObject();
            object.put("message", message);
            Userinfo userinfo = userDao.selectByPrimaryKey(message.getSender());
            object.put("username", userinfo.getUsername());
            object.put("userid", userinfo.getUserid());
            array.add(object);
        }
        messageDao.readMessage(uid);
        return array.toJSONString();
    }

    @Override
    public String deleteMessage(HttpServletRequest request,String msgId) {
        System.out.println("msg: " +msgId);
        JSONObject object = new JSONObject();
        if(messageDao.deleteByPrimaryKey(Integer.valueOf(msgId)) > 0){
            object.put("result","success");
        }else {
            object.put("result","fail");
        }
        return object.toJSONString();
    }

}
