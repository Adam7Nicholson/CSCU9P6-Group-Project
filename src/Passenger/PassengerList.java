package Passenger;

import java.util.ArrayList;

/** Object for storing array of passenger's details on a flight. Contains one instance variable of an array of PassengerDetails objects.
* @stereotype entity
* @author 2819600
*/
public class PassengerList{
	private PassengerDetails[] details;
	
	/**
	 * Appends a passenger onto the PassengerDetails array.
	 * @param details
	 */
	public void addPassenger(PassengerDetails details) {
		//There are more graceful ways to achieve this, however it would involve changing the array into an ArrayList.
		ArrayList<PassengerDetails> passengerDetailsArrayList = new ArrayList<PassengerDetails>();
		for (int i = 0; i < this.details.length; i++) {
			passengerDetailsArrayList.add(this.details[i]);
		}
		passengerDetailsArrayList.add(details);
		this.details = (PassengerDetails[]) passengerDetailsArrayList.toArray();
	}
	
}
