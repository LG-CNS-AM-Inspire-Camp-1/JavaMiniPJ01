package org.example.kudong.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Function;

import org.example.kudong.util.EmailValidator;

public class PlayerManager
{
	private final Map<String,Player> playerMap;
	private final InputSession sessionManager;
	private final SimpleDateFormat simpleFormat;
	
	public PlayerManager()
	{
		this.isSession = false;
		this.playerMap = new HashMap<>();
		this.sessionManager = new InputSession(this);
		this.simpleFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
	}
	
	public void registerPlayer(Scanner sc)
	{
		System.out.println("▣ 플레이어 입력 등록을 시작합니다.");
		System.out.println("▣ 플레이어의 아이디를 입력해주세요! (- 입력시 취소)");
		System.out.print("> ");
		
		this.sessionManager.start((name)->{
			
			if(name.equals("-"))
			{
				System.out.println("▣ 취소 하였습니다.");
				return false;
			}
			else if(this.playerMap.containsKey(name))
			{
				System.out.println("▣ 이미 존재하는 플레이어 입니다!");
				return false;
			}
			else
			{
				System.out.println("▣ 입력된 플레이어 아이디> " + name);
				System.out.println("▣ 플레이어의 이메일을 입력하세요! (- 입력시 취소)");
				System.out.print("> ");
				
				this.sessionManager.start((email)->{
					
					if(email.equals("-"))
					{
						System.out.println("▣ 취소 하였습니다.");
						return false;
					}
					
					if(EmailValidator.isValidEmail(email))
					{
						System.out.println("▣ 입력된 플레이어 이메일> " + email);
						
						Player p = this.createPlayer(name, email);
						this.playerMap.put(name, p);
						
						System.out.println("▣ 플레이어 <"+name+">의 정보");
						System.out.println("┌ 1. 아이디: "+p.getName());
						System.out.println("├ 2. 이메일: "+p.getEmail());
						System.out.println("├ 3. UUID: "+p.getUuid().toString());
						System.out.println("├ 4. 소유금액: "+p.getMoney()+"원");
						System.out.println("└ 5. 생성날짜: "+simpleFormat.format(p.getDate()));
						
						return false;
					}
					else
					{
						System.out.println("▣ 유효하지 않은 이메일 주소입니다. 다시 입력하세요!");
						System.out.print("> ");
						return true;
					}	
				});
			}
			return true;
		});
	}
	
	public void removePlayer(Scanner sc)
	{
		System.out.println("▣ 삭제하려는 플레이어의 아이디를 입력해주세요! (- 입력시 취소)");
		System.out.print("> ");
		
		this.sessionManager.start((name)->{
			
			if(name.equals("-"))
			{
				System.out.println("▣ 취소 하였습니다.");
				return false;
			}
			
			System.out.println("▣ 입력된 플레이어 아이디> " + name);
			
			if(this.playerMap.containsKey(name) == false)
			{
				System.out.println("▣ 해당 플레이어는 존재하지 않습니다!");
				return false;
			}
			else
			{
				Player p = this.playerMap.get(name);
				
				System.out.println("▣ 플레이어 <"+name+">의 정보");
				System.out.println("┌ 1. 아이디: "+p.getName());
				System.out.println("├ 2. 이메일: "+p.getEmail());
				System.out.println("├ 3. UUID: "+p.getUuid().toString());
				System.out.println("├ 4. 소유금액: "+p.getMoney()+"원");
				System.out.println("└ 5. 생성날짜: "+simpleFormat.format(p.getDate()));
				System.out.println("▣ 해당 플레이어를 삭제하시겠습니까? Y/N 입력");
				System.out.print("> ");
				
				this.sessionManager.start((result)->{
					
					if(result.equalsIgnoreCase("y"))
					{
						System.out.println("▣ 플레이어 <"+ name + ">가 삭제되었습니다.");
						this.playerMap.remove(name);
					}
					else
					{
						System.out.println("▣ 취소 하였습니다.");
					}
					return false;
				});
			}
			return true;
		});
		
	}
	
