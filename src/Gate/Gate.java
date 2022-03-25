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
	public static int gateStatus; 
	private int gateNumber;
	private int mCode;
	
	private GateInfoDatabase gateDatabase;
	
	//UI-Elements
	
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
		gateStatus = status;
	}
	/**
	 * 
	 * @param gateNumber
	 */
	public int getGateNumber()
	{
		return this.gateNumber;
	}
	/**
	 * 
	 * @param mCode
	 */
	public void allocate (int mCode) {
		
	}
	
	/**
	 * 
	 */
	public void docked() {
		status = Status.OCCUPIED.ordinal();
		gateStatus = status;
	}
	
	/**
	 * 
	 */
	public void departed() {
		status = Status.FREE.ordinal();
		gateStatus = status;
	}
	
	/**
	 * 
	 */
	public void refresh() {
		
	}

	
}
