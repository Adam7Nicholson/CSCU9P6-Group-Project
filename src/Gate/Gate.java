package Gate;
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

import Gate.GateInfoDatabase;
import Management.ManagementRecord;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gate{

	enum Status {
		FREE,
		RESERVED,
		OCCUPIED
	}

	private int status = Status.FREE.ordinal();
	private int gateNumber;
	private int mCode = -1;
	private GateInfoDatabase gateDatabase;

	public Gate(int gateNumber)
	{
		this.gateNumber = gateNumber;
	}

	/**
	 * Returns the current status code of the aircraft gate
	 * @return Integer Instance Variable 'status'
	 */
	public int getStatus() {
		return this.status;
	}

	/*
	 * Sets the current status of the gate
	 */
	public void setStatus(int statusNumber) {
		status = statusNumber;
	}
	/**
	 * Returns the Gate Number.
	 */
	public int getGateNumber()
	{
		return this.gateNumber;
	}


	/**
	 * Assigns the ManagementRecord with code - mCode to the gate.
	 * @param mCode
	 */
	public void allocate (int mCode) {
		this.mCode = mCode;
		status = Status.RESERVED.ordinal();
	}

	/**
	 * Returning the mCode related to this gate.
	 * @return Int value of the MR code related to this Gate.
	 */
	public int getMCode() {
		return this.mCode;
	}

	/**
	 * Sets the status of the gate to OCCUPIED
	 */
	public void docked() {
		status = Status.OCCUPIED.ordinal();
	}

	/**
	 * Sets the status of the gate to FREE
	 */
	public void departed() {
		status = Status.FREE.ordinal();
		mCode = -1;
	}

}
