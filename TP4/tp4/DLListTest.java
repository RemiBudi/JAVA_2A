package tp4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;



public class DLListTest {
	
	private DLList dllist;
	private Iterator<String> ite;
	


	public DLListTest()  {
	}
	
	@Before
	public void createList(){
		dllist = new DLList();
	}
	
	
	
	//Add some Strings to a list
	public void addThreeString(){
		dllist.append("Tête");
		dllist.append("Milieu");
		dllist.append("Queue");
	}
	
	
	@Test
    public void testFirst(){
		addThreeString();
		assertEquals(dllist.first(),"Tête");
	}
	
	@Test
    public void testLast(){
		addThreeString();
		assertEquals(dllist.last(),"Queue");
	}
	
	
	@Test
    public void testIsEmpty(){
		assertTrue((new DLList()).isEmpty());
		addThreeString();
		assertFalse(dllist.isEmpty());
	}
	
	@Test
    public void testSize(){
		assertEquals(0,(new DLList()).size());
		addThreeString();
		assertEquals(3,dllist.size());
	}
		
	
	@Test
    public void testPop(){
		addThreeString();
		assertEquals(dllist.pop(),"Tête");
		assertEquals(dllist.size(),2);
		assertEquals(dllist.first(),"Milieu");
	}
	
	@Test
	public void testPopException() {
		 Exception e;
	      e=assertThrows(EmptyStackException.class,() -> { dllist.pop();});
	      assertEquals(e.getMessage(),null);
	}
	
	@Test
	public void testPopSingleElement() {
		dllist.push("single element");
		dllist.pop();
		assertEquals(dllist.size(),0);
		
	}
	
	
	@Test
	public void testPush(){
		addThreeString();
		dllist.push("Nouvelle tête");
		assertEquals(dllist.first(),"Nouvelle tête");
	}
	
	@Test
	public void testEmptyPush() {
		dllist.push("Tête et queue");
		assertEquals(dllist.first(), "Tête et queue");
		assertEquals(dllist.last(), "Tête et queue");
	}
	
	@Test
	public void testAppend(){
		addThreeString();
		dllist.append("Nouvelle queue");
		assertEquals(dllist.last(),"Nouvelle queue");
	}
	
	@Test
	public void testEmptyAppend() {
		dllist.append("Tête et queue");
		assertEquals(dllist.first(), "Tête et queue");
		assertEquals(dllist.last(), "Tête et queue");
	}
	
	@Test
	public void testReverse() {
		addThreeString();
		DLList reverseList = new DLList();
		reverseList.append("Tête");
		reverseList.append("Milieu");
		reverseList.append("Queue");
		reverseList.reverse();
		
		assertEquals(dllist.first(), reverseList.last());
		assertEquals(dllist.last(), reverseList.first());
	}
	
	
	@Test
	public void testHasNext() {
		ite = dllist.iterator();
		assertFalse(ite.hasNext());
		addThreeString();
		assertTrue(ite.hasNext());
	}
	
	@Test
	public void testNext() {
		addThreeString();
		ite = dllist.iterator();
		
		assertEquals(ite.next(),"Tête");
		assertEquals(ite.next(),"Milieu");
		assertEquals(ite.next(),"Queue");
	}
	
	@Test
	public void testNextException() {
		ite = dllist.iterator();
		
		 Exception e;
	     e=assertThrows(NoSuchElementException.class,() -> { ite.next();});
	     assertTrue(e.getMessage().contains("No next in the list"));		
		
	}
	

	
}	
	
	
