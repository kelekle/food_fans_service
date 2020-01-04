package com.star.foodfans.dao;

import com.star.foodfans.entity.Friendrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendrangeDao implements FriendrangeMapper {
    @Autowired
    private FriendrangeMapper friendrangeMapper;

    @Override
    public int deleteByPrimaryKey(Integer userid, Integer friendid) {
        return friendrangeMapper.deleteByPrimaryKey(userid, friendid);
    }

    @Override
    public int insert(Friendrange record) {
        return friendrangeMapper.insert(record);
    }

    @Override
    public Friendrange selectByPrimaryKey(Integer userid, Integer friendid) {
        return friendrangeMapper.selectByPrimaryKey(userid, friendid);
    }

    @Override
    public List<Friendrange> selectAll() {
        return friendrangeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Friendrange record) {
        return friendrangeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Friendrange> selectPersonalFriends(Integer userid) {
        return friendrangeMapper.selectPersonalFriends(userid);
    }
}
