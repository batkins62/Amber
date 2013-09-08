package com.creepercountry.amber.listeners.commands.general;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.command.CommandSender;

public class RaffleCommand extends BaseCommand
{
    public RaffleCommand()
    {
        name = "raffle";
        usage = "<buy|status|draw> [player]";
        minArgs = 1;
        maxArgs = 2;
        allowConsole = true;
    }

    @Override
    public boolean execute()
    {
    	try
    	{
    		if (isPlayer)
    			user = TownUniverse.getDataSource().getUser(sender.getName());
        	if (args.size() == 2)
        		user = TownUniverse.getDataSource().getUser(args.get(1));
        	if (!(args.size() == 2) && !isPlayer)
        		return false;
        	
        	String action = args.get(0).toLowerCase();
        	double price = MainConfigObject.raffle_price;
        	
        	if (action.equals("buy"))
        	{
        		EconomyResponse er = Vault.econ.withdrawPlayer(user.getName(), price);
        		if (er.transactionSuccess())
        			BukkitUtils.sendMessage(sender, Colors.LightBlue, plugin.getRaffleHandler().newTicket(user));
        	}
        	else if (action.equals("draw"))
        	{
        		plugin.getRaffleHandler().getTotalTickets()
        	}
        	else if (action.equals("status"))
        	{
        		
        	}
    	}
    	catch (NotRegisteredException nre)
    	{
    		BukkitUtils.sendMessage(sender, Colors.Red, nre.getMessage());
    		return true;
    	}
    	
    	return true;
    }
    
    @Override
    public void moreHelp()
    {
        BukkitUtils.sendMessage(sender, Colors.Rose, "Use the raffle command.");
    }

    @Override
    public boolean permission(CommandSender csender)
    {
    	return Vault.perms.has(csender, "ct.command.raffle");
    }

    @Override
    public BaseCommand newInstance()
    {
        return new RaffleCommand();
    }
}