package org.example.kudong.commands;

import java.util.Scanner;

import org.example.kudong.api.CLIModule;

public class ServerManageModule implements CLIModule
{
	private final PlayerManager manager;
	
	
	public ServerManageModule()
	{
		this.manager = new PlayerManager();
	}
	
	@Override
	public void update(Scanner sc)
	{
		while(true)
		{
			String input = null;
			
			if(this.manager.isSession())
			{
				input = this.getInput(sc);
				boolean isAgain = this.manager.executeCallback(input);
				if(isAgain) continue;
				else this.manager.deActivateSession();
			}
			else
			{
				this.print();
				input = this.getInput(sc);
			}

			switch(input)
			{
				case "1": this.manager.registerPlayer(sc); break; //플레이어 등록
				case "2": this.manager.removePlayer(sc); break; //플레이어 삭제
				case "3": this.manager.findPlayer(sc); break; //플레이어 조회
				case "4": this.manager.listPlayer(sc); break; //플레이어 전체 조회
				case "x":
				case "X":
					return;
			}
		
		}
	}
	
	private String getInput(Scanner sc)
	{
		String choice = sc.next();
        sc.nextLine();
        return choice;
	}
	
	private void print()
	{
		System.out.println("---------------------------");
		System.out.println(" 구동혁 - 게임서버 플레이어 관리 CLI");
		System.out.println(" 1. 플레이어 등록");
		System.out.println(" 2. 플레이어 삭제");
		System.out.println(" 3. 플레이어 조회");
		System.out.println(" 4. 플레이어 리스트 출력");
		System.out.println(" X. 메인으로");
		System.out.println("---------------------------");
		System.out.print("> ");
	}

}
