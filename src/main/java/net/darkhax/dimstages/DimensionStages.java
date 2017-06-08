package net.darkhax.dimstages;

import java.util.HashMap;
import java.util.Map;

import minetweaker.MineTweakerAPI;
import net.darkhax.dimstages.compat.crt.DimensionStagesCrT;
import net.darkhax.gamestages.capabilities.PlayerDataHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "dimstages", name = "Dimension Stages", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.0.0.384,);required-after:gamestages@[1.0.8,);required-after:crafttweaker@[3.0.25.,)")
public class DimensionStages {

    public static final Map<Integer, String> DIMENSION_MAP = new HashMap<>();
    public static final int MESSAGE_ID = 95505255;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

        MineTweakerAPI.registerClass(DimensionStagesCrT.class);
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

        if (entity instanceof EntityPlayer && !PlayerDataHandler.getStageData((EntityPlayer) entity).hasUnlockedStage(requiredStage)) {

            event.setCanceled(true);
        }
    }
}
