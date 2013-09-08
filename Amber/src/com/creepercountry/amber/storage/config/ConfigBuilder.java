package com.creepercountry.amber.storage.config;

import java.util.LinkedHashMap;

public final class ConfigBuilder
{
	private static LinkedHashMap<Short, String> newConfig = new LinkedHashMap<Short, String>();
	private static Short key = 0;
	
	public static LinkedHashMap<Short, String> buildConfig()
	{
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                   Plugin Info                        | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "# The various versions for this plugin & components.");
		newConfig.put(key++, "version:");
		newConfig.put(key++, "  # This plugin's last recorded version.");
		newConfig.put(key++, "  plugin: " + formatValue(Config.plugin_version));
		newConfig.put(key++, "  # This configuration's last recorded version.");
		newConfig.put(key++, "  config: " + formatValue(Config.config_version));
		newConfig.put(key++, "");
		newConfig.put(key++, "# This token only shows when the plugin is disabled.");
		newConfig.put(key++, "# Entering this token when the plugin is enabled will");
		newConfig.put(key++, "# save changes made. Without this password, nothing saves.");
		newConfig.put(key++, "config-token: " + formatValue(Config.config_token));
		newConfig.put(key++, "");
		newConfig.put(key++, "# This will be true when the plugin is running");
		newConfig.put(key++, "plugin-running: " + formatValue(Config.plugin_running));
		newConfig.put(key++, "");
		newConfig.put(key++, "# Toggle whether the plugin will verbosely log.");
		newConfig.put(key++, "debug: " + formatValue(Config.debug));
		newConfig.put(key++, "debug-dev: " + formatValue(Config.debug_dev));
		newConfig.put(key++, "");
		newConfig.put(key++, "# As long as this stays set, and doesnt get removed or changed,");
		newConfig.put(key++, "# this plugin will not force admins to configure.");
		newConfig.put(key++, "first-run: " + formatValue(Config.first_run));
		newConfig.put(key++, "");
		newConfig.put(key++, "# Virtual config write frequency in seconds. This adjust how");
		newConfig.put(key++, "# often the virtual config saves its data to disk. if");
		newConfig.put(key++, "# the configuration has not been written to, config will not");
		newConfig.put(key++, "# save.");
		newConfig.put(key++, "save-config: " + formatValue(Config.save_config));
		newConfig.put(key++, "");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                     Dependencies                     | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "# These values set themselves. if the");
		newConfig.put(key++, "# required plugins do not return true, plugin will not load.");
		newConfig.put(key++, "# If the optional plugins do not return true, some of the");
		newConfig.put(key++, "# plugin features will not work.");
		newConfig.put(key++, "dependencies:");
		newConfig.put(key++, "  required:");
		newConfig.put(key++, "    vault: " + formatValue(Config.vault_depend));
		newConfig.put(key++, "    worldguard: " + formatValue(Config.worldguard_depend));
		newConfig.put(key++, "  optional:");
		newConfig.put(key++, "    essentials: " + formatValue(Config.essentials_depend));
		newConfig.put(key++, "    nocheatplus: " + formatValue(Config.nocheatplus_depend));
		newConfig.put(key++, "");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                         Portals                      | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "# This is the default location of where a portal not linked");
		newConfig.put(key++, "# will send a player. this most commonly is called the 'hub'");
		newConfig.put(key++, "default-portal:");
		newConfig.put(key++, "  ==: Vector");
		newConfig.put(key++, "  x: " + formatValue(Config.default_portal.getX()));
		newConfig.put(key++, "  y: " + formatValue(Config.default_portal.getY()));
		newConfig.put(key++, "  z: " + formatValue(Config.default_portal.getZ()));
		newConfig.put(key++, "  world: " + formatValue(Config.default_portal.getWorld().getName()));
		newConfig.put(key++, "  yaw: " + formatValue(Config.default_portal.getYaw()));
		newConfig.put(key++, "  pitch: " + formatValue(Config.default_portal.getPitch()));
		newConfig.put(key++, "");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                  Security/ Anti-Cheat                | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "# Toggles whether or not we will even check for fly violations.");
		newConfig.put(key++, "fly-violation-checks: " + formatValue(Config.fly_violation_checks));
		newConfig.put(key++, "");
		newConfig.put(key++, "# This is the location the player will be sent to when they");
		newConfig.put(key++, "# get a violation level higher then the punish-level.");
		newConfig.put(key++, "fly-punish-location:");
		newConfig.put(key++, "  ==: Vector");
		newConfig.put(key++, "  x: " + formatValue(Config.fly_punish_location.getX()));
		newConfig.put(key++, "  y: " + formatValue(Config.fly_punish_location.getY()));
		newConfig.put(key++, "  z: " + formatValue(Config.fly_punish_location.getZ()));
		newConfig.put(key++, "  world: " + formatValue(Config.fly_punish_location.getWorld().getName()));
		newConfig.put(key++, "  yaw: " + formatValue(Config.fly_punish_location.getYaw()));
		newConfig.put(key++, "  pitch: " + formatValue(Config.fly_punish_location.getPitch()));
		newConfig.put(key++, "");
		newConfig.put(key++, "# The violation level that punishments will be dealt.");
		newConfig.put(key++, "punish-fly:" + formatValue(Config.punish_fly));
		newConfig.put(key++, "");
		newConfig.put(key++, "# How often in seconds violation levels will decrement.");
		newConfig.put(key++, "decrement-fly-violations: " + formatValue(Config.decrement_fly_violations));
		newConfig.put(key++, "");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                       Consumer                       | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "consumer:");
		newConfig.put(key++, "  # How many objects queued to be saved before warning occurs.");
		newConfig.put(key++, "  queue-warn-size: " + formatValue(Config.queueWarningSize));
		newConfig.put(key++, "");
		newConfig.put(key++, "  # Consumer will force this many object saves per run.");
		newConfig.put(key++, "  consumer-force-process: " + formatValue(Config.forceToProccessAtLeast));
		newConfig.put(key++, "");
		newConfig.put(key++, "  # Consumer will try to save objects this long per run.");
		newConfig.put(key++, "  time-per-run: " + formatValue(Config.timePerRun));
		newConfig.put(key++, "");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "# |                   Residents                          | #");
		newConfig.put(key++, "# +------------------------------------------------------+ #");
		newConfig.put(key++, "############################################################");
		newConfig.put(key++, "");
		newConfig.put(key++, "# List of words that will not log when a watched player says");
		newConfig.put(key++, "# it. DO NOT FORGET THE /");
		newConfig.put(key++, "watched-players-blacklist:");
		for (String value : Config.blacklist_commands)
			newConfig.put(key++, "  - " + formatValue(value));
		newConfig.put(key++, "");
		newConfig.put(key++, "# List of words that will trigger precious stone nagger");
		newConfig.put(key++, "nag-pstone-offender:");
		for (String value : Config.pstone_nag_words)
			newConfig.put(key++, "  - " + formatValue(value));
		newConfig.put(key++, "");
		newConfig.put(key++, "# The amount of milliseconds that a resident will become");
		newConfig.put(key++, "# \"inactive\", unless said player is the obvious online.");
		newConfig.put(key++, "resident-inactive: " + formatValue(Config.user_inactive));
		newConfig.put(key++, "");
		newConfig.put(key++, "# Default cost of a plot in towns.");
		newConfig.put(key++, "plot:");
		newConfig.put(key++, "  cost: " + formatValue(Config.plotcost));
		newConfig.put(key++, "");
		newConfig.put(key++, "# People that will be messaged on major town events.");
		newConfig.put(key++, "# \'OP\' will message all server operators.");
		newConfig.put(key++, "# \'g:\' prefixed to a group name will specify group.");
		newConfig.put(key++, "# \'!\' prefixed to a name will disclude them from inheritance.");
		newConfig.put(key++, "# \'ONLINE\' will message everyone online.");
		newConfig.put(key++, "town-notification:");
		for (String value : Config.town_notifications)
			newConfig.put(key++, "  - " + formatValue(value));
		
		return newConfig;
	}
	
	public static int getSize()
	{
		return newConfig.size();
	}
	
	private static  String formatValue(Object value)
	{
		String out = "";
		if (value instanceof Integer)
			Integer.toString((Integer) value);
		if (value instanceof Long)
			Long.toString((Long) value);
		if (value instanceof Boolean)
			Boolean.toString((Boolean) value);	
		if (value instanceof String)
			out = (String) value;
		if (value.equals(null))
			out = "";
		return "\'" + out + "\'";
	}
}
