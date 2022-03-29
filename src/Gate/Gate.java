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

public class Gate extends JFrame implements ActionListener{

	enum Status {
		FREE,
		RESERVED,
		OCCUPIED
	}

	private int status = Status.FREE.ordinal();

	private int mCode;
	
	private GateInfoDatabase gateDatabase;
	
	//UI-Elements
    private String title;
    private int gateNumber;

    private JPanel left;
    private JPanel center;
    private JPanel right;
    private JPanel bottom;

    //Panel - left
    private JList passengerList;
    
    //Panel - center
    private JLabel gateStatusL;
    private JLabel planeStatusL;
    private JLabel flightCodeL;
    private JLabel flightFromL;
    private JLabel flightToL;
    private JLabel nextStopL;
    private JLabel numberOfPassengersL;
    private JLabel passengerNameL;

    //Panel - right
    private JTextField gateStatus;
    private JTextField planeStatus;
    private JTextField flightCode;
    private JTextField flightFrom;
    private JTextField flightTo;
    private JTextField nextStop;
    private JTextField numberOfPassengers;
    private JTextField passengerName;
    
    //Panel - bottom
    private JButton planeDocked;
    private JButton planeUnloaded;
    private JButton frtd; //flight ready to depart
    private JButton addPassenger;
	
    
    public Gate(GateInfoDatabase gateDatabase, String title)
    {
    	this.title = title;
    	this.gateDatabase = gateDatabase;
    	setTitle(title);
    	setLocation(720, 10);
    	setSize(650, 325);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	Container window = getContentPane();
    	window.setLayout(new FlowLayout());
    	
    	
    	//Setting up the GUI
    	//Panel - left
    	left = new JPanel();
    	left.setPreferredSize(new Dimension(120, 200));
    	passengerList = new JList();
    	passengerList.setPreferredSize(new Dimension(120,225));
    	window.add(passengerList);
    	
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