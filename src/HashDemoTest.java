import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * 
 */

/**
 * @author chaos
 *
 */
public class HashDemoTest {
	public HashDemo<Integer, String> testTable;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testTable = new HashDemo<Integer,String>();
	}

	@Test
	public void testAdd() {
		testTable.add(1, "test");
		assertEquals(1, testTable.getSize());
	}
	
	@Test
	public void testGet(){
		testTable.add(1, "smoke");
		String ans = testTable.getValue(1);
		assertEquals("smoke", ans);
	}
	
	@Test
	public void testUpdate(){
		testTable.add(1, "fire");
		assertEquals("fire", testTable.getValue(1));
		assertEquals(1, testTable.getSize());
		
		
		testTable.update(1, "Updated");
		assertEquals("Updated", testTable.getValue(1));
		assertEquals(1, testTable.getSize());
	}
	
	@Test
	public void testAddUpdate(){
		testTable.add(1, "fire");
		assertEquals("fire", testTable.getValue(1));
		assertEquals(1, testTable.getSize());
		
		
		testTable.add(1, "Updated");
		assertEquals("Updated", testTable.getValue(1));
		assertEquals(1, testTable.getSize());
	}
	
	@Test
	public void testDeleteEmpty(){
		assertEquals(0, testTable.getSize());
		testTable.delete(1);
		assertEquals(0, testTable.getSize());
	}
	
	@Test
	public void testDeleteCollision(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.delete(1);
		assertEquals("test1", testTable.getValue(12));
		assertEquals("testTable2", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(2, testTable.getSize());
	}
	
	@Test
	public void testDeleteCollision1(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.delete(12);
		assertEquals("test", testTable.getValue(1));
		assertEquals("testTable2", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(2, testTable.getSize());
	}
	
	@Test
	public void testDeleteCollision2(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.delete(23);
		assertEquals("test", testTable.getValue(1));
		assertEquals("test1", testTable.getValue(12));
		assertEquals(11, testTable.getLength());
		assertEquals(2, testTable.getSize());
	}
	
	@Test
	public void testUpdateNone(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.update(1, "t");
		assertEquals("t", testTable.getValue(1));
		assertEquals("test1", testTable.getValue(12));
		assertEquals("testTable2", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(3, testTable.getSize());
	}
	
	@Test
	public void testUpdateNone1(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.update(12, "t");
		testTable.update(23, "v");
		assertEquals("test", testTable.getValue(1));
		assertEquals("t", testTable.getValue(12));
		assertEquals("v", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(3, testTable.getSize());
	}
	
	@Test
	public void testUpdateNone3(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.update(7, "t");
		assertEquals("test", testTable.getValue(1));
		assertEquals("test1", testTable.getValue(12));
		assertEquals("testTable2", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(3, testTable.getSize());
	}
	
	@Test
	public void testUpdateNone4(){
		testTable.add(1, "test");
		testTable.add(12, "test1");
		testTable.add(23, "testTable2");
		testTable.update(34, "t");
		assertEquals("test", testTable.getValue(1));
		assertEquals("test1", testTable.getValue(12));
		assertEquals("testTable2", testTable.getValue(23));
		assertEquals(11, testTable.getLength());
		assertEquals(3, testTable.getSize());
	}
	
	@Test
	public void testCollision(){
		testTable.add(1, "test1");
		testTable.add(12, "test2");
		assertEquals("test1", testTable.getValue(1));
		assertEquals("test2", testTable.getValue(12));
	}

	@Test
	public void testResize(){
		for(int i = 0; i < 40; i++){
			testTable.add(i, "test"+i);
		}
		for(int i = 0; i < 40; i++){
			assertEquals("test"+i, testTable.getValue(i));
		}
		assertEquals(197,testTable.getLength());
		assertEquals(40, testTable.getSize());
	}
	
	@Test
	public void testGetSize(){
		assertEquals(11, testTable.getLength());
	}
	
	
}
