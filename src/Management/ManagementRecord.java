package Management;

import Flights.FlightDescriptor;
import Flights.Itinerary;
import Passenger.PassengerDetails;
import Passenger.PassengerList;

/**Class for storing information on a given flight record, as well as containing the enum object for a record's status.
 * @stereotype entity
 * @author 2819600
*/

public class ManagementRecord {

	/**
	 * Enum object used to denote the status code of a record
	 * @author Adam
	 *
	 */
	public static enum Status{
		FREE, //0
		IN_TRANSIT, //1
		WAITING_TO_LAND,//2
		GROUND_CLEARANCE_GRANTED,//3
		LANDING,//4
		LANDED,//5
		TAXIING,//6
		UNLOADING,//7
		READY_CLEAN_AND_MAINT,//8
		FAULTY_AWAIT_CLEAN,//9
		CLEAN_AWAIT_MAINT,//10
		OK_AWAIT_CLEAN,//11
		AWAIT_REPAIR,//12
		READY_REFUEL,//13
		READY_PASSENGERS,//14
		READY_DEPART,//15
		AWAITING_TAXI,//16
		AWAITING_TAKEOFF,//17
		DEPARTING_THROUGH_LOCAL_AIRSPACE//18
	}

	private int status;
	private int gateNumber;
	private String flightCode;
	private Itinerary itinerary;
	private PassengerList passengerList;
	private String faultDescription;

	/**
	 * Instantiates managementRecord object and sets status to FREE, leaving instance variables blank to be assigned from a FlightDescriptor.
	 */
	public ManagementRecord() {
		setStatus(Status.FREE.ordinal());
		this.gateNumber = -1;
	}

	/**
	 * Assigns instance variables to those of the given FlightDescriptor, filling the record.
	 * @param fd
	 */
	public void radarDetect(FlightDescriptor fd) {
		if(getStatus() == Status.FREE.ordinal()) {
			setStatus (fd.getItinerary().getNext().equalsIgnoreCase("Stirling") ? Status.WAITING_TO_LAND : Status.IN_TRANSIT);
		}
		this.flightCode = fd.getFlightCode();
		this.itinerary = fd.getItinerary();
		this.passengerList = fd.getList();
	}

	/**
	 * Clears the instance variables for re-assignment and sets status to free, providing the record was IN_TRANSIT or DEPARTING_THROUGH_LOCAL_AIRSPACE.
	 */
	public void radarLostcontact() {
		if (getStatus() == Status.DEPARTING_THROUGH_LOCAL_AIRSPACE.ordinal() || getStatus() == Status.IN_TRANSIT.ordinal()) {
			setStatus(Status.FREE.ordinal());
			this.flightCode = "";
			this.itinerary.clearItenerary();
			this.passengerList.clearPassengerList();
		}
	}

	/**
	 * Sets the record's status to TAXIING & allocates the flight's gate number equal to the passed parameter.
	 * @param gateNumber
	 */
	public void taxiTo(int gateNumber) {
		setStatus(Status.TAXIING);
		this.gateNumber = gateNumber;
	}

	/**
	 * If the status is READY_CLEAN_AND_MAINT or CLEAN_AWAIT_MAINT, sets faultDescription equal to description.
	 * @param description
	 */
	public void faultsFound(String description) {
		if (this.status == Status.READY_CLEAN_AND_MAINT.ordinal() || this.status == Status.CLEAN_AWAIT_MAINT.ordinal() || this.status == Status.AWAIT_REPAIR.ordinal()) {
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
	 * Clears the current MR's assigned gate, assigning it as -1.
	 */
	public void clearGate() {
		this.gateNumber = -1;
	}

	/**
	 * 
	 * @return Instance variable integer 'gateNumber' used to identify current allocated gate
	 */
	public int getGate() {
		return this.gateNumber;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	//GETTERS
	
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
	
	/**
	 * Returns the instance variable String 'flightCode'
	 * @return
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * @return the integer representation of the Record's status.
	 */
	public int getStatus() {
		return this.status;
	}
	
	/**
	 * 
	 * @return The instance variable string faultDescription
	 */
	public String getFaultDescription() {
		return this.faultDescription;
	}
	
	//SETTERS
	
	/**
	 * Sets the instance variable 'status' equal to the passed integer
	 * @param newStatus : The new status to be set to.
	 */
	public void setStatus (int newStatus) {
		this.status = newStatus;
	}

	/**
	 * Sets the instance variable 'status' equal to the passed Status' ordial.
	 * @param newStatus : The new status enum to be set to.
	 */
	public void setStatus (Status newStatus) {
		this.status = newStatus.ordinal();
	}


}//EO Class

