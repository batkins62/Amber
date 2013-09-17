package com.creepercountry.amber.util.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.hooks.WorldGuard;
import com.creepercountry.amber.storage.config.Config;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class CheckFlyTask extends BukkitRunnable
{
	private final WorldGuardPlugin plugin = (WorldGuardPlugin) WorldGuard.pl;
	private final Player plr;
	private final Location loc;
	 
    public CheckFlyTask(Player player, Location location)
    {
        this.plr = player;
        this.loc = location;
    }
    
	@Override
	public void run()
	{
		if (!plr.isFlying())
			return;
		
		if (Vault.perms.has(plr, "ct.bypass.fly"))
			return;

		if (Config.nofly_worlds.contains(plr.getWorld().getName()) ||
				!plugin.canBuild(plr, plr.getLocation().getBlock().getRelative(0, -1, 0)))
			deny();
	}

	private void deny()
	{
		//TODO: this needs functionality:
		// 1. set player fly to cancel
		// 2. log & display fly notification
		// 3. suspend fly?
		// 4. increment user fly VL
		// 5. update & check new VL and proceed where needed
	}
}
