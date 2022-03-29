package Gate;

import java.util.List;
import java.util.Vector;


/**
* @stereotype model
* @author 2819600
* @author 2816391
*/

public class GateInfoDatabase {
	private Vector<Gate> gates = new Vector<Gate>();
	public int maxGateNumber = 2;
	

	public void addGate(Gate g) {
		gates.add(g);
	}
	
	public int getStatus(int gateNumber) {
		for(Gate g : this.gates) {
			if (gateNumber == g.getGateNumber())return g.getStatus();
		}
		return -1;
	}
	
	public int[] getStatuses() {
		int statuses[] = new int[maxGateNumber];
		int i = 0;
		for (Gate g : this.gates) {
				statuses[i] = g.getStatus();
				i++;
			}
		i = 0;
		return statuses;
	}

	
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
