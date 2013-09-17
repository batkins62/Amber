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
	 * @param name
	 * Name of the hook
	 * @param hook
	 * Hook object
	 * @return the registered hook or null if no hook by the given name was registered
	 */
	public Hook registerHook(PluginHook name)
	{
		if (name.equals(PluginHook.ESSENTIALS))
			this.hooks.put(name, new Essentials());
		if (name.equals(PluginHook.NOCHEATPLUS))
			this.hooks.put(name, new NoCheatPlus());
		if (name.equals(PluginHook.PRECIOUSSTONES))
			this.hooks.put(name, new PreciousStones());
		if (name.equals(PluginHook.VAULT))
			this.hooks.put(name, new Vault());
		if (name.equals(PluginHook.WORLDEDIT))
			this.hooks.put(name, new WorldEdit());
		if (name.equals(PluginHook.WORLDGUARD))
			this.hooks.put(name, new WorldGuard());
		
		return this.hooks.get(name);
	}

	/**
	 * unregister a hook
	 *
	 * @param name
	 * Hook name to unregister
	 * @return the unregistered hook or null if no hook by the given name was registered
	 */
	public Hook unregisterHook(PluginHook name)
	{
		final Hook ret = this.hooks.get(name);
		this.hooks.remove(name);
		return ret;
	}
	
	/**
	 * gets a registered hook by name
	 * 
	 * @param name
	 * @return hook
	 * @throws NotRegisteredException if not registered
	 */
	private Hook checkHook(PluginHook name) throws NoPluginRegisteredException
	{
		if (hooks.containsKey(name))
			return hooks.get(name);
		else
			throw new NoPluginRegisteredException("Hook not registered: " + name);
	}
	
	/**
	 * gets a registered hook by name
	 * 
	 * @param name
	 * @return hook
	 */
	public Hook getHook(PluginHook name)
	{
		try
		{
			return checkHook(name);
		}
		catch (NoPluginRegisteredException npre)
		{
			return registerHook(name);
		}
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
