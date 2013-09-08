package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public class WorldGuard implements Hook
{
    private Plugin pl;
	
	@Override
	public void onEnable(AmberPlugin plugin)
	{
		this.pl = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
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
	public String getName()
	{
		return pl.getName();
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
}