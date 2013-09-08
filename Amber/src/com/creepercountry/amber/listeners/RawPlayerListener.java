package com.creepercountry.amber.listeners;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitTask;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Engine;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.hooks.WorldGuard;
import com.creepercountry.amber.objects.handler.StorageManager;
import com.creepercountry.amber.objects.handler.UserHandler;
import com.creepercountry.amber.objects.town.Portal;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.util.Colors;
import com.creepercountry.amber.util.StopWatch;
import com.creepercountry.amber.util.exception.AlreadyRegisteredException;
import com.creepercountry.amber.util.exception.NotRegisteredException;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RawPlayerListener implements Listener
{
	/**
     * The plugin instance
     */
	private AmberPlugin plugin;
	
	/**
	 * The StopWatch object
	 */
	private StopWatch sw;
	
	/**
	 * the UserHandler object
	 */
	private UserHandler uh;
	
	/**
	 * The StorageManager object
	 */
	private StorageManager sm;

	/**
	 * constructor
	 * @param plugin
	 */
	public RawPlayerListener(AmberPlugin instance)
    {
		plugin = instance;
		sw = instance.getStopWatch();
		uh = instance.getStorageManager().getUserHandler();
		sm = instance.getStorageManager();
    }

	@EventHandler // EventPriority.NORMAL by default
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (!Engine.ENABLED)
		{
			event.getPlayer().kickPlayer("Error x63CTPL: count to 10 then try again.");
		}

		if (Engine.DEVMODE)
		{
			// TODO: move this to the UserHandler()
			Notifier.sendMessage(NotifierType.REGULAR, event.getPlayer(), Colors.Gold + "Development mode is active. please note that the server may reboot " +
					"randomly and frequently, while we work to bring new content, updates, and general programming to this server. " +
					"We do not currently have an ETA on how long this will take us, but strive to minimize any downtime.");
		}
		
		uh.onLogin(event.getPlayer());
	}

	@EventHandler // EventPriority.NORMAL by default
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		uh.onLogout(event.getPlayer());
	}
	
	@EventHandler // EventPriority.NORMAL: by default
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{
		/*
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType().equals(Material.SIGN) || event.getClickedBlock().getType().equals(Material.WALL_SIGN) || event.getClickedBlock().getType().equals(Material.SIGN_POST)))
		{
			Sign sign = (Sign) event.getClickedBlock().getState();
			if (sign.getLine(0).equalsIgnoreCase(Colors.Navy + "[FOR SALE]"))
			{
				// check for perms
				if (!Vault.perms.has(event.getPlayer(), "ct.plot.use"))
				{
					BukkitUtils.sendMessage(event.getPlayer(), Colors.Red, "You do not have permission to use plots!");
					return;
				}
				
				try
				{
					User user = TownUniverse.getDataSource().getUser(event.getPlayer().getName());
					Town town = user.getTown();
					
					if (!user.hasChunk())
						BukkitUtils.sendMessage(event.getPlayer(), Colors.Red, "Only one plot may be owned at a time!");
					
					//TODO: output message telling player 1 plot per user.
					if (town.hasChunk(event.getClickedBlock().getChunk()) && !user.hasChunk())
					{
						EconomyResponse e = Vault.econ.withdrawPlayer(event.getPlayer().getName(), Double.parseDouble(sign.getLine(1).replace("$", "")));
						if (e.transactionSuccess())
						{
							BukkitUtils.sendMessage(event.getPlayer(), Colors.Green, String.format("You just had %s removed from your account.", e.amount));
							user.setChunk(event.getClickedBlock().getChunk());
						}
						else
						{
							BukkitUtils.sendMessage(event.getPlayer(), Colors.Red, e.errorMessage);
							return;
						}
						
						// change the sign
						String name = event.getPlayer().getName();
						int len = name.length();
						
						sign.setLine(0, Colors.Red + "[SOLD]");
						sign.setLine(1, name.substring(0, (len > 15) ? 15 : len));
						sign.setLine(2, "|---------");
						sign.setLine(3, Integer.toString(event.getClickedBlock().getChunk().getX()) + "," + Integer.toString(event.getClickedBlock().getChunk().getZ()));
						
						BukkitUtils.sendMessage(event.getPlayer(), Colors.Gold, "You now own this chunk of land! :)");
						TownUniverse.getDataSource().saveUser(user);
					}
				}
				catch (NotRegisteredException nre)
				{
					BukkitUtils.sendMessage(event.getPlayer(), Colors.Red, nre.getMessage());
					return;
				}
			}
			else if (sign.getLine(0).equalsIgnoreCase("[SOLD]"))
			{
				// check for perms
				if (!Vault.perms.has(event.getPlayer(), "ct.plot.use"))
				{
					BukkitUtils.sendMessage(event.getPlayer(), Colors.Red, "You do not have permission to use plots!");
					return; 
				}
				
				Town town = null;
				Chunk chunk = null;
				User user = null;
				User clicker = null;
				try { clicker = TownUniverse.getDataSource().getUser(event.getPlayer().getName()); } catch (NotRegisteredException nre) { return; }
				
				for (Town t : TownUniverse.getDataSource().getTowns())
				{
					for (Chunk c : town.getChunks())
					{
						if (c.equals(sign.getChunk()))
						{
							chunk = c;
							town = t;
							
							for (User r : t.getUsers())
							{
								if (r.getChunk().equals(c))
								{
									user = r;
								}
							}
						}
					}
				}
				
				if ((town == null) || (chunk == null) || (user == null) || (clicker == null))
					return;
				
				if (town.isOwner(clicker))
				{
					// TODO
				}
				
				if (user.equals(clicker))
				{
					// TODO
				}
			}
		}
		*/
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPortalEvent(PlayerPortalEvent event)
	{
		if (!event.getCause().equals(TeleportCause.NETHER_PORTAL))
		{
			return;
		}
		
		if (event.isCancelled())
		{
			return;
		}
		
		long start = System.nanoTime();
		
		event.setCancelled(true);
		
		try
		{			
			for (String name : sm.getPoralHandler().getPortalKeys())
			{
				Portal por = sm.getPoralHandler().getPortal(name);
				
				WorldGuardPlugin wg = (WorldGuardPlugin) plugin.getDependancyManager().getHook("WorldGuard").getPlugin();
				ProtectedRegion pr = wg.getRegionManager(event.getPlayer().getWorld()).getRegion(name);
				int x = Long.valueOf(Math.round(event.getPlayer().getLocation().getX())).intValue();
				int y = Long.valueOf(Math.round(event.getPlayer().getLocation().getY())).intValue();
				int z = Long.valueOf(Math.round(event.getPlayer().getLocation().getZ())).intValue();
				if ((name != null) && (pr != null))
				{
					if (pr.contains(x, y, z))
					{
						if (por.isEnabled())
								event.getPlayer().teleport(por.getSendLocation());
						else
							Notifier.sendMessage(NotifierType.REGULAR, event.getPlayer(), Colors.Red + "This portal has been disabled.");
							
						// log to StopWatch
				        sw.setLoad("onPlayerPortalEvent", System.nanoTime() - start);
							
						return;
					}
				}
			}
		}
		catch (NotRegisteredException nre)
		{
			Notifier.log(NotifierLevel.HIGHEST, "A key for the Map didnt point to a value.");
	        sw.setLoad("onPlayerPortalEvent", System.nanoTime() - start);
			return;
		}
		
		// teleport user to default spot.
		event.getPlayer().teleport(Config.default_portal);
		
		// log to StopWatch
        sw.setLoad("onPlayerPortalEvent", System.nanoTime() - start);
	}
}
