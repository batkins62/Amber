package com.creepercountry.amber.listeners.commands.general;

import org.bukkit.command.CommandSender;

import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierType;
import com.creepercountry.amber.hooks.Vault;
import com.creepercountry.amber.util.Colors;

import java.util.List;

public class HelpCommand extends BaseCommand
{
	public HelpCommand()
	{
		name = "help";
		maxArgs = 1;
		minArgs = 0;
		usage = "<- lists all town commands";
	}

	@Override
	public boolean execute()
	{
		if (args.isEmpty()) // General help
		{
			Notifier.sendMessage(NotifierType.REGULAR, sender, cleanTitle(Colors.White + "General Help", "="));
			Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.White + "Type /cc help <command> for more info on that command");
			List<BaseCommand> var = plugin.getGenCommandExecutor().getCommands();
			for (BaseCommand cmd : var.toArray(new BaseCommand[var.size()]))
			{ 
				if (cmd.permission(sender))
				{
					Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Green + "- " + "/" + usedCommand + " " + cmd.name + Colors.Green + " " + cmd.usage);
				}
			}
		} // Command-specific help
		else {
			List<BaseCommand> var = plugin.getGenCommandExecutor().getCommands();
			for (BaseCommand cmd : var.toArray(new BaseCommand[var.size()])) {
				if (cmd.permission(sender) && cmd.name.equalsIgnoreCase(args.get(0)))
				{
					Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.White + "---------------------- Town - " + cmd.name);
					Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Green + "- " + "/" + usedCommand + " " + cmd.name + Colors.Green + " " + cmd.usage);
					cmd.sender = sender;
					cmd.moreHelp();
					return true;
				}
			}
			Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Red + "No command found by that name");
		}
		return true;
	}

	@Override
	public void moreHelp()
	{
		Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.LightBlue + "Shows all ccTown commands");
		Notifier.sendMessage(NotifierType.REGULAR, sender, Colors.Red + "Type " + Colors.Gray + "/town help <command>" + Colors.Red + " for help on that command");
	}

	@Override
	public boolean permission(CommandSender csender)
	{
		return Vault.perms.has(csender, "amber.command.help");
	}

	@Override
	public BaseCommand newInstance()
	{
		return new HelpCommand();
	}

	/**
	 * Formats a string with a provided title and padding and centers title.
	 * 
	 * @param title
	 * @param fill
	 * @return
	 */
	@Override
	protected String cleanTitle(String title, String fill)
	{
		int chatWidthMax = 53;										// Vanilla client line character max
		int titleWidth = title.length() + 2;						// Title's character width with 2 spaces padding
		int fillWidth = (int) ((chatWidthMax - titleWidth) / 2D);	// Fill string calculation for padding either side
		String cleanTitle = "";

		for(int i = 0; i < fillWidth; i++)
			cleanTitle += fill;
		cleanTitle += " " + title + " ";
		for(int i = 0; i < fillWidth; i++)
			cleanTitle += fill;

		return cleanTitle;
	}
}