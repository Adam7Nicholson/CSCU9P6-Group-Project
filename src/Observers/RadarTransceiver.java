package Observers;
/**
 * @author 2823424
 * @author
 * @author
 * @author
 * @author
 * @author
 */
import Flights.FlightDescriptor;
import Flights.Itinerary;
import Management.AircraftManagementDatabase;
import Management.ManagementRecord;
import Passenger.PassengerDetails;
import Passenger.PassengerList;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * @stereotype boundary/view/controller
 * @author 
 *
 */
public class RadarTransceiver extends JFrame
        implements ActionListener, Observer {


    private String selectedPlane = "";
    private int selectedPlaneIndex;
    private int mCode = -1;
    private int MAX_PASSENGERS;


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
    private JList<String> addPassengersList;
    private DefaultListModel addPassengerModel;
    private Vector<String> addPassengerVector;

    //will be in panel4
    private JLabel currentPlanes;
    private JList currentPlanesList;
    private DefaultListModel currentPlanesModel;
    private Vector<String> currentPlanesVector;

    private JButton leaveAirspace;

    //will be in panel 5
    private JLabel passengers;
    private JList boardedPassengersList;
    private Vector<String> boardedPassengersVector;
    private DefaultListModel boardedPassengersModel;

    /**
     * A constructor
     * @param model the model (AircraftManagementDatabase) that RadarTransceiver receive inputs and sends messages to
     * @param title the title of this screen
     */
    public RadarTransceiver(AircraftManagementDatabase model, String title) {

        addPassengerVector = new Vector<String>();
        currentPlanesVector = new Vector<String>();
        boardedPassengersVector = new Vector<String>();

        // Record reference to the model
        this.model = model;
        this.title = title;
        // Configure the window
        setTitle("Radar Transceiver");
        setLocation(5,10);
        setSize(700,270);
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
        detectFlight.setEnabled(false);
        panel2.add(detectFlight);
        detectFlight.addActionListener(this);
        window.add(panel2);

        //panel 3 components
        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(100,300));
        addPassengersList = new JList(addPassengerVector);
        addPassengersList.setPreferredSize(new Dimension(100,130));
        panel3.add(addPassengersList);
        window.add(panel3);

        //panel4 components
        panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(130,300));
        currentPlanes = new JLabel("Current Planes");
        currentPlanesList = new JList(currentPlanesVector);
        JScrollPane scrollPane = new JScrollPane(currentPlanesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(120,120));
        currentPlanesList.setLayoutOrientation(JList.VERTICAL);

        currentPlanesList.setPreferredSize(new Dimension(120,160));
        currentPlanesList.setSelectedIndex(0);
        //A mouse listener that enables to select a MR from the currentPlanesList
        currentPlanesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        selectedPlane = item.toString();
                        selectedPlaneIndex = model.getMCode(selectedPlane);
                        mCode = model.getMCode(selectedPlane);
                        if(mCode != -1){
                            PassengerList passengerList = model.getPassengerList(mCode);
                            boardedPassengersVector = passengerList.getList();
                            refreshList(boardedPassengersModel, boardedPassengersVector);

                            //enabling the leave Airspace button only for MRs with status IN_TRANSIT or DEPARTING_THROUGH_LOCAL_AIRSPACE
                        } if(model.getStatus(mCode) == ManagementRecord.Status.IN_TRANSIT.ordinal() || model.getStatus(mCode) == ManagementRecord.Status.DEPARTING_THROUGH_LOCAL_AIRSPACE.ordinal()){
                            leaveAirspace.setEnabled(true);
                        } else {
                            leaveAirspace.setEnabled(false);
                        }

                        }
                    }

            }
        });
        panel4.add(scrollPane);

        leaveAirspace = new JButton("Leave Airspace");
        leaveAirspace.addActionListener(this);
        leaveAirspace.setEnabled(false);
        panel4.add(leaveAirspace);
        window.add(panel4);

        //panel 5 components
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(120,300));
        passengers= new JLabel("Passengers");
        boardedPassengersList = new JList(boardedPassengersVector);
        boardedPassengersList.setPreferredSize(new Dimension(120,110));
        panel5.add(passengers);
        panel5.add(boardedPassengersList);
        window.add(panel5);

        // Display the frame
        setVisible(true);

        addPassengerModel = new DefaultListModel();
        addPassengersList.setModel(addPassengerModel);
        currentPlanesModel = new DefaultListModel();
        currentPlanesList.setModel(currentPlanesModel);
        boardedPassengersModel = new DefaultListModel();
        boardedPassengersList.setModel(boardedPassengersModel);
    } // constructor

    /**
     *A method that is invoked whenever an action happens (a button click)
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Detect FLight button is clicked - Creating all the necessary objects for the MR and setting it's status to IN_TRANSIT or WANTING_TO_LAND depending on nextStop
        if (e.getSource() == detectFlight){

            // if there are no free MRs, show a message
            if(currentPlanesVector.size() == 10){
                JOptionPane.showMessageDialog(null, "There are no FREE Management Records");
            } else {
                Itinerary itinerary = new Itinerary(flightFromTextField.getText(), flightToTextField.getText(),nextStopTextField.getText());
                PassengerList passList = new PassengerList();
                for(int i = 0; i < addPassengerVector.size(); i++){
                    PassengerDetails details = new PassengerDetails(addPassengerVector.get(i));
                    passList.addPassenger(details);
                }
                FlightDescriptor fd = new FlightDescriptor(flightCodeTextField.getText(), itinerary, passList);
                model.radarDetect(fd);

            }
            flightCodeTextField.setEnabled(true);
            flightToTextField.setEnabled(true);
            flightFromTextField.setEnabled(true);
            nextStopTextField.setEnabled(true);
            clearScreen();

            addPassengerVector.clear();
            refreshList(addPassengerModel, addPassengerVector);

        }
        // Add Passenger button is clicked - Adding passengers to the Jlist of passengers
        if(e.getSource()== addPassenger){
            //input validation  case 1: if passenger's name is provide but the flight details are not complete
            if((flightCodeTextField.getText().isEmpty() || flightToTextField.getText().isEmpty() || flightFromTextField.getText().isEmpty()
                    || nextStopTextField.getText().isEmpty()) && !passengerNameTextField.getText().isEmpty()){
                passengerNameTextField.setText("Please provide flight details");
                //input validation case 2: if flight details are complete but passenger's name is not provided
            } else if (!flightCodeTextField.getText().isEmpty() && !flightToTextField.getText().isEmpty() && !flightFromTextField.getText().isEmpty()
                    && !nextStopTextField.getText().isEmpty() && passengerNameTextField.getText().isEmpty()){
                passengerNameTextField.setText("Enter a passenger's name");
                //input validation case 3: if passenger's name and at least 1 of flight fields are empty
            } else if (flightCodeTextField.getText().isEmpty() || flightToTextField.getText().isEmpty() || flightFromTextField.getText().isEmpty()
                    || nextStopTextField.getText().isEmpty() || passengerNameTextField.getText().isEmpty()){
                passengerNameTextField.setText("Please provide full details");

                // in this case the passengers are added successfully
            } else{
                MAX_PASSENGERS = model.getMaxPassengers();
                if(addPassengerVector.size() >= MAX_PASSENGERS){
                    passengerNameTextField.setText("Maximum Passengers reached");
                } else {
                    addPassengerVector.addElement(passengerNameTextField.getText());
                    ArrayList<String> occupiedMRs = model.getLatcMRs();
                    for (String code : occupiedMRs) {
                        if (flightCodeTextField.getText().equals(code)) {
                            clearScreen();
                            passengerNameTextField.setText("Use unique flight code");
                            return;
                        }
                    }
                    passengerNameTextField.setText("");
                    refreshList(addPassengerModel, addPassengerVector);
                    if (addPassengerVector.size() > 0) {
                        detectFlight.setEnabled(true);
                        flightCodeTextField.setEnabled(false);
                        flightToTextField.setEnabled(false);
                        flightFromTextField.setEnabled(false);
                        nextStopTextField.setEnabled(false);
                    }
                }
            }
        }

        // Leave Airspace button is clicked - Changes the status of the selected MR to FREE
        if(e.getSource()== leaveAirspace){
            model.radarLostContact(selectedPlaneIndex);

            boardedPassengersVector.clear();
            refreshList(boardedPassengersModel, boardedPassengersVector);
        }
    }

    /**
     * A method that is automatically called if there are any changes to the model
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        currentPlanesVector = model.getDetectedMRs();
        refreshList(currentPlanesModel, currentPlanesVector);


        detectFlight.setEnabled(false);
        leaveAirspace.setEnabled(false);
    }

    /**
     * A method that refreshes a JList
     * @param model
     * @param vector
     */
    public void refreshList(DefaultListModel model, Vector vector){
        model.removeAllElements();
        for(int i = 0; i < vector.size(); i++){
            model.addElement(vector.get(i));
        }
    }

    /**
     * Clears the text fields for the input data
     */
    public void clearScreen(){
        flightCodeTextField.setText("");
        flightToTextField.setText("");
        flightFromTextField.setText("");
        nextStopTextField.setText("");
    }

}
