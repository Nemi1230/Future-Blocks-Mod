package jp.nemi.futureblocks.object.block.villageandpillage;

import jp.nemi.futureblocks.FBCore;
import jp.nemi.futureblocks.Reference;
import jp.nemi.futureblocks.init.FBBlocks;
import jp.nemi.futureblocks.init.FBItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.property.Properties;

import javax.annotation.Nullable;
import java.util.Random;

public class BambooSaplingBlock extends Block implements IGrowable {
    //0.0625D
    protected static final AxisAlignedBB BAMBOO_SAPLING_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.75D, 0.75D);

    public BambooSaplingBlock(String name, Material materialIn) {
        super(materialIn);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setCreativeTab(FBCore.VILLAGEANDPILLAGE);
        this.setUnlocalizedName(name);
        this.setTickRandomly(true);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        Vec3d vec3d = blockState.getOffset(worldIn, pos);
        return BAMBOO_SAPLING_AABB.offset(vec3d);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.getCollisionBoundingBox(state, source, pos);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (random.nextInt(3) == 0 && worldIn.isAirBlock(pos.up()) &&worldIn.getCombinedLight(pos.up(), 0) >= 9) {
            this.growBamboo(worldIn, pos);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return isValidPosition(worldIn.getBlockState(pos.down()).getBlock());
    }

    public boolean isValidPosition(Block block) {
        return block == FBBlocks.BAMBOO || block == FBBlocks.BAMBOO_SAPLING || block == Blocks.GRAVEL || block == Blocks.SAND || block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.MYCELIUM;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!isValidPosition(worldIn.getBlockState(pos.down()).getBlock())) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            return;
        }
        else {
            if (worldIn.getBlockState(pos.up()).getBlock() == FBBlocks.BAMBOO) {
                worldIn.setBlockState(pos, FBBlocks.BAMBOO.getDefaultState(), 2);
            }
        }
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(FBItems.BAMBOO);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return worldIn.isAirBlock(pos.up());
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.growBamboo(worldIn, pos);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        return player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword ? 1.0F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    protected void growBamboo(World world, BlockPos state) {
        world.setBlockState(state.up(), FBBlocks.BAMBOO.getDefaultState(), 3);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /*@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(FBBlocks.BAMBOO);
    }

    @Override
    public Vec3d getOffset(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        long i = MathHelper.getCoordinateRandom(pos.getX(), 0, pos.getZ());
        return new Vec3d((((float)(i >> 16L & 15L) / 15.0F) - 0.5F) * 0.5D, 0.0D, (((float)(i >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D);
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return getCollisionBoundingBox(state, source, pos);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        Vec3d vec3d = blockState.getOffset(worldIn, pos);
        return BAMBOO_SAPLING_AABB.offset(vec3d);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (random.nextInt(3) == 0 && worldIn.isAirBlock(pos.up()) && worldIn.getCombinedLight(pos.up(), 0) >= 9) {
            this.growBamboo(worldIn, pos);
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        return block == FBBlocks.BAMBOO || block == FBBlocks.BAMBOO_SAPLING || block == Blocks.GRAVEL || block == Blocks.SAND || block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.MYCELIUM;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!blockIn.canPlaceBlockAt(worldIn, pos)) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            return;
        }
        else {
            //Grow Bamboo
        }
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(FBBlocks.BAMBOO_SAPLING);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return worldIn.isAirBlock(pos.up());
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.growBamboo(worldIn, pos);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        return player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword ? 1.0F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    protected void growBamboo(World world, BlockPos state) {
        world.setBlockState(state.up(), FBBlocks.BAMBOO.getDefaultState(), 3);
    }*/
}
