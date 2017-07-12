package com.jy.entity.jc.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jy.entity.base.BaseEntity;
import com.jy.entity.jc.matchgame.MatchGame;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alias("Match")
public class Match extends BaseEntity{

    private Integer sumscore; //网球所有节数的比分和

    private String score; //比分
    private Short matchResult;
    //玩法
    private List<MatchGame> items = new ArrayList<MatchGame>();

    //赛事字典
    private String compName;
    private Short matchType;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.match_id
     *
     * @mbggenerated
     */
    private String matchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.comp_id
     *
     * @mbggenerated
     */
    private String compId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.cc_no
     *
     * @mbggenerated
     */
    private String ccNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.begintime
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date begintime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.endtime
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.deadline
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date deadline;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.team_id1
     *
     * @mbggenerated
     */
    private String teamId1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.team_id2
     *
     * @mbggenerated
     */
    private String teamId2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.result
     *
     * @mbggenerated
     */
    private Short result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.create_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.update_time
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.is_valid
     *
     * @mbggenerated
     */
    private Short isValid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match.oper_id
     *
     * @mbggenerated
     */
    private String operId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.match_id
     *
     * @return the value of jc_match.match_id
     *
     * @mbggenerated
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.match_id
     *
     * @param matchId the value for jc_match.match_id
     *
     * @mbggenerated
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.comp_id
     *
     * @return the value of jc_match.comp_id
     *
     * @mbggenerated
     */
    public String getCompId() {
        return compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.comp_id
     *
     * @param compId the value for jc_match.comp_id
     *
     * @mbggenerated
     */
    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.cc_no
     *
     * @return the value of jc_match.cc_no
     *
     * @mbggenerated
     */
    public String getCcNo() {
        return ccNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.cc_no
     *
     * @param ccNo the value for jc_match.cc_no
     *
     * @mbggenerated
     */
    public void setCcNo(String ccNo) {
        this.ccNo = ccNo == null ? null : ccNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.begintime
     *
     * @return the value of jc_match.begintime
     *
     * @mbggenerated
     */
    public Date getBegintime() {
        return begintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.begintime
     *
     * @param begintime the value for jc_match.begintime
     *
     * @mbggenerated
     */
    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.endtime
     *
     * @return the value of jc_match.endtime
     *
     * @mbggenerated
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.endtime
     *
     * @param endtime the value for jc_match.endtime
     *
     * @mbggenerated
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.deadline
     *
     * @return the value of jc_match.deadline
     *
     * @mbggenerated
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.deadline
     *
     * @param deadline the value for jc_match.deadline
     *
     * @mbggenerated
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.team_id1
     *
     * @return the value of jc_match.team_id1
     *
     * @mbggenerated
     */
    public String getTeamId1() {
        return teamId1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.team_id1
     *
     * @param teamId1 the value for jc_match.team_id1
     *
     * @mbggenerated
     */
    public void setTeamId1(String teamId1) {
        this.teamId1 = teamId1 == null ? null : teamId1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.team_id2
     *
     * @return the value of jc_match.team_id2
     *
     * @mbggenerated
     */
    public String getTeamId2() {
        return teamId2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.team_id2
     *
     * @param teamId2 the value for jc_match.team_id2
     *
     * @mbggenerated
     */
    public void setTeamId2(String teamId2) {
        this.teamId2 = teamId2 == null ? null : teamId2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.result
     *
     * @return the value of jc_match.result
     *
     * @mbggenerated
     */
    public Short getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.result
     *
     * @param result the value for jc_match.result
     *
     * @mbggenerated
     */
    public void setResult(Short result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.create_time
     *
     * @return the value of jc_match.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.create_time
     *
     * @param createTime the value for jc_match.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.update_time
     *
     * @return the value of jc_match.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.update_time
     *
     * @param updateTime the value for jc_match.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.is_valid
     *
     * @return the value of jc_match.is_valid
     *
     * @mbggenerated
     */
    public Short getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.is_valid
     *
     * @param isValid the value for jc_match.is_valid
     *
     * @mbggenerated
     */
    public void setIsValid(Short isValid) {
        this.isValid = isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match.oper_id
     *
     * @return the value of jc_match.oper_id
     *
     * @mbggenerated
     */
    public String getOperId() {
        return operId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match.oper_id
     *
     * @param operId the value for jc_match.oper_id
     *
     * @mbggenerated
     */
    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Short getMatchType() {
        return matchType;
    }

    public void setMatchType(Short matchType) {
        this.matchType = matchType;
    }

    public List<MatchGame> getItems() {
        return items;
    }

    public void setItems(List<MatchGame> items) {
        this.items = items;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Short getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(Short matchResult) {
        this.matchResult = matchResult;
    }

    public Integer getSumscore() {
        return sumscore;
    }

    public void setSumscore(Integer sumscore) {
        this.sumscore = sumscore;
    }
}