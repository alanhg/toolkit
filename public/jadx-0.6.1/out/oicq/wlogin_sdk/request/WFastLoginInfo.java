package oicq.wlogin_sdk.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class WFastLoginInfo implements Parcelable, Serializable {
    public static final Creator<WFastLoginInfo> CREATOR = new 1();
    private static final long serialVersionUID = 1;
    public byte[] _outA1;
    public String adUrl;
    public String iconUrl;
    public String profileUrl;

    public WFastLoginInfo() {
        this._outA1 = new byte[0];
        this.iconUrl = "";
        this.adUrl = "";
        this.profileUrl = "";
        this._outA1 = new byte[0];
    }

    public WFastLoginInfo(byte[] bArr) {
        this._outA1 = new byte[0];
        this.iconUrl = "";
        this.adUrl = "";
        this.profileUrl = "";
        if (bArr != null) {
            this._outA1 = (byte[]) bArr.clone();
        } else {
            this._outA1 = new byte[0];
        }
    }

    public void get_clone(WFastLoginInfo wFastLoginInfo) {
        if (wFastLoginInfo != null) {
            if (wFastLoginInfo._outA1 != null) {
                this._outA1 = (byte[]) wFastLoginInfo._outA1.clone();
            } else {
                this._outA1 = new byte[0];
            }
            this.iconUrl = wFastLoginInfo.iconUrl;
            this.adUrl = wFastLoginInfo.adUrl;
            this.profileUrl = wFastLoginInfo.profileUrl;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this._outA1);
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.adUrl);
        parcel.writeString(this.profileUrl);
    }

    public void readFromParcel(Parcel parcel) {
        this._outA1 = parcel.createByteArray();
        this.iconUrl = parcel.readString();
        this.adUrl = parcel.readString();
        this.profileUrl = parcel.readString();
    }

    private WFastLoginInfo(Parcel parcel) {
        this._outA1 = new byte[0];
        this.iconUrl = "";
        this.adUrl = "";
        this.profileUrl = "";
        readFromParcel(parcel);
    }
}
