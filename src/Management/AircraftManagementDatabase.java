package Management;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import javax.swing.Action;

import Flights.FlightDescriptor;
import Flights.Itinerary;
import Management.ManagementRecord.Status;
import Passenger.PassengerDetails;
import Passenger.PassengerList;
import Observers.GOC;

/**Class for handling validation of data requests and information changed. Stores and instantiates ManagementRecords in a local variable array.
 * MAX_MANAGEMENT_RECORDS denotes the maximum number of ManagementRecords the database will allow for.
 * @stereotype model
 * @author 2819600
 */

public class AircraftManagementDatabase extends Observable{
	public final int MAX_MANAGEMENT_RECORDS = 10;
	public final int MAX_PASSENGERS = 5;
	private ManagementRecord[] MRs;

	/**
	 * Creates an AirCraftManagementDatabase object, containing an array of ManagementRecords with a max size equal to the MAX_MANAGEMENT_RECORDS variable.
	 * Once array is created, creates a new, empty ManagementRecord object for each array value.
	 */
	public AircraftManagementDatabase() {
		this.MRs = new ManagementRecord[MAX_MANAGEMENT_RECORDS];
		for(int i = 0; i <MRs.length; i++){
			MRs[i] = new ManagementRecord();
		}
	}

	/**
	 * Internal method for verifying if mCodes are valid.
	 * If the mCode is <0 or > Number of Records -1, returns false.
	 * @param mCode
	 * @return If the mCode corresponds to an existing record
	 */
	private boolean isValidMCode(int mCode) {
		/*If the mCode exceeds the len-1 or is less than 0, the record cannot exist, ergo returns -1.
		Equally, it will return false if the mCode exceeds the max number of MRs allowed.*/
		return !((mCode > MAX_MANAGEMENT_RECORDS) || (mCode > (MRs.length-1)) || mCode < 0);
	}
	
	/** Internal method for verifying if a status code is valid.
	 * If the status exceeds the Statuses enum size-1 or is <0, it cannot be valid, ergo returns false.
	 * Otherwise, returns true.
	 * @param status
	 * @return
	 */
	private boolean isValidStatus(int status) {
		return !(status > Status.values().length-1 || status < 0);
	}

	/**
	 * Finds a managementRecord whos status is marked as 'FREE'. If one is found, forwards the descriptor.
	 * When one is found, all observers are notified.
	 * @param fd
	 */
	public void radarDetect (FlightDescriptor fd) {
		for (ManagementRecord MR : MRs) {
			if (MR.getStatus() == Status.FREE.ordinal()) {
				MR.radarDetect(fd);
				setChanged();
				notifyObservers();
				break;
			}//EO If
		}//EO For

		//TODO This method I am unsure as to its completion; If there is no found free MR, what happens?
		//If you are reading this and I (Adam) have forgot to raise it with the group already, please raise this
	}

