package com.jy.entity.jc.sensitiveword;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Alias("sensitiveWord")
public class SensitiveWord extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.sensitive_id
     *
     * @mbggenerated
     */
    private String sensitiveId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.sensitive_word
     *
     * @mbggenerated
     */
    private String sensitiveWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.replace_word
     *
     * @mbggenerated
     */
    private String replaceWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.update_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_sensitive_word.oper_id
     *
     * @mbggenerated
     */
    private String operId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.sensitive_id
     *
     * @return the value of jc_sensitive_word.sensitive_id
     *
     * @mbggenerated
     */
    public String getSensitiveId() {
        return sensitiveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.sensitive_id
     *
     * @param sensitiveId the value for jc_sensitive_word.sensitive_id
     *
     * @mbggenerated
     */
    public void setSensitiveId(String sensitiveId) {
        this.sensitiveId = sensitiveId == null ? null : sensitiveId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.sensitive_word
     *
     * @return the value of jc_sensitive_word.sensitive_word
     *
     * @mbggenerated
     */
    public String getSensitiveWord() {
        return sensitiveWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.sensitive_word
     *
     * @param sensitiveWord the value for jc_sensitive_word.sensitive_word
     *
     * @mbggenerated
     */
    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord == null ? null : sensitiveWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.replace_word
     *
     * @return the value of jc_sensitive_word.replace_word
     *
     * @mbggenerated
     */
    public String getReplaceWord() {
        return replaceWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.replace_word
     *
     * @param replaceWord the value for jc_sensitive_word.replace_word
     *
     * @mbggenerated
     */
    public void setReplaceWord(String replaceWord) {
        this.replaceWord = replaceWord == null ? null : replaceWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.create_time
     *
     * @return the value of jc_sensitive_word.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.create_time
     *
     * @param createTime the value for jc_sensitive_word.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.update_time
     *
     * @return the value of jc_sensitive_word.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.update_time
     *
     * @param updateTime the value for jc_sensitive_word.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_sensitive_word.oper_id
     *
     * @return the value of jc_sensitive_word.oper_id
     *
     * @mbggenerated
     */
    public String getOperId() {
        return operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_sensitive_word.oper_id
     *
     * @param operId the value for jc_sensitive_word.oper_id
     *
     * @mbggenerated
     */
    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }
}