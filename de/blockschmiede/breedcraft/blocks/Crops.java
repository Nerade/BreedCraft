package de.blockschmiede.breedcraft.blocks;

import de.blockschmiede.breedcraft.BreedCraft;
import de.blockschmiede.breedcraft.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class Crops extends Block implements IPlantable{

	public Crops(int par1, int par2) {
		super(par1, par2, Material.plants);
		setTextureFile(CommonProxy.BLOCK_PNG);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }

    @Override
    public int getRenderType () {
        return 1;
    }

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (metadata/3);
	}
    
    @Override
    public boolean isOpaqueCube () {
        return false;
    }

    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
        Block soil = blocksList[world.getBlockId(x, y - 1, z)];
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world
                .canBlockSeeTheSky(x, y, z))
                && (soil != null && soil.canSustainPlant(world, x, y - 1, z,
                        ForgeDirection.UP, BreedCraft.cornSeeds));
    }
    
    @Override
    public void onNeighborBlockChange (World world, int x, int y, int z,
            int neighborId) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockWithNotify(x, y, z, 0);
        }
    }

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return this.blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return world.getBlockMetadata(x, y, z);
	}


}
