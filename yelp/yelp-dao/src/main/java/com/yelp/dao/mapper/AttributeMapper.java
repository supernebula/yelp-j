package com.yelp.dao.mapper;

import com.yelp.entity.Attribute;
import com.yelp.entity.AttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttributeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    long countByExample(AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int deleteByExample(AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int insert(Attribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int insertSelective(Attribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    List<Attribute> selectByExampleWithBLOBs(AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    List<Attribute> selectByExample(AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    Attribute selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Attribute record, @Param("example") AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") Attribute record, @Param("example") AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Attribute record, @Param("example") AttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Attribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(Attribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attribute
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Attribute record);
}