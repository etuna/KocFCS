package com.kocfcs.model.response;

public class CreditResponse {

	private String result;
	private String desc;
	private int amount;
	
	public CreditResponse(String string, String string2, int amount) {
		this.result = string;
		this.desc= string2;
		this.amount = amount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
