package me.sizzlemcgrizzle.blueprints.command;

import me.sizzlemcgrizzle.blueprints.BlueprintsPlugin;
import me.sizzlemcgrizzle.blueprints.placement.Blueprint;
import me.sizzlemcgrizzle.blueprints.settings.Settings;
import me.sizzlemcgrizzle.blueprints.util.SchematicUtil;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.ArrayList;
import java.util.List;

public class BlueprintsAddCommand extends SimpleSubCommand {
    
    private BlueprintsPlugin blueprintsPlugin = BlueprintsPlugin.getInstance();
    
    private static String notSpecial = Settings.Messages.MESSAGE_PREFIX + "&cThe block you are holding does not have a special name, and this is dangerous!";
    
    public BlueprintsAddCommand(final SimpleCommandGroup parent) {
        super(parent, "add");
        setMinArguments(1);
        setUsage("<schematicname.schematic> <type>");
        setPermission("blueprints.add");
    }
    
    @Override
    protected List<String> tabComplete() {
        if (args.length == 1)
            return completeLastWord(SchematicUtil.getSchematics());
        if (args.length == 2)
            return completeLastWord(Settings.TYPES);
        return new ArrayList<>();
    }
    
    @Override
    protected void onCommand() {
        checkConsole();
        final ItemStack blueprint = getPlayer().getInventory().getItemInMainHand();
        
        if (blueprint.getItemMeta() == null || !blueprint.getItemMeta().hasDisplayName()) {
            tell(notSpecial);
            return;
        }
        
        if (SchematicUtil.getSchematics().contains(args[0])) {
            tell(Settings.Messages.MESSAGE_PREFIX + "&7You successfully added a blueprint with name " + blueprint.getItemMeta().getDisplayName() + " &7calling schematic &d" + args[0]);
            BlueprintsPlugin.getInstance().addBlueprint(new Blueprint(blueprint.clone(), args[0], args.length > 1 ? args[1] : null));
        } else
            tell(Settings.Messages.MESSAGE_PREFIX + "&cThere is no such file '&4" + args[0] + "&c'. Please add the schematic to the Schematics folder.");
        
    }
}