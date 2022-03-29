package Observers;

import Management.AircraftManagementDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @stereotype boundary/view/controller
 * @author 
 *
 */
public class LATC extends JFrame
        implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel planes;
    private JList planesList;

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

    /**
     *
     * @param model the model (AircraftManagementDatabase) that LATC receive inputs and sends messages to
     * @param title the title of this screen
     */
    public LATC(AircraftManagementDatabase model, String title){
        this.model = model;
        this.title = title;

        setTitle("Local Air Traffic Controller");
        setLocation(40,350);
        setSize(380,470);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // contents of panel 1
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(100,500));
        planes = new JLabel("Planes");
        planesList = new JList();
        planesList.setPreferredSize(new Dimension(90,389));
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
        confirmPlaneHasLanded = new JButton("Confirm plane has landed");
        confirmPlaneHasLanded.addActionListener(this);
        allocateDepartureSlot = new JButton("Allocate Departure Slot");
        allocateDepartureSlot.addActionListener(this);
        permitTakeoff = new JButton("Permit Takeoff");
        permitTakeoff.addActionListener(this);

        planeDetailsArea = new JTextArea(7,20);

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
        panel2.add(planeDetailsArea);

        window.add(panel2);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
