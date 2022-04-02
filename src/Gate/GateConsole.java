package Gate;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Management.AircraftManagementDatabase;
import Management.ManagementRecord;
import Management.ManagementRecord.Status;
import Passenger.PassengerDetails;

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

public class GateConsole extends JFrame implements ActionListener, Observer{
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
    private DefaultListModel passengersModel;

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


    public GateConsole(GateInfoDatabase gateDatabase, AircraftManagementDatabase model, Gate gate, String title)
    {
        this.gateNumber = gate.getGateNumber();
        this.title = title;
        this.gateDatabase = gateDatabase;
        this.model = model;
        setTitle(title);
        if(title.equalsIgnoreCase("gate1")){
            setLocation(765, 10);
        } else if(title.equalsIgnoreCase("gate2")){
            setLocation(765, 250);
        }
        else {
        	setLocation(765, 490);
        }
        setSize(650, 235);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());


        //Setting up the GUI
        //Panel - left
        left = new JPanel();
        left.setPreferredSize(new Dimension(120, 280));
        passengerListL = new JLabel("Current Passengers:");
        passengerList = new JList();
        passengerList.setPreferredSize(new Dimension(80,130));
        left.add(passengerListL);
        JScrollPane scrollPane = new JScrollPane(passengerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100,140));
        passengerList.setLayoutOrientation(JList.VERTICAL);
        left.add(scrollPane);
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
        gateStatus.setText("FREE");
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
        passengerName.setEditable(false);

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
        planeDocked.setEnabled(false);
        planeUnloaded = new JButton("Plane Unloaded");
        planeUnloaded.addActionListener(this);
        planeUnloaded.setEnabled(false);
        frtd = new JButton("Flight Ready to Depart");
        frtd.addActionListener(this);
        frtd.setEnabled(false);
        addPassenger  = new JButton("Add Passenger");
        addPassenger.addActionListener(this);
        addPassenger.setEnabled(false);

        bottom.add(planeDocked);
        bottom.add(planeUnloaded);
        bottom.add(frtd);
        bottom.add(addPassenger);

        window.add(bottom);

        setVisible(true);

        passengersModel = new DefaultListModel();
        passengerList.setModel(passengersModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Pressing the Plane Docked button - Gate becomes OCCUUPIED, MR becomes UNLOADING.
        if(e.getSource() == planeDocked) {
            if(model.getStatus(gateDatabase.getMCode(gateNumber)) == ManagementRecord.Status.TAXIING.ordinal() && gateDatabase.getStatus(gateNumber) == Gate.Status.RESERVED.ordinal()) {
                model.setStatus(gateDatabase.getMCode(gateNumber), ManagementRecord.Status.UNLOADING.ordinal());
                gateDatabase.docked(gateNumber);
                
                planeUnloaded.setEnabled(true);
                planeDocked.setEnabled(false);
            }
        }
        //Pressing the Plane Unloaded button - Passenger list cleared, MR becomes UNLOADED.
        if(e.getSource() == planeUnloaded) {
            if(model.getStatus(gateDatabase.getMCode(gateNumber)) == ManagementRecord.Status.UNLOADING.ordinal() && gateDatabase.getStatus(gateNumber) == Gate.Status.OCCUPIED.ordinal()) {
                model.setStatus(gateDatabase.getMCode(gateNumber), ManagementRecord.Status.READY_CLEAN_AND_MAINT.ordinal());
                model.clearPassengerList(gateDatabase.getMCode(gateNumber));
                planeUnloaded.setEnabled(false);
            }
        }

        //Pressing the addPassenger button - if the textField is not empty it adds the Details to the PassengerList until it is full.
        if(e.getSource() == addPassenger) {
            int maxPassengers = model.getMaxPassengers();
            if(model.getPassengerList(gateDatabase.getMCode(gateNumber)).size() < maxPassengers)
            {
                if(model.getStatus(gateDatabase.getMCode(gateNumber)) == ManagementRecord.Status.READY_PASSENGERS.ordinal() && gateDatabase.getStatus(gateNumber) == Gate.Status.OCCUPIED.ordinal()) {
                    if(!passengerName.getText().isEmpty()) {
                        PassengerDetails details = new PassengerDetails(passengerName.getText());
                        model.addPassenger(gateDatabase.getMCode(gateNumber), details);
                        passengerName.setText("");
                        frtd.setEnabled(true);
                    }
                    else JOptionPane.showMessageDialog(null, "Enter passenger's details!");
                }
            }
            else {
            	JOptionPane.showMessageDialog(null, "Passenger limit reached!");
            	passengerName.setText("");
            }
        }

        if(e.getSource() == frtd) {
            if(model.getStatus(gateDatabase.getMCode(gateNumber)) == ManagementRecord.Status.READY_PASSENGERS.ordinal()){
                model.setStatus(gateDatabase.getMCode(gateNumber), ManagementRecord.Status.READY_DEPART.ordinal());
                addPassenger.setEnabled(false);
                frtd.setEnabled(false);
                passengerName.setEditable(false);
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {

    	if(gateDatabase.getStatus(gateNumber) == Gate.Status.RESERVED.ordinal()) planeDocked.setEnabled(true);
    	
    	if(model.getStatus(gateDatabase.getMCode(gateNumber)) == ManagementRecord.Status.READY_PASSENGERS.ordinal()) {
    		passengerName.setEditable(true);
    		addPassenger.setEnabled(true);
    	}
        //Filling in the information in the correct field when there is a plane at the Gate.
        if(gateDatabase.getMCode(gateNumber) != -1)
        {
            gateStatus.setText(gateDatabase.getStringStatus(gateNumber));
            planeStatus.setText(model.getStringStatus(gateDatabase.getMCode(gateNumber)));
            flightCode.setText(model.getFlightCode(gateDatabase.getMCode(gateNumber)));
            flightFrom.setText(model.getItinirary(gateDatabase.getMCode(gateNumber)).getFrom());
            flightTo.setText(model.getItinirary(gateDatabase.getMCode(gateNumber)).getTo());
            nextStop.setText(model.getItinirary(gateDatabase.getMCode(gateNumber)).getNext());
            numberOfPassengers.setText(String.valueOf(model.getPassengerList(gateDatabase.getMCode(gateNumber)).size()));

            //Refreshes the list of Passengers
            refreshList(passengersModel,model.getPassengerList(gateDatabase.getMCode(gateNumber)).getList());
        } else {
        	
        	//Filling in the information in the correct fields when the Gate is Free
            gateStatus.setText(gateDatabase.getStringStatus(gateNumber));
            planeStatus.setText("");
            flightCode.setText("");
            flightFrom.setText("");
            flightTo.setText("");
            nextStop.setText("");
            numberOfPassengers.setText("");
            Vector<String> empty = new Vector<String>();
            refreshList(passengersModel, empty);
        }

    }

    /**
     * A public method that refreshes a list
     * @param model, vector
     */
    public void refreshList(DefaultListModel model, Vector vector){
        model.removeAllElements();
        for(int i = 0; i < vector.size(); i++){
            model.addElement(vector.get(i));
        }
    }
}
