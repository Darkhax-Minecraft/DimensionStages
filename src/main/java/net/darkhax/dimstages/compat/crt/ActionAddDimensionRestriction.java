package net.darkhax.dimstages.compat.crt;

import minetweaker.IUndoableAction;
import net.darkhax.dimstages.DimensionStages;

public class ActionAddDimensionRestriction implements IUndoableAction {

    private final String stage;
    private final int dimensionId;

    public ActionAddDimensionRestriction (String stage, int dimensionId) {

        this.stage = stage;
        this.dimensionId = dimensionId;
    }

    @Override
    public void apply () {

        DimensionStages.DIMENSION_MAP.put(this.dimensionId, this.stage);
    }

    @Override
    public String describe () {

        return String.format("Dimesnion %d has been added to stage %s", this.dimensionId, this.stage);
    }

    @Override
    public void undo () {

        DimensionStages.DIMENSION_MAP.remove(this.dimensionId);
    }

    @Override
    public String describeUndo () {

        return String.format("Removed dimension %d from stage %s", this.dimensionId, this.stage);
    }

    @Override
    public boolean canUndo () {

        return true;
    }

    @Override
    public Object getOverrideKey () {

        return null;
    }
}
