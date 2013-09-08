package com.creepercountry.amber.objects.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.Chunk;
import com.creepercountry.amber.api.IUser;
import com.creepercountry.amber.objects.MasterObject;

public class User extends MasterObject implements IUser
{
	private volatile String champfought, town, rank;
	private volatile long termlen, lastpvp, lastonline;
	private volatile double donworth;
	private volatile AtomicInteger flyvl, lostcount, woncount;
	private volatile boolean validdon, nagpstone;
	private volatile Map<Long, String> donpkg = new ConcurrentHashMap<Long, String>();
	private volatile List<String> messages = new ArrayList<String>(), raffletickets = new ArrayList<String>();
	private volatile Chunk chunk;
	
	public User(String user)
	{
		this.setName(user);
		clear();
	}
	
	public synchronized void clear()
	{
		this.raffletickets.clear();
		this.messages.clear();
		this.donpkg.clear();
	}

	@Override
	public String getNewestDonationPackage()
	{
		long highest = 0L;
		for (long num : donpkg.keySet())
			if (highest < num)
				highest = num;
		return this.donpkg.get(Long.valueOf(highest)).intern();
	}

	@Override
	public Map<Long, String> getPurchasedPackages()
	{
		return new HashMap<Long, String>(this.donpkg);
	}

	@Override
	public void addDonationPackage(long time, String name)
	{
		this.donpkg.put(Long.valueOf(time), name);
	}

	@Override
	public void removeDonationPackage(long time)
	{
		this.donpkg.remove(Long.valueOf(time));
		
	}

	@Override
	public boolean hasChampionFought()
	{
		return this.champfought != null;
	}

	@Override
	public String getChampionFought()
	{
		return this.champfought;
	}

	@Override
	public void setChampionFought(String name)
	{
		this.champfought = name;
		
	}

	@Override
	public boolean hasTown()
	{
		return this.town != null;
	}

	@Override
	public String getTown()
	{
		return this.town;
	}

	@Override
	public void setTown(String name)
	{
		this.town = name;
	}

	@Override
	public boolean hasRank()
	{
		return this.rank != null;
	}

	@Override
	public String getRank()
	{
		return this.rank;
	}

	@Override
	public void setRank(String name)
	{
		this.rank = name;
	}

	@Override
	public long getLastOnline()
	{
		return this.lastonline;
	}

	@Override
	public void setLastOnline(long epoch)
	{
		this.lastonline = epoch;
	}

	@Override
	public long getPurchaseDate()
	{
		long highest = 0L;
		for (long num : donpkg.keySet())
			if (highest < num)
				highest = num;
		return highest;
	}

	@Override
	public long getTermLength()
	{
		return this.termlen;
	}

	@Override
	public void setTermLength(long epoch)
	{
		this.termlen = epoch;
	}

	@Override
	public long getLastPvP()
	{
		return this.lastpvp;
	}

	@Override
	public void setLastPvP(long epoch)
	{
		this.lastpvp = epoch;
	}

	@Override
	public int getFlyViolation()
	{
		return this.flyvl.get();
	}

	@Override
	public int incrementFlyViolation()
	{
		return this.flyvl.incrementAndGet();
	}

	@Override
	public int decrementFlyViolation()
	{
		return this.flyvl.decrementAndGet();
	}

	@Override
	public void setFlyViolation(int amount)
	{
		this.flyvl.set(amount);
	}

	@Override
	public Short getLostPvPCount()
	{
		return this.lostcount.shortValue();
	}

	@Override
	public void setLostPvPCount(short amount)
	{
		this.lostcount.set(amount);
	}

	@Override
	public Short getWonPvPCount()
	{
		return this.woncount.shortValue();
	}

	@Override
	public void setWonPvPCount(short amount)
	{
		this.woncount.set(amount);
	}

	@Override
	public double getDonationWorth()
	{
		return this.donworth;
	}

	@Override
	public void setDonationWorth(double amount)
	{
		this.donworth = amount;
	}

	@Override
	public boolean isValidDonator()
	{
		return this.validdon;
	}

	@Override
	public void setValidDonator(boolean valid)
	{
		this.validdon = valid;
	}

	@Override
	public boolean hasPlacedPstone()
	{
		return this.nagpstone;
	}

	@Override
	public void setPlacedPstone(boolean placed)
	{
		this.nagpstone = placed;
	}

	@Override
	public List<String> getMessages()
	{
		return new ArrayList<String>(this.messages);
	}

	@Override
	public void addMessage(String msg)
	{
		this.messages.add(msg);
	}

	@Override
	public boolean clearMessages()
	{
		this.messages.clear();
		return this.messages.isEmpty();
	}

	@Override
	public void removeMessage(String msg)
	{
		this.messages.remove(msg);
	}

	@Override
	public boolean hasMessages()
	{
		return !this.messages.isEmpty();
	}

	@Override
	public List<String> getRaffleTickets()
	{
		return new ArrayList<String>(this.raffletickets);
	}

	@Override
	public void addRaffleTicket(String ticket)
	{
		this.raffletickets.add(ticket);
	}

	@Override
	public boolean clearRaffleTickets()
	{
		this.raffletickets.clear();
		return this.raffletickets.isEmpty();
	}

	@Override
	public void removeRaffleTicket(String ticket)
	{
		this.raffletickets.remove(ticket);
	}

	@Override
	public boolean hasRaffleTickets()
	{
		return !this.raffletickets.isEmpty();
	}

	@Override
	public Chunk getChunk()
	{
		return this.chunk;
	}

	@Override
	public void setChunk(Chunk chu)
	{
		this.chunk = chu;
	}
	
	@Override
	public boolean hasChunk()
	{
		return this.chunk != null;
	}
}
