package net.darkhax.dimstages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod("dimstages")
public class DimensionStages {
    
    public static final Logger LOG = LogManager.getLogger("Dimension Stages");
    public static final RestrictionManager MANAGER = new RestrictionManager();
}