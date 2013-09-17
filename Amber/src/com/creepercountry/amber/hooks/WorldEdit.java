package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public class WorldEdit implements Hook
{
    public static Plugin pl;
	
	@Override
	public void onEnable(AmberPlugin plugin)
	{
		WorldEdit.pl = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
	}

	@Override
	public void onDisable(AmberPlugin plugin)
	{
		WorldEdit.pl = null;
	}

	@Override
	public int getUniqueID()
	{
		return pl.hashCode();
	}

	@Override
	public PluginHook getName()
	{
		return PluginHook.WORLDEDIT;
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
		return "WorldEdit";
	}
}
