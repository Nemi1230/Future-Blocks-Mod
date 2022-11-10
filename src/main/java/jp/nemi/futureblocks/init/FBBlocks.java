package jp.nemi.futureblocks.init;

import jp.nemi.futureblocks.config.FBConfig;
import jp.nemi.futureblocks.object.block.netherupdate.*;
import jp.nemi.futureblocks.object.block.villageandpillage.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class FBBlocks {
    //1.20 Minecraft1.20

    //1.19

    //1.18

    //1.17

    //1.16 Nether Update
    public static final Block BASALT;

    //1.15

    //1.14 Village & Pillage Update
    public static final Block BAMBOO_SAPLING;
    public static final Block BAMBOO;

    //1.13

    static {
        BASALT = new BasaltBlock("basalt", Material.ROCK, MapColor.BLACK);
        BAMBOO_SAPLING = new BambooSaplingBlock("bamboo_sapling", Material.WOOD);
        BAMBOO = new BambooBlock("bamboo", Material.WOOD, MapColor.FOLIAGE);
    }

    public static void register() {
        if (FBConfig.GENERAL.EnableNetherUpdateBlocks) {
            registerBlock(BASALT);
        }
        if (FBConfig.GENERAL.EnableVillageAndPillageUpdateBlocks || true) {
            registerBlock(BAMBOO_SAPLING, null);
            registerBlock(BAMBOO, null);
        }
    }

    public static void registerBlock(Block block) {
        registerBlock(block, new ItemBlock(block));
    }

    public static void registerBlock(Block block, ItemBlock itemBlock) {
        if(block.getRegistryName() == null)
            throw new IllegalArgumentException("The block you were trying to register does not have a registered name.");
        RegistryHandler.Blocks.add(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(block.getRegistryName());
            RegistryHandler.Items.add(itemBlock);
        }
    }
}
