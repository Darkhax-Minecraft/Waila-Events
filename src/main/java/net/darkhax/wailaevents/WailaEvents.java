package net.darkhax.wailaevents;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import net.darkhax.wailaevents.common.CommonProxy;
import net.darkhax.wailaevents.util.Constants;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION, acceptableRemoteVersions = "*", dependencies = "required-after:Waila")
public class WailaEvents {
    
    @SidedProxy(serverSide = Constants.SERVER, clientSide = Constants.CLIENT)
    public static CommonProxy proxy;
    
    @Mod.Instance(Constants.MODID)
    public static WailaEvents instance;
}