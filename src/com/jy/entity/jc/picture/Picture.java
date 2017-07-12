package com.jy.entity.jc.picture;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 上传的图片
 */
@Alias("picture")
public class Picture extends BaseEntity {
    private String keyWord;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.pic_id
     *
     * @mbggenerated
     */
    private String picId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.comment
     *
     * @mbggenerated
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.path
     *
     * @mbggenerated
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_picture.oper_id
     *
     * @mbggenerated
     */
    private String operId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.pic_id
     *
     * @return the value of jc_picture.pic_id
     *
     * @mbggenerated
     */
    public String getPicId() {
        return picId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.pic_id
     *
     * @param picId the value for jc_picture.pic_id
     *
     * @mbggenerated
     */
    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.name
     *
     * @return the value of jc_picture.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.name
     *
     * @param name the value for jc_picture.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.comment
     *
     * @return the value of jc_picture.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.comment
     *
     * @param comment the value for jc_picture.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.path
     *
     * @return the value of jc_picture.path
     *
     * @mbggenerated
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.path
     *
     * @param path the value for jc_picture.path
     *
     * @mbggenerated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.create_time
     *
     * @return the value of jc_picture.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.create_time
     *
     * @param createTime the value for jc_picture.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_picture.oper_id
     *
     * @return the value of jc_picture.oper_id
     *
     * @mbggenerated
     */
    public String getOperId() {
        return operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_picture.oper_id
     *
     * @param operId the value for jc_picture.oper_id
     *
     * @mbggenerated
     */
    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}