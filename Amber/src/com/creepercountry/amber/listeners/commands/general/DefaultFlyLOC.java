package com.creepercountry.amber.listeners.commands.general;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.util.Colors;

public class DefaultFlyLOC extends BaseCommand
{
    public DefaultFlyLOC()
    {
        name = "default";
        usage = "";
        minArgs = 0;
        maxArgs = 0;
        allowConsole = false;
    }

    @Override
    public boolean execute()
    {
    	final Location loc = player.getLocation();
    	
    	Notifier.sendMessage(NotifierType.REGULAR, player, Colors.Gold + "Setting default location for fly offenders...");
    	Config.fly_punish_location = loc;
    	plugin.getConsumer().queueConfigSave();
    	plugin.getConf().load();
    	Notifier.sendMessage(NotifierType.REGULAR, player, Colors.Green + "Successfully set value.");
    	
		
		return true;
    }
    
    @Override
    public void moreHelp()
    {
    	Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Rose + "default location for fly. use standing in the tp location");
    }

    @Override
    public boolean permission(CommandSender csender)
    {
    	return Vault.perms.has(csender, "ct.command.defaultflyloc");
    }

    @Override
    public BaseCommand newInstance()
    {
        return new DefaultFlyLOC();
    }
}