package Flights;

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
public class Itinerary {
	private String from;
	private String to;
	private String next;
	
	
	/**
	 * Write Javadoc Description Here
	 * @param from
	 * @param to
	 * @param next
	 */
	public Itinerary(String from, String to, String next){
      this.from = from;
      this.to = to;
      this.next = next;
  }

  
  public String getFrom(){
     return from;
  }

  
  public String getTo(){
        return to;
  }

  
  public String getNext(){
      return next;
   }

  
 
}
