package com.creepercountry.amber.listeners.commands.general;

import org.bukkit.command.CommandSender;

public class StopWatchCommand extends BaseCommand
{
    public StopWatchCommand()
    {
        name = "sw";
        usage = "";
    }

    @Override
    public boolean execute()
    {
    	StopWatch sw = plugin.getStopWatch();
    	BukkitUtils.sendMessage(sender, Colors.LightBlue, sw.output());
		
        return true;
    }
    
    @Override
    public void moreHelp()
    {
        BukkitUtils.sendMessage(sender, Colors.Rose, "view StopWatch data");
        BukkitUtils.sendMessage(sender, Colors.Rose, "used for development or error purposes only!");
    }

    @Override
    public boolean permission(CommandSender csender)
    {
    	return Vault.perms.has(csender, "ct.command.debug");
    }

    @Override
    public BaseCommand newInstance()
    {
        return new StopWatchCommand();
    }
}
