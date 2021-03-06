package com.jy.entity.jc.jcaccountlog;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Alias("JcAccountLog")
public class JcAccountLog extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.account_log_id
     *
     * @mbggenerated
     */
    private String accountLogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.account_id
     *
     * @mbggenerated
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.last_balance
     *
     * @mbggenerated
     */
    private Integer lastBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.curr_balance
     *
     * @mbggenerated
     */
    private Integer currBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.income
     *
     * @mbggenerated
     */
    private Integer income;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.expenditure
     *
     * @mbggenerated
     */
    private Integer expenditure;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.source
     *
     * @mbggenerated
     */
    private Integer source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_account_log.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.account_log_id
     *
     * @return the value of jc_account_log.account_log_id
     *
     * @mbggenerated
     */
    public String getAccountLogId() {
        return accountLogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.account_log_id
     *
     * @param accountLogId the value for jc_account_log.account_log_id
     *
     * @mbggenerated
     */
    public void setAccountLogId(String accountLogId) {
        this.accountLogId = accountLogId == null ? null : accountLogId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.account_id
     *
     * @return the value of jc_account_log.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.account_id
     *
     * @param accountId the value for jc_account_log.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.last_balance
     *
     * @return the value of jc_account_log.last_balance
     *
     * @mbggenerated
     */
    public Integer getLastBalance() {
        return lastBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.last_balance
     *
     * @param lastBalance the value for jc_account_log.last_balance
     *
     * @mbggenerated
     */
    public void setLastBalance(Integer lastBalance) {
        this.lastBalance = lastBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.curr_balance
     *
     * @return the value of jc_account_log.curr_balance
     *
     * @mbggenerated
     */
    public Integer getCurrBalance() {
        return currBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.curr_balance
     *
     * @param currBalance the value for jc_account_log.curr_balance
     *
     * @mbggenerated
     */
    public void setCurrBalance(Integer currBalance) {
        this.currBalance = currBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.income
     *
     * @return the value of jc_account_log.income
     *
     * @mbggenerated
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.income
     *
     * @param income the value for jc_account_log.income
     *
     * @mbggenerated
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.expenditure
     *
     * @return the value of jc_account_log.expenditure
     *
     * @mbggenerated
     */
    public Integer getExpenditure() {
        return expenditure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.expenditure
     *
     * @param expenditure the value for jc_account_log.expenditure
     *
     * @mbggenerated
     */
    public void setExpenditure(Integer expenditure) {
        this.expenditure = expenditure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.source
     *
     * @return the value of jc_account_log.source
     *
     * @mbggenerated
     */
    public Integer getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.source
     *
     * @param source the value for jc_account_log.source
     *
     * @mbggenerated
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_account_log.create_time
     *
     * @return the value of jc_account_log.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_account_log.create_time
     *
     * @param createTime the value for jc_account_log.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}