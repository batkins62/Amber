package com.creepercountry.amber.storage.config;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;

import com.creepercountry.amber.AmberPlugin;

public final class Config
{
	public static String plugin_version, default_portal_world, fly_punish_world;
	public static int config_version, punish_fly, queueWarningSize, forceToProccessAtLeast;
	public static long config_token, user_inactive, save_config, decrement_fly_violations, timePerRun;
	public static List<String> blacklist_commands, pstone_nag_words, town_notifications;
	public static Location fly_punish_location, default_portal;
	public static boolean plugin_running, debug, first_run, vault_depend, worldguard_depend,
					nocheatplus_depend, essentials_depend, fly_violation_checks, debug_dev;
	public static double plotcost, raffle_price;
	public static final Map<String, World> worlds = new HashMap<String, World>();
	
    private final AmberPlugin plugin;
    private File configFile;
    public static boolean ENABLED = false;
    
    public Config(final AmberPlugin instance)
    {
    	this.plugin = instance;
    	if (load())
    		ENABLED = true;
    }
    
    public File getFile()
    {
    	return configFile;
    }
    
    public boolean load()
    {
    	// Grab config, copy just the defaults
    	FileConfiguration config = plugin.getConfig();
    	configFile = new File(plugin.getDataFolder(), "config.yml");
        config.options().copyDefaults(true);
        
        // Load the World objects
    	for (final World world : Bukkit.getWorlds())
    		worlds.put(world.getName(), world);
        
        // Load String variables
        plugin_version = config.getString(ConfigNode.PLUGIN_VERSION.getPath(), "1.1.0");
        // Load int variables
        config_version = config.getInt(ConfigNode.CONFIG_VERSION.getPath(), 1);
        punish_fly = config.getInt(ConfigNode.PUNISH_FLY.getPath(), 5);
        queueWarningSize = config.getInt(ConfigNode.QUEUE_WARN_SIZE.getPath(), 10);
        forceToProccessAtLeast = config.getInt(ConfigNode.CONSUMER_FORCE_TO_PROCESS.getPath(), 1);
    	// Load long variables
        config_token = config.getLong(ConfigNode.CONFIG_TOKEN.getPath(), 0L);
        user_inactive = config.getLong(ConfigNode.RESIDENT_INACTIVE.getPath(), 45L);
        save_config = config.getLong(ConfigNode.SAVE_CONFIG.getPath(), 3600L);
        decrement_fly_violations = config.getLong(ConfigNode.DECREMENT_FLY_VIOLATIONS.getPath(), 3600L);
        timePerRun = config.getLong(ConfigNode.CONSUMER_TIME_PER_RUN.getPath(), 200L);
        // Load List<String> variables
        blacklist_commands = config.getStringList(ConfigNode.BLACKLIST_WATCHED.getPath());
        pstone_nag_words = config.getStringList(ConfigNode.PSTONE_NAG_TRIGGER_WORDS.getPath());
        town_notifications = config.getStringList(ConfigNode.TOWN_NOTIFICATIONS.getPath());
        // Load Vector variables
        fly_punish_location = toLocation(config.getVector(ConfigNode.FLY_PUNISH_LOCATION.getPath(), new Vector(0,64,0)),
        		config.getString(ConfigNode.FLY_PUNISH_LOCATION.getWorldLocationPath(), "world"),
        		config.getString(ConfigNode.FLY_PUNISH_LOCATION.getYawLocationPath()),
        		config.getString(ConfigNode.FLY_PUNISH_LOCATION.getPitchLocationPath()));
        default_portal = toLocation(config.getVector(ConfigNode.DEFAULT_PORTAL.getPath(), new Vector(0,64,0)),
        		config.getString(ConfigNode.DEFAULT_PORTAL.getWorldLocationPath()),
        		config.getString(ConfigNode.DEFAULT_PORTAL.getYawLocationPath()),
        		config.getString(ConfigNode.DEFAULT_PORTAL.getPitchLocationPath()));
     	// Load boolean variables
        plugin_running = config.getBoolean(ConfigNode.PLUGIN_RUNNING.getPath(), false);
        debug = config.getBoolean(ConfigNode.DEBUG.getPath(), false);
        debug_dev = config.getBoolean(ConfigNode.DEBUG_DEV.getPath(), false);
        first_run = config.getBoolean(ConfigNode.FIRST_RUN.getPath(), true);
        vault_depend = config.getBoolean(ConfigNode.VAULT_DEPEND.getPath(), false);
        worldguard_depend = config.getBoolean(ConfigNode.WORLDGUARD_DEPEND.getPath(), false);
        nocheatplus_depend = config.getBoolean(ConfigNode.NOCHEATPLUS_DEPEND.getPath(), false);
        essentials_depend = config.getBoolean(ConfigNode.ESSENTIALS_DEPEND.getPath(), false);
        fly_violation_checks = config.getBoolean(ConfigNode.FLY_VIOLATION_CHECKS.getPath(), true);
        // Load double variables
        plotcost = config.getDouble(ConfigNode.PLOT_COST.getPath(), 100.00d);
        raffle_price = config.getDouble(ConfigNode.RAFFLE_PRICE.getPath(), 250.00d);
        
    	return true;
    }
    
    private Location toLocation(Vector vector, String world, String yaw, String pitch)
    {
    	return vector.toLocation(worlds.get(world), Float.parseFloat(yaw), Float.parseFloat(pitch));
    }
}
