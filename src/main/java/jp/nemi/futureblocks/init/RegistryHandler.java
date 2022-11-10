package jp.nemi.futureblocks.init;

import jp.nemi.futureblocks.FBCore;
import jp.nemi.futureblocks.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;

public class RegistryHandler {
    @EventBusSubscriber(modid = Reference.MOD_ID)
    public static class Blocks {
        private static final List<Block> BLOCKS = new LinkedList<>();

        static void add(Block block) {
            BLOCKS.add(block);
        }

        @SubscribeEvent
        public static void register(final RegistryEvent.Register<Block> event) {
            BLOCKS.forEach(block -> event.getRegistry().register(block));
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onItemModelRegister(ModelRegistryEvent event) {
            for (Block block : BLOCKS) {
                FBCore.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
            }
        }
    }

    @EventBusSubscriber(modid = Reference.MOD_ID)
    public static class Items {
        private static final List<Item> ITEMS = new LinkedList<>();

        static void add(Item item) {
            ITEMS.add(item);
        }

        public static List<Item> getItems() {
            return ITEMS;
        }

        @SubscribeEvent
        public static void register(final RegistryEvent.Register<Item> event) {
            ITEMS.forEach(item -> event.getRegistry().register(item));
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onItemModelRegister(ModelRegistryEvent event) {
            for (Item item : ITEMS) {
                FBCore.proxy.registerItemRenderer(item, 0, "inventory");
            }
        }
    }

    public static void init() {
        FBBlocks.register();
        FBItems.register();
    }
}
