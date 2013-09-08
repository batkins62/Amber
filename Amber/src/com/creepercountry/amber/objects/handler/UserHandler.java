package com.creepercountry.amber.objects.handler;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.objects.user.User;
import com.creepercountry.amber.objects.user.UserLoader;
import com.creepercountry.amber.storage.FileMgmt;
import com.creepercountry.amber.util.Colors;
import com.creepercountry.amber.util.exception.NotRegisteredException;

public class UserHandler extends BaseHandler
{
	public UserHandler()
	{
		Notifier.log(NotifierLevel.NORMAL, "Initialising UserHandler for use.");

		// Create files and folders if non-existent
		try
		{
			FileMgmt.checkFolders(new String[]
				{ 
					rootFolder,
					rootFolder + dataFolder,
					rootFolder + dataFolder + FileMgmt.fileSeparator() + "users",
					rootFolder + dataFolder + FileMgmt.fileSeparator() + "users" + FileMgmt.fileSeparator() + "deleted",
				});
				FileMgmt.checkFiles(new String[]
					{
						rootFolder + dataFolder + FileMgmt.fileSeparator() + "users.txt",
					});
		}
		catch (IOException e)
		{
			Notifier.log(NotifierLevel.HIGHEST, "Error: Could not create default files and folders for UserHandler.");
		}
	}
	
	public void onLogin(Player player)
	{
		 if (!checkPlayer(player) && !verifyUser(player.getName()))
			 return;
		
		User user;
		
		// check userlist first before adding to it
		if (!userlist.contains(player.getName()))
		{
			userlist.add(player.getName());
			plugin.getConsumer().queueUserListSave();
		}
		
		// check to add a player.
		if (!users.containsKey(player.getName()))
		{
			user = new User(player.getName());
			users.put(player.getName(), user);
			UserLoader.loadUser(user);
		}
		else
			user = users.get(player.getName());

		user.setLastOnline(System.currentTimeMillis());
		
		if (user.hasMessages())
		{
			String msgs = Integer.toString(user.getMessages().size());
			Notifier.sendMessage(NotifierType.REGULAR, player, Colors.Blue + "You have " + Colors.Red + msgs + Colors.Blue + " TOWN messages. use \'/town mail read\' to read.");
		}
		
		if (user.isValidDonator())
		{
			Calendar ter = GregorianCalendar.getInstance(plugin.getTimeZone(), plugin.getLocale());
			ter.setTimeInMillis(user.getPurchaseDate());
				
			Notifier.sendMessage(NotifierType.REGULAR, Bukkit.getPlayer(player.getName()), String.format(plugin.getLocale(), Colors.Green + "Welcome Back Valid Donator!%s purchase date: %s, package: %s",
					Colors.Gold,
					DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, plugin.getLocale()).format(ter.getTime()),
					user.getNewestDonationPackage()));
				
			int vl = user.getFlyViolation();
			if (vl != 0)
				Notifier.sendMessage(NotifierType.REGULAR, player, Colors.Red + "You have a Fly Violation Level of: " + Colors.Gold + Integer.toString(vl));
		}
		
		plugin.getConsumer().queueUserSave(user);
	}

	public void onLogout(Player player)
	{
		User user = users.get(player.getName());
		user.setLastOnline(System.currentTimeMillis());
		plugin.getConsumer().queueUserSave(user);
	}

	public String getUserFilename(String name)
	{
		return rootFolder + dataFolder + FileMgmt.fileSeparator() + "users" + FileMgmt.fileSeparator() + name + ".yml";
	}
	
	public String getUserListFilename()
	{
		return rootFolder + dataFolder + FileMgmt.fileSeparator() + "users.yml";
	}
	
	public boolean verifyUser(String user)
	{
		return (plugin.isOnline(user) || Bukkit.getOfflinePlayer(user).hasPlayedBefore()) ? true:false;
	}
	
	public boolean checkPlayer(Player plr) 
	{
		if (!plr.isOnline())
			return false;
		
		if (!Vault.perms.has(plr, "ct.account.create.self"))
			return false;

		// Test and kick any players with invalid names.
		if ((plr.getName().trim() == null) || (plr.getName().contains(" ")))
		{
			plr.kickPlayer("Invalid name!");
			return false;
		}
		else if (plr.getName().contains("notch") || plr.getName().contains("jeb_"))
		{
			plr.kickPlayer("This login has been recorded, violation in accordance to C.R.S § 1 b");
			return false;
		}
		
		return true;
	}
	
	public User getUser(String name) throws NotRegisteredException
	{
		if (users.contains(name))
			return this.users.get(name);
		throw new NotRegisteredException("Player not registered");
	}
	
	public List<String> getUserList()
	{
		return userlist;
	}
}
