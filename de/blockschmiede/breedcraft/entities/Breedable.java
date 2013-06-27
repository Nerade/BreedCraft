package de.blockschmiede.breedcraft.entities;

import de.blockschmiede.breedcraft.util.DNASequence;

public interface Breedable {
	
	//float mutationrate,growrate,resistence,yield,fertility;
	public DNASequence getMutationDNA();
	public Breedable setMutationDNA(DNASequence dna);
	public DNASequence getGrowDNA();
	public Breedable setGrowDNA(DNASequence dna);
	public DNASequence getResistanceDNA();
	public Breedable setResistanceDNA(DNASequence dna);
	public DNASequence getYieldDNA();
	public Breedable setYieldDNA(DNASequence dna);
	public DNASequence getFertilityDNA();
	public Breedable setFertilityDNA(DNASequence dna);
	public DNASequence getPotencyDNA();
	public Breedable setPotencyDNA(DNASequence dna);
	public String getCurrentDisease();
	public Breedable setCurrentDisease(String disease);
	public boolean isHealthy();
}
