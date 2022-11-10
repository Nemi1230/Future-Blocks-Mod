package jp.nemi.futureblocks.config;

import jp.nemi.futureblocks.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@Config(modid = Reference.MOD_ID, category = "futureblocks")
public class FBConfig {
    public static General GENERAL = new General();

    public static class General {
        @Name(value = "EnableNetherUpdateBlocks")
        @Comment(value = {"When enabled, blocks, items, etc. added in Minecraft 1.16 can be used.\nIf disabled, multiple items, blocks, etc. will be missing.\nRestart the game to apply the changes."})
        public boolean EnableNetherUpdateBlocks = true;

        @Name(value = "EnableNetherUpdateBlocks")
        @Comment(value = {"When enabled, blocks, items, etc. added in Minecraft 1.14 can be used.\nIf disabled, multiple items, blocks, etc. will be missing.\nRestart the game to apply the changes."})
        public boolean EnableVillageAndPillageUpdateBlocks = true;
    }
}
