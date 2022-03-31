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

/**
 * An interface to SAAMS:
 * Public Information Screen:
 * Display of useful information about aircraft.
 * This class registers as an observer of the AircraftManagementDatabase, and is notified whenever any change occurs in that <<model>> element.
 * See written documentation.
 * @stereotype boundary/view
 * @url element://model:project::SAAMS/design:view:::id28ykdcko4qme4cko4sx0e
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 */

//import Management.AircraftManagementDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Management.AircraftManagementDatabase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @stereotype boundary/view
 * @author 
 *
 */
public class PublicInfo extends JFrame implements ActionListener{

	private AircraftManagementDatabase model;
	private String title;
	private JTable infoTable;
	private String headers[] = {"Flights", "From", "To", "Gate", "Status"};
	private JScrollPane tablePanel;
	private JButton reload;
  
	public PublicInfo(AircraftManagementDatabase model, String title)
	{
		this.title = title;
		this.model = model;
		
		setTitle("Public Information Interface");
    	setLocation(850,700);
    	setSize(500, 350);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	Container window = getContentPane();
    	window.setLayout(new FlowLayout(FlowLayout.CENTER));
    	
    	String[][] data = {{"XYZ789", "Amsterdam", "Stirling", "N/A", "Landing"}};
    	DefaultTableModel tModel = new DefaultTableModel();
    	tModel.addColumn("Flights");
    	tModel.addColumn("From");
    	tModel.addColumn("To");
    	tModel.addColumn("Gate");
    	tModel.addColumn("Status");
     	infoTable = new JTable(tModel);
    	infoTable.setPreferredScrollableViewportSize(new Dimension(450, 250));
    	tablePanel = new JScrollPane(infoTable);
    	
    	reload = new JButton("Reaload");
    	reload.addActionListener(this);
    	
    	window.add(tablePanel);
    	window.add(reload);
    	
    	setVisible(true);
    	
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

}
