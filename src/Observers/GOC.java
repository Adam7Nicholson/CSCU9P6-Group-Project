package Observers;
/**
* @stereotype boundary/view/controller
* @author 2816391
*/

import Management.AircraftManagementDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GOC extends JFrame implements ActionListener {

    private String title;
    private AircraftManagementDatabase model;
    
    private JPanel left;
    private JPanel right;

    //Panel - left
    private JLabel planesL;
    private JLabel gateStatusL;
    private JList planesList;
    private JList gateList;

    //Panel - right
    private JLabel controlsL;
    private JLabel inboundL;
    private JLabel outboundL;
    private JLabel planeDetailsL;
    
    private JButton grantGroundClearance;
    private JButton taxiToGate;
    private JButton grantTaxiRunwayClearance;
    private JTextArea planeDetailsArea;

    /**
     *
     * @param model the model (AircraftManagementDatabase) that LATC receive inputs and sends messages to
     * @param title the title of this screen
     */
    public GOC(AircraftManagementDatabase model, String title){
        this.title = title;
        this.model = model;
        
        setTitle(title);
        setLocation(450,350);
        setSize(380,450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //Panel - first
        left = new JPanel();
        left.setPreferredSize(new Dimension(100,500));
        planesL = new JLabel("Planes");
        planesList = new JList();
        planesList.setPreferredSize(new Dimension(90,150));
        
        gateStatusL = new JLabel("Gate Status");
        gateList = new JList();
        gateList.setPreferredSize(new Dimension(90,150));
        
        left.add(planesL);
        left.add(planesList);
        left.add(gateStatusL);
        left.add(gateList);
        
        window.add(left);

        //contents of plane 2
        right = new JPanel(new FlowLayout(FlowLayout.CENTER,0,7));
        right.setPreferredSize(new Dimension(200,460));

        // Horizontal lines used as separators between other components
        JSeparator line1 = new JSeparator(SwingConstants.HORIZONTAL);
        line1.setPreferredSize(new Dimension(200,10));
        JSeparator line2 = new JSeparator(SwingConstants.HORIZONTAL);
        line2.setPreferredSize(new Dimension(200,10));
        JSeparator line3 = new JSeparator(SwingConstants.HORIZONTAL);
        line3.setPreferredSize(new Dimension(200,10));

        controlsL = new JLabel("Controls");
        inboundL = new JLabel("Inbound");
        outboundL = new JLabel("Outbound");
        planeDetailsL = new JLabel("Plane Details");

        grantGroundClearance = new JButton("Grand Ground Clearance");
        grantGroundClearance.addActionListener(this);
        taxiToGate = new JButton("Taxi to Gate");
        taxiToGate.addActionListener(this);
        grantTaxiRunwayClearance = new JButton("Grant Taxi Runway Clearance");
        grantTaxiRunwayClearance.addActionListener(this);

        planeDetailsArea = new JTextArea(7,20);

        right.add(controlsL);
        right.add(line1);
        right.add(inboundL);
        right.add(grantGroundClearance);
        right.add(taxiToGate);
        right.add(line2);
        right.add(outboundL);
        right.add(grantTaxiRunwayClearance);
        right.add(line3);
        right.add(planeDetailsL);
        right.add(planeDetailsArea);

        window.add(right);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
