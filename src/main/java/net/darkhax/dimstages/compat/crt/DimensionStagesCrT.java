package net.darkhax.dimstages.compat.crt;

import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.DimensionStages")
public class DimensionStagesCrT {

    @ZenMethod
    public static void addDimensionStage (String stage, int id) {

        MineTweakerAPI.apply(new ActionAddDimensionRestriction(stage, id));
    }
}
