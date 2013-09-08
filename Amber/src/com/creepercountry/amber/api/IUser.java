package com.creepercountry.amber.api;

import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;

public interface IUser
{
	/**
	 * Donation packages are stored in a map, in which this
	 * gathers the newest bottom most package name.
	 * 
	 * @see isValidDonator() if this value is even used.
	 * @return the name of the donation package
	 */
	public String getNewestDonationPackage();
	
	/**
	 * See all the donation packages this user has purchased,
	 * as well as any epoch longs referencing the packages.
	 * 
	 * @return a COPY of the purchased packages.
	 */
	public Map<Long, String> getPurchasedPackages();
	
	/**
	 * Add an entry into the donation packages this user has
	 * purchased. the epoch time is the key, the Package name
	 * is the value of the key.
	 * 
	 * @param time <code>epoch long time, referencing date/time</code>
	 * @param name <code>of the donation package
	 * (creeper, deluxe, etc.)</code>
	 */
	public void addDonationPackage(long time, String name);
	
	/**
	 * Remove a donation package history, since time is the key,
	 * this key will erase the package associated with it.
	 * DO NOT USE UNLESS NEEDED!!! remember to preserve!
	 * 
	 * @param time in epoch in which is associated with the
	 * donation package you want to remove.
	 */
	public void removeDonationPackage(long time);
	
	/**
	 * If the user has fought a PvP Champion.
	 * 
	 * @return true if the PvPChampion is not null
	 */
	public boolean hasChampionFought();
	
	/**
	 * The name of the User in which was the PvP Champion
	 * this User fought last.
	 * 
	 * @return the PvP Champions name NOT the current user.
	 */
	public String getChampionFought();
	
	/**
	 * The name of the User in which was the PvP Champion
	 * this User fought last.
	 * 
	 * @param name of the PvP Champion this user last fought
	 */
	public void setChampionFought(String name);
	
	/**
	 * If the User has a town.
	 * 
	 * @return true if town is not null
	 */
	public boolean hasTown();
	
	/**
	 * Get the town's name the user is a resident to.
	 * 
	 * @return town name
	 */
	public String getTown();
	
	/**
	 * Set the town the user is a resident to.
	 * 
	 * @param name the String name of the town exactly as spelled.
	 */
	public void setTown(String name);
	
	/**
	 * If this user's has a rank in the town they are registered.
	 * 
	 * @return true if rank is not null
	 */
	public boolean hasRank();
	
	/**
	 * Get this user's town rank.
	 * 
	 * @return the rank name
	 */
	public String getRank();
	
	/**
	 * Set this user's town rank.
	 * 
	 * @param name to set the user's rank to
	 */
	public void setRank(String name);
	
	/**
	 * Get the last epoch time the user was online.
	 * this is recorded onLogin, and onLogout.
	 * 
	 * @return the epoch long time.
	 */
	public long getLastOnline();
	
	/**
	 * Set the last online time in epoch long.
	 * 
	 * @param epoch long to set last online to.
	 */
	public void setLastOnline(long epoch);
	
	/**
	 * Donation packages are stored in a map, in which this
	 * gathers the newest bottom most purchase date.
	 * 
	 * @see isValidDonator() if this key is even used.
	 * @return the epoch long representing the purchase date.
	 */
	public long getPurchaseDate();
	
	/**
	 * Get the term length of the recent most donation package.
	 * 
	 * @see isValidDonator() if this key is even used.
	 * @return a Timunit.MILLISECONDS of the length of package.
	 */
	public long getTermLength();
	
	/**
	 * Set the term length of the recent most donation package.
	 * 
	 * @param epoch a Timunit.MILLISECONDS of the length of package.
	 */
	public void setTermLength(long epoch);
	
	/**
	 * The epoch time the user last PvP a champion.
	 * 
	 * @see hasChampionFought() to see if the user has even fought
	 * a champion.
	 * @return the epoch time.
	 */
	public long getLastPvP();
	
	/**
	 * Set the epoch time the user last PvP a champion.
	 * 
	 * @param epoch representing the time of pvp.
	 */
	public void setLastPvP(long epoch);
	
	/**
	 * Get the Fly Violation Level for this user.
	 * 
	 * @return the current VL level.
	 */
	public int getFlyViolation();
	
	/**
	 * Increment the current fly violation level.
	 * THIS IS AN ATOMIC ACTION.
	 * 
	 * @return the new updated value
	 */
	public int incrementFlyViolation();
	
