package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.util.Date;
import java.util.zip.ZipException;

public class X5455_ExtendedTimestamp implements Serializable, Cloneable, r {
    static final byte ACCESS_TIME_BIT = (byte) 2;
    static final byte CREATE_TIME_BIT = (byte) 4;
    private static final ZipShort HEADER_ID = new ZipShort(21589);
    static final byte MODIFY_TIME_BIT = (byte) 1;
    private static final long serialVersionUID = 1;
    private ZipLong accessTime;
    private boolean bit0_modifyTimePresent;
    private boolean bit1_accessTimePresent;
    private boolean bit2_createTimePresent;
    private ZipLong createTime;
    private byte flags;
    private ZipLong modifyTime;

    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    public ZipShort getLocalFileDataLength() {
        int i = 4;
        int i2 = (this.bit0_modifyTimePresent ? 4 : 0) + 1;
        int i3 = (!this.bit1_accessTimePresent || this.accessTime == null) ? 0 : 4;
        i3 += i2;
        if (!this.bit2_createTimePresent || this.createTime == null) {
            i = 0;
        }
        return new ZipShort(i3 + i);
    }

    public ZipShort getCentralDirectoryLength() {
        return new ZipShort((this.bit0_modifyTimePresent ? 4 : 0) + 1);
    }

    public byte[] getLocalFileDataData() {
        Object obj = new byte[getLocalFileDataLength().getValue()];
        int i = 1;
        obj[0] = null;
        if (this.bit0_modifyTimePresent) {
            obj[0] = (byte) (obj[0] | 1);
            System.arraycopy(this.modifyTime.getBytes(), 0, obj, 1, 4);
            i = 5;
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            obj[0] = (byte) (obj[0] | 2);
            System.arraycopy(this.accessTime.getBytes(), 0, obj, i, 4);
            i += 4;
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            obj[0] = (byte) (obj[0] | 4);
            System.arraycopy(this.createTime.getBytes(), 0, obj, i, 4);
            i += 4;
        }
        return obj;
    }

    public byte[] getCentralDirectoryData() {
        Object obj = new byte[getCentralDirectoryLength().getValue()];
        System.arraycopy(getLocalFileDataData(), 0, obj, 0, obj.length);
        return obj;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        int i3 = i + i2;
        int i4 = i + 1;
        setFlags(bArr[i]);
        if (this.bit0_modifyTimePresent) {
            this.modifyTime = new ZipLong(bArr, i4);
            i4 += 4;
        }
        if (this.bit1_accessTimePresent && i4 + 4 <= i3) {
            this.accessTime = new ZipLong(bArr, i4);
            i4 += 4;
        }
        if (this.bit2_createTimePresent && i4 + 4 <= i3) {
            this.createTime = new ZipLong(bArr, i4);
            i4 += 4;
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        parseFromLocalFileData(bArr, i, i2);
    }

    private void reset() {
        setFlags((byte) 0);
        this.modifyTime = null;
        this.accessTime = null;
        this.createTime = null;
    }

    public void setFlags(byte b) {
        boolean z;
        boolean z2 = true;
        this.flags = b;
        if ((b & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        this.bit0_modifyTimePresent = z;
        if ((b & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        this.bit1_accessTimePresent = z;
        if ((b & 4) != 4) {
            z2 = false;
        }
        this.bit2_createTimePresent = z2;
    }

    public byte getFlags() {
        return this.flags;
    }

    public boolean isBit0_modifyTimePresent() {
        return this.bit0_modifyTimePresent;
    }

    public boolean isBit1_accessTimePresent() {
        return this.bit1_accessTimePresent;
    }

    public boolean isBit2_createTimePresent() {
        return this.bit2_createTimePresent;
    }

    public ZipLong getModifyTime() {
        return this.modifyTime;
    }

    public ZipLong getAccessTime() {
        return this.accessTime;
    }

    public ZipLong getCreateTime() {
        return this.createTime;
    }

    public Date getModifyJavaTime() {
        return this.modifyTime != null ? new Date(this.modifyTime.getValue() * 1000) : null;
    }

    public Date getAccessJavaTime() {
        return this.accessTime != null ? new Date(this.accessTime.getValue() * 1000) : null;
    }

    public Date getCreateJavaTime() {
        return this.createTime != null ? new Date(this.createTime.getValue() * 1000) : null;
    }

    public void setModifyTime(ZipLong zipLong) {
        this.modifyTime = zipLong;
    }

    public void setAccessTime(ZipLong zipLong) {
        this.accessTime = zipLong;
    }

    public void setCreateTime(ZipLong zipLong) {
        this.createTime = zipLong;
    }

    public void setModifyJavaTime(Date date) {
        setModifyTime(dateToZipLong(date));
    }

    public void setAccessJavaTime(Date date) {
        setAccessTime(dateToZipLong(date));
    }

    public void setCreateJavaTime(Date date) {
        setCreateTime(dateToZipLong(date));
    }

    private static ZipLong dateToZipLong(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime() / 1000;
        if (time < 4294967296L) {
            return new ZipLong(time);
        }
        throw new IllegalArgumentException("Cannot set an X5455 timestamp larger than 2^32: " + time);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x5455 Zip Extra Field: Flags=");
        stringBuilder.append(Integer.toBinaryString(s.b(this.flags))).append(" ");
        if (this.bit0_modifyTimePresent && this.modifyTime != null) {
            stringBuilder.append(" Modify:[").append(getModifyJavaTime()).append("] ");
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            stringBuilder.append(" Access:[").append(getAccessJavaTime()).append("] ");
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            stringBuilder.append(" Create:[").append(getCreateJavaTime()).append("] ");
        }
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X5455_ExtendedTimestamp)) {
            return false;
        }
        X5455_ExtendedTimestamp x5455_ExtendedTimestamp = (X5455_ExtendedTimestamp) obj;
        if ((this.flags & 7) != (x5455_ExtendedTimestamp.flags & 7)) {
            return false;
        }
        if (this.modifyTime != x5455_ExtendedTimestamp.modifyTime && (this.modifyTime == null || !this.modifyTime.equals(x5455_ExtendedTimestamp.modifyTime))) {
            return false;
        }
        if (this.accessTime != x5455_ExtendedTimestamp.accessTime && (this.accessTime == null || !this.accessTime.equals(x5455_ExtendedTimestamp.accessTime))) {
            return false;
        }
        if (this.createTime == x5455_ExtendedTimestamp.createTime || (this.createTime != null && this.createTime.equals(x5455_ExtendedTimestamp.createTime))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (this.flags & 7) * -123;
        if (this.modifyTime != null) {
            i ^= this.modifyTime.hashCode();
        }
        if (this.accessTime != null) {
            i ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
        }
        if (this.createTime != null) {
            return i ^ Integer.rotateLeft(this.createTime.hashCode(), 22);
        }
        return i;
    }
}
