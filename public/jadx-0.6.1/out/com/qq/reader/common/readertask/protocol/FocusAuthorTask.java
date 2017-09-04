package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class FocusAuthorTask extends ReaderProtocolJSONTask {
    public FocusAuthorTask(c cVar, String str) {
        super(cVar);
        this.mUrl = e.a + "focusManito?authorId=" + str + "&platform=3";
    }
}