	/**
	 * Decrement the current fly violation level.
	 * THIS IS AN ATOMIC ACTION.
	 * 
	 * @return the new updated value
	 */
	public int decrementFlyViolation();
	
	/**
	 * Set the fly violation level for this user.
	 * 
	 * @param amount to set the fly vl to.
	 */
	public void setFlyViolation(int amount);
	
	/**
	 * Get the PvP loss count.
	 * 
	 * @see hasChampionFought() to see if the user has even fought
	 * a champion.
	 * @return the loss increment for this user.
	 */
	public Short getLostPvPCount();
	
	/**
	 * Set the PvP loss count.
	 * ATOMIC ACTION
	 * 
	 * @see hasChampionFought() to see if the user has even fought
	 * a champion. 
	 * @param amount to set the loss counter to.
	 */
	public void setLostPvPCount(short amount);
	
	/**
	 * Get the PvP won count.
	 * 
	 * @see hasChampionFought() to see if the user has even fought
	 * a champion.
	 * @return the won increment for this user.
	 */
	public Short getWonPvPCount();
	
	/**
	 * Set the PvP won count.
	 * ATOMIC ACTION
	 * 
	 * @see hasChampionFought() to see if the user has even fought
	 * a champion. 
	 * @param amount to set the won counter to.
	 */
	public void setWonPvPCount(short amount);
	
	/**
	 * Get the total worth of this user. Every time this user pays
	 * for a donation package. it will add to a total worth.
	 * 
	 * @return the total worth of this user.
	 */
	public double getDonationWorth();
	
	/**
	 * Set the total worth of this user. Every time this user pays
	 * for a donation package. it will add to a total worth.
	 * 
	 * @param amount to set the total worth of this user to.
	 */
	public void setDonationWorth(double amount);
	
	/**
	 * Get if the user has a current and valid donator package.
	 * 
	 * @return true if user is current and valid donator.
	 */
	public boolean isValidDonator();
	
	/**
	 * Set if the user has a valid donator package.
	 * 
	 * @param valid if the user is valid.
	 */
	public void setValidDonator(boolean valid);
	
	/**
	 * If the user has placed a precious stone (pstone)
	 * 
	 * @return true if placed.
	 */
	public boolean hasPlacedPstone();
	
	/**
	 * set if the user has placed a pstone.
	 * 
	 * @param placed if user has placed the pstone.
	 */
	public void setPlacedPstone(boolean placed);
	
	/**
	 * Messages NOT ess mail. A COPY!!!! not the object itself.
	 * 
	 * @return a String List of all the messages. as a COPY!!!
	 */
	public List<String> getMessages();
	
	/**
	 * add a message NOT an ess mail.
	 * 
	 * @param msg to add
	 */
	public void addMessage(String msg);
	
	/**
	 * Clear all messages NOT ess mail.
	 * 
	 * @return true once cleared.
	 */
	public boolean clearMessages();
	
	/**
	 * Remove a message NOT an ess mail.
	 * 
	 * @param msg to remove
	 */
	public void removeMessage(String msg);
	
	/**
	 * If the user has message(s) NOT ess mail.
	 * 
	 * @return true if messages is not empty.
	 */
	public boolean hasMessages();
	
	/**
	 * Get all the raffle tickets this user owns.
	 * 
	 * @return a copy of the String list.
	 */
	public List<String> getRaffleTickets();
	
	/**
	 * Add a raffle ticket this user owns.
	 * 
	 * @param ticket name to add.
	 */
	public void addRaffleTicket(String ticket);
	
	/**
	 * Clears all raffle tickets this user owns.
	 * 
	 * @return true on success.
	 */
	public boolean clearRaffleTickets();
	
	/**
	 * Remove an raffle ticket this user owns.
	 * 
	 * @param ticket name to remove.
	 */
	public void removeRaffleTicket(String ticket);
	
	/**
	 * If the user has Raffle Tickets.
	 * 
	 * @return true if raffle tickets is not empty.
	 */
	public boolean hasRaffleTickets();
	
	/**
	 * Get the plot chunk for the user. this is inside
	 * the users town.
	 * 
	 * @return the Chunk the user owns.
	 */
	public Chunk getChunk();
	
	/**
	 * Set the plot chunk for this user.
	 * 
	 * @param chu the chunk this user owns.
	 */
	public void setChunk(Chunk chu);
	
	/**
	 * Checks if User has a town chunk.
	 * 
	 * @return true if chunk is not null.
	 */
	public boolean hasChunk();
}
