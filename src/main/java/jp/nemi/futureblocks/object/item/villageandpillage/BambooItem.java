package jp.nemi.futureblocks.object.item.villageandpillage;

import jp.nemi.futureblocks.FBCore;
import jp.nemi.futureblocks.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BambooItem extends ItemBlock {
    public BambooItem(String name, Block block) {
        super(block);
        this.setRegistryName(Reference.MOD_ID, name);
        //this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName(name);
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 50;
    }
}
