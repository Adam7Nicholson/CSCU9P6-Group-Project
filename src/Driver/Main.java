package Driver;

import Gate.Gate;

/*Please put your student ID in so proper accreditation can be given for your work. 
Ensure it is only your Student ID and *not* your name as marking is done anonymously.
Please only add your name on this class if you have worked on this class.
Work can take any form from refactoring to code writing and anything in between, of course
You should always take credit for your work.*/

import Management.AircraftManagementDatabase;
import Observers.CleaningSupervisor;
import Observers.GOC;
import Observers.LATC;
import Observers.MaintenanceInspector;
import Observers.PublicInfo;
import Observers.RadarTransceiver;
import Observers.RefuellingSupervisor;
import Gate.GateInfoDatabase;
/**
 * @author 2819600
 * @author 2816391
 * @author
 * @author
 * @author
 * @author
 */

public class Main {

	public static void main(String[] args) {
		//TODO Auto-Generated Method Stub
		AircraftManagementDatabase model1 = new AircraftManagementDatabase();
		GateInfoDatabase gateDatabase = new GateInfoDatabase();
		
		RadarTransceiver radar = new RadarTransceiver(model1, "Radar Transceiver");
		LATC latc = new LATC(model1, "Radar Transceiver");
		MaintenanceInspector mainInspector = new MaintenanceInspector(model1, "Radar Transceiver");
		RefuellingSupervisor refuellingSupervisor = new RefuellingSupervisor(model1,"Refuelling Supervisor");
		Gate gate = new Gate(gateDatabase,"Gate");
		GOC goc = new GOC(model1,"GOC");
		PublicInfo pi = new PublicInfo(model1,"Public Information Interface");
		CleaningSupervisor cs = new CleaningSupervisor(model1,"Cleaning Supervisor");
	}
	
	

}