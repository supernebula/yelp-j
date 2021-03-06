package com.yelp.dao.mapper;

import com.yelp.entity.EliteYears;
import com.yelp.entity.EliteYearsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EliteYearsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    long countByExample(EliteYearsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int deleteByExample(EliteYearsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int insert(EliteYears record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int insertSelective(EliteYears record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    List<EliteYears> selectByExample(EliteYearsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    EliteYears selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EliteYears record, @Param("example") EliteYearsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EliteYears record, @Param("example") EliteYearsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EliteYears record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table elite_years
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EliteYears record);
}