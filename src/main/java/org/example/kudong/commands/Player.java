package org.example.kudong.commands;

import java.util.Date;
import java.util.UUID;

public class Player
{
	private String name;
	private String email;
	private UUID uuid;
	private Date date;
	private double money;
	
	public Player(String name, String email, UUID uuid, Date date)
	{
		this.name = name;
		this.email = email;
		this.uuid = uuid;
		this.date = date;
		this.money = 0.0;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

}
