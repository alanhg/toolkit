package com.tencent.qalsdk.base;

public enum CloseConnReason {
    appCall,
    netChange,
    readError,
    writeError,
    continueWaitRspTimeout,
    closeLastOpened,
    setTestServer,
    pushNeedReConn,
    connFull,
    invalidData,
    unRegisterPush,
    unRegisterNotify,
    ssoInvalidCookie,
    clearToken,
    lastMessageTimeTooLong,
    closeByNetDetectFailed,
    closeByNetDetectTooLongForPhoneSleep,
    closeByZlibDataLengthTooShort,
    closeByZlibUncompressException,
    closeForScrrenOnFirstMsgTimeout,
    closeForOtherReason,
    closeByDecryptFailOnce,
    closeByDecryptFailTwice,
    closeByDecryptFailEmpty,
    closeByPbUnpackFailInLoginMerge,
    closeByNoPackReceived
}