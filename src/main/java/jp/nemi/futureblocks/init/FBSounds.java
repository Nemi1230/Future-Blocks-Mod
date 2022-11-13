package jp.nemi.futureblocks.init;

import jp.nemi.futureblocks.Reference;
import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class FBSounds {
    public static SoundEvent BLOCK_BAMBOO_BREAK = registerSound("block.bamboo.break");
    public static SoundEvent BLOCK_BAMBOO_FALL = registerSound("block.bamboo.fall");;
    public static SoundEvent BLOCK_BAMBOO_HIT = registerSound("block.bamboo.hit");;
    public static SoundEvent BLOCK_BAMBOO_PLACE = registerSound("block.bamboo.place");;
    public static SoundEvent BLOCK_BAMBOO_STEP = registerSound("block.bamboo.step");;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_BREAK = registerSound("block.bamboo_sapling.break");;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_HIT = registerSound("block.bamboo_sapling.hit");;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_PLACE = registerSound("block.bamboo_sapling.place");;

    public static final SoundType BAMBOO = new SoundType(1.0f, 1.0f, BLOCK_BAMBOO_BREAK, BLOCK_BAMBOO_STEP, BLOCK_BAMBOO_PLACE, BLOCK_BAMBOO_HIT, BLOCK_BAMBOO_STEP);
    public static final SoundType BAMBOO_SAPLING = new SoundType(1.0F, 1.0F, BLOCK_BAMBOO_SAPLING_BREAK, BLOCK_BAMBOO_STEP, BLOCK_BAMBOO_SAPLING_PLACE, BLOCK_BAMBOO_SAPLING_HIT, BLOCK_BAMBOO_FALL);

    private static SoundEvent registerSound(String name) {
        ResourceLocation resource = new ResourceLocation(Reference.MOD_ID, name);
        return new SoundEvent(resource).setRegistryName(resource);
    }

    public static void registerSounds(IForgeRegistry<SoundEvent> event) {
        event.register(BLOCK_BAMBOO_BREAK);
        event.register(BLOCK_BAMBOO_FALL);
        event.register(BLOCK_BAMBOO_HIT);
        event.register(BLOCK_BAMBOO_PLACE);
        event.register(BLOCK_BAMBOO_STEP);
        event.register(BLOCK_BAMBOO_SAPLING_BREAK);
        event.register(BLOCK_BAMBOO_SAPLING_HIT);
        event.register(BLOCK_BAMBOO_SAPLING_PLACE);
    }
}
