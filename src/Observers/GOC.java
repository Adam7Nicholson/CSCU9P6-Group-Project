package Observers;
/*Please put your student ID in so proper accreditation can be given for your work. 
Ensure it is only your Student ID and *not* your name as marking is done anonymously.
Please only add your name on this class if you have worked on this class.
Work can take any form from refactoring to code writing and anything in between, of course
You should always take credit for your work.*/
/**
 * @author 2816391
 * @author
 * @author
 * @author
 * @author
 * @author
 */

import Management.AircraftManagementDatabase;
import Management.ManagementRecord;
import Passenger.PassengerList;

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

import Gate.GateInfoDatabase;

public class GOC extends JFrame implements ActionListener,Observer {

    private int selectedPlaneIndex;
    private String selectedPlane = "";
    private String selectedPlanesDetails = "";
    private int selectedGateIndex;
    private int mCode = -1;

    private String title;
    private AircraftManagementDatabase model;
    private GateInfoDatabase gateData;

    private JPanel left;
    private JPanel right;

    //Panel - left
    private JLabel planesL;
    private JLabel gateStatusL;
    private JList planesList;
    private DefaultListModel planesListModel;
    private JList gateList;
    private DefaultListModel gateListModel;

    //Panel - right
    private JLabel controlsL;
    private JLabel inboundL;
    private JLabel outboundL;
    private JLabel planeDetailsL;

    private JButton grantGroundClearance;
    private JButton taxiToGate;
    private JButton grantTaxiRunwayClearance;
    private JTextArea planeDetailsArea;


    private String gate = "";

