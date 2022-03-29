package Observers;
import Management.AircraftManagementDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @stereotype boundary/view/controller
 * @author 
 *
 */
public class RadarTransceiver extends JFrame implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    // will be in panel1
    private JLabel flightCode;
    private JLabel flightTo;
    private JLabel flightFrom;
    private JLabel nextStop;
    private JLabel passengerName;

    // will be in panel2
    private JTextField flightCodeTextField;
    private JTextField flightToTextField;
    private JTextField flightFromTextField;
    private JTextField nextStopTextField;
    private JTextField passengerNameTextField;
    private JButton addPassenger;
    private JButton detectFlight;

    // will be in panel3
    //TODO WHAT DATA SHOULD IT SHOW???
    private JList UNKNOWN;

    //will be in panel4
    private JLabel currentPlanes;
    private JList currentPlanesList;
    private JButton leaveAirspace;

    //will be in panel 5
    private JLabel passengers;
    private JList passengersList;


    /**
     *
     * @param model the model (AircraftManagementDatabase) that RadarTransceiver receive inputs and sends messages to
     * @param title the title of this screen
     */
    public RadarTransceiver(AircraftManagementDatabase model, String title) {

        // Record reference to the model
        this.model = model;
        this.title = title;
        // Configure the window
        setTitle("Radar Transceiver");
        setLocation(40,40);
        setSize(680,270);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // Set up input GUI
        // panel 1 components
        panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
        panel1.setPreferredSize(new Dimension(100,300));
        flightCode = new JLabel("Flight Code:");
        flightTo = new JLabel("Flight To:");
        flightFrom = new JLabel("Flight From:");
        nextStop = new JLabel("Next Stop:");
        passengerName = new JLabel("Passenger Name:");
        panel1.add(flightCode);
        panel1.add(flightTo);
        panel1.add(flightFrom);
        panel1.add(nextStop);
        panel1.add(passengerName);
        window.add(panel1);

        //panel 2 components
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,7));
        panel2.setPreferredSize(new Dimension(170,300));
        flightCodeTextField = new JTextField(14);
        flightToTextField = new JTextField(14);
        flightFromTextField = new JTextField(14);
        nextStopTextField = new JTextField(14);
        passengerNameTextField = new JTextField(14);
        panel2.add(flightCodeTextField);
        panel2.add(flightToTextField);
        panel2.add(flightFromTextField);
        panel2.add(nextStopTextField);
        panel2.add(passengerNameTextField);

        addPassenger = new JButton("Add Passenger");
        panel2.add(addPassenger);
        addPassenger.addActionListener(this);
        detectFlight = new JButton("Detect Flight");
        panel2.add(detectFlight);
        detectFlight.addActionListener(this);
        window.add(panel2);

        //panel 3 components
        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(100,300));
        UNKNOWN = new JList();
        UNKNOWN.setPreferredSize(new Dimension(100,130));
        panel3.add(UNKNOWN);
        window.add(panel3);

        //panel4 components
        panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(130,300));
        currentPlanes = new JLabel("Current Planes");
        currentPlanesList = new JList();
        currentPlanesList.setPreferredSize(new Dimension(120,78));
        panel4.add(currentPlanes);
        panel4.add(currentPlanesList);

        leaveAirspace = new JButton("Leave Airspace");
        panel4.add(leaveAirspace);
        leaveAirspace.addActionListener(this);
        window.add(panel4);

        //panel 5 components
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(90,300));
        passengers= new JLabel("Passengers");
        passengersList = new JList();
        passengersList.setPreferredSize(new Dimension(120,110));
        panel5.add(passengers);
        panel5.add(passengersList);
        window.add(panel5);

        // Display the frame
        setVisible(true);

    } // constructor

    /**
     *
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
