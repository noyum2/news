package com.newstracer.VO;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;


@Alias("Keyword")
@JsonInclude
public class Keyword extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 360710294901222956L;

	private int keywordSeq;
	private int userSeq;
	private String content;
	public int getKeywordSeq() {
		return keywordSeq;
	}
	public void setKeywordSeq(int keywordSeq) {
		this.keywordSeq = keywordSeq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
