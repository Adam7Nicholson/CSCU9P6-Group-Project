package Gate;

import java.util.List;


/**
* @stereotype model
* @author 2819600
*/

public class GateInfoDatabase {
	private Gate[] gates;
	public int maxGateNumber = 2;
	
	
	/**
	 * 
	 * @param gateNumber
	 * @param mCode
	 */
	public void allocate(int gateNumber, int mCode) {
		
	}
	
	/**
	 * 
	 * @param gateNumber
	 * @param mCode
	 */
	public void docked(int gateNumber, int mCode) {
		
	}
	
	/**
	 * 
	 * @param gateNumber
	 * @param mCode
	 */
	public void departed(int gateNumber, int mCode) {
		
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	//GETTERS 
	/**
	 * 
	 * @param gateNumber
	 * @return
	 */
	public int getStatus(int gateNumber) {
		return this.getStatus(gateNumber);
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getStatuses() {
		int statuses[] = {};
		for (Gate g : this.gates) statuses[statuses.length] = g.getStatus();
		return statuses;
	}
	//SETTERS
}
