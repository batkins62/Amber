package com.creepercountry.amber.objects.user;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import com.creepercountry.amber.AmberPlugin;

public final class UserBuilder
{
	private static LinkedHashMap<Short, String> newConfig = new LinkedHashMap<Short, String>();
	private static transient Short key = 0;
	
	private static Calendar gc = GregorianCalendar.getInstance(AmberPlugin.getInstance().getTimeZone(), AmberPlugin.getInstance().getLocale());
	private static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, AmberPlugin.getInstance().getLocale());
	
	public static LinkedHashMap<Short, String> buildConfig(User user)
	{
		gc.setTimeInMillis(System.currentTimeMillis());
		newConfig.put(key++, "# Scribed: " + df.format(gc.getTime()));
		newConfig.put(key++, "general:");
		newConfig.put(key++, "  last-online: " + formatValue(user.getLastOnline()));
		if (user.hasMessages())
		{
			newConfig.put(key++, "  messages:");
			for (String msg : user.getMessages())
				newConfig.put(key++, "    - " + formatValue(msg));
		}
		newConfig.put(key++, "town:");
		newConfig.put(key++, "  pstone-nag: " + formatValue(user.hasPlacedPstone()));
		if (user.hasTown())
			newConfig.put(key++, "  town: " + formatValue(user.getTown()));
		if (user.hasRank())
			newConfig.put(key++, "  rank: " + formatValue(user.getRank()));
		if (user.hasChunk())
		{
			newConfig.put(key++, "  chunk:");
			newConfig.put(key++, "    x: " + formatValue(user.getChunk().getX()));
			newConfig.put(key++, "    z: " + formatValue(user.getChunk().getZ()));
			newConfig.put(key++, "    world: " + formatValue(user.getChunk().getWorld()));
		}
		if (user.hasRaffleTickets())
		{
			newConfig.put(key++, "  raffle-tickets:");
			for (String tkt : user.getRaffleTickets())
				newConfig.put(key++, "    - " + formatValue(tkt));
		}
		if (user.isValidDonator())
		{
			newConfig.put(key++, "donation:");
			newConfig.put(key++, "  donation-package:");
			for (Long mapkey : user.getPurchasedPackages().keySet())
				newConfig.put(key++, "    - " + formatValue(user.getPurchasedPackages().get(mapkey) + ":" + Long.toString(mapkey)));
			newConfig.put(key++, "  fly-violation: " + formatValue(user.getFlyViolation()));
			newConfig.put(key++, "  valid: " + formatValue(user.isValidDonator()));
			newConfig.put(key++, "  term: " + formatValue(user.getTermLength()));
			newConfig.put(key++, "  worth: " + formatValue(user.getDonationWorth()));
		}
		if (user.hasChampionFought())
		{
			newConfig.put(key++, "fighter:");
			newConfig.put(key++, "  champion-fought: " + formatValue(user.getChampionFought()));
			newConfig.put(key++, "  last-pvp: " + formatValue(user.getLastPvP()));
			newConfig.put(key++, "  lost-count: " + formatValue(user.getLostPvPCount()));
			newConfig.put(key++, "  won-count: " + formatValue(user.getWonPvPCount()));
		}
		
		return newConfig;
	}
	
	public static int getSize()
	{
		return newConfig.size();
	}
	
	private static String formatValue(Object value)
	{
		String out = "";
		if (value instanceof Integer)
			Integer.toString((Integer) value);
		else if (value instanceof Long)
			Long.toString((Long) value);
		else if (value instanceof Boolean)
			Boolean.toString((Boolean) value);	
		else if (value instanceof String)
			out = (String) value;
		else if (value.equals(null))
			out = "";
		else
			return "{}";
		return "\'" + out + "\'";
	}
}
