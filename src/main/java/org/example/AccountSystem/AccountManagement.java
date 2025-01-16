package org.example.AccountSystem;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class AccountManagement {
	private static Set<Account> accounts = new HashSet<>();
	private static Set<String> anos = new HashSet<>();
	
	// 1. 계좌 개설
	public void makeAccount(Scanner scanner) {
		System.out.println("\n계좌를 개설합니다.");
		
		// 1-1. 이름 입력
		System.out.print("이름을 입력하세요 : ");
		String name = scanner.nextLine();
		
		// 1-2. 비밀번호 입력
		String inputPassword = null;
		while (true) {
			inputPassword = inputPassword(scanner);
			if (inputPassword != null) break;
			System.out.println("\n비밀번호가 형식에 맞지 않습니다.");
		}
		
		// 1-3. 계좌번호 생성
		String accountNumber = generateUniqueAccountNumber();
		
		// 1-4. 계좌 생성
		Account newAccount = new Account(accountNumber, name, inputPassword, 0);
		accounts.add(newAccount);
		anos.add(accountNumber);
		
		System.out.println("\n계좌 생성이 완료되었습니다.");
		System.out.println("계좌 정보 : " + newAccount.toString());
	}
	
	// 2. 계좌 조회
	public void checkAccount(Scanner scanner) {
		System.out.println("\n계좌를 조회합니다.");
		
		// 2-1. 계좌번호 입력
		System.out.print("계좌번호를 입력하세요 : ");
		String inputAno = scanner.nextLine();
		
		// 2-2. 계좌번호 비교
		for (Account account : accounts) {
			if (account.getAno().equals(inputAno)) {
				System.out.println("\n조회된 계좌 정보 :");
				System.out.println(account.toString());
				return;
			}
		}
		System.out.println("\n조회된 계좌 정보가 없습니다.");
	}
	
	// 3. 계좌 삭제
	public void deleteAccount(Scanner scanner) {
		System.out.println("\n계좌를 삭제합니다.");
		
		// 3-1. 계좌번호 입력
		System.out.print("계좌번호를 입력하세요 : ");
		String inputAno = scanner.nextLine();
		Account myAccount = findAccountByAno(accounts, inputAno);
		if (myAccount == null) {
			System.out.println("\n조회된 계좌 정보가 없습니다.");
			return;
		}
		
		// 3-3. 비밀번호 입력
		String inputPassword = inputPassword(scanner);
		if (inputPassword == null || !inputPassword.equals(myAccount.getPassword())) {
			System.out.println("\n비밀번호가 올바르지 않습니다.");
			return;
		}
		
		// 3-4. 계좌 삭제
		accounts.remove(myAccount);
		anos.remove(myAccount.getAno());
		System.out.println("\n계좌가 삭제되었습니다.");
	}
	
	// 4. 입금
	public void deposit(Scanner scanner) {
		System.out.println("\n입금합니다.");
		
		// 4-1. 계좌번호 입력
		System.out.print("계좌번호를 입력하세요 : ");
		String inputAno = scanner.nextLine();
		Account myAccount = findAccountByAno(accounts, inputAno);
		if (myAccount == null) {
			System.out.println("\n조회된 계좌 정보가 없습니다.");
			return;
		}

		// 4-2. 입금
		System.out.print("입금할 금액을 입력하세요 : ");
		int money = scanner.nextInt();
		scanner.nextLine();
		myAccount.balanceSetter(money);
		accounts.add(myAccount);
		
		// 4-3. 확인 메세지
		System.out.printf("\n%d원이 입금되었습니다.\n", money);
		System.out.println(myAccount.toString());
				
	}
	
	// 5. 출금
	public void Withdrawal(Scanner scanner) {
		System.out.println("\n출금합니다.");
		
		// 5-1. 계좌번호 입력
		System.out.print("계좌번호를 입력하세요 : ");
		String inputAno = scanner.nextLine();
		Account myAccount = findAccountByAno(accounts, inputAno);
		if (myAccount == null) {
			System.out.println("\\n조회된 계좌 정보가 없습니다.");
			return;
		}
		
		// 5-3. 비밀번호 입력
		String inputPassword = inputPassword(scanner);
		if (inputPassword == null || !inputPassword.equals(myAccount.getPassword())) {
			System.out.println("\n비밀번호가 올바르지 않습니다.");
			return;
		}

		// 5-4. 출금
		System.out.print("출금할 금액을 입력하세요 : ");
		int money = scanner.nextInt();
		scanner.nextLine();
		if (money > myAccount.getBalance()) {
			System.out.println("\n잔고가 부족합니다.");
			return;
		}
		myAccount.balanceSetter(-money);
		accounts.add(myAccount);
		
		// 5-5. 확인 메세지
		System.out.printf("\n%d원이 출금되었습니다.\n", money);
		System.out.println(myAccount.toString());

	}
	
	// 6. 송금
	public void remit(Scanner scanner) {
		System.out.println("\n송금합니다.");
		
		// 6-1. 내 계좌번호 입력
		System.out.print("본인 계좌번호를 입력하세요 : ");
		String inputMyAno = scanner.nextLine();
		Account myAccount = findAccountByAno(accounts, inputMyAno);
		if (myAccount == null) {
			System.out.println("\n조회된 계좌 정보가 없습니다.");
			return;
		}
		
		// 6-2 상대방 계좌번호 입력
		System.out.print("송금할 계좌번호를 입력하세요 : ");
		String inputRecipientAno = scanner.nextLine();
		Account recipientAccount = findAccountByAno(accounts, inputRecipientAno);
		if (recipientAccount == null) {
			System.out.println("\n조회된 계좌 정보가 없습니다.");
			return;
		}
		
		// 6-3. 비밀번호 입력
		String inputPassword = inputPassword(scanner);
		if (inputPassword == null || !inputPassword.equals(myAccount.getPassword())) {
			System.out.println("\n비밀번호가 올바르지 않습니다.");
			return;
		}

		// 6-4. 송금 금액 입력
		System.out.print("송금할 금액을 입력하세요 : ");
		int money = scanner.nextInt();
		scanner.nextLine();
		if (money > myAccount.getBalance()) {
			System.out.println("\n잔고가 부족합니다.");
			return;
		}
		
		// 6-5. 송금
		myAccount.balanceSetter(-money);
		recipientAccount.balanceSetter(money);
		accounts.add(myAccount);
		accounts.add(recipientAccount);
		
		
		// 6-6. 확인 메세지
		System.out.printf("\n%d원이 송금되었습니다.\n", money);
		System.out.println(myAccount.toString());
	}
	
	
	// 계좌번호 랜덤 생성 함수
    private static String generateUniqueAccountNumber() {
        String accountNumber;

        // 중복되지 않은 계좌번호가 생성될 때까지 반복
        do {
            accountNumber = generateAccountNumber();
        } while (anos.contains(accountNumber));

        return accountNumber;
    }

    private static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        // 10자리 계좌번호 생성
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); // 0부터 9까지 랜덤 숫자
            accountNumber.append(digit);
        }

        return accountNumber.toString();
    }
    
    // 계좌번호 찾는 함수
    private static Account findAccountByAno(Set<Account> accounts, String ano) {
    	for (Account account : accounts) {
    		if (account.getAno().equals(ano)) return account;
    	}
    	return null;
    }
    
    // 비밀번호 입력 함수
    private static String inputPassword(Scanner scanner) {
    	String password = "0000";
        System.out.print("비밀번호를 입력하세요(4자리 숫자): ");

        password = scanner.nextLine();
        
        // 비밀번호가 4자리 숫자인지 확인
        if (password.length() != 4 || !password.chars().allMatch(Character::isDigit)) {
            return null;
        }
        
		return password;
    }
}
