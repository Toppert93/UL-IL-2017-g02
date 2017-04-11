package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;



import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

/**
 * The Enum EtCategory which holds the different categories.
 */
public enum EtExperienceRank implements JIntIs{
	
	/** A beginner coordianator*/
	Novice,
	/** A coordinator with moderate experience */
	Intermediate,
	/** A coordinator with a lot of experience */
	Expert;

	@Override
	public PtBoolean is() {
		
		return new PtBoolean(this.name() == EtExperienceRank.Novice.name() || this.name() == EtExperienceRank.Intermediate.name()||  this.name() == EtExperienceRank.Expert.name());
	}
	
}
	
