package com.xdx.constant;

public enum EMenuType {
	rootMenu(1),childMenu(2);
	private EMenuType(int value){
		this.value=value;
	}
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
