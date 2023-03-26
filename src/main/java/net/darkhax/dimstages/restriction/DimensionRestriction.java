package net.darkhax.dimstages.restriction;

import javax.annotation.Nullable;

import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public abstract class DimensionRestriction implements IDimensionRestriction {
    
    /**
     * The default restriction message that is displayed to players when they get restricted.
     */
    public static final Component DEFAULT_MESSAGE = Component.translatable("message.dimstages.noentry");
    
    /**
     * The actual restriction message to display when a player's access to a dimension is
     * restricted.
     */
    @Nullable
    private Component restrictionMessage = DEFAULT_MESSAGE;
    
    /**
     * Sets the restriction message to a new message.
     * 
     * @param newMessage The new message to display.
     * @return The previous restriction message.
     */
    @Nullable
    public Component setRestrictionMessage (@Nullable Component newMessage) {
        
        final Component oldMessage = this.restrictionMessage;
        this.restrictionMessage = newMessage;
        return oldMessage;
    }
    
    @Override
    public Component getRestrictedMessage (Player player, ResourceLocation dimension) {
        
        return this.restrictionMessage;
    }
}