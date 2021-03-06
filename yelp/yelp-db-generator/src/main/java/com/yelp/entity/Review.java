package com.yelp.entity;

import java.util.Date;

public class Review {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.stars
     *
     * @mbg.generated
     */
    private Integer stars;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.date
     *
     * @mbg.generated
     */
    private Date date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.useful
     *
     * @mbg.generated
     */
    private Integer useful;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.funny
     *
     * @mbg.generated
     */
    private Integer funny;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.cool
     *
     * @mbg.generated
     */
    private Integer cool;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.business_id
     *
     * @mbg.generated
     */
    private String businessId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review.text
     *
     * @mbg.generated
     */
    private String text;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.id
     *
     * @return the value of review.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.id
     *
     * @param id the value for review.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.stars
     *
     * @return the value of review.stars
     *
     * @mbg.generated
     */
    public Integer getStars() {
        return stars;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.stars
     *
     * @param stars the value for review.stars
     *
     * @mbg.generated
     */
    public void setStars(Integer stars) {
        this.stars = stars;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.date
     *
     * @return the value of review.date
     *
     * @mbg.generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.date
     *
     * @param date the value for review.date
     *
     * @mbg.generated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.useful
     *
     * @return the value of review.useful
     *
     * @mbg.generated
     */
    public Integer getUseful() {
        return useful;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.useful
     *
     * @param useful the value for review.useful
     *
     * @mbg.generated
     */
    public void setUseful(Integer useful) {
        this.useful = useful;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.funny
     *
     * @return the value of review.funny
     *
     * @mbg.generated
     */
    public Integer getFunny() {
        return funny;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.funny
     *
     * @param funny the value for review.funny
     *
     * @mbg.generated
     */
    public void setFunny(Integer funny) {
        this.funny = funny;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.cool
     *
     * @return the value of review.cool
     *
     * @mbg.generated
     */
    public Integer getCool() {
        return cool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.cool
     *
     * @param cool the value for review.cool
     *
     * @mbg.generated
     */
    public void setCool(Integer cool) {
        this.cool = cool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.business_id
     *
     * @return the value of review.business_id
     *
     * @mbg.generated
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.business_id
     *
     * @param businessId the value for review.business_id
     *
     * @mbg.generated
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.user_id
     *
     * @return the value of review.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.user_id
     *
     * @param userId the value for review.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review.text
     *
     * @return the value of review.text
     *
     * @mbg.generated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review.text
     *
     * @param text the value for review.text
     *
     * @mbg.generated
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}