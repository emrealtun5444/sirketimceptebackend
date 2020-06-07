package com.aymer.sirketimceptebackend.exception;


import static freemarker.template.utility.CollectionUtils.EMPTY_OBJECT_ARRAY;

public abstract class BaseRuntimeException extends RuntimeException {
    private String msgKey;
    private Object[] msgArgs;

    public BaseRuntimeException() {
        this.msgArgs = EMPTY_OBJECT_ARRAY;
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
    }

    public BaseRuntimeException(String message) {
        super(message);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
        this.setMsgKey(message);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
    }

    public BaseRuntimeException(String msgKey, Object... msgArgs) {
        this.msgArgs = EMPTY_OBJECT_ARRAY;
        this.setMsgKey(msgKey);
        this.setMsgArgs(msgArgs);
    }

    public BaseRuntimeException(String message, Throwable cause, String msgKey, Object... msgArgs) {
        super(message, cause);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
        this.setMsgKey(msgKey);
        this.setMsgArgs(msgArgs);
    }

    public BaseRuntimeException(String message, String msgKey, Object... msgArgs) {
        super(message);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
        this.setMsgKey(msgKey);
        this.setMsgArgs(msgArgs);
    }

    public BaseRuntimeException(Throwable cause, String msgKey, Object... msgArgs) {
        super(cause);
        this.msgArgs = EMPTY_OBJECT_ARRAY;
        this.setMsgKey(msgKey);
        this.setMsgArgs(msgArgs);
    }

    public String getMsgKey() {
        return this.msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public Object[] getMsgArgs() {
        return this.msgArgs == null ? EMPTY_OBJECT_ARRAY : this.msgArgs;
    }

    public void setMsgArgs(Object[] msgArgs) {
        this.msgArgs = msgArgs;
    }
}
