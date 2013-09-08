package com.creepercountry.amber.api;

import java.util.Locale;
import java.util.TimeZone;

import com.creepercountry.amber.Engine;
import com.creepercountry.amber.hooks.DependancyManager;
import com.creepercountry.amber.listeners.commands.general.GenCommandExecutor;
import com.creepercountry.amber.objects.handler.StorageManager;
import com.creepercountry.amber.objects.handler.UserHandler;
import com.creepercountry.amber.objects.town.WorldGuardObject;
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
	 * @return the town command executor
	 */
	public TownCommandExecutor getTownCommandExecutor();
	
	/**
	 * @return the main command executor
	 */
	public PortalCommandExecutor getPortalCommandExecutor();
	
	/**
	 * @return the PreciousStones dependancy
	 */
	public DependancyManager getDependancyManager();

	/**
	 * @return the StorageManager
	 */
	public StorageManager getStorageManager();

	/**
	 * @return Config object
	 */
	public Config getConf();
	
	/**
	 * @return Consumer object
	 */
	public Consumer getConsumer();
	
	/**
	 * @return WorldGuardObject object
	 */
	public WorldGuardObject getWorldGuardObject();
}
