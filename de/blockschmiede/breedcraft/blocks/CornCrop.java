package de.blockschmiede.breedcraft.blocks;

import java.util.Random;

import de.blockschmiede.breedcraft.BreedCraft;
import de.blockschmiede.breedcraft.CommonProxy;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class CornCrop extends Crops {

	public CornCrop(int id) {
		super(id, 1);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.6F, 1.0F);
		setTickRandomly(true);
	}

	public void fertilize(World world, int x, int y, int z, Random rand) {
		if (world.isAirBlock(x, y + 1, z)) {
			if (world.getBlockId(x, y - 1, z) == Block.tilledField.blockID) {

				world.setBlockAndMetadataWithNotify(x, y + 1, z, blockID, 15);
				world.markBlockForUpdate(x, y + 1, z);
			}
			if(world.getBlockMetadata(x, y, z)<=9){
				world.setBlockMetadataWithNotify(x, y, z, 9);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, 15);
			}
			world.markBlockForUpdate(x, y, z);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 9) {
				return;
			}

			if (world.getBlockId(x, y - 1, z) == Block.tilledField.blockID) {

				if (world.getBlockMetadata(x, y, z) == 11) {
					if (world.isAirBlock(x, y + 1, z)) {
						world.setBlockAndMetadataWithNotify(x, y + 1, z,
								blockID, 12);
						world.markBlockForUpdate(x, y + 1, z);
					}
					return;
				}
			} else {
				if (world.getBlockMetadata(x, y, z) == 15) {
					return;
				}
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
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		if (soil == null) {
			return false;
		}
		if (!(soil.blockID == BreedCraft.cornCrop.blockID || soil.blockID == Block.tilledField.blockID)) {
			return false;
		}
		if (soil.blockID == BreedCraft.cornCrop.blockID
				&& world.getBlockMetadata(x, y, z) < 12) {
			return false;
		} else if (soil.blockID == Block.tilledField.blockID
				&& world.getBlockMetadata(x, y, z) > 11) {
			return false;
		} else {
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world
					.canBlockSeeTheSky(x, y, z));
		}
	}

	@Override
	public int idDropped(int metadata, Random random, int par2) {
		switch (metadata) {

		case 15:
			return BreedCraft.cornCob.itemID;
		default:
			// Error case!
			return BreedCraft.cornSeeds.itemID; // air
		}
	}

	@Override
	public int idPicked(World world, int x, int y, int z) {
		return BreedCraft.cornSeeds.itemID;
	}
}
