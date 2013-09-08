package com.creepercountry.amber.listeners.commands.general;

import org.bukkit.command.CommandSender;

import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.util.Colors;

public class AddWordCommand extends BaseCommand
{
    public AddWordCommand()
    {
        name = "word";
        usage = "<ignore|nag> <word>";
        minArgs = 2;
    }

    @Override
    public boolean execute()
    {
    	
		
        return true;
    }
    
    @Override
    public void moreHelp()
    {
    	Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Gold + "Add a word to the lists of special chat words");
    	Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Rose + "nag: words that will trigger pstone offenders nag.");
    	Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Rose + "ignore: words that wont log for watched players");
    }

    @Override
    public boolean permission(CommandSender csender)
    {
    	return Vault.perms.has(csender, "ct.command.word");
    }

    @Override
    public BaseCommand newInstance()
    {
        return new AddWordCommand();
    }
}