package me.sizzlemcgrizzle.blueprints.conversation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;

/*
 * Made by SydMontague:
 * https://github.com/SydMontague/CLCore/tree/craftcitizen/src/main/java/de/craftlancer/core/conversation
 */
public class ConvoCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Conversable && ((Conversable) sender).isConversing() && args.length >= 1)
			((Conversable) sender).acceptConversationInput(args[0]);

		return true;
	}
}
