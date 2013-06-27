package de.blockschmiede.breedcraft.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.blockschmiede.breedcraft.BreedCraft;
import de.blockschmiede.breedcraft.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class Herbs extends Block implements IPlantable {

	public Herbs(int id) {
		super(id, 11,Material.plants);
		setBlockName("herbs");
		setCreativeTab(BreedCraft.breedTab);
		setBlockBounds(0.0F,0.0F,0.0F,1.0F,0.9F,1.0F);
		this.setTickRandomly(true);
		//this.metadata = metadata;
		// TODO Auto-generated constructor stub
	}
	public boolean canPlaceBlockAt(World world,int par1,int par2, int par3){
		return (world.getBlockId(par1, par2-1, par3)==BreedCraft.fertileSoil.blockID);
	}
	
	public void fertilize(World world, int x, int y, int z, Random rand){
		spread(world,x,y,z,rand.nextInt(4));
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 9) {
				return;
			}
			if (!world.canBlockSeeTheSky(x, y, z)){
				return;
			}
			spread(world,x,y,z,random.nextInt(4));
		}
	}
	
	private void spread(World world, int x, int y, int z, int rand){
		
		switch(rand%4){
			
		case 0:
			if(world.isAirBlock(x-1, y, z) && world.getBlockId(x-1, y-1, z)==BreedCraft.fertileSoil.blockID){
				world.setBlockAndMetadataWithNotify(x-1, y, z, blockID, world.getBlockMetadata(x, y, z));
				world.markBlockForUpdate(x-1, y, z);
			}
			break;
		case 1:
			if(world.isAirBlock(x+1, y, z) && world.getBlockId(x+1, y-1, z)==BreedCraft.fertileSoil.blockID){
				world.setBlockAndMetadataWithNotify(x+1, y, z, blockID, world.getBlockMetadata(x, y, z));
				world.markBlockForUpdate(x+1, y, z);
			}
			break;
		case 2:
			if(world.isAirBlock(x, y, z-1) && world.getBlockId(x, y-1, z-1)==BreedCraft.fertileSoil.blockID){
				world.setBlockAndMetadataWithNotify(x, y, z-1, blockID, world.getBlockMetadata(x, y, z));
				world.markBlockForUpdate(x, y, z-1);
			}
			break;
		case 3:
			if(world.isAirBlock(x, y, z+1) && world.getBlockId(x, y-1, z+1)==BreedCraft.fertileSoil.blockID){
				world.setBlockAndMetadataWithNotify(x, y, z+1, blockID, world.getBlockMetadata(x, y, z));
				world.markBlockForUpdate(x, y, z+1);
			}
			break;
		}
	}
	
	@Override
	public String getTextureFile () {
		return CommonProxy.BLOCK_PNG;
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
    public boolean isOpaqueCube () {
        return false;
    }
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 7; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
    @Override
    public int getBlockTextureFromSideAndMetadata (int side, int metadata) {
        return 11 + metadata;
    }

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return EnumPlantType.Plains;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return world.getBlockMetadata(x, y, z);
	}

}
