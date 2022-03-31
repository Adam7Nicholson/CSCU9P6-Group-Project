package Passenger;

import java.util.ArrayList;
import java.util.Vector;

/** Object for storing arraylist of passenger's details on a flight. Contains one instance variable of an arraylist of PassengerDetails objects.
* @stereotype entity
* @author 2819600
*/
public class PassengerList{
	private ArrayList<PassengerDetails> details;
	
	/**
	 * Appends a passenger onto the PassengerDetails arrayList.
	 * @param details
	 */
	public void addPassenger(PassengerDetails details) {
		this.details.add(details);
	}
	
	/**
	 * 
	 * @return A String Vector of all passenger names
	 */
	public Vector<String> getList (){
		Vector<String> detailsList =  new Vector<String>();
		for (PassengerDetails passenger : details) {
			detailsList.addElement(passenger.getName());
		}
		return detailsList;
	}
	
	/**
	 * 
	 * @return Size of the passengerDetails arraylist
	 */
	public int size() {
		return details.size();
	}
}
