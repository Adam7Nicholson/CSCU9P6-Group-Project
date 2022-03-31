package Gate;

/**
* @author 2819600
* @author 2816391
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
