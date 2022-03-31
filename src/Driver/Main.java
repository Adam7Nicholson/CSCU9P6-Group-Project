package Driver;

import Gate.Gate;
import Gate.GateConsole;
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
 * @author 2823424
 * @author
 * @author
 * @author
 */

public class Main {

	public static void main(String[] args) {
		AircraftManagementDatabase AMDmodel = new AircraftManagementDatabase();
		GateInfoDatabase gateDatabase = new GateInfoDatabase();
		GOC goc = new GOC(gateDatabase, AMDmodel,"GOC");
		AMDmodel.addObserver(goc);
		gateDatabase.addObserver(goc);
		RadarTransceiver radar = new RadarTransceiver(AMDmodel, "Radar Transceiver");
		AMDmodel.addObserver(radar);
		LATC latc = new LATC(AMDmodel, "Radar Transceiver");
		AMDmodel.addObserver(latc);

		for(int i = 0; i < gateDatabase.getNumberOfGates(); i ++){
			Gate gate = new Gate(i);
			gateDatabase.addGate(gate);
			GateConsole gateConsole = new GateConsole(gateDatabase,AMDmodel,gate, "Gate" + (i+1));
			gateDatabase.addObserver(gateConsole);
			AMDmodel.addObserver(gateConsole);
		}

		MaintenanceInspector mainInspector = new MaintenanceInspector(AMDmodel, "Radar Transceiver");
		AMDmodel.addObserver(mainInspector);

		CleaningSupervisor cleaningSupervisor = new CleaningSupervisor(AMDmodel,"Cleaning Supervisor");
		AMDmodel.addObserver(cleaningSupervisor);

		RefuellingSupervisor refuellingSupervisor = new RefuellingSupervisor(AMDmodel,"Refuelling Supervisor");
		AMDmodel.addObserver(refuellingSupervisor);

		PublicInfo pi = new PublicInfo(AMDmodel,"Public Information Interface 1");
		AMDmodel.addObserver(pi);

		PublicInfo pi2 = new PublicInfo(AMDmodel, "Public Information Interface 2");
		AMDmodel.addObserver(pi2);
	}

}