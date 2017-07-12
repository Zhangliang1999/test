package com.jy.entity.jc.matchgame;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("MatchGame")
public class MatchGame extends BaseEntity{

    private Date createTime;
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.match_game_id
     *
     * @mbggenerated
     */
    private String matchGameId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.match_id
     *
     * @mbggenerated
     */
    private String matchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.bet_type
     *
     * @mbggenerated
     */
    private Short betType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.data1
     *
     * @mbggenerated
     */
    private String data1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.data2
     *
     * @mbggenerated
     */
    private String data2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.data3
     *
     * @mbggenerated
     */
    private String data3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.sort
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_match_game.match_result
     *
     * @mbggenerated
     */
    private Short matchResult;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.match_id
     *
     * @return the value of jc_match_game.match_id
     *
     * @mbggenerated
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.match_id
     *
     * @param matchId the value for jc_match_game.match_id
     *
     * @mbggenerated
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.bet_type
     *
     * @return the value of jc_match_game.bet_type
     *
     * @mbggenerated
     */
    public Short getBetType() {
        return betType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.bet_type
     *
     * @param betType the value for jc_match_game.bet_type
     *
     * @mbggenerated
     */
    public void setBetType(Short betType) {
        this.betType = betType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.data1
     *
     * @return the value of jc_match_game.data1
     *
     * @mbggenerated
     */
    public String getData1() {
        return data1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.data1
     *
     * @param data1 the value for jc_match_game.data1
     *
     * @mbggenerated
     */
    public void setData1(String data1) {
        this.data1 = data1 == null ? null : data1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.data2
     *
     * @return the value of jc_match_game.data2
     *
     * @mbggenerated
     */
    public String getData2() {
        return data2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.data2
     *
     * @param data2 the value for jc_match_game.data2
     *
     * @mbggenerated
     */
    public void setData2(String data2) {
        this.data2 = data2 == null ? null : data2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.data3
     *
     * @return the value of jc_match_game.data3
     *
     * @mbggenerated
     */
    public String getData3() {
        return data3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.data3
     *
     * @param data3 the value for jc_match_game.data3
     *
     * @mbggenerated
     */
    public void setData3(String data3) {
        this.data3 = data3 == null ? null : data3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.sort
     *
     * @return the value of jc_match_game.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.sort
     *
     * @param sort the value for jc_match_game.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_match_game.match_result
     *
     * @return the value of jc_match_game.match_result
     *
     * @mbggenerated
     */
    public Short getMatchResult() {
        return matchResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_match_game.match_result
     *
     * @param matchResult the value for jc_match_game.match_result
     *
     * @mbggenerated
     */
    public void setMatchResult(Short matchResult) {
        this.matchResult = matchResult;
    }

    public String getMatchGameId() {
        return matchGameId;
    }

    public void setMatchGameId(String matchGameId) {
        this.matchGameId = matchGameId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}