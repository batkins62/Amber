package com.creepercountry.amber;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Engine
{
    /**
     * If Amber is currently enabled
     */
    public static boolean ENABLED = false;
    
    /**
     * If this plugin should use fly expansion
     */
    public static boolean CHECKFLY = false;
    
    /**
     * the pvp handler is running or not.
     */
    public static boolean PVPHANDLER = false;
    
    /**
     * debug toggle variable
     */
    public static boolean DEBUGMODE = false;
    
    /**
     * debugmode's dev mode
     */
    public static boolean DEVMODE = false;
    
    /**
     * BukkitTask id's for players with a repeating checkflytask
     */
    public static Map<Player, BukkitTask> flyid = new HashMap<Player, BukkitTask>();
    
    /**
     * BukkitTask id for the consumer
     */
    public static BukkitTask CONSUMER;
}
