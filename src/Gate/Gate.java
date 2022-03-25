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

    	//Panel - center
    	center = new JPanel(new FlowLayout(FlowLayout.LEFT,20,7));
    	center.setPreferredSize(new Dimension(140,280));
        gateStatusL = new JLabel("Gate Status:");
        planeStatusL = new JLabel("Plane Status:");
        flightCodeL = new JLabel("Flight Code:");
        flightFromL = new JLabel("Flight From:");
        flightToL = new JLabel("Flight To:");
        nextStopL = new JLabel("Next Stop:");
        numberOfPassengersL = new JLabel("Number of Passengers:");
        passengerNameL = new JLabel("Passenger Name:");
        
        center.add(gateStatusL);
        center.add(planeStatusL);
        center.add(flightCodeL);
        center.add(flightFromL);
        center.add(flightToL);
        center.add(nextStopL);
        center.add(numberOfPassengersL);
        center.add(passengerNameL);
        
        window.add(center);
        
    	//Panel - right
        right = new JPanel(new FlowLayout(FlowLayout.LEFT,0,3));
        right.setPreferredSize(new Dimension(175,280));
        gateStatus = new JTextField(14);
        planeStatus = new JTextField(14);
        flightCode = new JTextField(14);
        flightFrom = new JTextField(14);
        flightTo = new JTextField(14);
        nextStop = new JTextField(14);
        numberOfPassengers = new JTextField(14);
        passengerName = new JTextField(14);
        
        right.add(gateStatus);
        right.add(planeStatus);
        right.add(flightCode);
        right.add(flightFrom);
        right.add(flightTo);
        right.add(nextStop);
        right.add(numberOfPassengers);
        right.add(passengerName);
        
        window.add(right);
        
        //Panel - bottom
    	bottom = new JPanel(new FlowLayout(FlowLayout.CENTER,0,7));
    	bottom.setPreferredSize(new Dimension(160,280));
    	planeDocked = new JButton("Plane Docked");
    	planeDocked.addActionListener(this);
        planeUnloaded = new JButton("Plane Unloaded");
        planeUnloaded.addActionListener(this);
        frtd = new JButton("Flight Ready to Depart"); 
        frtd.addActionListener(this);
        addPassenger  = new JButton("Add Passenger");
        addPassenger.addActionListener(this);
        
        bottom.add(planeDocked);
        bottom.add(planeUnloaded);
        bottom.add(frtd);
        bottom.add(addPassenger);
        
        window.add(bottom);
        
        setVisible(true);
}
    
	/**
	 * Returns the current status code of the aircraft gate
	 * @return Integer Instance Variable 'status'
	 */
	public int getStatus() {
		return this.status;
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
		
	}
	
	/**
	 * 
	 */
	public void departed() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}