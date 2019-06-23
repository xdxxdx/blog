package com.xdx.constant;

public enum ECommonStatus {
	unExam(0),pass(1),noPass(-1);
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	private ECommonStatus(int value){
		this.value=value;
	}
}
