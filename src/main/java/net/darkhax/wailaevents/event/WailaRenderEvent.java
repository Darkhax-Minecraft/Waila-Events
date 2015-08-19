package net.darkhax.wailaevents.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

public class WailaRenderEvent extends Event {
    
    /**
     * This event is triggered before Waila renders it's HUD. This event will only be fired if
     * Utilities.isSafeForTooltip returns true. If this event is canceled, the waila HUD will
     * not be rendered. This event is called every render tick, and does not check that a valid
     * target is being looked at.
     */
    @Cancelable
    public static class Pre extends WailaRenderEvent {
        
        public Pre() {
        
        }
    }
    
    /**
     * This event is triggered after Waila renders it's HUD. This event will only be fired if
     * the Pre event is not canceled, and a valid target is found. This event can not be
     * canceled.
     */
    public static class Post extends WailaRenderEvent {
        
        public Post() {
        
        }
    }
}
