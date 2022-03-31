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
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class PublicInfo extends JFrame implements Observer{

	private AircraftManagementDatabase model;
	private String title;
	private JTable infoTable;
	private DefaultTableModel tModel;
	private String headers[] = {"Flights", "From", "To", "Gate", "Status"};
	private JScrollPane tablePanel;

	public PublicInfo(AircraftManagementDatabase model, String title)
	{
		this.title = title;
		this.model = model;

		setTitle("Public Information Interface");
		if(title.equalsIgnoreCase("Public Information Interface 1"))setLocation(380,750);
		else setLocation(910,750);
		setSize(540, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new FlowLayout(FlowLayout.CENTER));
		tModel = new DefaultTableModel(null,headers);
		infoTable = new JTable(tModel);
		infoTable.setPreferredScrollableViewportSize(new Dimension(510, 270));
		tablePanel = new JScrollPane(infoTable);

		window.add(tablePanel);

		setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {
		//Removes all the elemets to update the table with the new list.
		tModel.setRowCount(0);
		
		//Goes through all the Detected Management Records
		for(String s : model.getDetectedMRs()) {
			Vector<String> publicInfoRow = new Vector<String>();
			int mCode = model.getMCode(s);
			publicInfoRow.add(s);
			publicInfoRow.add(model.getItinirary(mCode).getFrom());
			publicInfoRow.add(model.getItinirary(mCode).getTo());
			if(model.getGate(mCode) == -1)publicInfoRow.add("N/A");
			else publicInfoRow.add(String.valueOf(model.getGate(mCode) + 1));
			publicInfoRow.add(model.getStringStatus(mCode));
			tModel.addRow(publicInfoRow);
		}
	}

}
