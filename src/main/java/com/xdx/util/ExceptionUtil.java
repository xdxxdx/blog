package com.xdx.util;

public class ExceptionUtil {
	public static StackTraceElement getExceptionInfo(Throwable e) {
		return e.getStackTrace()[0];
	}

}
