package org.example.kudong;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.example.kudong.api.CLIModule;
import org.example.kudong.commands.ServerManageModule;

/***
 * 
 * 게임서버 플레이어 관리 (구동혁)
 * 
 * KUDONGMain 진입점 입니다.
 * 
 * Scanner scanner = new Scanner(System.in);
 * CLIModule module = new ServerManageModule();
 * module.update(scanner);
 * 
 * @author KUDONG
 *
 */
public class KUDONGMain
{
	private Map<String,CLIModule> map = new HashMap<>();
	
	public void test(Scanner scanner)
	{
		while(true)
		{
			System.out.println("----------------------");
			System.out.println("실행할 프로그램을 선택하세요.");
			System.out.println("1. 구동혁 - 게임서버 플레이어 관리");
			System.out.println("2. 홍길동 - 도서 관리");
			System.out.println("3. 홍길동 - 영화 관리");
			System.out.println("----------------------");
			System.out.print("> ");
			
			String choice = scanner.next();
	        scanner.nextLine();

	        if(choice.equals("-1")) {
	        	System.out.println("▣ 프로그램을 종료합니다.");
	        	break;
	        }
	        
	        CLIModule module = map.get(choice);
	        if(module != null) module.update(scanner);
	        else System.out.println("▣ 잘못된 입력값입니다.");
		}
	}
	
	public void registerModule(String num, CLIModule module)
	{
		this.map.put(num, module);
	}
	
	public static void run(Scanner scanner)
	{
		CLIModule module = new ServerManageModule();
		module.update(scanner);
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		KUDONGMain.run(scanner);

//		모듈로 실행할시 사용할것		
//		Scanner scanner = new Scanner(System.in);
//		KUDONGMain core = new KUDONGMain();
//		core.registerModule("1", new ServerManageModule());
//		core.test(scanner);
	}

}
