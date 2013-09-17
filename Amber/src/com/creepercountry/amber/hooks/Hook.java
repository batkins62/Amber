package com.creepercountry.amber.hooks;

import org.bukkit.plugin.Plugin;

import com.creepercountry.amber.AmberPlugin;

public interface Hook
{
	public void onEnable(AmberPlugin plugin);
	
	public void onDisable(AmberPlugin plugin);
	
	public int getUniqueID();
	
	public PluginHook getName();
	
	public String toString();
	
	public boolean isEnabled();
	
	public Plugin getPlugin();
}
