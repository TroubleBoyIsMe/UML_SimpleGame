package com.example.easymail;


import com.example.easymail.javabean.InboxItem;

import java.util.List;

public interface IReceiveAsyncResponse {

    void onDataReceivedSuccess(List<InboxItem> listData);
    void onDataReceivedFailed();

}
