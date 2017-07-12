package com.jy.service.jc.paintedEgg;

import java.util.List;
import java.util.Map;

import com.jy.entity.jc.jcuserrank.JcUserRank;
import org.apache.ibatis.annotations.Param;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.paintedEgg.PaintedEgg;
import com.jy.entity.jc.paintedEgg.PaintedEggAnswer;
import com.jy.entity.jc.paintedEgg.PaintedEggUser;
import com.jy.entity.jc.paintedEgg.PaintedEggWiner;
import com.jy.service.base.BaseService;
import com.sun.corba.se.spi.ior.ObjectKey;

/**
 * Created by susen-pc on 2016/9/17.
 */
public interface PaintedEggService  extends BaseService<PaintedEgg> {
	
	/**
	 * 查询所有的答案
	 * @param os
	 */
	public List<PaintedEggAnswer> queryPaintedEggAnswer(PaintedEggAnswer os);


    /**
     * 查询所有的购买的人
     * @param o
     * @param page
     * @return
     */
	public Page<PaintedEggUser> queryPaintedEggUser(PaintedEggUser o,Page<PaintedEggUser> page);
	
	
	
	/**
     * 查询某用户的购买的的所有猜蛋信息 
     * @param o
     * @param page
     * @return
     */
	public List<PaintedEggUser> queryUserBuyList(PaintedEgg os);
	
	/**
	 * 查询中奖的人
	 * @param os
	 * @return 
	 */
	public List<PaintedEggWiner> queryPaintedEggWinner(PaintedEggWiner os);
	
	
	
	/**
	 * 批量插入答案
	 * @param os
	 */
	public void insertAnswerBatch(List<PaintedEggAnswer> os);
	
	
	
	/**
	 * 批量修改购买的答案是否正确
	 * @param os
	 */
	public void ModifyAnswerUserBatch(PaintedEggUser os);
	
	
	
	/**
	 * 批量某个猜蛋答案
	 * @param os
	 */
	public void deleteAnswerBatch(PaintedEggAnswer os);
	
	
	/**
	 * 指定中奖人
	 * @param os
	 */
	public void zdWin(PaintedEggWiner os);
	
	
	/**
	 * 指定中奖人
	 * @param os
	 */
	public void updateWin(PaintedEggWiner os);

    /*导出购买用户信息*/
    List<PaintedEggUser> exportExcel(PaintedEggUser o);
    
    //获取购买用户中所有幸运号最大的开始  结束 和总过购买数量
    PaintedEggUser queryEggMaxUserInfo();
    
    void insertUserAnswer(PaintedEggUser user);
    
    void insertUserBatch(List<PaintedEggUser> user);
    
    /**
     * 查询购买用户信息后50名的时间  降序列取时间
     * @param user
     * @return
     */
    PaintedEggUser getByUer50(PaintedEggUser user);

}
