package com.star.foodfans.dao;

import com.star.foodfans.entity.Record;
import com.star.foodfans.entity.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardDao implements RewardMapper {

    @Autowired
    private RewardMapper rewardMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return rewardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Reward record) {
        return rewardMapper.insert(record);
    }

    @Override
    public Reward selectByPrimaryKey(Integer rewardid) {
        return rewardMapper.selectByPrimaryKey(rewardid);
    }

    @Override
    public List<Reward> selectAll() {
        return rewardMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Reward record) {
        return rewardMapper.updateByPrimaryKey(record);
    }

}
