package com.tencent;

import java.util.List;

public interface TIMMessageListener {
    boolean onNewMessages(List<TIMMessage> list);
}
