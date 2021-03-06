package com.creepercountry.amber.api;

import java.util.Locale;
import java.util.TimeZone;

import com.creepercountry.amber.Engine;
import com.creepercountry.amber.hooks.DependancyManager;
import com.creepercountry.amber.listeners.commands.general.GenCommandExecutor;
import com.creepercountry.amber.storage.Consumer;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.util.StopWatch;

public interface IAmber
{
	/**
	 * runs before {@link onEnable()}
	 */
	public void onLoad();
	
	/**
	 * runs on invocation.
	 */
	public void onEnable();
	
	/**
	 * runs on shutdown.
	 */
	public void onDisable();
	
	/**
     * Register all of the events used
     */
	public void registerEvents();
	
	/**
     * Register all of the commands used, point them to executor
     */
	public void registerCommands();
	
	/**
     * Check for required plugins to be loaded
     */
	public void pluginHooks();
	
	/**
     * checks if a player is online
     * 
     * @param playerName (string - use player.getName() if necessary)
     * @return false if player isnt online
     */
	public boolean isOnline(String playerName);
	
	/**
	 * @return the StopWatch object
	 */
	public StopWatch getStopWatch();
	
	/**
	 * @return the Locale
	 */
	public Locale getLocale();
	
	/**
	 * @return the timezone
	 */
	public TimeZone getTimeZone();
	
	/**
     * @return the engine instance
     */
    public Engine getEngine();
    
    /**
	 * @return the General command executor
	 */
	public GenCommandExecutor getGenCommandExecutor();
	
	/**
	 * @return the PreciousStones dependancy
	 */
	public DependancyManager getDependancyManager();

	/**
	 * @return Config object
	 */
	public Config getConf();
	
	/**
	 * @return Consumer object
	 */
	public Consumer getConsumer();
	
	/**
	 * registers permissions to this plugin
	 */
	public void registerPermissions();
}
