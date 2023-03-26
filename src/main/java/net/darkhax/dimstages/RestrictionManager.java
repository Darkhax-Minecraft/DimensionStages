package net.darkhax.dimstages;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.dimstages.restriction.IDimensionRestriction;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;

public class RestrictionManager extends SimplePreparableReloadListener<Void> {
    
    private final Multimap<ResourceLocation, IDimensionRestriction> restrictions = ArrayListMultimap.create();
    
    public RestrictionManager() {
        
        MinecraftForge.EVENT_BUS.addListener(this::onEntityTravelToDimension);
        MinecraftForge.EVENT_BUS.addListener(this::addReloadListeners);
    }
    
    public <T extends IDimensionRestriction> T addRestriction (ResourceLocation dimensionId, T restriction) {
        
        this.restrictions.put(dimensionId, restriction);
        return restriction;
    }
    
    private void onEntityTravelToDimension (EntityTravelToDimensionEvent event) {
        
        if (event.getEntity() instanceof ServerPlayer) {
            
            final ServerPlayer player = (ServerPlayer) event.getEntity();
            final ResourceLocation dimensionId = event.getDimension().location();
            
            for (final IDimensionRestriction restriction : this.restrictions.get(dimensionId)) {
                
                if (restriction != null && restriction.shouldRestrict(player, event.getDimension().location())) {
                    
                    event.setCanceled(true);
                    DimensionStages.LOG.debug("Restricted {} from accessing dimension {}. Restriction={}", player.getDisplayName().getString(), dimensionId, restriction);
                    
                    final Component message = restriction.getRestrictedMessage(player, dimensionId);
                    
                    if (message != null) {
                        
                        player.displayClientMessage(message, true);
                    }
                    
                    break;
                }
            }
        }
    }
    
    private void addReloadListeners (AddReloadListenerEvent event) {
        
        event.addListener(this);
    }
    
    @Override
    protected Void prepare (ResourceManager manager, ProfilerFiller profiler) {
        
        return null;
    }
    
    @Override
    protected void apply (Void data, ResourceManager manager, ProfilerFiller profiler) {
        
        this.restrictions.clear();
    }
}