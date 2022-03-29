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
public class RefuellingSupervisor extends JFrame implements ActionListener {

    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel planesBeingRefuelled;
    private JList planesBeingRefuelledList;


    //will be in panel 2
    private JButton planeIsRefuelledBtn;
    private JLabel selectAPlane;

    /**
     *
     * @param model the model (AircraftManagementDatabase) that RefuellingSupervisor receive inputs and sends messages to
     * @param title
     */
    public RefuellingSupervisor(AircraftManagementDatabase model, String title){
        this.model = model;
        this.title = title;

        setTitle("Refuelling Supervisor");
        setLocation(1000,450);
        setSize(380,330);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // contents of panel 1
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(150,300));

        planesBeingRefuelled = new JLabel("Planes Being Refuelled");
        planesBeingRefuelledList = new JList();
        planesBeingRefuelledList.setPreferredSize(new Dimension(140,250));

        panel1.add(planesBeingRefuelled);
        panel1.add(planesBeingRefuelledList);
        window.add(panel1);

        //contents of plane 2
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,90));
        panel2.setPreferredSize(new Dimension(150,300));
        planeIsRefuelledBtn = new JButton("Plane is Refuelled");
        planeIsRefuelledBtn.addActionListener(this);
        selectAPlane = new JLabel("No planes selected");

        panel2.add(planeIsRefuelledBtn);
        panel2.add(selectAPlane);

        window.add(panel2);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
