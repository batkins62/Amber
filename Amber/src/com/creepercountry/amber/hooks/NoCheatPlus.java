package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public class NoCheatPlus implements Hook
{
    private Plugin pl;
	
	@Override
	public void onEnable(AmberPlugin plugin)
	{
		this.pl = plugin.getServer().getPluginManager().getPlugin("NoCheatPlus");
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
		return PluginHook.NOCHEATPLUS;
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
		return "NoCheatPlus";
	}
}