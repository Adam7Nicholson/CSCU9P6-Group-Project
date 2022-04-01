package Management;

import Flights.FlightDescriptor;
import Flights.Itinerary;
import Passenger.PassengerDetails;
import Passenger.PassengerList;

/*Please put your student ID in so proper accreditation can be given for your work. 
Ensure it is only your Student ID and *not* your name as marking is done anonymously.
Please only add your name on this class if you have worked on this class.
Work can take any form from refactoring to code writing and anything in between, of course
You should always take credit for your work.*/
/**
* @author 2839798
* @author 
* @author 
* @author 
* @author 
* @author 
*/

public class ManagementRecord {
	
	enum Status{
		FREE,
		IN_TRANSIT,
		WAITING_TO_LAND,
		GROUND_CLEARANCE_GRANTED,
		LANDING,
		LANDED,
		TAXIING,
		UNLOADING,
		READY_CLEAN_AND_MAINT,
		FAULTY_AWAIT_CLEAN,
		CLEAN_AWAIT_MAINT,
		OK_AWAIT_CLEAN,
		AWAIT_REPAIR,
		READY_REFUEL,
		READY_PASSENGERS,
		READY_DEPART,
		AWAITING_TAXI,
		AWAITING_TAKEOFF,
		DEPARTING_THROUGH_LOCAL_AIRSPACE
	}
  
	private int status;
	private int gateNumber;
	private String flightCode;
	private Itinerary itinerary;
	private PassengerList passengerList;
	private String faultDescription;
	
	public void setStatus (int newStatus) {
		this.status = newStatus;
	}
  
	public void setStatus (Status newStatus) {
		this.status = newStatus.ordinal();
	}

	public int getStatus() {
		return this.status;
	}
	
	public String getFlightCode() {
		return flightCode;
	}
	
	public void radarDetect(FlightDescriptor fd){
		this.status = IN_TRANSIT;
		this.flightCode = fd.getFlightCode();
		this.itinerary = fd.getLnkUnnamed();
		this.passengerList = fd.getLnkUnnamed1();
	  }
	
	  public void radarLostContact(){
		this.status = IN_TRANSIT;
	  }
	
	  public void taxiTo(int gateNumber){
		this.status=LANDED;
		this.gateNumber = gateNumber;
	  }
	

	/**
	 * If the status is READY_CLEAN_AND_MAINT or CLEAN_AWAIT_MAINT, sets faultDescription equal to description.
	 * @param description
	 */
	public void faultsFound(String description) {
		if (this.status == Status.READY_CLEAN_AND_MAINT.ordinal() || this.status == Status.CLEAN_AWAIT_MAINT.ordinal()) {
			this.faultDescription = description;
		}
	}
	
	/**
	 * Appends a passenger onto the PassengerList if status is READY_PASSENGERS
	 * @param details
	 */
	public void addPassenger(PassengerDetails details) {
		if (this.status == Status.READY_PASSENGERS.ordinal()) {
			this.passengerList.addPassenger(details);
		}
	}
	
	/**
	 * Returns current instance's passenger list
	 * @return Instance PassengerList object 'passengerList'
	 */
	public PassengerList getPassengerList() {
		return this.passengerList;
	}
	
	/**
	 * Returns the current instance's itinerary object
	 * @return Instance Itinerary Object 'itinerary'
	 */
	public Itinerary getItinerary() {
		return this.itinerary;
	}
}

