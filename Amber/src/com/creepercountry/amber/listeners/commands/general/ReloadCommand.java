package com.creepercountry.amber.listeners.commands.general;

import org.bukkit.command.CommandSender;

public class ReloadCommand extends BaseCommand
{
    public ReloadCommand()
    {
        name = "reload";
        usage = "";
        minArgs = 0;
        maxArgs = 0;
    }

    @Override
    public boolean execute()
    {
    	// reload the config.yml
    	plugin.saveConfig();
		plugin.reloadConfig();
		sender.sendMessage("Reloaded config.yml");
		
		// save all the universe data
		TownUniverse.getDataSource().saveAll();
		sender.sendMessage("Saved all universe data");
		
		return true;
    }
    
    @Override
    public void moreHelp()
    {
        BukkitUtils.sendMessage(sender, Colors.Rose, "Reload Amber v" + CTInfo.FULL_VERSION.toString());
    }

    @Override
    public boolean permission(CommandSender csender)
    {
    	return Vault.perms.has(csender, "ct.command.reload");
    }

    @Override
    public BaseCommand newInstance()
    {
        return new ReloadCommand();
    }
}