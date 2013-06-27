package de.blockschmiede.breedcraft.util;

public class DNASequence {
	//public static enum DNAStatus {ACTIVE,PASSIVE};
	
		private int active;
		private int passive;
		private boolean intermediate;
		private boolean positiveDominat;
	
	public DNASequence(int active,int passive,boolean intermediate,boolean positiveDominate) {
		this.active=active;
		this.passive=passive;
		this.intermediate=intermediate;
	}
	
	public DNASequence(int active,int passive) {
		this(active,passive,true,true);
	}
	public DNASequence(int active,int passive,boolean intermediate) {
		this(active,passive,intermediate,true);
	}
	
	public static DNASequence convert(int input){
		return (new DNASequence(input>>16,(input<<16)>>16));
	}
	
	public float getResulting(){
		if(!intermediate){
			if(this.positiveDominat){
				return (active&passive);
			} else {
				return (active|passive);
			}
		} else {
			return ((active + passive)/2);
		}
	}
	
	public boolean isIntermediate(){
		return intermediate;
	}
	public int getActive(){
		return active;
	}
	public int getPassive(){
		return passive;
	}
	
	public boolean isPositiveDominant(){
		return this.positiveDominat;
	}
	
	public DNASequence setActive(int active){
		this.active=active;
		return this;
	}
	public DNASequence setPassive(int passive){
		this.passive=passive;
		return this;
	}
	
}
