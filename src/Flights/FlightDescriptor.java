package Flights;

import java.awt.List;

import Passenger.PassengerList;

/**
* Instantiates a flightdescriptor object containing all information for a flight
* This includes:
* The flight code, used as an identifier of the flight {flightCode}
* The itinerary, used to track the flight's destination(s) {itinierary}
* The list of passengers on the flight: Note this is a PassengerList object and not a List object. {list}
* @stereotype entity
* @author 2823424
* @author 2819600 
*/
public class FlightDescriptor {
	private String flightCode;
	private Itinerary itinerary;
	private PassengerList list;
	
	/** Instantiates a flightdescriptor object containing all information for a flight
	 * This includes:
	 * The flight code, used as an identifier of the flight {flightCode}
	 * The itinerary, used to track the flight's destination(s) {itinierary}
	 * The list of passengers on the flight: Note this is a PassengerList object and not a List object. {list}
	 * @param flightCode
	 * @param itinerary
	 * @param list
	 */
	public FlightDescriptor(String flightCode, Itinerary itinerary, PassengerList list) {
		this.flightCode = flightCode;
		this.itinerary = itinerary;
		this.list = list;
	}

	/*
	 * GETTERS & SETTERS
	 */
	
	//GETTERS
	 /** @return Instance variable String 'flightCode'*/
	public String getFlightCode() {
		return flightCode;
	}

	 /** @return Instance variable Itinerary object 'itinerary'*/
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	 /** @return Instance variable PassengerList object 'list' containing passenger information.*/
	public PassengerList getList() {
		return list;
	}

	//SETTERS
}//EO Class.
