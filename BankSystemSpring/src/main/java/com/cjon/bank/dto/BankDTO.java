package com.cjon.bank.dto;

public class BankDTO {

	private String userid;
	private String accountNum;
	private int balance;
	private boolean result;
	
	public BankDTO() {
	}
	
	public BankDTO(String userid, String accountNum, int balance) {
		super();
		this.userid = userid;
		this.accountNum = accountNum;
		this.balance = balance;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}
