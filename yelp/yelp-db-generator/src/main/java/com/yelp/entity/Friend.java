package com.yelp.entity;

public class Friend {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend.friend_id
     *
     * @mbg.generated
     */
    private String friendId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.user_id
     *
     * @return the value of friend.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.user_id
     *
     * @param userId the value for friend.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend.friend_id
     *
     * @return the value of friend.friend_id
     *
     * @mbg.generated
     */
    public String getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend.friend_id
     *
     * @param friendId the value for friend.friend_id
     *
     * @mbg.generated
     */
    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }
}