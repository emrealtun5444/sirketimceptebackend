package com.aymer.sirketimceptebackend.exception;

public class ServiceException extends BaseRuntimeException {
	public ServiceException() {
	}

	public ServiceException(String msgKey, Object... msgArgs) {
		super(msgKey, msgArgs);
	}

	public ServiceException(String message, String msgKey, Object... msgArgs) {
		super(message, msgKey, msgArgs);
	}

	public ServiceException(String message, Throwable cause, String msgKey, Object... msgArgs) {
		super(message, cause, msgKey, msgArgs);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.setMsgKey(message);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause, String msgKey, Object... msgArgs) {
		super(cause, msgKey, msgArgs);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
