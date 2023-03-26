package net.darkhax.dimstages.restriction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.darkhax.dimstages.DimensionStages;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

public class StagedDimensionRestriction extends DimensionRestriction {
    
    /**
     * A set of required stages.
     */
    private final Set<String> requiredStages = new HashSet<>();
    
    /**
     * An unmodifiable view of {@link #requiredStages} accessed by
     * {@link #getRequiredStages()}.
     */
    private final Set<String> unmodifiable = Collections.unmodifiableSet(this.requiredStages);
    
    /**
     * Adds a stage to the restriction. This will validate that the name is valid and warn if
     * it's not registered in the known stages file.
     * 
     * @param stage The name of the stage to add to this restriction.
     * @return Whether or not the stage was added.
     */
    public boolean addStage (String stage) {
        
        if (!GameStageHelper.isValidStageName(stage)) {
            
            throw new IllegalArgumentException("The stage name '" + stage + "' is not valid.");
        }
        
        if (!GameStageHelper.isStageKnown(stage)) {
            
            DimensionStages.LOG.warn("Restriction configured with unknown stage name '{}'. Refer to your known_stages.json file.", stage);
        }
        
        return this.requiredStages.add(stage);
    }
    
    /**
     * Gets an unmodifiable view of the required stages.
     * 
     * @return An unmodifiable view of the required stages.
     */
    public Set<String> getRequiredStages () {
        
        return this.unmodifiable;
    }
    
    @Override
    public boolean shouldRestrict (Player player, ResourceLocation dimension) {
        
        return !GameStageHelper.hasAllOf(player, this.requiredStages);
    }
    
    @Override
    public String toString () {
        
        return "StagedDimensionRestriction [requiredStages=" + this.requiredStages + "]";
    }
}