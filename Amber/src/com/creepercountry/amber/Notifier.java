package com.creepercountry.amber;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;

public class Notifier
{
	/**
	 * AmberPlugin object
	 */
	private static AmberPlugin plugin;
	
	/**
     * Logger instance
     */
    private  static Logger logger;
    
    /**
     * set the plugin
     */
    protected void setPlugin(AmberPlugin instance)
    {
    	plugin = instance;
    	logger = Logger.getLogger(plugin.getName());
    }
    
    public enum NotifierType
    {
    	REGULAR, AS_AMBER;
    }
    
    public enum NotifierLevel
    {
    	DEBUG, HIGHEST, HIGH, NORMAL, LOW, LOWEST;
    }
    
    public static void sendMessage(NotifierType type, CommandSender sender, String msg_id)
    {
    	// TODO: msg_id should be from config, if no id, then msg
    	if (type.equals(NotifierType.REGULAR))
    	{
    		if (sender != null)
    		{
    			sender.sendMessage(msg_id);
    		}
    	}
    	else if (type.equals(NotifierType.AS_AMBER))
    	{
    		log(NotifierLevel.HIGHEST, "NOT SETUP!!!");
    	}
    }
    
    public static void log(NotifierLevel level, String msg)
    {
    	if (level.equals(NotifierLevel.DEBUG))
    	{
    		logger.info("[AMBER DEBUG] " + msg);
    	}
    	else
    	{
    		logger.info(msg);
    		//TODO actually use the level thing as intended.
    	}
    }
    
    
    public static void logException(Throwable throwbl)
    {
    	// TODO once amber works, have her output this, not the console, (or whatevs)
    	for (StackTraceElement el : throwbl.getStackTrace())
    		logger.log(Level.SEVERE, el.getClassName() + "." + el.getFileName() + " Method: " + el.getMethodName() + " Line: " + Integer.toString(el.getLineNumber()));
    }
}
