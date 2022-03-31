package Observers;
/**
 * 
/*Please put your student ID in so proper accreditation can be given for your work. 
Ensure it is only your Student ID and *not* your name as marking is done anonymously.
Please only add your name on this class if you have worked on this class.
Work can take any form from refactoring to code writing and anything in between, of course
You should always take credit for your work.*/
/**
* @author 2816391
* @author 2823424
* @author 
* @author 
* @author 
* @author 
*

/**An interface to SAAMS:
 * Cleaning Supervisor Screen:
 * Inputs events from the Cleaning Supervisor, and displays aircraft information.
 * This class is a controller for the AircraftManagementDatabase: sending it messages to change the aircraft status information.
 * This class also registers as an observer of the AircraftManagementDatabase, and is notified whenever any change occurs in that <<model>> element.
 * See written documentation.
 * @stereotype boundary/view/controller
 * @url element://model:project::SAAMS/design:view:::id3y5z3cko4qme4cko4sw81
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @author 2816391
 */
//package Observers;

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
 * 
 * @stereotype boundary/view/controller
 *
 */
public class CleaningSupervisor extends JFrame implements ActionListener, Observer {
    private int selectedPlaneIndex;
    private String selectedPlane = "";
    int mCode = -1;


public class CleaningSupervisor extends JFrame implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel left;
    private JPanel right;

    //Will be in panel 1
    private JLabel planesListL;
    private JList planesList;
    private DefaultListModel planesListModel;
    private Vector<String> planesListVector;


    //Will be in panel 2
    private JButton cleanAirplane;
    private JLabel selectAPlane;

    /**
     *
     * @param model the model (AircraftManagementDatabase) that RefuellingSupervisor receive inputs and sends messages to
     * @param title the title of this screen
     */
    public CleaningSupervisor(AircraftManagementDatabase model, String title){

        planesListVector = new Vector<String>();

        this.model = model;
        this.title = title;

        setTitle("Cleaning Supervisor");
        setLocation(1440,350);
        setSize(330,330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //Panel 1 contents
        left = new JPanel();
        left.setPreferredSize(new Dimension(150,300));

        planesListL= new JLabel("Planes list: MR codes");
        planesList = new JList();
        planesList.setPreferredSize(new Dimension(140,250));

        left.add(planesListL);
        left.add(planesList);
        window.add(left);

        //Panel 2 contents
        right = new JPanel(new FlowLayout(FlowLayout.LEFT,0,90));
        right.setPreferredSize(new Dimension(150,300));

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

                        selectAPlane.setText(model.getStringStatus(mCode));

                        //enabling the button if the selected MR's status allow the button to be clicked
                        if(model.getStatus(mCode) == 8 || model.getStatus(mCode) == 9 || model.getStatus(mCode) == 11){
                            cleanAirplane.setEnabled(true);
                        } else{
                            cleanAirplane.setEnabled(false);
                        }
                        }
                    }
                }

        });
        cleanAirplane = new JButton("Clean Airplane");
        cleanAirplane.addActionListener(this);
        cleanAirplane.setEnabled(false);
        selectAPlane = new JLabel("No planes selected");

        right.add(cleanAirplane);
        right.add(selectAPlane);

        window.add(right);

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

        // Clean Airplane button is clicked - Depending on the current status of the selected MR, changes it to either CLEAN_AWAIT_MAINT, AWAIT_REPAIR or READY_REFUEL
        if (e.getSource() == cleanAirplane) {
            if (model.getStatus(selectedPlaneIndex) == 11) {
                model.setStatus(selectedPlaneIndex, 13);
            } else if (model.getStatus(selectedPlaneIndex) == 9) {
                model.setStatus(selectedPlaneIndex, 12);
            } else if (model.getStatus(selectedPlaneIndex) == 8) {
                model.setStatus(selectedPlaneIndex, 10);
            }
        }
    }

    /**
     * A method that is automatically called if there are any changes to the model
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        // An ArrayList with the MRs' flight codes with statuses specific to the Cleaning Supervisor screen
        ArrayList<String> occupiedMRs = model.getMaintanceCleaningMRs();
        planesListVector.clear();
        planesListVector.clear();
        for(int i = 0; i < occupiedMRs.size(); i ++){
            planesListVector.addElement(occupiedMRs.get(i));
        }
        //If there are no planes in the List, set the text to the JLabel
        if(planesListVector.size() == 0) {
            selectAPlane.setText("No plane selected");
            refreshList(planesListModel, planesListVector);
        }

        //whenever the model is updated the button is disabled
        cleanAirplane.setEnabled(false);
        refreshList(planesListModel, planesListVector);
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
