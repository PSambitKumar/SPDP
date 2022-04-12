package com.spdp.bean;

import java.util.List;

public class BankMasterBean {

	private Long bankId;
	private String bName;
	private List<String> bankNames;
	
	public BankMasterBean() { }

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public List<String> getBankNames() {
		return bankNames;
	}

	public void setBankNames(List<String> bankNames) {
		this.bankNames = bankNames;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}
	
	
}
