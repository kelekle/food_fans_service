package com.star.foodfans.dao;

import com.star.foodfans.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int insert(Record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    Record selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    List<Record> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Record record);

    List<Record> selectByUserId(int id);
}