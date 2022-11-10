package jp.nemi.futureblocks.object.creativetab;

import jp.nemi.futureblocks.init.FBBlocks;
import jp.nemi.futureblocks.init.FBItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetherUpdateTab extends CreativeTabs {
    public NetherUpdateTab() {
        super("netherupdate_tab");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(FBBlocks.BASALT));
    }
}
