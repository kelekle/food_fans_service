package com.star.foodfans.entity;

import java.io.Serializable;

public class History implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column history.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column history.contentId
     *
     * @mbggenerated
     */
    private String contentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column history.createStamp
     *
     * @mbggenerated
     */
    private Long createstamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column history.isCollected
     *
     * @mbggenerated
     */
    private Integer iscollected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table history
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column history.userId
     *
     * @return the value of history.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column history.userId
     *
     * @param userid the value for history.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column history.contentId
     *
     * @return the value of history.contentId
     *
     * @mbggenerated
     */
    public String getContentid() {
        return contentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column history.contentId
     *
     * @param contentid the value for history.contentId
     *
     * @mbggenerated
     */
    public void setContentid(String contentid) {
        this.contentid = contentid == null ? null : contentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column history.createStamp
     *
     * @return the value of history.createStamp
     *
     * @mbggenerated
     */
    public Long getCreatestamp() {
        return createstamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column history.createStamp
     *
     * @param createstamp the value for history.createStamp
     *
     * @mbggenerated
     */
    public void setCreatestamp(Long createstamp) {
        this.createstamp = createstamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column history.isCollected
     *
     * @return the value of history.isCollected
     *
     * @mbggenerated
     */
    public Integer getIscollected() {
        return iscollected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column history.isCollected
     *
     * @param iscollected the value for history.isCollected
     *
     * @mbggenerated
     */
    public void setIscollected(Integer iscollected) {
        this.iscollected = iscollected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", contentid=").append(contentid);
        sb.append(", createstamp=").append(createstamp);
        sb.append(", iscollected=").append(iscollected);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}