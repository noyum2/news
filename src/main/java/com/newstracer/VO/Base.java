package com.newstracer.VO;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Base")
public class Base implements Serializable {

	private static final long serialVersionUID = 6618793047631655226L;
	
	private int resultCode;
	private String resultMessage;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
