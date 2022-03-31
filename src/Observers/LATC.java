package Observers;

/**
 * @author 2823424
 * @author
 * @author
 * @author
 * @author
 * @author
 */
import Management.AircraftManagementDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * @stereotype boundary/view/controller
 * @author 
 *
 */
public class LATC extends JFrame
        implements ActionListener, Observer{

    private int selectedPlaneIndex;
    private String selectedPlane = "";
    private String selectedPlanesDetails = "";
    int mCode = -1;
    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel planes;
    private JList planesList;
    private DefaultListModel planesListModel;
    private Vector<String> planesVector;

    //will be in panel 2
    private JLabel controls;
    private JLabel inbound;
    private JLabel outbound;
    private JLabel planeDetails;
    private JButton allowApproachClearance;
    private JButton confirmPlaneHasLanded;
    private JButton allocateDepartureSlot;
    private JButton permitTakeoff;
    private JTextArea planeDetailsArea;


    private String gate = "";

    /**
     *
     * @param model the model (AircraftManagementDatabase) that LATC receive inputs and sends messages to
     * @param title the title of this screen
     */
    public LATC(AircraftManagementDatabase model, String title){

        planesVector = new Vector<String>();

        this.model = model;
        this.title = title;

        setTitle("Local Air Traffic Controller");
        setLocation(5,300);
        setSize(380,510);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // contents of panel 1
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(100,500));
        planes = new JLabel("Planes");
        planesList = new JList();
        planesList.setPreferredSize(new Dimension(90,389));

        planesList.setSelectedIndex(0);
        //A mouse listener that enables to select a MR from the planesList
        planesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        selectedPlane = item.toString();
                        selectedPlaneIndex = model.getMCode(selectedPlane);
                        mCode = model.getMCode(selectedPlane);

                        //Displaying the correct details of the selected MR
                        if(model.getGate(mCode) == -1){
                            gate = "N/A";
                        } else {
                            gate = "Gate " + model.getGate(mCode);
                        }
                        selectedPlanesDetails = "Flight code: " + model.getFlightCode(mCode) + "\n" +
                                "Status: " + model.getStringStatus(mCode) + "\n" +
                                "Gate #: " + gate + "\n" +
                                "# of passengers: " + model.getPassengerList(mCode).size() + "\n" +
                                "To: " + model.getItinirary(mCode).getTo() + "\n" +
                                "From: " + model.getItinirary(mCode).getFrom() + "\n" +
                                "Next: " + model.getItinirary(mCode).getNext();
                        planeDetailsArea.setText(selectedPlanesDetails);

                        //Enabling buttons depending on the selected MR's status
                        if(model.getStatus(mCode) == 3) allowApproachClearance.setEnabled(true);
                        if(model.getStatus(mCode) == 4) confirmPlaneHasLanded.setEnabled(true);
                        if(model.getStatus(mCode) == 15) allocateDepartureSlot.setEnabled(true);
                        if(model.getStatus(mCode) == 17) permitTakeoff.setEnabled(true);

                    }
                }

            }
        });
        panel1.add(planes);
        panel1.add(planesList);
        window.add(panel1);

        //contents of plane 2
        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,7));
        panel2.setPreferredSize(new Dimension(200,460));

        // Horizontal lines used as separators between other components
        JSeparator line1 = new JSeparator(SwingConstants.HORIZONTAL);
        line1.setPreferredSize(new Dimension(200,10));
        JSeparator line2 = new JSeparator(SwingConstants.HORIZONTAL);
        line2.setPreferredSize(new Dimension(200,10));
        JSeparator line3 = new JSeparator(SwingConstants.HORIZONTAL);
        line3.setPreferredSize(new Dimension(200,10));

        controls = new JLabel("Controls");
        inbound = new JLabel("Inbound");
        outbound = new JLabel("Outbound");
        planeDetails = new JLabel("Plane Details");

        allowApproachClearance = new JButton("Allow Approach Clearance");
        allowApproachClearance.addActionListener(this);
        allowApproachClearance.setEnabled(false);
        confirmPlaneHasLanded = new JButton("Confirm plane has landed");
        confirmPlaneHasLanded.addActionListener(this);
        confirmPlaneHasLanded.setEnabled(false);
        allocateDepartureSlot = new JButton("Allocate Departure Slot");
        allocateDepartureSlot.addActionListener(this);
        allocateDepartureSlot.setEnabled(false);
        permitTakeoff = new JButton("Permit Takeoff");
        permitTakeoff.addActionListener(this);
        permitTakeoff.setEnabled(false);

        planeDetailsArea = new JTextArea(7,16);
        planeDetailsArea.setEditable(false);

        panel2.add(controls);
        panel2.add(line1);
        panel2.add(inbound);
        panel2.add(allowApproachClearance);
        panel2.add(confirmPlaneHasLanded);
        panel2.add(line2);
        panel2.add(outbound);
        panel2.add(allocateDepartureSlot);
        panel2.add(permitTakeoff);
        panel2.add(line3);
        panel2.add(planeDetails);
        JScrollPane detailsScrollPane = new JScrollPane(planeDetailsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        detailsScrollPane.setPreferredSize(new Dimension(180,150));
        planesList.setLayoutOrientation(JList.VERTICAL);
        panel2.add(detailsScrollPane);

        window.add(panel2);
        setVisible(true);

        planesListModel = new DefaultListModel();
        planesList.setModel(planesListModel);
    }

    /**
     * A method that is invoked whenever an action happens (a button click)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Allow Approach Clearance button is clicked - Changes the status of the selected MR to LANDING
        if(e.getSource() == allowApproachClearance) {
            if(model.getStatus(selectedPlaneIndex) == 3){
                model.setStatus(selectedPlaneIndex, 4);
            }
        }

        // Confirm Plane Has Landed button is clicked - Changes the status of the selected MR to LANDED
        if(e.getSource() == confirmPlaneHasLanded) {
            if(model.getStatus(selectedPlaneIndex) == 4)model.setStatus(selectedPlaneIndex, 5);

        }

        // Allocate Departure Slot button is clicked - Changes the status of the selected MR to AWAITING_TAXI
        if(e.getSource() == allocateDepartureSlot) {
            if(model.getStatus(selectedPlaneIndex) == 15)model.setStatus(selectedPlaneIndex, 16);

        }

        // Permit Takeoff button is clicked - Changes the status of the selected MR to DEPARTING_THROUGH_LOCAL_AIRSPACE
        if(e.getSource() == permitTakeoff) {
            if(model.getStatus(selectedPlaneIndex) == 17)model.setStatus(selectedPlaneIndex, 18);

        }
    }

    /**
     * A method that is automatically called if there are any changes to the model
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        // An ArrayList with the MRs' flight codes with statuses specific to the LATC screen
        ArrayList<String> occupiedMRs = model.getLatcMRs();
        planesVector.clear();
        for(int i = 0; i < occupiedMRs.size(); i ++){
            planesVector.addElement(occupiedMRs.get(i));
        }

        //If the model is updated, show the details of the selected MR, otherwise clear the planeDetailsArea
        if(planesVector.size() == 0){
            planeDetailsArea.setText("");
        } else {
            if(model.getGate(mCode) == -1){
                gate = "N/A";
            } else {
                gate = "Gate " + model.getGate(mCode);
            }
            if(mCode != -1) {
                selectedPlanesDetails = "Flight code: " + model.getFlightCode(mCode) + "\n" +
                        "Status: " + model.getStringStatus(mCode) + "\n" +
                        "Gate #: " + gate + "\n" +
                        "# of passengers: " + model.getPassengerList(mCode).size() + "\n" +
                        "To: " + model.getItinirary(mCode).getTo() + "\n" +
                        "From: " + model.getItinirary(mCode).getFrom() + "\n" +
                        "Next: " + model.getItinirary(mCode).getNext();
                planeDetailsArea.setText(selectedPlanesDetails);
            }
        }

        //whenever the model is updated lock all the buttons
        allowApproachClearance.setEnabled(false);
        confirmPlaneHasLanded.setEnabled(false);
        allocateDepartureSlot.setEnabled(false);
        permitTakeoff.setEnabled(false);

        refreshList(planesListModel, planesVector);
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
}
