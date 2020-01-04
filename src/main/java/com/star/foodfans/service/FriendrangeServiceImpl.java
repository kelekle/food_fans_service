package com.star.foodfans.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.star.foodfans.controller.User;
import com.star.foodfans.dao.FriendrangeDao;
import com.star.foodfans.dao.UserDao;
import com.star.foodfans.entity.Friendrange;
import com.star.foodfans.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.star.foodfans.util.Utils.resource_prefix;

@Service("FriendrangeService")
public class FriendrangeServiceImpl implements FriendrangeService {

    @Autowired
    private FriendrangeDao friendrangeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public String addFriend(HttpServletRequest request, Integer friendId) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        JSONObject object = new JSONObject();
        if(friendrangeDao.selectByPrimaryKey(uid, friendId) != null){
            object.put("result", "success");
            object.put("msg", "您已关注该好友");
            return object.toJSONString();
        }
        Friendrange friendrange = new Friendrange();
        friendrange.setUserid(uid);
        friendrange.setFriendid(friendId);
        friendrange.setCreattimestamp(System.currentTimeMillis());
        friendrange.setStatus(1);
        if (friendrangeDao.insert(friendrange) > 0) {
            object.put("result", "success");
            object.put("msg", "关注成功");
        } else {
            object.put("result", "fail");
            object.put("msg", "关注失败");
        }
        return object.toJSONString();
    }

    @Override
    public String deleteFriend(HttpServletRequest request, Integer friendId) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        JSONObject object = new JSONObject();
        if (friendrangeDao.deleteByPrimaryKey(uid, friendId) > 0) {
            object.put("result", "success");
            object.put("msg", "删除成功");
        } else {
            object.put("result", "fail");
            object.put("msg", "删除失败");
        }
        return object.toJSONString();
    }

    public String getPersonalFriends(HttpServletRequest request) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        List<Friendrange>  friendranges = friendrangeDao.selectPersonalFriends(uid);
        List<Userinfo> userinfos = new ArrayList<Userinfo>();
        for(Friendrange friendrange : friendranges){
            Userinfo userinfo = userDao.selectByPrimaryKey(friendrange.getFriendid());
            userinfo.setHeadpicture(resource_prefix + userinfo.getHeadpicture());
            userinfos.add(userinfo);
        }
        return JSONObject.toJSONString(userinfos);
    }
}
