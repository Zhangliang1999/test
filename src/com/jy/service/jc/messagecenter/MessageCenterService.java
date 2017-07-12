package com.jy.service.jc.messagecenter;

import com.jy.entity.jc.activitycenter.ActivityCenter;
import com.jy.entity.jc.match.Match;
import com.jy.entity.jc.messagecenter.MessageCenter;
import com.jy.entity.jc.messagecenter.MessageReceiver;
import com.jy.service.base.BaseService;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/17.
 */
public interface MessageCenterService extends BaseService<MessageCenter> {


    int insertMessage(MessageCenter o);
    int updateMessage(MessageCenter o);

    public List<MessageReceiver> findReceivers(MessageReceiver o);

    /**
     * 批量添加接收人
     * @param o 集合
     */
    public void insertReceivers(List<MessageReceiver> o);
}
