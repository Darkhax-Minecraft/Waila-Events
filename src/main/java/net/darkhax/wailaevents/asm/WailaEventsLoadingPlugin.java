package net.darkhax.wailaevents.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.TransformerExclusions("net.darkhax.wawla.asm")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class WailaEventsLoadingPlugin implements IFMLLoadingPlugin {
    
    @Override
    public String[] getASMTransformerClass () {
    
        return new String[] { OverlayRendererTransformer.class.getName() };
    }
    
    @Override
    public String getModContainerClass () {
    
        return null;
    }
    
    @Override
    public String getSetupClass () {
    
        return null;
    }
    
    @Override
    public void injectData (Map<String, Object> data) {
    
    }
    
    @Override
    public String getAccessTransformerClass () {
    
        return null;
    }
}