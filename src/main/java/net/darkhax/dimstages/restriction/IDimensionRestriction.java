package net.darkhax.dimstages.restriction;

import javax.annotation.Nullable;

import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public interface IDimensionRestriction {
    
    /**
     * Checks if a player should be restricted from entering a dimension.
     * 
     * @param player The player entering the dimension.
     * @param dimension The ID of the dimension.
     * @return Whether or not the player is restricted from entering the dimension.
     */
    public boolean shouldRestrict (Player player, ResourceLocation dimension);
    
    /**
     * Gets a message that is displayed to the player when they are restricted from entering
     * the dimension.
     * 
     * @param player The player that was restricted.
     * @param dimension The dimension they were restricted from entering.
     * @return A message to display to the player when they are restricted. If null is returned
     *         no message will be displayed.
     */
    @Nullable
    public Component getRestrictedMessage (Player player, ResourceLocation dimension);
}