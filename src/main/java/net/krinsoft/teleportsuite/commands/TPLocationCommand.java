package net.krinsoft.teleportsuite.commands;

import net.krinsoft.teleportsuite.Localization;
import net.krinsoft.teleportsuite.TeleportPlayer;
import net.krinsoft.teleportsuite.TeleportSuite;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.io.StringWriter;
import java.util.List;

/**
 * @author krinsdeath
 */
public class TPLocationCommand extends TeleportCommand {
    
    public TPLocationCommand(TeleportSuite plugin) {
        super(plugin);
        setName("TeleportSuite: Location");
        setCommandUsage("/tploc x y z world");
        addCommandExample("/tploc 15 75 99 -- Teleport to the specified location in your current world.");
        addCommandExample("/tploc 131 55 63 world -- Teleport to the location in the world 'world'");
        setArgRange(3, 4);
        addKey("teleport location");
        addKey("tps location");
        addKey("tplocation");
        addKey("location");
        addKey("tploc");
        setPermission("teleport.location", "Allows this user to teleport to a set of [x, y, z] coordinates.", PermissionDefault.TRUE);
    }
    
    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        if (sender instanceof ConsoleCommandSender) { return; }
        TeleportPlayer player = plugin.getManager().getPlayer(sender.getName());
        World world = player.getLocation().getWorld(); 
        if (args.size() == 4) {
            if (plugin.getServer().getWorld(args.get(3)) != null) {
                world = plugin.getServer().getWorld(args.get(3));
            }
        }
        if (!player.hasPermission("teleport.world." + world.getName())) {
            return;
        }
        Location loc;
        try {
            double[] l = new double[3];
            l[0] = Double.parseDouble(args.get(0));
            l[1] = Double.parseDouble(args.get(1));
            l[2] = Double.parseDouble(args.get(2));
            loc = new Location(world, l[0], l[1], l[2]);
        } catch (Exception e) {
            player.sendLocalizedString("error.invalid.destination", null);
            return;
        }
        player.teleport(loc);
    }
}