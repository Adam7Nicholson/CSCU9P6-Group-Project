package Flights;

/** An object containing information on the flight's current location and future destinations.
* @stereotype entity
* @author 2819600*/
public class Itinerary {
	private String from;
	private String to;
	private String next;
	
	
	/**
	 * Instantiates an Itenrary object containing information on a flight's path. 
	 * This includes:
	 * The destination from which the flight is from {from} 
	 * The destination the flight is travelling to {to}
	 * The next again destination after the aftermentioned 'to' location. {next}
	 * @param from
	 * @param to
	 * @param next
	 */
	public Itinerary(String from, String to, String next) {
		this.from = from;
		this.to = to;
		this.next = next;
	}

	/*
	 * GETTERS & SETTERS
	 */
	
	//GETTERS 
	
	/**
	 * Returns the destination the flight will depart from
	 * @return String Instance Variable 'from'
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Returns the current destination the flight will travel to
	 * @return String Instance Variable 'to'
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Returns the destination the flight will travel to after 'to'
	 * @return String Instance Variable 'next'
	 */
	public String getNext() {
		return next;
	}

	//SETTERS
}// EO Class.
