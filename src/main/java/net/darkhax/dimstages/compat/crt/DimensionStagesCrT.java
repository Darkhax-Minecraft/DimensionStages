package net.darkhax.dimstages.compat.crt;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.impl.util.text.MCTextComponent;

import net.darkhax.dimstages.restriction.DimensionRestriction;

@ZenRegister
@ZenCodeType.Name("mods.dimstages.DimensionStages")
public class DimensionStagesCrT {
    
    @ZenCodeType.Method
    public static void stageDimension (String dimensionId, String... stages) {
        
        CraftTweakerAPI.apply(new ActionStageDimension(dimensionId, new MCTextComponent(DimensionRestriction.DEFAULT_MESSAGE), stages));
    }
    
    @ZenCodeType.Method
    public static void stageDimensionWithMessage (String dimensionId, MCTextComponent message, String... stages) {
        
        CraftTweakerAPI.apply(new ActionStageDimension(dimensionId, message, stages));
    }
}