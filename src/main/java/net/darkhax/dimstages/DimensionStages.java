package net.darkhax.dimstages;

import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod("dimstages")
public class DimensionStages {
    
    public static final Logger LOG = LogManager.getLogger("Dimension Stages");
    public static final RestrictionManager MANAGER = new RestrictionManager();
    public static final ConfigOptions CONFIG = ConfigOptions.load(FMLPaths.CONFIGDIR.get().resolve("dimstages.json").toFile());
}