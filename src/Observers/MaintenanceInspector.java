package Observers;

import Management.AircraftManagementDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @stereotype boundary/view/controller
 *
 */
public class MaintenanceInspector extends JFrame implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel maintenance;
    private JList maintenanceList;

    //TODO THESE SHOULD BE SOMETHING OTHER THAN JLABELS
    private JLabel currentStatus;
    private JLabel currentLocation;

    //will be in panel 2
    private JLabel comment;
    private JTextArea commentsArea;
    private JButton reportFaults;
    private JButton complete;

    /**
     *
     * @param model the model (AircraftManagementDatabase) that MaintenanceInspector receive inputs and sends messages to
     * @param title the title of this screen
     */
    public MaintenanceInspector(AircraftManagementDatabase model, String title){
        this.model = model;
        this.title = title;

        setTitle("Maintenance Inspector");
        setLocation(1000,40);
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
        commentsArea = new JTextArea(10,30);

        reportFaults = new JButton("Report Faults");
        reportFaults.addActionListener(this);
        reportFaults.setEnabled(false);
        complete = new JButton("Complete");
        complete.addActionListener(this);
        complete.setEnabled(false);

        panel2.add(comment);
        panel2.add(commentsArea);
        panel2.add(reportFaults);
        panel2.add(complete);

        window.add(panel2);

        setVisible(true);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
