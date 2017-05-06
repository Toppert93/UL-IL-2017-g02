package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DtExpPoints extends DtInteger implements JIntIs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 227L;

	public DtExpPoints(PtInteger v) {
		super(v);
	
	}
	
	private int _minNumber = 0;
	private int _maxNumber = 120;

	@Override
	public PtBoolean is() {
		
		return new PtBoolean(this.value.getValue() >= _minNumber && this.value.getValue() <= _maxNumber);
	}
	
	public PtString getExpectedDataStructure(){
		return new PtString("Expected strucutre of the experience points is to have a minimum size of " + _minNumber + " and a maximum size of " + _maxNumber); 
	}

}
