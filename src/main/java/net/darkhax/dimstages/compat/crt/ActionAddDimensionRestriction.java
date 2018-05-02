package net.darkhax.dimstages.compat.crt;

import crafttweaker.IAction;
import net.darkhax.dimstages.DimensionStages;

public class ActionAddDimensionRestriction implements IAction {
    
    private final String stage;
    private final int dimensionId;
    
    public ActionAddDimensionRestriction(String stage, int dimensionId) {
        
        this.stage = stage;
        this.dimensionId = dimensionId;
    }
    
    @Override
    public void apply () {
        
        DimensionStages.DIMENSION_MAP.put(this.dimensionId, this.stage);
    }
    
    @Override
    public String describe () {
        
        return String.format("Dimension %d has been added to stage %s", this.dimensionId, this.stage);
    }
}
