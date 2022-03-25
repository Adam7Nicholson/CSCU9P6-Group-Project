package Gate;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Management.AircraftManagementDatabase;

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

public class GateConsole extends JFrame implements ActionListener{
	private int gateNumber;

	private GateInfoDatabase gateDatabase;
	private AircraftManagementDatabase model;
	//UI-Elements
    private String title;

    private JPanel left;
    private JPanel center;
    private JPanel right;
    private JPanel bottom;

    //Panel - left
    private JList passengerList;
    
    //Panel - center
    private JLabel passengerListL;
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
	
    
    public GateConsole(GateInfoDatabase gateDatabase, Gate gate, String title)
    {
    	this.gateNumber = gate.getGateNumber();
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
    	left.setPreferredSize(new Dimension(120, 280));
    	passengerListL = new JLabel("Current Passengers:");
    	passengerList = new JList();
    	passengerList.setPreferredSize(new Dimension(120,225));
    	left.add(passengerListL);
    	left.add(passengerList);
    	window.add(left);
    	
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
        gateStatus.setText("Free");
        gateStatus.setEditable(false);
        planeStatus = new JTextField(14);
        planeStatus.setEditable(false);
        flightCode = new JTextField(14);
        flightCode.setEditable(false);
        flightFrom = new JTextField(14);
        flightFrom.setEditable(false);
        flightTo = new JTextField(14);
        flightTo.setEditable(false);
        nextStop = new JTextField(14);
        nextStop.setEditable(false);
        numberOfPassengers = new JTextField(14);
        numberOfPassengers.setEditable(false);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
