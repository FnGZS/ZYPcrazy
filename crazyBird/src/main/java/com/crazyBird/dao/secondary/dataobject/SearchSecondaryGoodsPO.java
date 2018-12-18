package com.crazyBird.dao.secondary.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class SearchSecondaryGoodsPO extends PageQueryDO{
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	

}
