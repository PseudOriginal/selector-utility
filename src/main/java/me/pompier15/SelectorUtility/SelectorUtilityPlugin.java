package me.pompier15.SelectorUtility;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SelectorUtilityPlugin extends JavaPlugin {

    @Override
    public void onEnable()
    {
        new Listeners(this);
    }

    @Override
    public void onDisable()
    {
        //Fired when the server stops and disables all plugins
    }

    public static boolean hasValidSelection(CommandSender sender) throws Exception {
        if (!(sender instanceof Player))
        {
            throw new Exception("L'entité n'est pas un joueur");
        }


        Player player = (Player) sender;

        /**
         * Permission needed to access this plugin
         */
        if(!(player.hasPermission("SelectorUtility")))
        {
            throw new Exception("Permission manquante");
        }

        if (!(player.hasMetadata(CST.METADATA_WORLD_1.Value())
                && player.hasMetadata(CST.METADATA_WORLD_2.Value())
                && player.hasMetadata(CST.METADATA_X_1.Value())
                && player.hasMetadata(CST.METADATA_Z_1.Value())
                && player.hasMetadata(CST.METADATA_Y_1.Value())
                && player.hasMetadata(CST.METADATA_Y_2.Value())
                && player.hasMetadata(CST.METADATA_X_2.Value())
                && player.hasMetadata(CST.METADATA_Z_2.Value())
        ))
        {
            throw new Exception("Sélection incomplète");
        }

        String world1 = player.getMetadata(CST.METADATA_WORLD_1.Value()).get(0).asString();
        String world2 = player.getMetadata(CST.METADATA_WORLD_2.Value()).get(0).asString();


        /**
         * Check if the selection is not between worlds
         */
        if (!world1.equals(world2))
        {
            throw new Exception("Les deux sélections sont dans des mondes différents");
        }

        return true;
    }

    public static SULocation getSelectPos1(CommandSender sender) throws Exception
    {
        var result = hasValidSelection(sender);

        if(result)
        {
            Player player = (Player) sender;

            int posX1 = player.getMetadata(CST.METADATA_X_1.Value()).get(0).asInt();
            int posY1 = player.getMetadata(CST.METADATA_Y_1.Value()).get(0).asInt();
            int posZ1 = player.getMetadata(CST.METADATA_Z_1.Value()).get(0).asInt();
            String world1 = player.getMetadata(CST.METADATA_WORLD_1.Value()).get(0).asString();

            return new SULocation(posX1,posY1,posZ1,world1);
        }
        return null;
    }

    public static SULocation getSelectPos2(CommandSender sender) throws Exception
    {
        var result = hasValidSelection(sender);

        if(result)
        {
            Player player = (Player) sender;

            int posX2 = player.getMetadata(CST.METADATA_X_2.Value()).get(0).asInt();
            int posY2 = player.getMetadata(CST.METADATA_Y_2.Value()).get(0).asInt();
            int posZ2 = player.getMetadata(CST.METADATA_Z_2.Value()).get(0).asInt();
            String world2 = player.getMetadata(CST.METADATA_WORLD_2.Value()).get(0).asString();

            return new SULocation(posX2,posY2,posZ2,world2);
        }
        return null;
    }
}
