package me.pompier15.SelectorUtility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;


//Based on this Listener implementation : https://github.com/sekwah41/Advanced-Portals/blob/425093d8874271e100bc2474c3a864dcafe982a2/src/main/java/com/sekwah/advancedportals/bukkit/listeners/Listeners.java
public class Listeners implements Listener
{
    private final SelectorUtilityPlugin _plugin;

    public Listeners(SelectorUtilityPlugin plugin)
    {
        _plugin = plugin;

        //Register to recover events from the plugin manager
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);

    }

    //Trigger when a player interact with an item
    //This annotation is very important to be catch by the bukkit runtime
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        //TODO check permissions

        if (event.getItem() != null && event.getItem().getType() == Material.NETHERITE_AXE)
        {

            if (event.getAction() == Action.LEFT_CLICK_BLOCK)
            {

                Location blockLocation = event.getClickedBlock().getLocation();


                player.setMetadata(CST.METADATA_X_1.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockX()));
                player.setMetadata(CST.METADATA_Y_1.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockY()));
                player.setMetadata(CST.METADATA_Z_1.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockZ()));

                player.setMetadata(CST.METADATA_WORLD_1.Value(), new FixedMetadataValue(_plugin, blockLocation.getWorld().getName()));

                player.sendMessage(
                        "Sélection 1 : X:" + blockLocation.getBlockX() +" Y:"+blockLocation.getBlockY()+ " Z:" + blockLocation.getBlockZ());

                //Prevent from block destroying
                event.setCancelled(true);
            }
            else if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
            {

                Location blockLocation = event.getClickedBlock().getLocation();


                player.setMetadata(CST.METADATA_X_2.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockX()));
                player.setMetadata(CST.METADATA_Y_2.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockY()));
                player.setMetadata(CST.METADATA_Z_2.Value(), new FixedMetadataValue(_plugin, blockLocation.getBlockZ()));

                player.setMetadata(CST.METADATA_WORLD_2.Value(), new FixedMetadataValue(_plugin, blockLocation.getWorld().getName()));

                player.sendMessage(
                        "Sélection 2 : X:" + blockLocation.getBlockX() +" Y:"+blockLocation.getBlockY()+ " Z:" + blockLocation.getBlockZ());

                // Stops the event so the block is not interacted with
                event.setCancelled(true);

            }

        }
    }

}
