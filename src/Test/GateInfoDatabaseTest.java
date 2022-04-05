package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import Gate.Gate;
import Gate.GateInfoDatabase;

/**
 * @author 2839798
 */
public class GateInfoDatabaseTest {

	@Test
	public void validateMcodeTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(1);
		g.allocate(10);
		gi.addGate(g);
		int mcode=gi.getMCode(1);
		assertEquals(mcode, 10);

	}
	@Test
	public void validateDepartedTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(1);
		g.allocate(10);
		//g.setStatus(1);
		gi.addGate(g);
		gi.departed(1);
		int mcode=gi.getMCode(1);
		assertEquals(mcode, -1);

	}
	@Test
	public void validateDockTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(1);
		g.allocate(10);
		gi.addGate(g);
		gi.docked(1);
		int mcode=gi.getMCode(1);
		assertEquals(mcode, 10);
		int status=g.getStatus();		
		assertEquals(status,2);
	}
	@Test
	public void validateGetStatusTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(4);
		g.allocate(10);
		gi.addGate(g);
		assertEquals(gi.getStatus(4),1);
		assertEquals(gi.getStatus(10),-1);
	}
	@Test
	public void validateGetStringStatusTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(4);
		g.allocate(10);
		gi.addGate(g);
		assertEquals(gi.getStringStatus(4),"RESERVED");
	}
	
	@Test
	public void validateGetStatusesTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		Gate g = new Gate(4);
		g.allocate(10);
		gi.addGate(g);
		int arr[]=gi.getStatuses();
		assertEquals(1,arr[0]);
		assertEquals(0,arr[1]);
		
	}
	
	@Test
	public void validatemaxGateNumberTest()
	{
		GateInfoDatabase gi= new GateInfoDatabase();
		assertEquals(3,gi.maxGateNumber);
		
	}
}
