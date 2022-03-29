package Observers;
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


/**
 * 
 * @stereotype boundary/view/controller
 *
 */
public class CleaningSupervisor extends JFrame implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel left;
    private JPanel right;

    //Panel - left
    private JLabel planesListL;
    private JList planesList;


    //Panel - right
    private JButton cleanAirplane;
    private JLabel selectAPlane;

    /**
     *
     * @param model the model (AircraftManagementDatabase) that RefuellingSupervisor receive inputs and sends messages to
     * @param title
     */
    public CleaningSupervisor(AircraftManagementDatabase model, String title){
        this.model = model;
        this.title = title;

        setTitle("Cleaning Supervisor");
        setLocation(1400,350);
        setSize(380,330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //Panel - left
        left = new JPanel();
        left.setPreferredSize(new Dimension(150,300));

        planesListL= new JLabel("Planes list: MR codes(gate number)");
        planesList = new JList();
        planesList.setPreferredSize(new Dimension(140,250));

        left.add(planesListL);
        left.add(planesList);
        window.add(left);

        //Panel - right
        right = new JPanel(new FlowLayout(FlowLayout.LEFT,0,90));
        right.setPreferredSize(new Dimension(150,300));
        cleanAirplane = new JButton("Clean Airplane");
        cleanAirplane.addActionListener(this);
        selectAPlane = new JLabel("No planes selected");

        right.add(cleanAirplane);
        right.add(selectAPlane);

        window.add(right);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
