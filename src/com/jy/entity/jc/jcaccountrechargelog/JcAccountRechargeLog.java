package com.jy.entity.jc.jcaccountrechargelog;

import java.util.Date;

public class JcAccountRechargeLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.account_log_id
     *
     * @mbggenerated
     */
    private String accountLogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.pay_log_id
     *
     * @mbggenerated
     */
    private String payLogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.pay_type
     *
     * @mbggenerated
     */
    private Short payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.money
     *
     * @mbggenerated
     */
    private Integer money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_recharge_log.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.account_log_id
     *
     * @return the value of jc_account_recharge_log.account_log_id
     *
     * @mbggenerated
     */
    public String getAccountLogId() {
        return accountLogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.account_log_id
     *
     * @param accountLogId the value for jc_account_recharge_log.account_log_id
     *
     * @mbggenerated
     */
    public void setAccountLogId(String accountLogId) {
        this.accountLogId = accountLogId == null ? null : accountLogId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.pay_log_id
     *
     * @return the value of jc_account_recharge_log.pay_log_id
     *
     * @mbggenerated
     */
    public String getPayLogId() {
        return payLogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.pay_log_id
     *
     * @param payLogId the value for jc_account_recharge_log.pay_log_id
     *
     * @mbggenerated
     */
    public void setPayLogId(String payLogId) {
        this.payLogId = payLogId == null ? null : payLogId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.pay_type
     *
     * @return the value of jc_account_recharge_log.pay_type
     *
     * @mbggenerated
     */
    public Short getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.pay_type
     *
     * @param payType the value for jc_account_recharge_log.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(Short payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.money
     *
     * @return the value of jc_account_recharge_log.money
     *
     * @mbggenerated
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.money
     *
     * @param money the value for jc_account_recharge_log.money
     *
     * @mbggenerated
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.create_time
     *
     * @return the value of jc_account_recharge_log.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.create_time
     *
     * @param createTime the value for jc_account_recharge_log.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_recharge_log.update_time
     *
     * @return the value of jc_account_recharge_log.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_recharge_log.update_time
     *
     * @param updateTime the value for jc_account_recharge_log.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}