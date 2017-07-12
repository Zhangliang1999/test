package com.jy.repository.jc.messagecenter;

import com.jy.common.mybatis.Page;
import com.jy.entity.jc.matchgame.MatchGame;
import com.jy.entity.jc.messagecenter.MessageCenter;
import com.jy.entity.jc.messagecenter.MessageReceiver;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Receiver;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/4.
 */
@JYBatis
public interface MessageCenterDao extends BaseDao<MessageCenter> {

    /**
     * 批量添加接收人
     * @param o 集合
     */
    public void insertReceivers(List<MessageReceiver> o);

    /**
     * 批量删除接收人
     * @param msgId
     */
    public void deleteReceivers(@Param("msgId")String msgId);

    /**
     * 收件人
     * @param o
     * @return
     */
    public List<MessageReceiver> findReceivers(MessageReceiver o);



}
