package jp.nemi.futureblocks.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class FBSoundEvents {
    public static SoundEvent BLOCK_BAMBOO_BREAK;
    public static SoundEvent BLOCK_BAMBOO_FALL;
    public static SoundEvent BLOCK_BAMBOO_HIT;
    public static SoundEvent BLOCK_BAMBOO_PLACE;
    public static SoundEvent BLOCK_BAMBOO_STEP;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_BREAK;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_HIT;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_PLACE;

    public static void register() {
        BLOCK_BAMBOO_BREAK = registerSound("futureblocks:bamboo_break");
        BLOCK_BAMBOO_FALL = registerSound("futureblocks:bamboo_fall");
        BLOCK_BAMBOO_HIT = registerSound("futureblocks:bamboo_hit");
        BLOCK_BAMBOO_PLACE = registerSound("futureblocks:bamboo_place");
        BLOCK_BAMBOO_STEP = registerSound("futureblocks:bamboo_step");
        BLOCK_BAMBOO_SAPLING_BREAK = registerSound("futureblocks:bamboo_sapling_break");
        BLOCK_BAMBOO_SAPLING_HIT = registerSound("futureblocks:bamboo_sapling_hit");
        BLOCK_BAMBOO_SAPLING_PLACE = registerSound("futureblocks:bamboo_sapling_place");
    }

    private static SoundEvent registerSound(String soundName) {
        ResourceLocation resource = new ResourceLocation(soundName);
        SoundEvent sound = new SoundEvent(resource).setRegistryName(soundName);
        RegistryHandler.Sound.add(sound);
        return sound;
    }
}
