package com.qq.reader.module.bookstore.search.card;

import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.widget.ImageMaskView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SearchAuthorRecCard extends SearchBaseCard {
    private static final String JSON_KEY_AUTHORNAME = "authorname";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_ICON = "icon";
    private static final String JSON_KEY_INFO = "info";
    private static final String JSON_KEY_LEVEL = "level";
    private static final String JSON_KEY_LEVELNAME = "levelName";
    private String mAuthorName;
    private String mDesc;
    private String mIcon;
    private int mLevel;
    private String mLevelName;
    private String mUrl;

    public SearchAuthorRecCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_authorrec_card;
    }

    public void attachView() {
        super.attachView();
        setImage(((ImageMaskView) ap.a(getRootView(), R.id.concept_cover_img)).getImageView(), getIcon(), null);
        ((ImageView) ap.a(getRootView(), R.id.concept_cover_tag)).setImageResource(getUserTagimg(this.mLevel));
        ((TextView) ap.a(getRootView(), R.id.concept_title)).setText(getAuthorName());
        ((TextView) ap.a(getRootView(), R.id.concept_content)).setText(getDesc());
    }

    protected void expose() {
        super.expose();
        i.a("event_B179", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B179", this.mLogMap);
    }

    public void doClickedCard() {
        super.doClickedCard();
        i.a("event_B180", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B180", this.mLogMap);
    }

    private int getUserTagimg(int i) {
        switch (i) {
            case 102:
                return R.drawable.card_star;
            case 103:
            case 104:
                return R.drawable.card_platinum;
            case 105:
                return R.drawable.card_god;
            default:
                return R.drawable.card_auther;
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mDesc = jSONObject.optString("desc");
        this.mIcon = jSONObject.optString("icon");
        this.mAuthorName = jSONObject.optString(JSON_KEY_AUTHORNAME);
        this.mLevel = jSONObject.optInt(JSON_KEY_LEVEL);
        this.mLevelName = jSONObject.optString(JSON_KEY_LEVELNAME);
        return true;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getAuthorName() {
        return this.mAuthorName;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getIcon() {
        return this.mIcon;
    }

    public String getLevelName() {
        return this.mLevelName;
    }
}
