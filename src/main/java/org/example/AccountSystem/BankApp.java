package org.example.AccountSystem;

import java.util.Scanner;

public class BankApp {
	public static void printMenu() {
		System.out.println("\n--- 계좌 관리 시스템 ---");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 계좌 조회");
		System.out.println("3. 계좌 삭제");
		System.out.println("4. 입금");
		System.out.println("5. 출금");
		System.out.println("6. 송금");
		System.out.println("0. 종료");
		System.out.println("-----------------");
		System.out.print("원하시는 메뉴를 선택하세요 : ");
	}

	public static void bankStart(Scanner scanner) {
		AccountManagement accountmanagement = new AccountManagement();
		
		while(true) {
			printMenu();
			int choice = -1;
			
			try {
				choice = Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException ex) {
				System.out.println("\n올바른 값을 입력하세요.");
				continue;
			}
			
			
			switch(choice) {
			case 1:
				accountmanagement.makeAccount(scanner);
				break;
			case 2:
				accountmanagement.checkAccount(scanner);
				break;
			case 3:
				accountmanagement.deleteAccount(scanner);
				break;
			case 4:
				accountmanagement.deposit(scanner);
				break;
			case 5:
				accountmanagement.Withdrawal(scanner);
				break;
			case 6:
				accountmanagement.remit(scanner);
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
//				scanner.close();
				return;
			default:
				System.out.println("\n올바른 값을 입력하세요.");
				
			}
		}
	}
	
}