	public void findPlayer(Scanner sc)
	{
		System.out.println("▣ 찾으시는 플레이어의 아이디를 입력하세요 (- 입력시 취소)");
		System.out.print("> ");
		
		String name = sc.next();
        sc.nextLine();
        
		if(name.equals("-"))
		{
			System.out.println("▣ 취소 하였습니다.");
		}
		else if(this.playerMap.containsKey(name) == false)
		{
			System.out.println("▣ 해당 플레이어는 존재하지 않습니다!");
		}
		else
		{
			Player p = this.playerMap.get(name);
			
			System.out.println("▣ 플레이어 <"+name+">의 정보");
			System.out.println("┌ 1. 아이디: "+p.getName());
			System.out.println("├ 2. 이메일: "+p.getEmail());
			System.out.println("├ 3. UUID: "+p.getUuid().toString());
			System.out.println("├ 4. 소유금액: "+p.getMoney()+"원");
			System.out.println("└ 5. 생성날짜: "+simpleFormat.format(p.getDate()));
		}
	}
	
	public void listPlayer(Scanner sc)
	{
		if(this.playerMap.size() == 0)
		{
			System.out.println("▣ 리스트에 플레이어가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("▣ 모든 플레이어의 리스트가 출력 됩니다.");

		this.playerMap.forEach((name,p)->{
			System.out.println("▣ 플레이어 <"+name+">의 정보");
			System.out.println("┌ 1. 아이디: "+p.getName());
			System.out.println("├ 2. 이메일: "+p.getEmail());
			System.out.println("├ 3. UUID: "+p.getUuid().toString());
			System.out.println("├ 4. 소유금액: "+p.getMoney()+"원");
			System.out.println("└ 5. 생성날짜: "+simpleFormat.format(p.getDate()));
		});
		
		System.out.println("▣ 총 "+this.playerMap.size()+"명의 플레이어가 검색되었습니다.");
	}
	
	public void updatePlayerMoney(Scanner sc)
	{
		System.out.println("▣ 플레이어의 아이디를 입력하세요 (- 입력시 취소)");
		System.out.print("> ");
		
		String name = sc.next();
        sc.nextLine();
        
		if(name.equals("-"))
		{
			System.out.println("▣ 취소 하였습니다.");
		}
		else if(this.playerMap.containsKey(name) == false)
		{
			System.out.println("▣ 해당 플레이어는 존재하지 않습니다!");
		}
		else
		{
			Player p = this.playerMap.get(name);
			System.out.println("▣ 플레이어 <"+name+">의 소유 금액은 "+p.getMoney()+"원 입니다.");
			System.out.println("▣ 해당 플레이어를 소유금액을 수정하시겠습니까? Y/N 입력");
			System.out.print("> ");
			
			this.sessionManager.start((result)->{
				
				if(result.equalsIgnoreCase("y"))
				{
					System.out.println("▣ 수정하고 싶으신 금액을 입력하세요 (- 입력시 취소)");
					System.out.print("> ");
					
					this.sessionManager.start((money)->{
						
						if(money.equals("-"))
						{
							System.out.println("▣ 취소 하였습니다.");
							return false;
						}
						try
						{
							double m = Double.parseDouble(money);
							p.setMoney(m);
							System.out.println("▣ 플레이어의 소유금액이 "+m+"원으로 변경되었습니다.");
							return false;
						}
						catch(NumberFormatException e)
						{
							System.out.println("▣ 잘못된 숫자가 입력되었습니다. 다시 입력해주세요.");
							System.out.print("> ");
							return true;
						}
					});
					return true;
				}
				else
				{
					System.out.println("▣ 취소 하였습니다.");
					return false;
				}
			});
		}
	}
	
	public Player createPlayer(String name, String email)
	{
		Player p = new Player(name,email,UUID.randomUUID(),new Date());
		return p;
	}

	public boolean isSession()
	{
		return isSession;
	}

	public void activateSession(Function<String, Boolean> session)
	{
		this.isSession = true;
		this.session = session;
	}
	
	public void deActivateSession()
	{
		this.isSession = false;
		this.session = null;
	}
	
	public boolean executeCallback(String input)
	{
		return session.apply(input);
	}
	
	public void setCallback(Function<String, Boolean> session)
	{
		this.session = session;
	}
	
	private boolean isSession = false;
	private Function<String, Boolean> session;
}
