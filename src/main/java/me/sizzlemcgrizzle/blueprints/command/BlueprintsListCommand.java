package me.sizzlemcgrizzle.blueprints.command;

import me.sizzlemcgrizzle.blueprints.BlueprintsPlugin;
import me.sizzlemcgrizzle.blueprints.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.model.SimpleComponent;
import org.mineacademy.fo.remain.CompSound;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BlueprintsListCommand extends SimpleSubCommand {
	private BlueprintsPlugin blueprintsPlugin = (BlueprintsPlugin) Bukkit.getPluginManager().getPlugin("Blueprints");

	protected BlueprintsListCommand(SimpleCommandGroup parent) {
		super(parent, "list");
		setPermission("blueprints.list");
		setMinArguments(1);
		setUsage("<number>");
	}

	@Override
	protected void onCommand() {
		int num = Integer.parseInt(args[0]);
		try {

			HashMap<ItemStack, String> map = blueprintsPlugin.schematicCache().listBlueprints();

			if (map == null) {
				tell(Settings.Messages.MESSAGE_PREFIX + "&cThere are currently no blueprints.");
				return;
			}
			if (num == 0) {
				tell(Settings.Messages.MESSAGE_PREFIX + "&cThere are no blueprints on this page!");
				return;
			}

			int counter = 0;
			tell(Settings.Messages.MESSAGE_PREFIX + "&6&m---&6&o Blueprint page " + num + "/" + (int) Math.ceil(map.size() / 7.0) + " &6&m---");
			getPlayer().playSound(getPlayer().getLocation(), CompSound.NOTE_PLING.getSound(), 1F, 2F);
			for (Map.Entry<ItemStack, String> entry : map.entrySet()) {
				counter++;
				if ((7 * (num - 1)) < counter && counter <= (7 * num))
					SimpleComponent
							.of(Settings.Messages.MESSAGE_PREFIX)
							.append(" &e" + counter + ".  &f" + entry.getKey().getItemMeta().getDisplayName())
							.onHover("&5Click me to get the blueprint!\n\n&r&f" + entry.getKey().getItemMeta().getDisplayName() + "&7 uses \n&c" + entry.getValue() + "&7 as a schematic")
							.onClickRunCmd("/blueprints get " + entry.getValue())
							.send(getPlayer());
			}
			if ((map.size() / 7.0) > num) {
				SimpleComponent
						.of(Settings.Messages.MESSAGE_PREFIX)
						.append("&d&m---&d&o Click here for page " + (num + 1) + " &d&m---")
						.onClickRunCmd("/blueprints list " + (num + 1))
						.send(getPlayer());
			}
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}


}
