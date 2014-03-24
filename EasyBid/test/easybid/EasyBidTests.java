package easybid;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.naming.spi.DirectoryManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EasyBidTests {
	EasyBid e = null;
	String path;
	FileInputStream i = null;
	
	@Before
	public void setUp(){
		ClassLoader cl = EasyBid.class.getClassLoader();
		this.path =  cl.getResource("").getPath()+"easybid/";
		
	}
	
	@After
	public void tearDown(){
		this.e = null;
		this.path = "";
		this.i = null;
	}
	
	@Test
	public void testEasyBid() {
		e = new EasyBid();
		assertNotNull(e);
	}	
	
	@Test
	public void testRun() {
		
		
		System.out.println(path);
		try {
			path += "tests/testRun.txt";
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
	
	/*
	@Test
	public void testEasyBiud() {
		fail("Not yet implemented");
	}

	@Test
	public void testRuny() {
		fail("Not yet implemented");
	}
	*/
}
