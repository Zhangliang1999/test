package com.jy.service.jc.paintedEgg;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.paintedEgg.PaintedEgg;
import com.jy.entity.jc.paintedEgg.PaintedEggAnswer;
import com.jy.entity.jc.paintedEgg.PaintedEggUser;
import com.jy.entity.jc.paintedEgg.PaintedEggWiner;
import com.jy.entity.jc.userrecommendrel.UserRecommendRel;
import com.jy.repository.jc.paintedEgg.PaintedEggDao;
import com.jy.service.base.BaseServiceImp;
import com.sun.org.apache.bcel.internal.generic.DALOAD;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("PaintedEggService")
public class PaintedEggServiceImp extends BaseServiceImp<PaintedEgg> implements PaintedEggService {
	@Autowired
	private PaintedEggDao dao;

	@Override
	public List<PaintedEggAnswer> queryPaintedEggAnswer(PaintedEggAnswer os) {
	 List<PaintedEggAnswer> list = dao.queryPaintedEggAnswer(os);
	 return list;
		
	}

	@Override
	public List<PaintedEggWiner> queryPaintedEggWinner(PaintedEggWiner os) {
		List<PaintedEggWiner> list = dao.queryPaintedEggWinner(os);
		 return list;
	}

	
	@Override
    public Page<PaintedEggUser> queryPaintedEggUser(PaintedEggUser o, Page<PaintedEggUser> page) {
        page.setResults(dao.queryPaintedEggUser(o, page));
        return page;
    }

	@Override
	public void insertAnswerBatch(List<PaintedEggAnswer> os) {
		dao.insertAnswerBatch(os);
		
	}

	@Override
	public void deleteAnswerBatch(PaintedEggAnswer os) {
		dao.deleteAnswerBatch(os);
		
	}

	@Override
	public void zdWin(PaintedEggWiner os) {
		dao.zdWin(os);
	}

    @Override
    public List<PaintedEggUser> exportExcel(PaintedEggUser o) {
        return dao.queryPaintedEggUser(o);
    }

	@Override
	public List<PaintedEggUser> queryUserBuyList(PaintedEgg os) {
		 List<PaintedEggUser>  list = dao.queryUserBuyList(os);
		 return list;
	}

	@Override
	public void updateWin(PaintedEggWiner os) {
		dao.updateWin(os);
	}

	@Override
	public PaintedEggUser queryEggMaxUserInfo() {
		PaintedEggUser map =dao.queryEggMaxUserInfo();
		return map;
	}



	@Override
	public void ModifyAnswerUserBatch(PaintedEggUser os) {
		dao.ModifyAnswerUserBatch(os);
	}

	@Override
	public void insertUserAnswer(PaintedEggUser user) {
		dao.insertUserAnswer(user);
		
	}

	@Override
	public void insertUserBatch(List<PaintedEggUser> user) {
		dao.insertUserBatch(user);
	}

	@Override
	public PaintedEggUser getByUer50(PaintedEggUser user) {
		// TODO Auto-generated method stub
		PaintedEggUser paintedEggUser =dao.getByUer50(user);
		return paintedEggUser;
	}

}
