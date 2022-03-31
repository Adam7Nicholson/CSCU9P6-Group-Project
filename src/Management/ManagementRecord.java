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

	/**
	 * Instantiates managementRecord object and sets status to FREE, leaving instance variables blank to be assigned from a FlightDescriptor.
	 */
	public ManagementRecord() {
		setStatus(Status.FREE.ordinal());
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
		if (this.status == Status.IN_TRANSIT.ordinal() || this.status == Status.DEPARTING_THROUGH_LOCAL_AIRSPACE.ordinal()) {	
			//Clears the contents of this MR and sets the status to FREE
			this.itinerary = null;
			this.passengerList = null;
			this.faultDescription = "";
			this.gateNumber = -1;
			this.status = Status.FREE.ordinal();
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

