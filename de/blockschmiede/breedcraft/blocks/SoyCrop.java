package de.blockschmiede.breedcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import de.blockschmiede.breedcraft.BreedCraft;

public class SoyCrop extends Crops {

	public SoyCrop(int id) {
		super(id, 7);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
		this.setTickRandomly(true);
		// TODO Auto-generated constructor stub
	}
    @Override
    public int getRenderType () {
        return 6;
    }
    
    public void fertilize(World world, int x,int y,int z,Random rand){
    	
    	world.setBlockMetadataWithNotify(x, y, z, 9);
    	world.markBlockForUpdate(x, y, z);
    	
    }

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
				if (world.getBlockMetadata(x, y, z) == 9) {
					return;
				}
			    if (world.getBlockLightValue(x, y + 1, z) < 9) {
			        return;
			    }

			// if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 25) != 0)
			// {
			// return;
			// }
			int old_meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, old_meta + 1);
			world.markBlockForUpdate(x, y, z);
		}
	}
    
    
    @Override
    public int idDropped (int metadata, Random random, int par2) {

            return BreedCraft.soyBeans.itemID;
    
    }

    @Override
    public int idPicked (World world, int x, int y, int z) {
        return BreedCraft.soyBeans.itemID;
    }


}
