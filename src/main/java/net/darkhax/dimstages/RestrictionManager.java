package net.darkhax.dimstages;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.darkhax.dimstages.restriction.IDimensionRestriction;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;

public class RestrictionManager extends ReloadListener<Void> {
    
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
        
        if (event.getEntity() instanceof ServerPlayerEntity) {
            
            final ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
            final ResourceLocation dimensionId = event.getDimension().location();
            
            for (final IDimensionRestriction restriction : this.restrictions.get(dimensionId)) {
                
                if (restriction != null && restriction.shouldRestrict(player, event.getDimension().location())) {
                    
                    event.setCanceled(true);
                    DimensionStages.LOG.debug("Restricted {} from accessing dimension {}. Restriction={}", player.getDisplayName().getString(), dimensionId, restriction);
                    
                    final ITextComponent message = restriction.getRestrictedMessage(player, dimensionId);
                    
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
    protected Void prepare (IResourceManager manager, IProfiler profiler) {
        
        return null;
    }
    
    @Override
    protected void apply (Void data, IResourceManager manager, IProfiler profiler) {
        
        this.restrictions.clear();
    }
}