    /**
     *
     * @param GOC receive inputs and sends messages
     * @param title the title of this screen
     */
    public GOC(GateInfoDatabase gateData, AircraftManagementDatabase model, String title){
        this.title = title;
        this.model = model;
        this.gateData = gateData;

        setTitle(title);
        setLocation(390,300);
        setSize(380,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //Panel - first
        left = new JPanel();
        left.setPreferredSize(new Dimension(100,500));
        planesL = new JLabel("Planes");
        planesList = new JList();
        
        //Adding a mouse listener to select a MR from a list.
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

                                                    if(model.getGate(mCode) == -1)gate = "N/A"; 
                                                    else gate = "Gate " + (model.getGate(mCode)+1);
                                                    
                                                    selectedPlanesDetails = "Flight code: " + model.getFlightCode(mCode) + "\n" +
                                                            "Status: " + model.getStringStatus(mCode) + "\n" +
                                                            "Gate #: " + gate + "\n" +
                                                            "# of passengers: " + model.getPassengerList(mCode).size() + "\n" +
                                                            "To: " + model.getItinirary(mCode).getTo() + "\n" +
                                                            "From: " + model.getItinirary(mCode).getFrom() + "\n" +
                                                            "Next: " + model.getItinirary(mCode).getNext();
                                                    planeDetailsArea.setText(selectedPlanesDetails);
                                                    
                                                   if(model.getStatus(mCode) == ManagementRecord.Status.WAITING_TO_LAND.ordinal())grantGroundClearance.setEnabled(true);
                                                   if(model.getStatus(mCode) == ManagementRecord.Status.LANDED.ordinal())taxiToGate.setEnabled(true);
                                                   if(model.getStatus(mCode) == ManagementRecord.Status.AWAITING_TAXI.ordinal())grantTaxiRunwayClearance.setEnabled(true);
                                                }
                                            }
                                        }
                                    }
        );
        planesList.setPreferredSize(new Dimension(90,150));


        gateStatusL = new JLabel("Gate Status");
        gateList = new JList();
        
        //Adding a mouse listener to the list, using to allocate the Gate to the MR selected above.
        gateList.addMouseListener(new MouseAdapter() {
                                      public void mouseClicked(MouseEvent me) {
                                          if (me.getClickCount() == 1) {
                                              JList target = (JList)me.getSource();
                                              int index = target.locationToIndex(me.getPoint());
                                              if (index >= 0) selectedGateIndex = index;
                                          }
                                      }

                                  }
        );
        left.add(planesL);
        JScrollPane scrollPane1 = new JScrollPane(planesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setPreferredSize(new Dimension(100,160));
        planesList.setLayoutOrientation(JList.VERTICAL);
        left.add(scrollPane1);
        left.add(gateStatusL);
        JScrollPane scrollPane2 = new JScrollPane(gateList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setPreferredSize(new Dimension(100,160));
        planesList.setLayoutOrientation(JList.VERTICAL);
        left.add(scrollPane2);

        window.add(left);

        //contents of plane 2
        right = new JPanel(new FlowLayout(FlowLayout.CENTER,0,7));
        right.setPreferredSize(new Dimension(240,450));

        // Horizontal lines used as separators between other components
        JSeparator line1 = new JSeparator(SwingConstants.HORIZONTAL);
        line1.setPreferredSize(new Dimension(240,10));
        JSeparator line2 = new JSeparator(SwingConstants.HORIZONTAL);
        line2.setPreferredSize(new Dimension(240,10));
        JSeparator line3 = new JSeparator(SwingConstants.HORIZONTAL);
        line3.setPreferredSize(new Dimension(240,10));

        controlsL = new JLabel("Controls");
        inboundL = new JLabel("Inbound");
        outboundL = new JLabel("Outbound");
        planeDetailsL = new JLabel("Plane Details");

        grantGroundClearance = new JButton("Grand Ground Clearance");
        grantGroundClearance.addActionListener(this);
        grantGroundClearance.setEnabled(false);
        
        taxiToGate = new JButton("Taxi to Gate");
        taxiToGate.addActionListener(this);
        taxiToGate.setEnabled(false);
        
        grantTaxiRunwayClearance = new JButton("Grant Taxi Runway Clearance");
        grantTaxiRunwayClearance.addActionListener(this);
        grantTaxiRunwayClearance.setEnabled(false);

        planeDetailsArea = new JTextArea(7,10);
        planeDetailsArea.setEditable(false);

        right.add(controlsL);
        right.add(line1);
        inboundL.setPreferredSize(new Dimension(240,10));
        inboundL.setHorizontalAlignment(JLabel.CENTER);
        right.add(inboundL);
        right.add(grantGroundClearance);
        right.add(taxiToGate);
        right.add(line2);
        right.add(outboundL);
        right.add(grantTaxiRunwayClearance);
        right.add(line3);
        
        right.add(planeDetailsL);
        JScrollPane scrollPane3 = new JScrollPane(planeDetailsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setPreferredSize(new Dimension(210,130));
        planesList.setLayoutOrientation(JList.VERTICAL);
        right.add(scrollPane3);

        window.add(right);
        setVisible(true);

        planesListModel = new DefaultListModel();
        planesList.setModel(planesListModel);

        gateListModel = new DefaultListModel();
        gateList.setModel(gateListModel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	//Pressing the Grant Ground Clearance button - Changes the status of the MR to GROUND_CLEARANCE_GRANTED.
        if(e.getSource()== grantGroundClearance){
            if(model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.WAITING_TO_LAND.ordinal())model.setStatus(selectedPlaneIndex, ManagementRecord.Status.GROUND_CLEARANCE_GRANTED.ordinal());
        }
        
        //Pressing Taxi to Gate - Changes the status of the MR to TAXIING and the status of the Gate to RESERVED,
        //Makes a connection between the MR and the Gate.
        if(e.getSource() == taxiToGate) {
            if(model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.LANDED.ordinal() && gateData.getStatus(selectedGateIndex) == Gate.Gate.Status.FREE.ordinal()) {
                model.taxiTo(selectedPlaneIndex, selectedGateIndex);
                gateData.allocate(selectedGateIndex, selectedPlaneIndex);
            }
            else {
            	JOptionPane.showMessageDialog(null, "Make sure the gate selected is FREE!");
            }
        }

        //Pressing the Grant Taxi Runaway Clearance - Changes the status of the MR to AWAITING_TAKEOFF and the status of the Gate to Free.
        //Removes the connection between the Gate and the MR. 
        if(e.getSource() == grantTaxiRunwayClearance) {
            if(model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.AWAITING_TAXI.ordinal()){
                gateData.departed(model.getGate(selectedPlaneIndex));
                
                model.clearGate(selectedPlaneIndex);
                model.setStatus(selectedPlaneIndex, ManagementRecord.Status.AWAITING_TAKEOFF.ordinal());
            }
        }
    }



    @Override
    public void update(Observable o, Object arg) {

    //Checks if the MR has been allocated a Gate yet.
        if(model.getGate(mCode) == -1)gate = "N/A";
        else gate = "Gate " + (model.getGate(selectedPlaneIndex) + 1);//Printing purpose
        
      //Checks if the source of the update is from the AircraftManagementRecord class
        if(o.equals(model)) {
            Vector<String> MRs = model.getGocMRs();
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
            if (model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.FREE.ordinal()){
                planeDetailsArea.setText("");
            }
            refreshList(planesListModel,MRs);
        }
        
        //Checks if the source of the update is from the GateInfoDatabase class
        if(o.equals(gateData)) {
        	Vector<String> gatesListed  = new Vector<String>();
            int[] statuses = gateData.getStatuses();
            for(int i = 0; i < statuses.length; i++)
            {
                if(statuses[i] == Gate.Gate.Status.FREE.ordinal())gatesListed.add("Gate " + (i+1) + " - " + "Free");
                else if(statuses[i] == Gate.Gate.Status.RESERVED.ordinal())gatesListed.add("Gate " + (i+1) + " - " + "Reserved");
                else gatesListed.add("Gate " + (i+1) + " - " + "Occupied");
            }
            refreshList(gateListModel, gatesListed);
        }
        
        grantGroundClearance.setEnabled(false);
        taxiToGate.setEnabled(false);
        grantTaxiRunwayClearance.setEnabled(false);
        
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
