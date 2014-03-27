package easybid;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EasyBidTests {
	EasyBid e = null;
	String path, workString;
	FileInputStream i = null;
	
	@Before
	public void setUp(){
		ClassLoader cl = EasyBid.class.getClassLoader();
		
		this.workString =  cl.getResource("").getPath();
		
		this.path = workString.substring(0, workString.length()-4);
		this.path += "src/easybid/tests/";
	}
	
	@After
	public void tearDown(){
		this.e = null;
		this.path = "";
		this.i = null;
	}
	
	@Test
	public void testEasyBid() {
		System.out.println("_______________________  testEasyBid  _________________________");
		e = new EasyBid();
		assertNotNull(e);
	}	
	

	@Test
	public void testCreateAndShowUsers(){
		System.out.println("_______________________  testCreateAndShowUsers  _________________________");
		System.out.println(path);
		try {
			path += "testCreateAndShowUsers.txt";
			i = new FileInputStream(path);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("File not found");
		}
		assertNotNull(i);
		
		e = new EasyBid(i);
		assertNotNull(e);
		
		this.e.run();
	}
	
}
