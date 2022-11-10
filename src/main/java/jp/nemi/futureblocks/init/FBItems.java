package jp.nemi.futureblocks.init;

import jp.nemi.futureblocks.config.FBConfig;
import jp.nemi.futureblocks.object.item.villageandpillage.*;
import net.minecraft.item.Item;

public class FBItems {
    //1.16 Nether Update
    public static final Item BASALT;

    //1.14 Village & Pillage Update
    public static final Item BAMBOO;

    static {
        BAMBOO = new BambooItem("bamboo", FBBlocks.BAMBOO_SAPLING);
        //BAMBOO = new BambooItemTest();
        BASALT = new Item();
    }

    public static void register() {
        if (FBConfig.GENERAL.EnableNetherUpdateBlocks) {}
        if (FBConfig.GENERAL.EnableVillageAndPillageUpdateBlocks ||true) {
            registerItem(BAMBOO);
        }
    }

    public static void registerItem(Item item) {
        RegistryHandler.Items.add(item);
    }
}
