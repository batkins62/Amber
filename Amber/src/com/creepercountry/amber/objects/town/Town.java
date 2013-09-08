package com.creepercountry.amber.objects.town;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.objects.MasterObject;
import com.creepercountry.amber.util.exception.AlreadyRegisteredException;
import com.creepercountry.amber.util.exception.NotRegisteredException;
import com.creepercountry.amber.util.exception.TownException;

public class Town extends MasterObject implements IResidentList
{
	private List<String> residents = new ArrayList<String>();
	private List<String> assistants = new ArrayList<String>();
	private List<Chunk> chunks = new ArrayList<Chunk>();
	private List<Portal> portals = new ArrayList<Portal>();
	private List<String> rules = new ArrayList<String>();
	private List<String> flags = new ArrayList<String>();
	private String owner, vice;
	private double townPrice;
	private boolean isPublic, isActive;
	private String region;
	private Location vault;
	private double balance;

	public Town(String name)
	{
		setName(name);
		townPrice = 0.0;
		isPublic = true;
		isActive = true;
		balance = 0.0;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void setBalance(double bal)
	{
		this.balance = bal;
	}
	
	// TODO
	/*
	public List<Chunk> getChunks()
	{
		return chunks;
	}
	
	public void addChunk(Chunk chu) throws AlreadyRegisteredException
	{
		if (!hasChunk(chu))
			chunks.add(chu);
		else
			throw new AlreadyRegisteredException("Chunk has already been assigned a town.");
	}
	
	public boolean hasChunk(Chunk chu)
	{
		return chunks.contains(chu);
	}
	
	public boolean hasChunks()
	{
		return !chunks.isEmpty();
	}
	
	public void removeChunk(Chunk chu) throws NotRegisteredException
	{
		if (hasChunk(chu))
			chunks.remove(chu);
		else
			throw new NotRegisteredException("Couldnt not find a chunk by this name.");
	}
	*/
	
	public boolean hasVault()
	{
		return vault != null;
	}
	
	public Location getVault()
	{
		return vault;
	}
	
	public void setVault(Location loc)
	{
		this.vault = loc;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public String getVice()
	{
		return vice;
	}
	
	public void setVice(String vice)
	{
		this.vice = vice;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public void setRegion(String region)
	{
		this.region = region;
	}
	
	@Override
	public List<String> getUsers()
	{
		return residents;
	}

	public List<String> getAssistants()
	{
		return assistants;
	}
	
	public List<String> getRules()
	{
		return rules;
	}
	
	public List<String> getFlags()
	{
		return flags;
	}
	
	public List<Portal> getPortals()
	{
		return portals;
	}
	
	public void addPortal(Portal por) throws AlreadyRegisteredException
	{
		if (!hasPortal(por))
			portals.add(por);
		else
			throw new AlreadyRegisteredException("Portal has already been assigned a town.");
	}
	
	public boolean hasPortal(Portal por)
	{
		return portals.contains(por);
	}
	
	public void removePortal(Portal por) throws NotRegisteredException
	{
		if (hasPortal(por))
			portals.remove(por);
		else
			throw new NotRegisteredException("Couldnt not find a portal by this name.");
	}

	@Override
	public boolean hasUser(String name)
	{
		for (String resident : residents)
			if (resident.equalsIgnoreCase(name))
				return true;
		return false;
	}

	public boolean hasUser(String resident)
	{
		return residents.contains(resident);
	}

	public boolean hasAssistant(String resident)
	{
		return assistants.contains(resident);
	}
	
	public boolean isStaff(String resident)
	{
		return (isOwner(resident) || hasAssistant(resident));
	}
	
	public boolean hasRule(String rule)
	{
		return rules.contains(rule);
	}
	
	public boolean hasFlag(String flag)
	{
		return flags.contains(flag);
	}
	
	public void addUser(String resident) throws AlreadyRegisteredException
	{
		addUserCheck(resident);
		residents.add(resident);
		resident.setTown(this.getName());
	}

	public void addUserCheck(String resident) throws AlreadyRegisteredException, NotRegisteredException
	{
		if (hasUser(resident))
			throw new AlreadyRegisteredException("You are already in the town!");
		else if (AmberPlugin.getInstance().getUserHandler().hasTown(resident))
			try
			{
				if (!resident.getTown().equals(this))
					throw new AlreadyRegisteredException("You are already in a town!");
			}
			catch (NotRegisteredException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void addAssistant(String resident) throws TownException
	{
		if (hasAssistant(resident))
			throw new TownException(resident + " was already an asstant of " + this.getName());

		if (!hasUser(resident))
			throw new TownException(resident.getName() + " doesn't belong to your town.");

		assistants.add(resident);
	}
	
	public void addRule(String rule) throws TownException
	{
		if (hasRule(rule))
			throw new TownException("you already have that rule");

		rules.add(rule);
	}
	
	public void addFlag(String flag) throws TownException
	{
		if (hasFlag(flag))
			throw new TownException("you already have that flag");

		flags.add(flag);
	}

	public boolean isOwner(User resident)
	{
		return resident == owner;
	}
	
	public boolean isVice(User resident)
	{
		return resident == vice;
	}
	
	public boolean isAssistant(User resident)
	{
		return hasAssistant(resident);
	}

	public boolean hasOwner()
	{
		return owner != null;
	}
	
	public boolean hasVice()
	{
		return vice != null;
	}
	
	public boolean hasRegion()
	{
		return region != null;
	}
	
	public boolean hasPortal()
	{
		return portals != null;
	}
	
	public void removeUser(User resident) throws NotRegisteredException
	{
		if (isOwner(resident))
			return;
		
		if (!hasUser(resident))
			throw new NotRegisteredException();
		else {

			remove(resident);

			if (getNumResidents() == 0)
			{
				clear();
			}
		}
	}

	private void removeAllUsers()
	{
		for (User resident : new ArrayList<User>(residents))
			remove(resident);
	}
	
	private void remove(User resident)
	{
		Notifier.log(NotifierLevel.LOWEST, "removing resident: " + resident.getName() + " from town: " + getName());
		
		// lets check that the user is not an owner before we remove him
		if (isOwner(resident))
		{
			if (residents.size() > 1)
			{
				// lets assign an assistant to be our new owner
				for (User assistant : new ArrayList<User>(getAssistants()))
					if (assistant != resident)
					{
						setOwner(assistant);
						continue;
					}
				
				if (isOwner(resident))
				{
					// Still owner and no assistants so pick a resident to be mayor
					for (User newMayor : new ArrayList<User>(getUsers()))
						if (newMayor != resident)
						{
							setOwner(newMayor);
							continue;
						}
				}
			}

		}
		
		if (hasAssistant(resident))
			removeAssistant(resident);
		
		try
		{
			resident.setTown(null);
		}
		catch (AlreadyRegisteredException e)
		{
		}
		residents.remove(resident);
	}
	
	public void removeAssistant(User resident)
	{
		if (!hasAssistant(resident))
			BukkitUtils.info("Not Registered");
		else
			assistants.remove(resident);
	}
	
	public void removeRule(String rule) throws TownException
	{
		if (!hasRule(rule))
			throw new TownException("you dont have that rule");
		else
			rules.remove(rule);
	}
	
	public void clear()
	{
		removeAllUsers();
		owner = null;
		vice = null;
		region = null;
		vault.zero();
		chunks.clear();
		residents.clear();
		assistants.clear();
		rules.clear();
		portals.clear();
	}
	
	public void setTownPrice(double townPrice)
	{
		this.townPrice = townPrice;
	}

	public double getTownPrice()
	{
		return townPrice;
	}
	
	public int getNumResidents()
	{
		return residents.size();
	}
	
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public boolean isActive()
	{
		return isActive;
	}
	
	public void setPublic(boolean isPublic)
	{
		this.isPublic = isPublic;
	}

	public boolean isPublic()
	{
		return isPublic;
	}

	public boolean isOpen()
	{
		if (isActive && isPublic)
			return true;
		
		return false;
	}
}