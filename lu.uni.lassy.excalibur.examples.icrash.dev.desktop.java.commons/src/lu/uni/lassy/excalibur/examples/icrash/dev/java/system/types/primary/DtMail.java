package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class DtMail, which holds a datatype of the mail.
 */
public class DtMail  extends DtString implements JIntIs{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 227L;

		/**
		 * Instantiates a new datatype mail.
		 *
		 * @param s The primitive type of the string to create the datatype 
		 */
		public DtMail(PtString s) {
			super(s);

		}
		
		/** The minimum length a mail can be. */
		private int _minLength = 6;
		
		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#is()
		 */
		public boolean contains = this.value.getValue().contains("@");
		public boolean minLen = this.value.getValue().length() >= _minLength;
		public boolean twoConstraints = contains && minLen;
		public PtBoolean isMail = new PtBoolean(twoConstraints);
		
		public PtBoolean is(){
			return isMail;
		}
		
		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#getExpectedDataStructure()
		 */
		public PtString getExpectedDataStructure(){
			return new PtString("Expected strucutre of the mail is to have a minimum length of " + _minLength + "and it should be containing the string '@'"); 
		}
}
