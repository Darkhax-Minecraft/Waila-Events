package net.darkhax.wailaevents.asm;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class ModContainer extends DummyModContainer {
    
    public ModContainer() {
        
        super(new ModMetadata());
        
        ModMetadata md = getMetadata();
        md.modId = "wailaevents";
        md.name = "Waila Events";
        md.version = "1.0.0";
        md.credits = "Darkhax, ProfMobius";
        md.authorList = Arrays.asList("Darkhax");
        md.description = "Adds several new events to the Waila mod.";
        md.url = "darkhax.net";
    }
    
    @Override
    public boolean registerBus (EventBus bus, LoadController controller) {
        
        return true;
    }
}
