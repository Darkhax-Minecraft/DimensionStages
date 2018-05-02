package net.darkhax.dimstages;

import java.util.HashMap;
import java.util.Map;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "dimstages", name = "Dimension Stages", version = "@VERSION@", dependencies = "required-after:bookshelf;required-after:gamestages@[2.0.89,);required-after:crafttweaker", certificateFingerprint = "@FINGERPRINT@")
public class DimensionStages {
    
    public static final Map<Integer, String> DIMENSION_MAP = new HashMap<>();
    public static final int MESSAGE_ID = 95505255;
    public static final LoggingHelper LOG = new LoggingHelper("dimstages");
    
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onDimChange (EntityTravelToDimensionEvent event) {
        
        final String requiredStage = DIMENSION_MAP.get(event.getDimension());
        
        if (requiredStage != null && !requiredStage.isEmpty()) {
            
            if (event.getEntity() instanceof EntityPlayer) {
                
                attemptEntryBlock(event.getEntity(), requiredStage, event);
            }
            
            else if (event.getEntity() instanceof EntityThrowable) {
                
                final EntityThrowable throwable = (EntityThrowable) event.getEntity();
                attemptEntryBlock(throwable.getThrower(), requiredStage, event);
            }
            
            else if (event.getEntity() instanceof EntityItem) {
                
                final EntityItem item = (EntityItem) event.getEntity();
                
                if (item.getThrower() != null && !item.getThrower().isEmpty()) {
                    
                    attemptEntryBlock(event.getEntity().getEntityWorld().getPlayerEntityByName(item.getThrower()), requiredStage, event);
                }
            }
            
            else if (event.getEntity() instanceof EntityArrow) {
                
                final EntityArrow arrow = (EntityArrow) event.getEntity();
                attemptEntryBlock(arrow.shootingEntity, requiredStage, event);
            }
        }
    }
    
    private static void attemptEntryBlock (Entity entity, String requiredStage, Event event) {
        
        if (entity instanceof EntityPlayer && !GameStageHelper.hasStage((EntityPlayer) entity, requiredStage)) {
            
            event.setCanceled(true);
        }
    }
}
