package jp.nemi.futureblocks.object.block.villageandpillage;

import jp.nemi.futureblocks.Reference;
import jp.nemi.futureblocks.init.FBBlocks;
import jp.nemi.futureblocks.init.FBItems;
import jp.nemi.futureblocks.init.FBSoundType;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BambooBlock extends Block implements IGrowable {
    protected static final AxisAlignedBB NORMAL_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
    protected static final AxisAlignedBB LARGE_LEAVE_AABB = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    protected static final AxisAlignedBB COLLISION_AABB = new AxisAlignedBB(0.40625D, 0.0D, 0.40625D, 0.59375D, 1.0D, 0.59375D);
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 1);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    public static final PropertyEnum<BambooBlock.BambooLeaves> BAMBOO_LEAVES = PropertyEnum.create("bamboo_leaves", BambooBlock.BambooLeaves.class);

    public BambooBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(name);
        this.setTickRandomly(this.ticksRandomly(this.getDefaultState()));
        this.setHardness(1.0F);
        this.setResistance(1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)).withProperty(STAGE, Integer.valueOf(0)).withProperty(BAMBOO_LEAVES, BambooBlock.BambooLeaves.NONE));
        this.setSoundType(FBSoundType.BAMBOO);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return FBItems.BAMBOO;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {AGE, STAGE, BAMBOO_LEAVES});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta < 6) {
            if (meta < 3) {
                return getDefaultState().withProperty(AGE, Integer.valueOf(0)).withProperty(STAGE, Integer.valueOf(0)).withProperty(BAMBOO_LEAVES, BambooLeaves.values()[meta]);
            }
            else {
                return getDefaultState().withProperty(AGE, Integer.valueOf(0)).withProperty(STAGE, Integer.valueOf(1)).withProperty(BAMBOO_LEAVES, BambooLeaves.values()[meta - 3]);
            }
        }
        else {
            if (meta < 9) {
                return getDefaultState().withProperty(AGE, Integer.valueOf(1)).withProperty(STAGE, Integer.valueOf(0)).withProperty(BAMBOO_LEAVES, BambooLeaves.values()[meta - 6]);
            }
            else {
                return getDefaultState().withProperty(AGE, Integer.valueOf(1)).withProperty(STAGE, Integer.valueOf(1)).withProperty(BAMBOO_LEAVES, BambooLeaves.values()[meta - 9]);
            }
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;

        if (state.getValue(STAGE) == 1)
            meta += 3;

        switch (state.getValue(BAMBOO_LEAVES)) {
            case SMALL:
                meta += 1;
                break;
            case LARGE:
                meta += 2;
                break;
            case NONE:
            default:
                break;
        }

        if (state.getValue(AGE) == 1)
            meta += 6;

        return meta;
    }

    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Vec3d vec = state.getOffset(worldIn, pos);
        return COLLISION_AABB.offset(vec);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        AxisAlignedBB axisAlignedBB = state.getValue(BAMBOO_LEAVES) == BambooBlock.BambooLeaves.LARGE ?LARGE_LEAVE_AABB : NORMAL_AABB;
        Vec3d vec = state.getOffset(worldIn, pos);
        return axisAlignedBB.offset(vec);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = world.getBlockState(pos.down());
        if (state.getBlock() == Blocks.SAND || state.getBlock() == FBBlocks.BAMBOO || state.getBlock() == FBBlocks.BAMBOO_SAPLING || state.getBlock() == Blocks.GRAVEL || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.MYCELIUM) {
            if (state.getBlock() == FBBlocks.BAMBOO_SAPLING) {
                return this.getDefaultState().withProperty(AGE, Integer.valueOf(1));
            }
            else if (state.getBlock() == FBBlocks.BAMBOO) {
                int i = state.getValue(AGE) > 0 ? 1 : 0;
                return this.getDefaultState().withProperty(AGE, Integer.valueOf(i));
            }
            else {
                IBlockState state1 = world.getBlockState(pos.up());
                return (state1.getBlock() != FBBlocks.BAMBOO && state1.getBlock() != FBBlocks.BAMBOO_SAPLING) ? FBBlocks.BAMBOO_SAPLING.getDefaultState() : this.getDefaultState().withProperty(AGE, state1.getValue(AGE));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!this.canPlaceBlockAt(worldIn, pos))
            worldIn.destroyBlock(pos, true);
    }

    public boolean ticksRandomly(IBlockState state) {
        return state.getValue(AGE) == 0;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (state.getValue(STAGE) == 0) {
            if (worldIn.isAirBlock(pos.up()) && worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                int i = this.getNumBambooBlocksBelow(worldIn, pos);
                if (i < 16 && random.nextInt(3) == 0) {
                    this.grow(worldIn, random, pos, state);
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return isBlockValidForPlacement(worldIn.getBlockState(pos.down()).getBlock());
    }

    public boolean isBlockValidForPlacement(Block block) {
        return block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.SAND || block == Blocks.GRAVEL || block == FBBlocks.BAMBOO || block == Blocks.MYCELIUM;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!canPlaceBlockAt(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
            return;
        }

        if (worldIn.getBlockState(pos.up()).getBlock() == FBBlocks.BAMBOO && worldIn.getBlockState(pos.up()).getValue(AGE) > worldIn.getBlockState(pos.up()).getValue(AGE)) {
            worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(1)), 2);
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        int i = this.getNumBambooBlocksAbove(worldIn, pos);
        int j = this.getNumBambooBlocksBelow(worldIn, pos);

        return i + j + 1 < 16 && worldIn.getBlockState(pos.up(i)).getValue(STAGE) != 1;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int i = this.getNumBambooBlocksAbove(worldIn, pos);
        int j = this.getNumBambooBlocksBelow(worldIn, pos);
        int k = i + j + 1;
        int l = 1 + rand.nextInt(2);

        for(int i1 = 0; i1 < l; ++i1) {
            BlockPos blockpos = pos.up(i);
            IBlockState blockState = worldIn.getBlockState(blockpos);
            if (k >= 16 || blockState.getValue(STAGE) == 1 || !worldIn.isAirBlock(blockpos.up())) {
                return;
            }

            this.grow(blockState, worldIn, blockpos, rand, k);
            ++i;
            ++k;
        }
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        return player.getHeldItemMainhand().getItem() instanceof ItemSword ? 1.0F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    protected void grow(IBlockState blockStateIn, World worldIn, BlockPos posIn, Random rand, int maxTotalSize) {
        IBlockState blockState = worldIn.getBlockState(posIn.down());
        BlockPos blockpos = posIn.down(2);
        IBlockState blockState1 = worldIn.getBlockState(blockpos);
        BambooLeaves bambooleaves = BambooLeaves.NONE;
        if (maxTotalSize >= 1) {
            if (blockState.getBlock() == FBBlocks.BAMBOO && blockState.getValue(BAMBOO_LEAVES) != BambooLeaves.NONE) {
                if (blockState.getBlock() == FBBlocks.BAMBOO && blockState.getValue(BAMBOO_LEAVES) != BambooLeaves.NONE) {
                    bambooleaves = BambooLeaves.LARGE;
                    if (blockState1.getBlock() == FBBlocks.BAMBOO) {
                        worldIn.setBlockState(posIn.down(), blockState.withProperty(BAMBOO_LEAVES, BambooLeaves.SMALL), 3);
                        worldIn.setBlockState(blockpos, blockState1.withProperty(BAMBOO_LEAVES, BambooLeaves.NONE), 3);
                    }
                }
            } else {
                bambooleaves = BambooLeaves.SMALL;
            }
        }

        int i = blockStateIn.getValue(AGE) != 1 && blockState1.getBlock() != FBBlocks.BAMBOO ? 0 : 1;
        int j = (maxTotalSize < 11 || !(rand.nextFloat() < 0.25F)) && maxTotalSize != 15 ? 0 : 1;
        worldIn.setBlockState(posIn.up(), this.getDefaultState().withProperty(AGE, Integer.valueOf(i)).withProperty(BAMBOO_LEAVES, bambooleaves).withProperty(STAGE, Integer.valueOf(j)), 3);
    }

    protected int getNumBambooBlocksAbove(World worldIn, BlockPos pos) {
        int i;
        for (i = 0; i < 16 && (worldIn.getBlockState(pos.up(i + 1)).getBlock() == FBBlocks.BAMBOO); ++i);
        return i;
    }
    protected int getNumBambooBlocksBelow(World worldIn, BlockPos pos) {
        int i;
        for (i = 0; i < 16 && (worldIn.getBlockState(pos.down(i + 1)).getBlock() == FBBlocks.BAMBOO); ++i);
        return i;
    }

    public static enum BambooLeaves implements IStringSerializable {
        NONE("none"),
        SMALL("small"),
        LARGE("large");

        private final String name;

        private BambooLeaves(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
