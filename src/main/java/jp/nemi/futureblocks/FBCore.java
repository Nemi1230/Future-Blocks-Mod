package jp.nemi.futureblocks;

import jp.nemi.futureblocks.init.RegistryHandler;
import jp.nemi.futureblocks.object.creativetab.*;
import jp.nemi.futureblocks.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class FBCore {

    @Instance(Reference.MOD_ID)
    public static FBCore instance;
    @SidedProxy(clientSide = Reference.CLIENT_SIDE, serverSide = Reference.SERVER_SIDE)
    public static CommonProxy proxy;

    private static Logger logger;

    //Creative Tabs
    public static final CreativeTabs NETHERUPDATE = new NetherUpdateTab();
    public static final CreativeTabs VILLAGEANDPILLAGE = new VillageAndPillageUpdateTab();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        RegistryHandler.init();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
