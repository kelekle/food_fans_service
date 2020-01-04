package com.star.foodfans.dao;

import com.star.foodfans.entity.Achievemenkindtinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AchievementkindinfoDao implements AchievemenkindtinfoMapper{

    @Autowired
    private AchievemenkindtinfoMapper achievemenkindtinfoMapper;


    @Override
    public int deleteByPrimaryKey(Integer typeid) {
        return achievemenkindtinfoMapper.deleteByPrimaryKey(typeid);
    }

    @Override
    public int insert(Achievemenkindtinfo record) {
        return achievemenkindtinfoMapper.insert(record);
    }

    @Override
    public Achievemenkindtinfo selectByPrimaryKey(Integer typeid) {
        return achievemenkindtinfoMapper.selectByPrimaryKey(typeid);
    }

    @Override
    public List<Achievemenkindtinfo> selectAll() {
        return achievemenkindtinfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Achievemenkindtinfo record) {
        return achievemenkindtinfoMapper.updateByPrimaryKey(record);
    }

}
