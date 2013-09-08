package com.creepercountry.amber.objects.user;

public enum UserNode
{
	DONATIONPKG("donation.donation-package"),
	FLYVIOLATION("donation.fly-violation"),
	VALID("donation.valid"),
	TERM("donation.term"),
	DONATIONWORTH("donation.worth"),
	LASTPVP("fighter.last-pvp"),
	CHAMPFOUGHT("fighter.champion-fought"),
	PVPLOST("figther.lost-count"),
	PVPWON("fighter.won-count"),
	CHUNK("town.chunk"),
	NAGPSTONE("town.pstone-nag"),
	LASTONLINE("general.last-online"),
	MESSAGES("general.messages"),
	RANK("town.rank"),
	TOWN("town.town"),
	RAFFLETICKETS("town.raffle-tickets");
	
	private final String path;
	UserNode(String pth)
	{
		this.path = pth;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getWorldLocationPath()
	{
		return path + ".world";
	}
	
	public String getYawLocationPath()
	{
		return path + ".yaw";
	}
	
	public String getPitchLocationPath()
	{
		return path + ".pitch";
	}
	
	public String getChunkXPath()
	{
		return path + ".x";
	}
	
	public String getChunkZPath()
	{
		return path + ".z";
	}
	
	public String getChunkWorldPath()
	{
		return path + ".world";
	}
	
	@Override
	public String toString()
	{
		return path;
	}
}
