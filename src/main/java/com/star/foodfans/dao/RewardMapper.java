package com.star.foodfans.dao;

import com.star.foodfans.entity.Reward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RewardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reward
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer rewardid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reward
     *
     * @mbggenerated
     */
    int insert(Reward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reward
     *
     * @mbggenerated
     */
    Reward selectByPrimaryKey(Integer rewardid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reward
     *
     * @mbggenerated
     */
    List<Reward> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reward
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Reward record);
}