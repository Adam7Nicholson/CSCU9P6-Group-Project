package Observers;
/**
 * @author 2823424
 * @author 2816391
 * @author
 * @author
 * @author
 * @author
 */
import Management.AircraftManagementDatabase;
import Management.ManagementRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;


public class RefuellingSupervisor extends JFrame
        implements ActionListener,Observer {

    private int selectedPlaneIndex;
    private String selectedPlane = "";
    private int mCode = -1;

/**
 * 
 * @stereotype boundary/view/controller
 *
 */
    private AircraftManagementDatabase model;
    private String title;

    private JPanel panel1;
    private JPanel panel2;

    //will be in panel 1
    private JLabel planesBeingRefuelled;
    private JList planesBeingRefuelledList;
    private DefaultListModel planesListModel;

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
        setLocation(1440,10);
        setSize(330,330);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        // contents of panel 1
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(150,300));

        planesBeingRefuelled = new JLabel("Planes Being Refuelled");
        planesBeingRefuelledList = new JList();
        planesBeingRefuelledList.setPreferredSize(new Dimension(140,250));

        //A mouse listener that enables to select a MR from the planesList
        planesBeingRefuelledList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        selectedPlane = item.toString();
                        selectedPlaneIndex = model.getMCode(selectedPlane);
                        mCode = model.getMCode(selectedPlane);
                        selectAPlane.setText(model.getFlightCode(selectedPlaneIndex));

                        if(model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.READY_REFUEL.ordinal()) planeIsRefuelledBtn.setEnabled(true);
                    }
                }
            }
        }
        );

        panel1.add(planesBeingRefuelled);
        panel1.add(planesBeingRefuelledList);
        window.add(panel1);

        //contents of plane 2
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,90));
        panel2.setPreferredSize(new Dimension(150,300));
        planeIsRefuelledBtn = new JButton("Plane is Refuelled");
        planeIsRefuelledBtn.addActionListener(this);
        planeIsRefuelledBtn.setEnabled(false);
        selectAPlane = new JLabel("No planes selected");

        panel2.add(planeIsRefuelledBtn);
        panel2.add(selectAPlane);

        window.add(panel2);

        setVisible(true);

        planesListModel = new DefaultListModel();
        planesBeingRefuelledList.setModel(planesListModel);
    }

    /**
     * A method that is invoked whenever an action happens (a button click)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Plane Is Refuelled button is clicked - Changes the status of the selected MR to READY_PASSENGERS
        if(e.getSource()==planeIsRefuelledBtn){
            if(model.getStatus(selectedPlaneIndex) == ManagementRecord.Status.READY_REFUEL.ordinal() ) model.setStatus(selectedPlaneIndex, ManagementRecord.Status.READY_PASSENGERS.ordinal());
        }
    }

    /**
     * A method that is automatically called if there are any changes to the model
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        int[] planesWithStatus = model.getWithStatus(ManagementRecord.Status.READY_REFUEL.ordinal());
        Vector<String> planes = new Vector<String>();
        if(planesWithStatus != null) {
            for(int i = 0; i < planesWithStatus.length; i++) {
                planes.addElement(model.getFlightCode(planesWithStatus[i]));
            }
        }
        planeIsRefuelledBtn.setEnabled(false);
        selectAPlane.setText("No planes selected");
        refreshList(planesListModel, planes);
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