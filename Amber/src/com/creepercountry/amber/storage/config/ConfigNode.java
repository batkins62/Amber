package com.creepercountry.amber.storage.config;

public enum ConfigNode
{
	PLUGIN_VERSION ("version.plugin"),
	CONFIG_VERSION ("version.config"),
	PUNISH_FLY ("punish-fly"),
	CONFIG_TOKEN ("config-token"),
	RESIDENT_INACTIVE ("user-inactive"),
	SAVE_CONFIG ("save-config"),
	DECREMENT_FLY_VIOLATIONS ("decrement-fly-violations"),
	BLACKLIST_WATCHED ("watched-players-blacklist"),
	PSTONE_NAG_TRIGGER_WORDS ("nag-pstone-offender"),
	FLY_PUNISH_LOCATION ("fly-punish-location"),
	DEFAULT_PORTAL ("default-portal"),
	PLUGIN_RUNNING ("plugin-running"),
	DEBUG ("debug"),
	DEBUG_DEV ("debug-dev"),
	FIRST_RUN ("first-run"),
	VAULT_DEPEND ("dependencies.required.vault"),
	WORLDGUARD_DEPEND ("dependencies.required.worldguard"),
	NOCHEATPLUS_DEPEND ("dependencies.optional.nocheatplus"),
	ESSENTIALS_DEPEND ("dependencies.optional.essentials"),
	FLY_VIOLATION_CHECKS ("fly-violation-checks"),
	PLOT_COST ("plot.cost"),
	TOWN_NOTIFICATIONS ("town-notifications"),
	RAFFLE_PRICE ("raffle-price"),
	QUEUE_WARN_SIZE ("consumer.queue-warn-size"),
	CONSUMER_FORCE_TO_PROCESS ("consumer.consumer-force-process"),
	CONSUMER_TIME_PER_RUN ("consumer.time-per-run");
	
	private final String path;
	ConfigNode(String path)
	{
		this.path = path;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getWorldLocationPath()
	{
		return path + ".world";
	}
	
	public String getYawLocationPath()
	{
		return path + ".yaw";
	}
	
	public String getPitchLocationPath()
	{
		return path + ".pitch";
	}
	
	@Override
	public String toString()
	{
		return path;
	}
}