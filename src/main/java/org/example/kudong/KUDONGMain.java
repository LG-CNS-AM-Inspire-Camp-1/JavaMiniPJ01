package org.example.kudong;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.example.kudong.api.CLIModule;
import org.example.kudong.commands.ServerManageModule;

/***
 * 
 * 진입점 입니다.
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
	public Map<String,CLIModule> map = new HashMap<>();
	
	public void test(Scanner scanner)
	{
		while(true)
		{
			System.out.println("실행할 프로그램을 선택하세요.");
			System.out.println("1. 구동혁 - 서버관리");
			System.out.println("----------------------");
			System.out.print("> ");
			
			String choice = scanner.next();
	        scanner.nextLine();

	        if(choice.equals("-1")) {
	        	System.out.println("프로그램을 종료합니다.");
	        	break;
	        }
	        
	        CLIModule module = map.get(choice);
	        if(module != null) module.update(scanner);
	        else
	        	System.out.println("잘못된 입력값 - 다시 입력하세요.");
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
//		Scanner scanner = new Scanner(System.in);
//		KUDONGMain core = new KUDONGMain();
//		core.registerModule("1", new ServerManageModule());
//		core.test(scanner);
		Scanner scanner = new Scanner(System.in);
		KUDONGMain.run(scanner);
	}
}
