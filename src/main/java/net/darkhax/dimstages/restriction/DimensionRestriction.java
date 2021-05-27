package net.darkhax.dimstages.restriction;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class DimensionRestriction implements IDimensionRestriction {
    
    /**
     * The default restriction message that is displayed to players when they get restricted.
     */
    public static final ITextComponent DEFAULT_MESSAGE = new TranslationTextComponent("message.dimstages.noentry");
    
    /**
     * The actual restriction message to display when a player's access to a dimension is
     * restricted.
     */
    @Nullable
    private ITextComponent restrictionMessage = DEFAULT_MESSAGE;
    
    /**
     * Sets the restriction message to a new message.
     * 
     * @param newMessage The new message to display.
     * @return The previous restriction message.
     */
    @Nullable
    public ITextComponent setRestrictionMessage (@Nullable ITextComponent newMessage) {
        
        final ITextComponent oldMessage = this.restrictionMessage;
        this.restrictionMessage = newMessage;
        return oldMessage;
    }
    
    @Override
    public ITextComponent getRestrictedMessage (PlayerEntity player, ResourceLocation dimension) {
        
        return this.restrictionMessage;
    }
}