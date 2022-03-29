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