	/**
	 * Forwards the radarLostContact request to the relevant MR and notifies all observers of this change, if the MR is valid.
	 * @param mCode
	 */
	public void radarLostContact (int mCode) {
		if(isValidMCode(mCode)) {
			MRs[mCode].radarLostcontact();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Indicates to the given record to taxi to the given gate, if the record is valid.
	 * Notifies all observers once state has changed - if it has.
	 * @param mCode
	 * @param gateNumber
	 */
	public void taxiTo (int mCode, int gateNumber) {
		if (isValidMCode(mCode)) {
			MRs[mCode].taxiTo(gateNumber);
			setChanged();
			notifyObservers();
		}
	}

	/** Indicates to the given record that faults have been found, passing the description of the faults along
	 * And notifies any observers of this change, if they have occured.
	 * @param mCode
	 * @param description
	 */
	public void faultsFound (int mCode, String description) {
		if (isValidMCode(mCode)) {
			MRs[mCode].faultsFound(description);
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Appends a given passenger's details to the given Management Record, as long as it exists.
	 * Once done, notifies any observers that the value has changed - if it has.
	 * @param mCode
	 * @param details
	 */
	public void addPassenger(int mCode, PassengerDetails details) {
		if (isValidMCode(mCode)) {
			MRs[mCode].addPassenger(details);
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Clears the list of passengers indicating that the UNLOADING process has been completed.
	 * Once done, notifies any observers that the value has changed.
	 * @param mCode
	 */
	public void clearPassengerList(int mCode) {
		MRs[mCode].getPassengerList().clearPassengerList();
		setChanged();
		notifyObservers();
	}

	
	/*
	 * GETTERS & SETTERS
	 */
	
	//GETTERS
	
	/**
	 * Returns the status the given record.
	 * If the given record does not exist, returns -1.
	 * @param mCode
	 * @return Int value of given record's status, if it exists
	 */
	public int getStatus(int mCode) {
		return isValidMCode(mCode) ? MRs[mCode].getStatus() : -1;
	}
	
	/**
	 * Returns a string representation of the given record's status, should it exist.
	 * If the code does not exist, returns null.
	 * @param mCode
	 * @return String representation of the Status enum.
	 */
	public String getStringStatus(int mCode){
		return isValidMCode(mCode) ? Status.values()[getStatus(mCode)].toString() : null;
	}
	
	/**
	 * Returns the flight code of given Management Record
	 * If the mCode is invalid, returns null.
	 * @param mCode
	 * @return The flight code of the given management record, if a valid code.
	 */
	public String getFlightCode(int mCode) {
		return isValidMCode(mCode) ? MRs[mCode].getFlightCode() : null;
	}
	
	/**
	 * Returns the list of passengers for a given record, should it exist.
	 * If the mCode does not exist, returns null.
	 * @param mCode
	 * @return
	 */
	public PassengerList getPassengerList(int mCode) {
		return isValidMCode(mCode) ? MRs[mCode].getPassengerList() : null;
	}
	
	/**
	 * Returns the Itinerary of a given record, should the record exist.
	 * If the record does not exist, returns null.
	 * @param mCode
	 * @return
	 */
	public Itinerary getItinirary(int mCode) {
		return isValidMCode(mCode) ? MRs[mCode].getItinerary() : null;
	}
	
	 /** @param flightCode : String representation of flight code
	 * @return mCode of next ManagementRecord with the given flight code.
	 */
	public int getMCode (String flightCode){
		for (int i = 0; i < MRs.length; i++) {
			if(MRs[i].getFlightCode().equalsIgnoreCase(flightCode)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get the MRs from the Radar Transceiver that will go to GOC
	 * @return A vector of the flightCodes of MRs that have statuses different from FREE and IN TRANSIT
	 * @author 2816391
	 * @author 2819600 (Minor legibility tweaks)
	 */
	public Vector<String> getGocMRs (){
		Vector<String> occupiedMRs = new Vector<String>();
		for (int i =0; i < MRs.length; i++) {
			if(MRs[i].getStatus() != Status.FREE.ordinal() && MRs[i].getStatus() != Status.IN_TRANSIT.ordinal()){
				occupiedMRs.add(MRs[i].getFlightCode());
			}//EO If
		}//EO For
		return occupiedMRs;
	}

	/**
	 * Get the MRs from the Radar Transceiver that will go to LATC
	 * @return ArrayList of all MRs that have status different from FREE
	 * @author 2823424
	 * @author 2819600 (Minor legibility tweaks)
	 */
	public ArrayList<String> getLatcMRs (){
		ArrayList<String> occupiedMRs = new ArrayList<String>();
		for (int i =0; i < MRs.length; i++) {
			if(MRs[i].getStatus() != Status.FREE.ordinal() && (MRs[i].getStatus() == Status.IN_TRANSIT.ordinal() || MRs[i].getStatus() == Status.WAITING_TO_LAND.ordinal()
					|| MRs[i].getStatus() == Status.GROUND_CLEARANCE_GRANTED.ordinal() || MRs[i].getStatus() == Status.LANDING.ordinal()
					|| MRs[i].getStatus() == Status.READY_DEPART.ordinal() || MRs[i].getStatus() == Status.AWAITING_TAXI.ordinal()
					|| MRs[i].getStatus() == Status.AWAITING_TAKEOFF.ordinal() || MRs[i].getStatus() == Status.DEPARTING_THROUGH_LOCAL_AIRSPACE.ordinal())){
				occupiedMRs.add(MRs[i].getFlightCode());
			}//EO If
		}//EO For
		return occupiedMRs;
	}
	
	/**
	 * Returns an int array of all mCodes with the given status.
	 * If the status code given is invalid, returns null.
	 * @param statusCode
	 * @return Int array of all mCodes with the given status
	 */
	public int[] getWithStatus(int statusCode) {
		if (!(isValidStatus(statusCode))) return null;

		ArrayList<Integer> mCodesList = new ArrayList<Integer>();

		/*This function iterates through each Record using mCode as the initialised iterator
		 * As the iterator in MRs works identically to a for loop, this is used.
		 * If the record's status code matches the given status code, the record's code is added to the list.*/

		for (int mCode = 0; mCode < MRs.length; mCode++) {
			if (MRs[mCode].getStatus() == statusCode)
				mCodesList.add(mCode);
		}
		//If list is empty, returns null. Else, returns an array of the mCodes with the given status.
		//For some unknown reason there is no kind way to parse Integer Lists to int arrays in Java
		return mCodesList.isEmpty() ? null : mCodesList.stream().mapToInt(i -> i).toArray();
	}
	
	/**
	 * 
	 * @return A String Vector of all ManagementRecord Flight Codes whos status is not "FREE"
	 */
	public Vector<String> getDetectedMRs(){
		Vector<String> detectedMRs = new Vector<String>();
		for (int i =0; i < MRs.length; i++) {
			if(MRs[i].getStatus() != Status.FREE.ordinal()){
				detectedMRs.add(MRs[i].getFlightCode());
			}
		}
		return detectedMRs;
	}
	
	//SETTERS
	
	/**
	 * If the given status code and mCode are valid, changes the mCode's status equal to the newStatus value.
	 * @param mCode
	 * @param newStatus
	 */
	public void setStatus (int mCode, int newStatus) {
		if (newStatus < ManagementRecord.Status.values().length || newStatus >-1) { //Validates Status
			if (isValidMCode(mCode))
				MRs[mCode].setStatus(newStatus);
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Returns instance variable MAX_PASSENGERS
	 * @return
	 */
	public int getMaxPassengers() {
		return MAX_PASSENGERS; 
	}

	/**
	 * Returns a list of all MRs with code:
	 * 0, 8, 9, 10, 11, 12
	 * @return
	 */
	public ArrayList<String> getMaintanceCleaningMRs() {
		ArrayList<String> maintenancecleaningMRs = new ArrayList<String>();
		for (int i = 0; i < MRs.length; i++) {
			if (MRs[i].getStatus() != Status.FREE.ordinal() && (MRs[i].getStatus() == 8 || MRs[i].getStatus() == 9
					|| MRs[i].getStatus() == 10 || MRs[i].getStatus() == 11 || MRs[i].getStatus() == 12)) {
				maintenancecleaningMRs.add(MRs[i].getFlightCode());
			}
		}
		return maintenancecleaningMRs;
	}

	/**
	 * Returns the gate of an MR, should it exist.
	 * If it does not, returns -1.
	 * @param mCode
	 * @return
	 */
	public int getGate(int mCode) {
		if (isValidMCode(mCode)) {
			return MRs[mCode].getGate();
		} else {
			return -1;
		}
	}

	/**
	 * Returns the fault description of a given mCode, should it exist. If not, returns null.
	 * @param mCode
	 * @return
	 */
	public String getFaults (int mCode){
		if (isValidMCode(mCode)) {
			return MRs[mCode].getFaultDescription();
		} else {
			return null;
		}
	}

	/**
	 * Clears the given gate for an mCode and notifies all subscribers.
	 * @param mCode
	 */
	public void clearGate (int mCode) {
		if (isValidMCode(mCode)) {
			MRs[mCode].clearGate();
		}
		setChanged();
		notifyObservers();
	}
	
	
}
