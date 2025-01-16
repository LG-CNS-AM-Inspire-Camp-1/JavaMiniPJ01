package org.example.AccountSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Account {
	private String ano;//계좌번호
	private String owner;//소유자
	private String password;//비밀번호
	
	private int balance;//잔고
	
	public void balanceSetter(int money) {
		this.balance += money;
	}
}
