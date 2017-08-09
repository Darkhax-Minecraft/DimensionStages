package net.darkhax.dimstages.compat.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.DimensionStages")
public class DimensionStagesCrT {

    @ZenMethod
    public static void addDimensionStage (String stage, int id) {

       CraftTweakerAPI.apply(new ActionAddDimensionRestriction(stage, id));
    }
}
