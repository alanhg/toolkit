package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t116;
import tencent.tls.tlvs.tlv_t8;

public class req_smslogin_reask extends oicq_request {
    public req_smslogin_reask(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 19;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(int i, int i2, long[] jArr) {
        final int i3 = i;
        final int i4 = i2;
        final long[] jArr2 = jArr;
        TLVPacker anonymousClass1 = new TLVPacker(new int[]{260, 8, 278}) {
            protected byte[] loop(int i) {
                byte[] bArr = new byte[0];
                switch (i) {
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 260:
                        return req_global.get_async_data(req_smslogin_reask.this._g._seq)._t104.get_buf();
                    case 278:
                        return new tlv_t116().get_tlv_116(i3, i4, jArr2);
                    default:
                        return bArr;
                }
            }
        };
        return encrypt_body(anonymousClass1.doit(), this._sub_cmd, anonymousClass1.getTlvCnt());
    }

    public int make_request(int i, int i2, long[] jArr, TLSUserInfo tLSUserInfo) {
        int i3 = req_global._app_client_version;
        int i4 = 0;
        while (true) {
            get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i3, this._default_ext_instance, get_request_body(i, i2, jArr));
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i5 = get_response();
            QLog.i("retry num:" + i4 + " ret:" + i5, this._g._uin);
            if (i5 != 180) {
                return i5;
            }
            snd_rcv_req = i4 + 1;
            if (i4 >= 1) {
                return i5;
            }
            i4 = snd_rcv_req;
        }
    }
}