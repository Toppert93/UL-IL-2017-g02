package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;



import java.math.BigInteger;
import java.security.SecureRandom;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class DtCaptcha, which holds a datatype of the captcha.
 */
public class DtCaptcha  extends DtString implements JIntIs{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 227L;

		/**
		 * Instantiates a new datatype captcha.
		 *
		 * @param s The primitive type of the string to create the datatype 
		 */
		public DtCaptcha(PtString s) {
			super(s);

		}
		
		/** The minimum length a captcha can be. */
		private int _minLength = 6;
		
		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#is()
		 */

		public boolean minLen = this.value.getValue().length() >= _minLength;

		public PtBoolean isCaptcha = new PtBoolean(minLen);
		
		public PtBoolean is(){
			return isCaptcha;
		}
		
		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#getExpectedDataStructure()
		 */
		public PtString getExpectedDataStructure(){
			return new PtString("Expected strucutre of the captcha is to have a minimum length of " + _minLength); 
		}
		
		/*
		 *  generates a new  captcha
		 * */
		public DtCaptcha generateNewCaptcha(){
			
			SecureRandom random = new SecureRandom();
			String randomString = new BigInteger(130, random).toString(32);
			randomString = randomString.substring(0, 6);
			DtCaptcha newcaptcha = new DtCaptcha(new PtString(randomString)); 
			
			return newcaptcha;
		}
}
