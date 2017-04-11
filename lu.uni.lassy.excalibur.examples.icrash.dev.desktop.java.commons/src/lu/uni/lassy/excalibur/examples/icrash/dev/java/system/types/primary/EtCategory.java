package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;



import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

/**
 * The Enum EtCategory which holds the different categories.
 */
public enum EtCategory  implements JIntIs{
	
	
	Hospital, 

	PoliceStation, 

	Garage,
	
	Parking,
	
	InsuranceOffice,
	
	FireStation;
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#is()
	 */
	public PtBoolean is(){
		return new PtBoolean(this.name() == EtCategory.Hospital.name() || this.name() == EtCategory.PoliceStation.name()||  this.name() == EtCategory.Garage.name() || this.name() == EtCategory.Parking.name() || this.name() == EtCategory.InsuranceOffice.name()||  this.name() == EtCategory.FireStation.name());
	}
}
