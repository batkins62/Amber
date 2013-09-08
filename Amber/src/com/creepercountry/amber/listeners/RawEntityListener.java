package com.creepercountry.amber.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Engine;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.objects.user.User;
import com.creepercountry.amber.util.exception.NotRegisteredException;

public class RawEntityListener implements Listener
{
	/**
	 * The plugin instance
	 */
	private AmberPlugin plugin;

	/**
	 * constructor
	 * @param plugin
	 */
	public RawEntityListener(AmberPlugin instance)
	{
		plugin = instance;
	}
	
	@EventHandler // EventPriority.NORMAL by default
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{	
		if (event.isCancelled())
			return;

		if (!Engine.CHECKFLY)
			return;
		
		if ((event.getDamager() instanceof Player))
		{
			Player player = (Player)event.getDamager();
			
			if (player.isOp() || player.getGameMode().equals(GameMode.CREATIVE) || Vault.perms.has(player, "ct.fly.attack.override"))
				return;
			
			if (player.isFlying())
			{
				try
				{
					event.setCancelled(true);
					User user = plugin.getStorageManager().getUserHandler().getUser(player.getName());
					user.incrementFlyViolation();
					plugin.getConsumer().queueUserSave(user);
				}
				catch (NotRegisteredException e) {}
			}
		}
	}
}