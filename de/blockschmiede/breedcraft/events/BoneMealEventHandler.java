package de.blockschmiede.breedcraft.events;

import de.blockschmiede.breedcraft.BreedCraft;
import de.blockschmiede.breedcraft.blocks.CornCrop;
import de.blockschmiede.breedcraft.blocks.Herbs;
import de.blockschmiede.breedcraft.blocks.SoyCrop;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BoneMealEventHandler {

	public BoneMealEventHandler() {
	}

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		if (!event.world.isRemote) {
			if (event.ID == BreedCraft.cornCrop.blockID) {
				((CornCrop) BreedCraft.cornCrop).fertilize(event.world,
						event.X, event.Y, event.Z, event.world.rand);
			} else if (event.ID == BreedCraft.soyCrop.blockID) {

				((SoyCrop) BreedCraft.soyCrop).fertilize(event.world, event.X,
						event.Y, event.Z, event.world.rand);
			} else if (event.ID == BreedCraft.herbCrop.blockID) {

				((Herbs) BreedCraft.herbCrop).fertilize(event.world, event.X,
						event.Y, event.Z, event.world.rand);
			}
		}
	}
}
