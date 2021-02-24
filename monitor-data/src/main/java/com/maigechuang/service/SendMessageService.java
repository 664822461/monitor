package com.maigechuang.service;


import com.maigechuang.entity.SendMessage;

import java.util.List;

public interface SendMessageService{

    List<SendMessage> getMessageList(int page,int limit);
    int getCount();
}
