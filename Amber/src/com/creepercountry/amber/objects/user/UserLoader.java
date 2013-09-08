package com.creepercountry.amber.objects.user;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.objects.handler.UserHandler;

public class UserLoader
{	
	public static void loadUser(User user)
	{
		UserHandler uh = AmberPlugin.getInstance().getUserHandler();
		File file = new File(uh.getUserFilename(user.getName()));
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		String w = null;
		int z = 0, x = 0;
		
		if (file.exists() && file.isFile())
		{
			if (yml.isString(UserNode.CHAMPFOUGHT.getPath()))
				user.setChampionFought(yml.getString(UserNode.CHAMPFOUGHT.getPath()));
			if (yml.isLong(UserNode.LASTPVP.getPath()))
				user.setLastPvP(yml.getLong(UserNode.LASTPVP.getPath()));
			if (yml.isInt(UserNode.PVPLOST.getPath()))
				user.setLostPvPCount(Integer.valueOf(yml.getInt(UserNode.PVPLOST.getPath())).shortValue());
			if (yml.isInt(UserNode.PVPWON.getPath()))
				user.setLostPvPCount(Integer.valueOf(yml.getInt(UserNode.PVPWON.getPath())).shortValue());
			
			if (yml.isBoolean(UserNode.NAGPSTONE.getPath()))
				user.setPlacedPstone(yml.getBoolean(UserNode.NAGPSTONE.getPath()));
			if (yml.isInt(UserNode.CHUNK.getChunkXPath()))
				x = yml.getInt(UserNode.CHUNK.getChunkXPath());
			if (yml.isInt(UserNode.CHUNK.getChunkZPath()))
				z = yml.getInt(UserNode.CHUNK.getChunkZPath());
			if (yml.isString(UserNode.CHUNK.getChunkWorldPath()))
				w = yml.getString(UserNode.CHUNK.getChunkWorldPath());
			if ((x != 0) && (z != 0) && (w != null))
				user.setChunk(Bukkit.getWorld(w).getChunkAt(x, z));
			
			if ((yml.isBoolean(UserNode.VALID.getPath())) ? yml.getBoolean(UserNode.VALID.getPath()) : false)
			{
				user.setValidDonator(true);
				long key = 0L;
				String value = "";
				
				for (String k : yml.getStringList(UserNode.DONATIONPKG.getPath()))
				{
					key = Long.parseLong(k.substring(k.indexOf(":")));
					value = k.substring(0, k.indexOf(":"));
					user.addDonationPackage(key, value);
				}
				
				if (yml.isInt(UserNode.FLYVIOLATION.getPath()))
					user.setFlyViolation(yml.getInt(UserNode.FLYVIOLATION.getPath()));
				if (yml.isLong(UserNode.TERM.getPath()))
					user.setTermLength(yml.getLong(UserNode.TERM.getPath()));
			}
			
			if (yml.isDouble(UserNode.DONATIONWORTH.getPath()))
				user.setDonationWorth(yml.getDouble(UserNode.DONATIONWORTH.getPath()));
			
			if (yml.isString(UserNode.TOWN.getPath()))
				user.setTown(yml.getString(UserNode.TOWN.getPath()));
			if (yml.isString(UserNode.RANK.getPath()))
				user.setRank(yml.getString(UserNode.RANK.getPath()));
			if (yml.isList(UserNode.RAFFLETICKETS.getPath()))
				for (String ticket : yml.getStringList(UserNode.RAFFLETICKETS.getPath()))
					user.addRaffleTicket(ticket);
			
			if (yml.isLong(UserNode.LASTONLINE.getPath()))
				user.setLastOnline(yml.getLong(UserNode.LASTONLINE.getPath()));
			if (yml.isList(UserNode.MESSAGES.getPath()))
				for (String msg : yml.getStringList(UserNode.MESSAGES.getPath()))
					user.addMessage(msg);
		}
	}
}
