package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class of the datatype PointOfInterest ID.
 */
public class DtPointOfInterestID extends DtString implements JIntIs{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 227L;
	
		/**
		 * Instantiates a new datatype PointOfInterest id.
		 *
		 * @param id The string to use to create the PointOfInterest ID
		 */
		public DtPointOfInterestID(PtString id) {
			super(id);
		}
		
		/** The minimum length of a PointOfInterest ID that is not acceptable. */
		private int _minLength = 0;
		
		/** The maximum length of a PointOfInterest ID that is acceptable. */
		private int _maxLength = 30;

		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#is()
		 */
		public PtBoolean is(){
			return new PtBoolean(this.value.getValue().length() > _minLength && this.value.getValue().length() <= _maxLength);
		}
}


