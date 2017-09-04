package com.tencent;

import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class cq extends ah<List<TIMFriendResult>> {
    cq(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "bindID failed|code: " + i + " desc: " + str);
        this.a.onError(i, str);
        BeaconUtil.onEvent(BeaconEvents.addFriend, false, -1, -1, hashMap, false);
    }

    public final void a(List<TIMFriendResult> list) {
        this.a.onSuccess(list);
        BeaconUtil.onEvent(BeaconEvents.addFriend, true, -1, -1, null, false);
    }
}
