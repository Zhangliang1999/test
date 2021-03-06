package com.jy.entity.jc.user;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Alias("jcuser")
public class User extends BaseEntity{
    private Integer isValid;
    private String operId;
    private Integer isRobot;



	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.head_icon
     *
     * @mbggenerated
     */
    private String headIcon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.real_name
     *
     * @mbggenerated
     */
    private String realName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.id_card
     *
     * @mbggenerated
     */
    private String idCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.nick_name
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.signature
     *
     * @mbggenerated
     */
    private String signature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.invite_code
     *
     * @mbggenerated
     */
    private String inviteCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.follow_count
     *
     * @mbggenerated
     */
    private Long followCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_user.update_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.user_id
     *
     * @return the value of jc_user.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.user_id
     *
     * @param userId the value for jc_user.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.mobile
     *
     * @return the value of jc_user.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.mobile
     *
     * @param mobile the value for jc_user.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.password
     *
     * @return the value of jc_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.password
     *
     * @param password the value for jc_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.head_icon
     *
     * @return the value of jc_user.head_icon
     *
     * @mbggenerated
     */
    public String getHeadIcon() {
        return headIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.head_icon
     *
     * @param headIcon the value for jc_user.head_icon
     *
     * @mbggenerated
     */
    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon == null ? null : headIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.real_name
     *
     * @return the value of jc_user.real_name
     *
     * @mbggenerated
     */
    public String getRealName() {
        return realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.real_name
     *
     * @param realName the value for jc_user.real_name
     *
     * @mbggenerated
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.id_card
     *
     * @return the value of jc_user.id_card
     *
     * @mbggenerated
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.id_card
     *
     * @param idCard the value for jc_user.id_card
     *
     * @mbggenerated
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.nick_name
     *
     * @return the value of jc_user.nick_name
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.nick_name
     *
     * @param nickName the value for jc_user.nick_name
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.signature
     *
     * @return the value of jc_user.signature
     *
     * @mbggenerated
     */
    public String getSignature() {
        return signature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.signature
     *
     * @param signature the value for jc_user.signature
     *
     * @mbggenerated
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.invite_code
     *
     * @return the value of jc_user.invite_code
     *
     * @mbggenerated
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.invite_code
     *
     * @param inviteCode the value for jc_user.invite_code
     *
     * @mbggenerated
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.follow_count
     *
     * @return the value of jc_user.follow_count
     *
     * @mbggenerated
     */
    public Long getFollowCount() {
        return followCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.follow_count
     *
     * @param followCount the value for jc_user.follow_count
     *
     * @mbggenerated
     */
    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.create_time
     *
     * @return the value of jc_user.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.create_time
     *
     * @param createTime the value for jc_user.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_user.update_time
     *
     * @return the value of jc_user.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_user.update_time
     *
     * @param updateTime the value for jc_user.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsRobot() {
        return isRobot;
    }

    public void setIsRobot(Integer isRobot) {
        this.isRobot = isRobot;
    }
}