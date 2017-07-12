package com.jy.repository.jc.paintedEgg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.paintedEgg.PaintedEgg;
import com.jy.entity.jc.paintedEgg.PaintedEggAnswer;
import com.jy.entity.jc.paintedEgg.PaintedEggUser;
import com.jy.entity.jc.paintedEgg.PaintedEggWiner;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
/**
 * 猜蛋
 * @author zr
 * 2016-10-13
 */
@JYBatis
public interface PaintedEggDao extends BaseDao<PaintedEgg>{
	/**
	 * 查询所有的答案
	 * @param os
	 * @return 
	 */
	public List<PaintedEggAnswer> queryPaintedEggAnswer(PaintedEggAnswer os);


    /**
     * 查询所有的购买的人
     * @param o
     * @param page
     * @return
     */
	public List<PaintedEggUser> queryPaintedEggUser(@Param("param") PaintedEggUser o,Page<PaintedEggUser> page);

    /**
     * 查询所有的购买的人
     * @param o
     */
    public List<PaintedEggUser> queryPaintedEggUser(@Param("param") PaintedEggUser o);

	/**
	 * 查询中奖的人
	 * @param os
	 */
	public List<PaintedEggWiner> queryPaintedEggWinner(PaintedEggWiner os);
	
	
	/**
	 * 批量插入答案
	 * @param os
	 */
	public void insertAnswerBatch(List<PaintedEggAnswer> os);
	
	
	/**
	 * 批量某个猜蛋答案
	 * @param os
	 */
	public void deleteAnswerBatch(PaintedEggAnswer os);
	
	/**
	 * 指定中奖人
	 * @param user
	 */
	public void zdWin(PaintedEggWiner user);

	 /**
     *  查询某用户的购买的的所有猜蛋信息 
     * @param o
     */
	public List<PaintedEggUser> queryUserBuyList(PaintedEgg os);

	/**
	 * 更新猜蛋中奖人员
	 * @param os
	 */
	public void updateWin(PaintedEggWiner os);
	
	
	
	public PaintedEggUser queryEggMaxUserInfo();
	
	
	
	public void ModifyAnswerUserBatch(PaintedEggUser os);

	public void insertUserAnswer(PaintedEggUser user);

	void insertUserBatch(List<PaintedEggUser> user);

	/**
	 * 查询购买用户信息后50名的时间 降序列取时间
	 * 
	 * @param user
	 * @return
	 */
	PaintedEggUser getByUer50(PaintedEggUser user);

}
