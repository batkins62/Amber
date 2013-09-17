package com.creepercountry.amber.hooks;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.util.Colors;

public class Vault implements Hook
{
    /**
     * our vault/hooks instances and variables
     */
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    
    private Plugin pl;
	private AmberPlugin plugin;

    @Override
    public void onEnable(AmberPlugin plugin)
    {
    	this.plugin = plugin;
		this.pl = plugin.getServer().getPluginManager().getPlugin("Vault");
		if (isEnabled())
		{
			setupEconomy();
			setupChat();
			setupPermissions();
		}
    }
    
	@Override
	public void onDisable(AmberPlugin plugin)
	{
		this.pl = null;
		Vault.econ = null;
		Vault.perms = null;
		Vault.chat = null;
	}
    
	/**
     * Depend on Vault.jar
     * @return
     */
    private boolean setupEconomy()
    {
    	RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
        return econ != null;
    }
    
    /**
     * Setup chat (part of vault) dependancy
     * @return
     */
    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    /**
     * Setup permissions (part of vault) dependancy
     * @return
     */
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

	@Override
	public int getUniqueID()
	{
		return pl.hashCode();
	}

	@Override
	public PluginHook getName()
	{
		return PluginHook.VAULT;
	}

	@Override
	public boolean isEnabled()
	{
		return pl != null;
	}

	@Override
	public Plugin getPlugin()
	{
		return pl;
	}
	
	@Override
	public String toString()
	{
		return "Vault";
	}
	
	public net.milkbowl.vault.Vault getVault()
	{
		return ((net.milkbowl.vault.Vault) this.pl);
	}
	
    /**
     * use vault to charge the user.
     * 
     * @param amount
     * @param player
     * @param msg to show what your charging the user for (only shows console when logged)
     * @return true once we successfully charge player
     */
    public boolean chargeuser(double amount, Player player, String msg)
    {
    	// charge the user then log outcome then return true
		EconomyResponse r = econ.withdrawPlayer(player.getName(), amount);
        if(r.transactionSuccess())
        {
            Notifier.sendMessage(NotifierType.REGULAR, player, String.format(Colors.Green + "You were charged %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
            Notifier.log(NotifierLevel.NORMAL, player.getName() + " was charged $" + amount + " for " + msg);
            return true;
        }
        else
        {
        	Notifier.sendMessage(NotifierType.REGULAR, player, String.format(Colors.Red + "An error occured: %s", r.errorMessage));
        }
        return false;
    }
    
    /**
     * use vault to pay the user
     * 
     * @param player
     * @param amount
     * @param msg
     */
    public void payuser(Player player, double amount, String msg)
    {
    	// pay the user then log outcome
		EconomyResponse r = econ.depositPlayer(player.getName(), amount);
        if(r.transactionSuccess())
        {
            Notifier.sendMessage(NotifierType.REGULAR, player, String.format(Colors.Green + "You were given %s and now have %s for " + msg, econ.format(r.amount), econ.format(r.balance)));
            Notifier.log(NotifierLevel.NORMAL, player.getName() + " recieved $" + amount + " for " + msg);
        }
        else
        {
        	Notifier.sendMessage(NotifierType.REGULAR, player, String.format(Colors.Red + "An error occured: %s", r.errorMessage));
        }
    }
}
