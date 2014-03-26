package environment;

import static org.junit.Assert.*;
import java.lang.Thread;
import java.util.Date;

import org.junit.Test;

public class BitTimerTest {

	BidTimer t;
	
	@Test
	public void testBidTimer() {
		t  = new BidTimer();
		assertNotNull(t);
		assertEquals(false, t.getFixedDate());
	}
	@Test
	public void testBidTimerOfDateD() {
		Date d = new Date(System.currentTimeMillis()+300000);
		t  = new BidTimer(d);
		assertNotNull(t);
		assertEquals(true, t.getFixedDate());
	}
	@Test
	public void testRefreshTime() {
		t  = new BidTimer();
		long t2 = t.getTime();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.refreshTime();
		
		if(t2 == t.getTime()){
			fail("The time was not updated");
		}
	}
	
	public void testGetTime(){
		//Date t = DateFormat.
	}

}
