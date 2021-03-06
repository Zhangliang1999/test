package com.jy.entity.jc.paintedEgg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.jy.entity.base.BaseEntity;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
@Alias("PaintedEgg")
public class PaintedEgg extends BaseEntity{
	private String title;
	private String team_id1;
	private String lucyNum;
	private String userName;
	private String userId;
	private String type;
	private String operType;
	private String answerId;

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	private int iszd;
	private int isright;

	public int getIsright() {
		return isright;
	}

	public void setIsright(int isright) {
		this.isright = isright;
	}

	public int getIszd() {
		return iszd;
	}

	public void setIszd(int iszd) {
		this.iszd = iszd;
	}

	public String getLucyNum() {
		return lucyNum;
	}

	public void setLucyNum(String lucyNum) {
		this.lucyNum = lucyNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	private List<PaintedEggAnswer> answers = new ArrayList<>();;
	private List<PaintedEggUser> users = new ArrayList<>();
	private List<PaintedEggWiner> winers = new ArrayList<>();

	public List<PaintedEggAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<PaintedEggAnswer> answers) {
		this.answers = answers;
	}

	public List<PaintedEggUser> getUsers() {
		return users;
	}

	public void setUsers(List<PaintedEggUser> users) {
		this.users = users;
	}

	public List<PaintedEggWiner> getWiners() {
		return winers;
	}

	public void setWiners(List<PaintedEggWiner> winers) {
		this.winers = winers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	private String  team_id2;


	public String getTeam_id1() {
		return team_id1;
	}

	public void setTeam_id1(String team_id1) {
		this.team_id1 = team_id1;
	}

	public String getTeam_id2() {
		return team_id2;
	}

	public void setTeam_id2(String team_id2) {
		this.team_id2 = team_id2;
	}


	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.eggId
     *
     * @mbggenerated
     */
    private String eggid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.goods_id
     *
     * @mbggenerated
     */
    private String goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.match_id
     *
     * @mbggenerated
     */
    private String matchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.price
     *
     * @mbggenerated
     */
    private Integer price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.purchased
     *
     * @mbggenerated
     */
    private Integer purchased;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.lowerLimit
     *
     * @mbggenerated
     */
    private Integer lowerlimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.rightAnswerId
     *
     * @mbggenerated
     */
    private String rightanswerid;
    private String selectAnserId;
    private String inputAnswers;

    public String getInputAnswers() {
		return inputAnswers;
	}

	public void setInputAnswers(String inputAnswers) {
		this.inputAnswers = inputAnswers;
	}

	public String getSelectAnserId() {
		return selectAnserId;
	}

	public void setSelectAnserId(String selectAnserId) {
		selectAnserId = selectAnserId;
	}


	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.createTime
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.createTime
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}


	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.updateTime
     *
     * @mbggenerated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jc_painted_egg.operId
     *
     * @mbggenerated
     */
    private String operid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.eggId
     *
     * @return the value of jc_painted_egg.eggId
     *
     * @mbggenerated
     */
    public String getEggid() {
        return eggid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.eggId
     *
     * @param eggid the value for jc_painted_egg.eggId
     *
     * @mbggenerated
     */
    public void setEggid(String eggid) {
        this.eggid = eggid == null ? null : eggid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.goods_id
     *
     * @return the value of jc_painted_egg.goods_id
     *
     * @mbggenerated
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.goods_id
     *
     * @param goodsId the value for jc_painted_egg.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.match_id
     *
     * @return the value of jc_painted_egg.match_id
     *
     * @mbggenerated
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.match_id
     *
     * @param matchId the value for jc_painted_egg.match_id
     *
     * @mbggenerated
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId == null ? null : matchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.content
     *
     * @return the value of jc_painted_egg.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.content
     *
     * @param content the value for jc_painted_egg.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.state
     *
     * @return the value of jc_painted_egg.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.state
     *
     * @param state the value for jc_painted_egg.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.price
     *
     * @return the value of jc_painted_egg.price
     *
     * @mbggenerated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.price
     *
     * @param price the value for jc_painted_egg.price
     *
     * @mbggenerated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.purchased
     *
     * @return the value of jc_painted_egg.purchased
     *
     * @mbggenerated
     */
    public Integer getPurchased() {
        return purchased;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.purchased
     *
     * @param purchased the value for jc_painted_egg.purchased
     *
     * @mbggenerated
     */
    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.lowerLimit
     *
     * @return the value of jc_painted_egg.lowerLimit
     *
     * @mbggenerated
     */
    public Integer getLowerlimit() {
        return lowerlimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.lowerLimit
     *
     * @param lowerlimit the value for jc_painted_egg.lowerLimit
     *
     * @mbggenerated
     */
    public void setLowerlimit(Integer lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.rightAnswerId
     *
     * @return the value of jc_painted_egg.rightAnswerId
     *
     * @mbggenerated
     */
    public String getRightanswerid() {
        return rightanswerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.rightAnswerId
     *
     * @param rightanswerid the value for jc_painted_egg.rightAnswerId
     *
     * @mbggenerated
     */
    public void setRightanswerid(String rightanswerid) {
        this.rightanswerid = rightanswerid == null ? null : rightanswerid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.createTime
     *
     * @return the value of jc_painted_egg.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.createTime
     *
     * @param createtime the value for jc_painted_egg.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.updateTime
     *
     * @return the value of jc_painted_egg.updateTime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.updateTime
     *
     * @param updatetime the value for jc_painted_egg.updateTime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jc_painted_egg.operId
     *
     * @return the value of jc_painted_egg.operId
     *
     * @mbggenerated
     */
    public String getOperid() {
        return operid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jc_painted_egg.operId
     *
     * @param operid the value for jc_painted_egg.operId
     *
     * @mbggenerated
     */
    public void setOperid(String operid) {
        this.operid = operid == null ? null : operid.trim();
    }

}