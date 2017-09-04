package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle6 */
public class m extends a {
    private ArrayList<c> c;
    private int d;

    public a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 6) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        if (optJSONArray == null || optJSONArray.length() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                c cVar = new c();
                cVar.d = optJSONObject.optString("title");
                cVar.e = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                cVar.a = optJSONObject.optString("qurl");
                cVar.c = optJSONObject.optString("image");
                arrayList.add(cVar);
            }
        }
        a mVar = new m();
        mVar.c = arrayList;
        mVar.a = this.a;
        mVar.b = this.b;
        return mVar;
    }

    public ArrayList<c> a() {
        return this.c;
    }

    public a a(a aVar) {
        if (!(aVar instanceof m)) {
            return aVar;
        }
        a aVar2 = (m) aVar;
        ArrayList a = aVar2.a();
        if (a == null) {
            return aVar;
        }
        int b = aVar2.b();
        int size = a.size();
        if (size <= 1) {
            return aVar;
        }
        int i = b + 1;
        if (i == size) {
            i = 0;
        }
        aVar2.a(i);
        return aVar2;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }
}
