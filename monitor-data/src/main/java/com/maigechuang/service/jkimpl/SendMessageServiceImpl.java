package com.maigechuang.service.jkimpl;

import com.maigechuang.entity.SendMessage;
import com.maigechuang.mapper.jk.SendMessageMapper;
import com.maigechuang.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    SendMessageMapper sendMessageMapper;
    @Override
    public List<SendMessage> getMessageList(int page,int limit) {

        return sendMessageMapper.getMessageList(limit,(page-1)*limit);
    }

    @Override
    public int getCount() {
        return sendMessageMapper.getCount();
    }
}
