package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public class Essentials implements Hook
{
    private Plugin pl;
	
	@Override
	public void onEnable(AmberPlugin plugin)
	{
		this.pl = plugin.getServer().getPluginManager().getPlugin("Essentials");
	}

	@Override
	public void onDisable(AmberPlugin plugin)
	{
		this.pl = null;
	}

	@Override
	public int getUniqueID()
	{
		return pl.hashCode();
	}

	@Override
	public PluginHook getName()
	{
		return PluginHook.ESSENTIALS;
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
		return "Essentials";
	}
	
	public com.earth2me.essentials.Essentials getEssentials()
	{
		return ((com.earth2me.essentials.Essentials) this.pl);
	}
	
    /**
     * Using essentials will return an epoch long  of players lastActive date
     * 
     * @param player <code>the Player</code>
     * @return epoch <code>in the form long</code>
     */
    public long lastActive(String playerName)
    {	
		return getEssentials().getOfflineUser(playerName).getLastLogin();
    }
}
