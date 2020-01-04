package com.star.foodfans.dao;

import com.star.foodfans.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao implements UserinfoMapper {

    @Autowired
    private UserinfoMapper mapper;

    @Override
    public int insertByEmail(String username, String email, String password) {
        return mapper.insertByEmail(username, email, password);
    }

    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return mapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(Userinfo record) {
        return mapper.insert(record);
    }

    @Override
    public int updateUserInfo(Integer userid, String username, String headpicture, String phone) {
        return mapper.updateUserInfo(userid, username, headpicture, phone);
    }

    @Override
    public Userinfo selectByEmail(String email) {
        return mapper.selectByEmail(email);
    }

    @Override
    public Userinfo selectByPrimaryKey(Integer userid) {
        return mapper.selectByPrimaryKey(userid);
    }

    @Override
    public List<Userinfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Userinfo record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public String selectPasswordByUserid(Integer userid) {
        return mapper.selectPasswordByUserid(userid);
    }

    @Override
    public int changePassword(Integer userid, String newPassword) {
        return mapper.changePassword(userid, newPassword);
    }

    @Override
    public int changePasswordByEmail(String email, String newPassword) {
        return mapper.changePasswordByEmail(email, newPassword);
    }

    @Override
    public List<Userinfo> selectNameByArticle(Integer userId) {
        return mapper.selectNameByArticle(userId);
    }

    @Override
    public List<Userinfo> selectNameByVideo(Integer userId) {
        return mapper.selectNameByVideo(userId);
    }


}
