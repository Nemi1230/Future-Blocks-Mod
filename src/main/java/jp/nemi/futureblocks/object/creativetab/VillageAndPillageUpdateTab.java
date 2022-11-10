package jp.nemi.futureblocks.object.creativetab;

import jp.nemi.futureblocks.init.FBBlocks;
import jp.nemi.futureblocks.init.FBItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class VillageAndPillageUpdateTab extends CreativeTabs {
    public VillageAndPillageUpdateTab() {
        super("villageandpillageupdate_tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(FBItems.BAMBOO);
    }
}
