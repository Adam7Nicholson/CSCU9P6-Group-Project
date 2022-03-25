package Gate;

import java.util.List;
import java.util.Vector;

/*Please put your student ID in so proper accreditation can be given for your work. 
Ensure it is only your Student ID and *not* your name as marking is done anonymously.
Please only add your name on this class if you have worked on this class.
Work can take any form from refactoring to code writing and anything in between, of course
You should always take credit for your work.*/
/**
* @author 2819600
* @author 2816391
* @author 
* @author 
* @author 
* @author 
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
	
	public void allocate(int gateNumber, int mCode) {
		
	}
	
	public void docked(int gateNumber, int mCode) {
		
	}
	
	public void departed(int gateNumber, int mCode) {
		
	}
	
	
}
