package Driver;

import Gate.Gate;

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
 * @stereotype control
 * @author 2816391
 * @author 2823424
 * @author 2819600
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