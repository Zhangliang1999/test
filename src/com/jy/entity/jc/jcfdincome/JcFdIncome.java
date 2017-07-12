package com.jy.entity.jc.jcfdincome;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("JcFdIncome")
public class JcFdIncome extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.issue
     *
     * @mbggenerated
     */
    private String issue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.fd_cnt
     *
     * @mbggenerated
     */
    private Integer fdCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.sell_cnt
     *
     * @mbggenerated
     */
    private Integer sellCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.income
     *
     * @mbggenerated
     */
    private Integer income;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.cycle_type
     *
     * @mbggenerated
     */
    private Integer cycleType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.bill_type
     *
     * @mbggenerated
     */
    private Integer billType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_fd_income.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.issue
     *
     * @return the value of jc_fd_income.issue
     *
     * @mbggenerated
     */
    public String getIssue() {
        return issue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.issue
     *
     * @param issue the value for jc_fd_income.issue
     *
     * @mbggenerated
     */
    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.user_id
     *
     * @return the value of jc_fd_income.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.user_id
     *
     * @param userId the value for jc_fd_income.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.fd_cnt
     *
     * @return the value of jc_fd_income.fd_cnt
     *
     * @mbggenerated
     */
    public Integer getFdCnt() {
        return fdCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.fd_cnt
     *
     * @param fdCnt the value for jc_fd_income.fd_cnt
     *
     * @mbggenerated
     */
    public void setFdCnt(Integer fdCnt) {
        this.fdCnt = fdCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.sell_cnt
     *
     * @return the value of jc_fd_income.sell_cnt
     *
     * @mbggenerated
     */
    public Integer getSellCnt() {
        return sellCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.sell_cnt
     *
     * @param sellCnt the value for jc_fd_income.sell_cnt
     *
     * @mbggenerated
     */
    public void setSellCnt(Integer sellCnt) {
        this.sellCnt = sellCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.income
     *
     * @return the value of jc_fd_income.income
     *
     * @mbggenerated
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.income
     *
     * @param income the value for jc_fd_income.income
     *
     * @mbggenerated
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.cycle_type
     *
     * @return the value of jc_fd_income.cycle_type
     *
     * @mbggenerated
     */
    public Integer getCycleType() {
        return cycleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.cycle_type
     *
     * @param cycleType the value for jc_fd_income.cycle_type
     *
     * @mbggenerated
     */
    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.bill_type
     *
     * @return the value of jc_fd_income.bill_type
     *
     * @mbggenerated
     */
    public Integer getBillType() {
        return billType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.bill_type
     *
     * @param billType the value for jc_fd_income.bill_type
     *
     * @mbggenerated
     */
    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_fd_income.create_time
     *
     * @return the value of jc_fd_income.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_fd_income.create_time
     *
     * @param createTime the value for jc_fd_income.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}