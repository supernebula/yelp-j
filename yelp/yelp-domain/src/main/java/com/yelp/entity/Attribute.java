package com.yelp.entity;

public class Attribute {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attribute.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attribute.business_id
     *
     * @mbg.generated
     */
    private String businessId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attribute.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attribute.value
     *
     * @mbg.generated
     */
    private String value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attribute.id
     *
     * @return the value of attribute.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attribute.id
     *
     * @param id the value for attribute.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attribute.business_id
     *
     * @return the value of attribute.business_id
     *
     * @mbg.generated
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attribute.business_id
     *
     * @param businessId the value for attribute.business_id
     *
     * @mbg.generated
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attribute.name
     *
     * @return the value of attribute.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attribute.name
     *
     * @param name the value for attribute.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attribute.value
     *
     * @return the value of attribute.value
     *
     * @mbg.generated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attribute.value
     *
     * @param value the value for attribute.value
     *
     * @mbg.generated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}