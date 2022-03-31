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
 * 
 * @stereotype boundary/view/controller
 *
 */

public class MaintenanceInspector extends JFrame
        implements ActionListener, Observer {
    private int selectedPlaneIndex;
    private String selectedPlane = "";
    int mCode = -1;

    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel maintenance;
    private JList maintenanceList;
    private DefaultListModel maintenanceModel;
    private Vector<String> maintenanceVector;

    private JLabel currentStatus;
    private JLabel currentLocation;

    //will be in panel 2
    private JLabel comment;
    private JTextArea commentsArea;
    private JButton reportFaults;
    private JButton complete;
    private JButton ready;

    private String gate = "";
    /**
     *
     * @param model the model (AircraftManagementDatabase) that MaintenanceInspector receive inputs and sends messages to
     * @param title the title of this screen
     */
    public MaintenanceInspector(AircraftManagementDatabase model, String title){

        maintenanceVector = new Vector<String>();
        this.model = model;
        this.title = title;

        setTitle("Maintenance Inspector");
        setLocation(1400,690);
        setSize(500,350);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // contents of panel 1
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(200,400));
        maintenance = new JLabel("Maintenance(s) needed");
        maintenance.setBackground(Color.white);
        maintenanceList = new JList();
        maintenanceList.setPreferredSize(new Dimension(100,200));

        maintenanceList.setSelectedIndex(0);
        //A mouse listener that enables to select a MR from the maintenanceList
        maintenanceList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        selectedPlane = item.toString();
                        selectedPlaneIndex = model.getMCode(selectedPlane);
                        mCode = model.getMCode(selectedPlane);

                        currentStatus.setText("Status " + model.getStringStatus(mCode));
                        gate = "Location: Gate " + model.getGate(mCode);
                        currentLocation.setText(gate);

                        //Enable the correct buttons depending on the currently selected MR's status
                        if(model.getStatus(mCode) == 11 || model.getStatus(mCode) == 9){
                                reportFaults.setEnabled(false);
                                complete.setEnabled(false);
                                ready.setEnabled(false);
                        } else if (model.getStatus(mCode) == 8){
                            reportFaults.setEnabled(true);
                            complete.setEnabled(false);
                            ready.setEnabled(true);
                        } else if (model.getStatus(mCode) == 12){
                            reportFaults.setEnabled(false);
                            complete.setEnabled(true);
                            ready.setEnabled(false);
                        } else if (model.getStatus(mCode) == 9 || model.getStatus(mCode) == 12){
                            commentsArea.setText(model.getFaults(mCode));
                        } else if (model.getStatus(mCode) == 10) {
                            reportFaults.setEnabled(true);
                            complete.setEnabled(false);
                            ready.setEnabled(true);
                        }

                        //Enabling or disabling the commentsArea depending on the current MR's status
                        if(model.getStatus(mCode) == 8 || model.getStatus(mCode) == 10){
                            commentsArea.setText("");
                            commentsArea.setEditable(true);
                        } else{
                            commentsArea.setEditable(false);
                            commentsArea.setText(model.getFaults(mCode));
                        }

                    }
                }

            }
        });

        currentStatus = new JLabel("     Current Status     ");
        currentStatus.setBackground(Color.white);
        currentLocation = new JLabel("     Current Location     ");
        currentLocation.setBackground(Color.white);

        panel1.add(maintenance);
        panel1.add(maintenanceList);
        panel1.add(currentStatus);
        panel1.add(currentLocation);
        window.add(panel1);

        //contents of plane 2
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(200,400));
        comment = new JLabel("Comment");
        commentsArea = new JTextArea(8,15);
        reportFaults = new JButton("Report Faults");
        reportFaults.addActionListener(this);
        reportFaults.setEnabled(false);
        complete = new JButton("Complete");
        complete.addActionListener(this);
        complete.setEnabled(false);
        ready = new JButton("Ready");
        ready.addActionListener(this);
        ready.setEnabled(false);

        panel2.add(comment);
        panel2.add(commentsArea);
        panel2.add(reportFaults);
        panel2.add(complete);
        panel2.add(ready);

        window.add(panel2);

        setVisible(true);

        maintenanceModel = new DefaultListModel();
        maintenanceList.setModel(maintenanceModel);
    }

    /**
     * A method that is invoked whenever an action happens (a button click)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Ready button is clicked - Depending on the current status, changes the status of the selected MR to either OK_AWAIT_CLEAN or READY_REFUEL
        if(e.getSource() == ready){
            if(model.getStatus(selectedPlaneIndex) == 8){
                model.setStatus(selectedPlaneIndex, 11);
                reportFaults.setEnabled(false);
                complete.setEnabled(false);
                ready.setEnabled(false);
            } else if (model.getStatus(selectedPlaneIndex) == 10){
                model.setStatus(selectedPlaneIndex, 13);
            }

        }

        // Report Faults button is clicked - Depending on the current status, changes the status of the selected MR to either FAULTY_AWAIT_CLEAN or AWAIT_REPAIR
        if(e.getSource() == reportFaults){
            if(commentsArea.getText().isEmpty()){
                commentsArea.setText("Enter the faults here");
            } else {
                model.faultsFound(mCode, commentsArea.getText());
                if(model.getStatus(selectedPlaneIndex) == 8){
                    model.setStatus(selectedPlaneIndex, 9);
                } else if (model.getStatus(selectedPlaneIndex) == 10){
                    model.setStatus(selectedPlaneIndex, 12);
                }
            }

            if(commentsArea.getText().equalsIgnoreCase(""))
            {
                System.out.println("test Report faults");
                reportFaults.setEnabled(false);
            }
        }

        // Complete button is clicked -Changes the status of the selected MR to READY_CLEAN_AND_MAINT
        if(e.getSource() == complete){
            if(model.getFaults(mCode) != ""){
                model.faultsFound(mCode,"");
                model.setStatus(selectedPlaneIndex, 8);
            }
        }
    }

    /**
     * A method that is automatically called if there are any changes to the model
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {

        // An ArrayList with the MRs' flight codes with statuses specific to the Maintenance Inspector screen
        ArrayList<String> occupiedMRs = model.getMaintanceCleaningMRs();
        maintenanceVector.clear();
        for(int i = 0; i < occupiedMRs.size(); i ++){
            maintenanceVector.addElement(occupiedMRs.get(i));
        }
        if(maintenanceVector.size() == 0) {
            currentStatus.setText("Status");
            currentLocation.setText("Location");
            refreshList(maintenanceModel, maintenanceVector);
        }

        //whenever the model is updated lock all the buttons
        reportFaults.setEnabled(false);
        ready.setEnabled(false);
        complete.setEnabled(false);
        refreshList(maintenanceModel, maintenanceVector);
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
