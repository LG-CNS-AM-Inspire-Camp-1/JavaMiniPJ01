package org.example.kudong.commands;

import java.util.function.Function;

public class InputSession
{
	private final PlayerManager manager;
	
	public InputSession(PlayerManager manager)
	{
		this.manager = manager;
	}
	
	public void start(Function<String, Boolean> result)
	{
		this.manager.activateSession(result);
	}
	
	public void stop(Player p)
	{
		this.manager.deActivateSession();
	}
	
}
