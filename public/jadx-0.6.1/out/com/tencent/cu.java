package com.tencent;

import java.util.List;

final class cu extends af<List<TIMUserProfile>> {
    cu(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMUserProfile> list) {
        this.a.onSuccess(list);
    }
}
