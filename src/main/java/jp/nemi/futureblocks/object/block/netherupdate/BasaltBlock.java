package jp.nemi.futureblocks.object.block.netherupdate;

import jp.nemi.futureblocks.FBCore;
import jp.nemi.futureblocks.Reference;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BasaltBlock extends BlockRotatedPillar {
    public BasaltBlock(String name, Material materialIn, MapColor color) {
        super(materialIn, color);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setCreativeTab(FBCore.NETHERUPDATE);
        this.setUnlocalizedName(name);
        this.setHardness(1.25f);
        this.setResistance(4.2F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
