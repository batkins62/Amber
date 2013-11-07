package com.creepercountry.amber.hooks;

import java.util.Collection;
import java.util.HashMap;

import com.creepercountry.amber.util.exception.NoPluginRegisteredException;

public class DependancyManager
{
	private final HashMap<PluginHook, Hook> hooks = new HashMap<PluginHook, Hook>();
	
	public DependancyManager()
	{
		this.hooks.clear();
	}
	
	/**
	 * Register an initialized hook object.
	 *
	 * @param hook
	 * Hook object
	 */
	public void registerHook(PluginHook name, Hook hook)
	{
		if (!checkHook(name))
			this.hooks.put(name, hook);
	}

	/**
	 * unregister a hook
	 *
	 * @param name
	 * Hook name to unregister
	 */
	public void unregisterHook(PluginHook name)
	{
		if (!checkHook(name))
			try { this.hooks.remove(getHook(name)); } catch (NoPluginRegisteredException e) {}
	}
	
	/**
	 * finds a registered hook by name
	 * 
	 * @param name
	 * @return true if registered, false if not found, or not registered
	 */
	private boolean checkHook(PluginHook name)
	{
		if (hooks.containsKey(name))
			return true;
		else
			return false;
	}
	
	/**
	 * gets a registered hook by name
	 * 
	 * @param name
	 * @return hook
	 * @throws NoPluginRegisteredException if no plugin is registered.
	 */
	public Hook getHook(PluginHook name) throws NoPluginRegisteredException
	{
		if (!checkHook(name))
			throw new NoPluginRegisteredException("Hook not registered: " + name);
		
		return this.hooks.get(name);
	}
	
	/**
	 * Get registered hooks!
	 * @return 
	 */
	public Collection<Hook> getRegistered()
	{
		return this.hooks.values();
	}
}
