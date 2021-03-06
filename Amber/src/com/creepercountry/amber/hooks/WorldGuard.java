package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public class WorldGuard implements Hook
{
    public static Plugin pl;
	
	@Override
	public void onEnable(AmberPlugin plugin)
	{
		WorldGuard.pl = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
	}

	@Override
	public void onDisable(AmberPlugin plugin)
	{
		WorldGuard.pl = null;
	}

	@Override
	public int getUniqueID()
	{
		return pl.hashCode();
	}

	@Override
	public PluginHook getName()
	{
		return PluginHook.WORLDGUARD;
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
		return "WorldGuard";
	}
}
