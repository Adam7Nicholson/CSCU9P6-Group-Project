package Passenger;

/**A class designed for the storing of passenger details. 
 * As of current version, this only contains a passenger's name
 * However, this class could be expanded upon in future to contain other information.
 * @stereotype entity
 * @author 2819600*/
public class PassengerDetails {
	private String name;
	
	/** Constructs passengerDetails object & sets instance variable 'name' equal to passed String.
	 * This variable denote's the passenger's legal name.
	 * @param name*/
	public PassengerDetails (String name) {
		this.name = name;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	 /** @return Instance variable string 'name'*/
	public String getName() {
		return name;
	}
}
