package com.tencent;

import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.Map;

final class er extends ah {
    er(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack) {
        super(tIMGroupManager, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
        BeaconUtil.onEvent(BeaconEvents.modifyGroupInfo, true, -1, -1, null, false);
    }

    public final void a(int i, String str) {
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "bindID failed|code: " + i + " desc: " + str);
        this.a.onError(i, str);
        BeaconUtil.onEvent(BeaconEvents.modifyGroupInfo, false, -1, -1, hashMap, false);
    }
}
