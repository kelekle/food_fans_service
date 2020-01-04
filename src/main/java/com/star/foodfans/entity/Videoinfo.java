package com.star.foodfans.entity;

import java.io.Serializable;

public class Videoinfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.videoid
     *
     * @mbggenerated
     */
    private Integer videoid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.pictureurl
     *
     * @mbggenerated
     */
    private String pictureurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.urls
     *
     * @mbggenerated
     */
    private String urls;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.praiseNum
     *
     * @mbggenerated
     */
    private Integer praisenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.readNum
     *
     * @mbggenerated
     */
    private Integer readnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.commentNum
     *
     * @mbggenerated
     */
    private Integer commentnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.avgscore
     *
     * @mbggenerated
     */
    private float avgscore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.creatStamp
     *
     * @mbggenerated
     */
    private Long creatstamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.isTop
     *
     * @mbggenerated
     */
    private Integer istop;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.visitNum
     *
     * @mbggenerated
     */
    private Integer visitnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column videoinfo.tag
     *
     * @mbggenerated
     */
    private String tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.videoid
     *
     * @return the value of videoinfo.videoid
     *
     * @mbggenerated
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.videoid
     *
     * @param videoid the value for videoinfo.videoid
     *
     * @mbggenerated
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.userId
     *
     * @return the value of videoinfo.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.userId
     *
     * @param userid the value for videoinfo.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.title
     *
     * @return the value of videoinfo.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.title
     *
     * @param title the value for videoinfo.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.pictureurl
     *
     * @return the value of videoinfo.pictureurl
     *
     * @mbggenerated
     */
    public String getPictureurl() {
        return pictureurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.pictureurl
     *
     * @param pictureurl the value for videoinfo.pictureurl
     *
     * @mbggenerated
     */
    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl == null ? null : pictureurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.urls
     *
     * @return the value of videoinfo.urls
     *
     * @mbggenerated
     */
    public String getUrls() {
        return urls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.urls
     *
     * @param urls the value for videoinfo.urls
     *
     * @mbggenerated
     */
    public void setUrls(String urls) {
        this.urls = urls == null ? null : urls.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.praiseNum
     *
     * @return the value of videoinfo.praiseNum
     *
     * @mbggenerated
     */
    public Integer getPraisenum() {
        return praisenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.praiseNum
     *
     * @param praisenum the value for videoinfo.praiseNum
     *
     * @mbggenerated
     */
    public void setPraisenum(Integer praisenum) {
        this.praisenum = praisenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.readNum
     *
     * @return the value of videoinfo.readNum
     *
     * @mbggenerated
     */
    public Integer getReadnum() {
        return readnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.readNum
     *
     * @param readnum the value for videoinfo.readNum
     *
     * @mbggenerated
     */
    public void setReadnum(Integer readnum) {
        this.readnum = readnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.commentNum
     *
     * @return the value of videoinfo.commentNum
     *
     * @mbggenerated
     */
    public Integer getCommentnum() {
        return commentnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.commentNum
     *
     * @param commentnum the value for videoinfo.commentNum
     *
     * @mbggenerated
     */
    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.avgscore
     *
     * @return the value of videoinfo.avgscore
     *
     * @mbggenerated
     */
    public float getAvgscore() {
        return avgscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.avgscore
     *
     * @param avgscore the value for videoinfo.avgscore
     *
     * @mbggenerated
     */
    public void setAvgscore(float avgscore) {
        this.avgscore = avgscore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.creatStamp
     *
     * @return the value of videoinfo.creatStamp
     *
     * @mbggenerated
     */
    public Long getCreatstamp() {
        return creatstamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.creatStamp
     *
     * @param creatstamp the value for videoinfo.creatStamp
     *
     * @mbggenerated
     */
    public void setCreatstamp(Long creatstamp) {
        this.creatstamp = creatstamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.isTop
     *
     * @return the value of videoinfo.isTop
     *
     * @mbggenerated
     */
    public Integer getIstop() {
        return istop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.isTop
     *
     * @param istop the value for videoinfo.isTop
     *
     * @mbggenerated
     */
    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.visitNum
     *
     * @return the value of videoinfo.visitNum
     *
     * @mbggenerated
     */
    public Integer getVisitnum() {
        return visitnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.visitNum
     *
     * @param visitnum the value for videoinfo.visitNum
     *
     * @mbggenerated
     */
    public void setVisitnum(Integer visitnum) {
        this.visitnum = visitnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column videoinfo.tag
     *
     * @return the value of videoinfo.tag
     *
     * @mbggenerated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column videoinfo.tag
     *
     * @param tag the value for videoinfo.tag
     *
     * @mbggenerated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videoinfo
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoid=").append(videoid);
        sb.append(", userid=").append(userid);
        sb.append(", title=").append(title);
        sb.append(", pictureurl=").append(pictureurl);
        sb.append(", urls=").append(urls);
        sb.append(", praisenum=").append(praisenum);
        sb.append(", readnum=").append(readnum);
        sb.append(", commentnum=").append(commentnum);
        sb.append(", avgscore=").append(avgscore);
        sb.append(", creatstamp=").append(creatstamp);
        sb.append(", istop=").append(istop);
        sb.append(", visitnum=").append(visitnum);
        sb.append(", tag=").append(tag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}