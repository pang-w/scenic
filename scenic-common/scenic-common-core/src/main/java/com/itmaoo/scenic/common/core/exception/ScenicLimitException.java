package com.itmaoo.scenic.common.core.exception;

public class ScenicLimitException extends RuntimeException {

	private static final long serialVersionUID = -8863391854740321517L;

	public ScenicLimitException() {
		super();
	}

	public ScenicLimitException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ScenicLimitException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ScenicLimitException(String arg0) {
		super(arg0);
	}

	public ScenicLimitException(Throwable arg0) {
		super(arg0);
	}

}
