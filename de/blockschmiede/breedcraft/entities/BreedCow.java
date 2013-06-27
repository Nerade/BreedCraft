package de.blockschmiede.breedcraft.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.blockschmiede.breedcraft.util.DNASequence;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BreedCow extends EntityCow implements Breedable {

	// private DNASequence mutation,grow,resistance,yield,fertility,potency;
	
	public BreedCow(World par1World, int[] parent1, int[] parent2) {
		super(par1World);
		// Random rand = new Random();
		this.texture="/mods/BreedCraft/breedcow.png";
		this.setMutationDNA(new DNASequence(parent1[0], parent2[0]));
		this.setGrowDNA(new DNASequence(parent1[1], parent2[1]));
		this.setResistanceDNA(new DNASequence(parent1[2], parent2[2]));
		this.setYieldDNA(new DNASequence(parent1[3], parent2[3]));
		this.setFertilityDNA(new DNASequence(parent1[4], parent2[4]));
		this.setPotencyDNA(new DNASequence(parent1[5], parent2[5]));
		// TODO Auto-generated constructor stub
	}
	
	public BreedCow(World par1World){
		super(par1World);
		Random rand = new Random();
		int[] parent1 = new int[6];
		int[] parent2 = new int[6];
		for(int i = 0;i<6;i++){
			parent1[i] = rand.nextInt(5000);
			parent2[i] = rand.nextInt(5000);
		}
		//this.setPosition(x, y, z);
		this.setMutationDNA(new DNASequence(parent1[0], parent2[0]));
		this.setGrowDNA(new DNASequence(parent1[1], parent2[1]));
		this.setResistanceDNA(new DNASequence(parent1[2], parent2[2]));
		this.setYieldDNA(new DNASequence(parent1[3], parent2[3]));
		this.setFertilityDNA(new DNASequence(parent1[4], parent2[4]));
		this.setPotencyDNA(new DNASequence(parent1[5], parent2[5]));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setShort("AMutation", (short) this.getMutationDNA()
				.getActive());
		par1nbtTagCompound.setShort("PMutation", (short) this.getMutationDNA()
				.getPassive());
		par1nbtTagCompound.setShort("AGrow", (short) this.getGrowDNA()
				.getActive());
		par1nbtTagCompound.setShort("PGrow", (short) this.getGrowDNA()
				.getPassive());
		par1nbtTagCompound.setShort("AResistance", (short) this
				.getResistanceDNA().getActive());
		par1nbtTagCompound.setShort("PResistance", (short) this
				.getResistanceDNA().getPassive());
		par1nbtTagCompound.setShort("AYield", (short) this.getYieldDNA()
				.getActive());
		par1nbtTagCompound.setShort("PYield", (short) this.getYieldDNA()
				.getPassive());
		par1nbtTagCompound.setShort("AFertility", (short) this
				.getFertilityDNA().getActive());
		par1nbtTagCompound.setShort("PFertility", (short) this
				.getFertilityDNA().getPassive());
		par1nbtTagCompound.setShort("APotency", (short) this.getPotencyDNA()
				.getActive());
		par1nbtTagCompound.setShort("PPotency", (short) this.getPotencyDNA()
				.getPassive());

		if (!this.isHealthy()) {
			par1nbtTagCompound.setString("Disease", this.getCurrentDisease());
		}

	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
		return this.spawnBabyAnimal(par1EntityAgeable);
	}

	private int[] getHaploidData() {

		int[] haploid = new int[6];
		Random rand = new Random();

		if (rand.nextBoolean()) {
			haploid[0] = this.getMutationDNA().getActive();
		} else {
			haploid[0] = this.getMutationDNA().getPassive();
		}
		if (rand.nextBoolean()) {
			haploid[1] = this.getGrowDNA().getActive();
		} else {
			haploid[1] = this.getGrowDNA().getPassive();
		}
		if (rand.nextBoolean()) {
			haploid[2] = this.getResistanceDNA().getActive();
		} else {
			haploid[2] = this.getResistanceDNA().getPassive();
		}
		if (rand.nextBoolean()) {
			haploid[3] = this.getYieldDNA().getActive();
		} else {
			haploid[3] = this.getYieldDNA().getPassive();
		}
		if (rand.nextBoolean()) {
			haploid[4] = this.getFertilityDNA().getActive();
		} else {
			haploid[4] = this.getFertilityDNA().getPassive();
		}
		if (rand.nextBoolean()) {
			haploid[5] = this.getPotencyDNA().getActive();
		} else {
			haploid[5] = this.getPotencyDNA().getPassive();
		}

		return haploid;
	}

	@Override
	public EntityCow spawnBabyAnimal(EntityAgeable par1EntityAgeable) {

		return new BreedCow(this.worldObj, this.getHaploidData(),
				((BreedCow) par1EntityAgeable).getHaploidData());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);

	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, Integer.valueOf(0));
		this.dataWatcher.addObject(21, Integer.valueOf(0));
		this.dataWatcher.addObject(22, Integer.valueOf(0));
		this.dataWatcher.addObject(23, Integer.valueOf(0));
		this.dataWatcher.addObject(24, Integer.valueOf(0));
		this.dataWatcher.addObject(25, Integer.valueOf(0));
		this.dataWatcher.addObject(26, "");
	}

	@Override
	public DNASequence getMutationDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(20));
	}

	@Override
	public DNASequence getGrowDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(21));
	}

	@Override
	public DNASequence getResistanceDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(22));
	}

	@Override
	public DNASequence getYieldDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(23));
	}

	@Override
	public DNASequence getFertilityDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(24));
	}

	@Override
	public DNASequence getPotencyDNA() {
		return DNASequence.convert(this.dataWatcher.getWatchableObjectInt(25));
	}

	@Override
	public String getCurrentDisease() {
		return this.dataWatcher.getWatchableObjectString(26);
	}

	@Override
	public boolean isHealthy() {
		return (getCurrentDisease() == null);
	}

	@Override
	public Breedable setMutationDNA(DNASequence dna) {
		this.dataWatcher.updateObject(20,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setGrowDNA(DNASequence dna) {
		this.dataWatcher.updateObject(21,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setResistanceDNA(DNASequence dna) {
		this.dataWatcher.updateObject(22,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setYieldDNA(DNASequence dna) {
		this.dataWatcher.updateObject(23,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setFertilityDNA(DNASequence dna) {
		this.dataWatcher.updateObject(24,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setPotencyDNA(DNASequence dna) {
		this.dataWatcher.updateObject(25,
				(dna.getActive() << 16) | dna.getPassive());
		return this;
	}

	@Override
	public Breedable setCurrentDisease(String disease) {
		this.dataWatcher.updateObject(26, disease);
		return this;
	}

}
