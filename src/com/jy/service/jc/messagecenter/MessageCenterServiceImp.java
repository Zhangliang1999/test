package com.jy.service.jc.messagecenter;

import com.jy.entity.jc.activitycenter.ActivityCenter;
import com.jy.entity.jc.messagecenter.MessageCenter;
import com.jy.entity.jc.messagecenter.MessageReceiver;
import com.jy.repository.jc.activitycenter.ActivityCenterDao;
import com.jy.repository.jc.messagecenter.MessageCenterDao;
import com.jy.service.base.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by susen-pc on 2016/9/17.
 */
@Service("MessageCenterService")
public class MessageCenterServiceImp extends BaseServiceImp<MessageCenter> implements MessageCenterService {
    @Autowired
    private MessageCenterDao dao;

    @Override
    public int insertMessage(MessageCenter o) {
        String msgId = o.getMsgId();
        List<MessageReceiver> receivers = o.getReceivers();
        o.setState(0); //未发送
        dao.insert(o);
        if (receivers != null && receivers.size() > 0) {
            for (MessageReceiver receiver : receivers) {
                receiver.setMsgId(msgId);
            }
            dao.insertReceivers(receivers);
        }
        return 1;
    }

    @Override
    public int updateMessage(MessageCenter o) {
        String msgId = o.getMsgId();
        dao.update(o);
        dao.deleteReceivers(msgId);
        if(1 == o.getMsgType()) {//私信
            List<MessageReceiver> receivers = o.getReceivers();
            if (receivers != null && receivers.size() > 0) {
                for (MessageReceiver receiver : receivers) {
                    receiver.setMsgId(msgId);
                }
                dao.insertReceivers(receivers);
            }
        }
        return 1;
    }

    @Override
    public List<MessageReceiver> findReceivers(MessageReceiver o) {
        return dao.findReceivers(o);
    }

    @Override
    public void insertReceivers(List<MessageReceiver> o) {
        dao.insertReceivers(o);
    }
}
