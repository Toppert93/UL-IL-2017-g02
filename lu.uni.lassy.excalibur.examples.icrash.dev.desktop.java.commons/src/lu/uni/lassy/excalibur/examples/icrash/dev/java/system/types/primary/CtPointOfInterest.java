package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class CtPointOfInterest implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	/** The id of the Point of interest. */
	public DtPointOfInterestID id;
	
	/** The category of Point Of interest. */
	public EtCategory Category;

	/** The location of the accident that is associated to the Point of interest. */
	public DtGPSLocation location;
	
	/** The comment associated with the PointOfInterest. Often refered to as the report. */
	public DtDescription Description;

	

	/**
	 * Initialises the PointOfInterest.
	 *
	 * @param aId the id of the Point of interest
	 * @param aCategory the category of the Point of interest
	 
	 * @param aLocation the location of the accident associated with the Point of interest
	
	 * @param aDescription the description of the Point of interest
	 * @return the success of the initialisation
	 */
	public PtBoolean init(DtPointOfInterestID aid, EtCategory aCategory,
			 DtGPSLocation aLocation,
			DtDescription aDescription) {

		id = aid;
		Category = aCategory;	
		location = aLocation;
		Description = aDescription;

		return new PtBoolean(true);

	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof CtPointOfInterest))
			return false;
		CtPointOfInterest aCtPointOfInterest = (CtPointOfInterest)obj;
		if (!aCtPointOfInterest.id.value.getValue().equals(this.id.value.getValue()))
			return false;
		if (!aCtPointOfInterest.Description.value.getValue().equals(this.Description.value.getValue()))
			return false;
		if (aCtPointOfInterest.location.latitude.value.getValue() != this.location.latitude.value.getValue())
			return false;
		if (aCtPointOfInterest.location.longitude.value.getValue() != this.location.longitude.value.getValue())
			return false;
		if (!aCtPointOfInterest.Category.equals(this.Category))
			return false;
		return true;
	}
}
