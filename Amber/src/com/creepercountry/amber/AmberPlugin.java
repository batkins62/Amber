package com.creepercountry.amber;

import java.util.Locale;
import java.util.TimeZone;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.api.IAmber;
import com.creepercountry.amber.hooks.DependancyManager;
import com.creepercountry.amber.hooks.Essentials;
import com.creepercountry.amber.hooks.Hook;
import com.creepercountry.amber.hooks.NoCheatPlus;
import com.creepercountry.amber.hooks.PreciousStones;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.hooks.WorldGuard;
import com.creepercountry.amber.listeners.commands.general.GenCommandExecutor;
import com.creepercountry.amber.storage.Consumer;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.util.StopWatch;
import com.creepercountry.amber.util.TickUtils;
import com.creepercountry.amber.util.TickUtils.TickUnit;
import com.creepercountry.amber.util.Version;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AmberPlugin extends JavaPlugin implements IAmber
{
	/**
     * Our Plugin instance
     */
    private static AmberPlugin instance;
    
    /**
     * The Consumer object
     */
    private Consumer consumer;
    
	/**
	 * The plugins Locale
	 */
	private Locale locale;
	
	/**
	 * The plugins TimeZone
	 */
	private TimeZone timezone;
	
	/**
	 * The config object
	 */
	private Config config;
	
	/**
	 * Notifier instance
	 */
	private Notifier notifier;
    
	/**
     * The StopWatch object
     */
    private StopWatch sw;
    
    /**
     * The DendancyManager Object
     */
    private DependancyManager dm;
    
    /**
     * The Engine variable
     */
    private Engine engine;
    
    /**
     * The listeners
     */
    //private RawPlayerListener playerListener;
    
    /**
     * The Executor
     */
    private GenCommandExecutor genCommandExecutor;
    
    @Override
    public void onLoad()
    {
    	// Get the current time for StopWatch
    	long start = System.nanoTime();
    	
    	// set notifier to plugin name
    	notifier.setPlugin(this);
    	instance = this;
    	
    	// Start the StopWatch
		sw = new StopWatch(this.getDescription().getName());
		
		// output to StopWatch
		sw.setLoadNoChirp("onEnable", System.nanoTime() - start, false);
    }
    
	@Override
    public void onEnable()
	{
		// Get the current time for StopWatch
		long start = System.nanoTime();
		
		// create the locale & TimeZone
		locale = new Locale("en", "US");
		timezone = TimeZone.getTimeZone("America/Phoenix");
		
		// Load config
		config = new Config(this);
		
		// hook into depends
		pluginHooks();

		// register the listeners & executors
		try
		{
			registerEvents();
			registerCommands();
		}
		catch (NoSuchFieldError e)
		{
			Notifier.logException(e);
		}

		// set version, get version, and display
		AmberInfo.setVersion(this.getDescription().getVersion());
		Version version = AmberInfo.FULL_VERSION;
		Notifier.log(NotifierLevel.HIGHEST, "At version: " + version.toString());
		
		// Start the engine and consumer
		Engine.ENABLED = true;
		consumer = new Consumer(this);
		Engine.CONSUMER = consumer.runTaskTimerAsynchronously(instance, TickUtils.convert(TickUnit.MINUTE, 2), TickUtils.convert(TickUnit.MINUTE, 2));
		
		// Re login anyone online. (In case of plugin reloading)
		for (Player player : getServer().getOnlinePlayers())
		{
			// TODO: run onLogin for every player
			//sm.getUserHandler().onLogin(player);
		}
				
		// output to StopWatch
		sw.setLoadNoChirp("onEnable", System.nanoTime() - start, false);
	}
	
	@Override
    public void onDisable()
	{
		// Get the current time for StopWatch
		long start = System.nanoTime();
				
		// Disable the plugin
		Engine.ENABLED = false;
		
		// logout anyone online. (In case of plugin reloading)
		for (Player player : getServer().getOnlinePlayers())
		{
			// TODO: run onLogout for every player
			//sm.getUserHandler().onLogout(player);
		}
		
		// unregister hooks
        for (Hook hook : dm.getRegistered())
        {
        	hook.onDisable(this);
        	dm.unregisterHook(hook.getName());
        }
		
		// Save our storage files
		while (consumer.getQueueSize() > 0)
		{
			Notifier.log(NotifierLevel.NORMAL, "Remaining queue size: " + Integer.toString(consumer.getQueueSize()));
			consumer.run();
		}
		
		// cancel ALL tasks we created
        getServer().getScheduler().cancelTasks(this);
        
        // unregister our events
        HandlerList.unregisterAll(this);
		
        // Finalize the StopWatch then output data
        sw.setLoad("onDisable", System.nanoTime() - start);
        Notifier.log(NotifierLevel.LOW, sw.output());
	}
	
	@Override
	public void registerEvents()
	{
		/*
    	// Get the current time for StopWatch
    	long start = System.nanoTime();
    			
        // Shared Objects
        playerListener = new RawPlayerListener(this);
        
        // register event listeners
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);

        
        // debug what we registered
        for (RegisteredListener listener : HandlerList.getRegisteredListeners(instance))
        	Notifier.log(NotifierLevel.DEBUG, "Listener: " + listener.getListener().getClass().getName() +
        			", Priority: " + listener.getPriority().toString());
        
        // log to StopWatch
        sw.setLoadNoChirp("registerEvents", (System.nanoTime() - start));
        */
	}
	
	@Override
	public void registerCommands()
	{
    	// Get the current time for StopWatch
    	long start = System.nanoTime();
    	
    	// Shared Ojects
    	genCommandExecutor = new GenCommandExecutor();
        
        // point commands to executor        
        getCommand("cc").setExecutor(genCommandExecutor);
        
        // log to StopWatch
        sw.setLoadNoChirp("registerCommands", (System.nanoTime() - start));
	}
	
	@Override
	public void pluginHooks()
	{
    	// Get the current time for StopWatch
    	long start = System.nanoTime();
    	
    	// Register the dependency
        dm = new DependancyManager();
        PluginManager pm = getServer().getPluginManager();
        if (pm.isPluginEnabled("NoCheatPlus"))
        	dm.registerHook("NoCheatPlus", new NoCheatPlus());
        if (pm.isPluginEnabled("Essentials"))
        	dm.registerHook("Essentials", new Essentials());
        if (pm.isPluginEnabled("WorldGuard"))
        	dm.registerHook("WorldGuard", new WorldGuard());
        if (pm.isPluginEnabled("Vault"))
        	dm.registerHook("Vault", new Vault());
        if (pm.isPluginEnabled("PreciousStones"))
        	dm.registerHook("PreciousStones", new PreciousStones());
        
        // Enable the dependencies
        for (Hook hook : dm.getRegistered())
        	hook.onEnable(this);
        
        // log to StopWatch
        sw.setLoadNoChirp("pluginHooks", (System.nanoTime() - start));
	}
	
	@Override
	public boolean isOnline(String playerName)
	{
		for (Player player : getServer().getOnlinePlayers())
			if (player.getName().equalsIgnoreCase(playerName))
				return true;

		return false;
	}
	
	@Override
	public void saveConfig()
	{
		return;
	}
	
	@Override
	public Consumer getConsumer()
	{
		return consumer;
	}
	
	@Override
	public DependancyManager getDependancyManager()
	{
		return dm;
	}
	
	@Override
	public GenCommandExecutor getGenCommandExecutor()
	{
		return genCommandExecutor;
	}
	
	@Override
	public StopWatch getStopWatch()
	{
		return sw;
	}
	
    @Override
    public Engine getEngine()
    {
    	return engine;
    }
	
    @Override
	public Locale getLocale()
	{
		return locale;
	}
	
	@Override
	public TimeZone getTimeZone()
	{
		return timezone;
	}
	
	@Override
	public Config getConf()
	{
		return config;
	}
	
	/**
	 * @return the current plugin instance
	 */
    public static AmberPlugin getInstance()
    {
        return instance;
    }
}
