package tp4;

import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DLListTest {
	
	private DLList dllist;


	public DLLisTest()  {
	}
	
	@BeforeEach
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
    public void testisEmpty(){
		assertTrue((new DLList()).isEmpty());
		assertFalse(dllist.iEmpty());
	}
	
	@Test
    public void testSize(){
		assertEquals(0,(new DLList()).size())
		dllist.addThreeRdv();
		assertEquals(3,dllist.size());
		
	
	@Test
    public void testPop(){
		dllist.addThreeString();
		assertEquals(dllist.pop(),"Tête");
		assertEquals(dllist.size(),2);
		assertEquals(dllist.first(),"Milieu");
	}
	
	
