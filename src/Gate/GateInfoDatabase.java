package Gate;

import java.util.List;
import java.util.Observable;
import java.util.Vector;

import Gate.Gate.Status;
import Management.ManagementRecord;

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

public class GateInfoDatabase extends Observable{
	private Vector<Gate> gates = new Vector<Gate>();
	public int maxGateNumber = 3;

	/**
	 * Add a gate to the Database.
	 * @param g
	 */
	public void addGate(Gate g) {
		gates.add(g);
		setChanged();
		notifyObservers();
	}

	/**
	 * This method returns the number of gates in the system.
	 * @return maxGateNumber
	 */
	public int getNumberOfGates()
	{
		return maxGateNumber;
	}

	/**
	 * Returns the status of a selected gate.
	 * @param gateNumber
	 * @return Int value of given gate's status, if one exists.
	 */
	public int getStatus(int gateNumber) {
		for(Gate g : this.gates) {
			if (gateNumber == g.getGateNumber())return g.getStatus();
		}
		return -1;
	}
	
	/**
	 * Returns the status of a selected gate in String.
	 * @param gateNumber
	 * @return String value of given gate's status, if one exists.
	 */
	public String getStringStatus(int gateNumber) {
		return Status.values()[getStatus(gateNumber)].toString();
	}
	
	/**
	 * Returns the statuses all the gates in the Database.
	 * @return Int[] value of all the gates' statuses.
	 */
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
	 * Returns the Management Record code associated to the gate.
	 * @param gateNumber
	 * @return Int value of the mCode related to the given gate, if it exists.
	 */
	public int getMCode(int gateNumber) {
		for(Gate g : this.gates) {
			if (gateNumber == g.getGateNumber())return g.getMCode();
		}
		return -1;
	}

	/**
	 * Makes a connection between a Gate and an Management Record
	 * by storing mCode into the gate with gateNumber.
	 * When this is finished observers are notified.
	 * @param gateNumber, mCode
	 */
	public void allocate(int gateNumber, int mCode) {
		for (Gate g : gates) {
			if (g.getGateNumber() == gateNumber) {
				g.allocate(mCode);
				setChanged();
				notifyObservers();
				break;
			}
		}
	}

	/**
	 * The Gate with gateNumber has its status updated.
	 * When this is finished observers are notified.
	 * @param gateNumber
	 */
	public void docked(int gateNumber) {
		for (Gate g : gates) {
			if (g.getGateNumber() == gateNumber) {
				g.docked();
				setChanged();
				notifyObservers();
				break;
			}
		}
	}

	/**
	 * This method is called when the Management Record no longer is present at the Gate with gateNumber.
	 * When this is finished observers are notified.
	 * @param gateNumber
	 */
	public void departed(int gateNumber) {
		for (Gate g : gates) {
			if (g.getGateNumber() == gateNumber) {
				g.departed();
				setChanged();
				notifyObservers();
				break;
			}
		}
	}


}
