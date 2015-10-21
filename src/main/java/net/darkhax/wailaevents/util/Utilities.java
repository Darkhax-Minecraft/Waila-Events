package net.darkhax.wailaevents.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mcp.mobius.waila.api.impl.ConfigHandler;
import net.minecraft.client.Minecraft;

public class Utilities {
    
    /**
     * Checks to see if it is safe for a tooltip to be rendered. This is very similar to
     * ProfMobius's check in Waila, however it does not take the target into account. This
     * check is called during the pre event.
     * 
     * @return boolean: true if it is safe to render a tip, false if it is not.
     */
    @SideOnly(Side.CLIENT)
    public static boolean isSafeForTooltip () {
        
        Minecraft mc = Minecraft.getMinecraft();
        return !((mc.currentScreen != null) || (mc.theWorld == null) || (!Minecraft.isGuiEnabled()) || (mc.gameSettings.keyBindPlayerList.getIsKeyPressed()) || (!ConfigHandler.instance().getConfig("general", mcp.mobius.waila.utils.Constants.CFG_WAILA_SHOW, true)));
    }
}