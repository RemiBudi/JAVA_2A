package tp6.generics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Test;

public class DLListSortTest extends DLListTestBase {
    @Test
    public void sortSorted() {
        appendNElements(dll,5);
        assertTimeout(Duration.ofMillis(200), () -> { dll.sort(); });
        assertEquals(element(1),dll.first());
        assertEquals(element(5),dll.last());
    }

    @Test
    public void sortRandom() {
        DLList dll=new DLList();
        appendRandomElements(dll,20);
        assertTimeout(Duration.ofMillis(200), () -> { dll.sort(); });
        String s1=dll.pop();
        String s2;
        while (!dll.isEmpty()) {
            s2=dll.pop();
            assertTrue(s1.compareTo(s2)<=0); // check that each element is larger than its predecessor
            s1=s2;
        }
    }
    
    @Test
    public void sortRandomIterator() {
        // Same as above but we also test that the list structure 
        // is not so damaged that iterator don't work
        DLList dll=new DLList();
        appendRandomElements(dll,20);
        assertTimeout(Duration.ofMillis(200), () -> { dll.sort(); });
        String s1=null;
        for (String s2: dll) {
            if (s1!=null) {  
                assertTrue(s1.compareTo(s2)<=0); // check that each element is larger than its predecessor
            }
            s1=s2;
        }
    }
    
    @Test
    public void sortWithNullFirst() {
        DLList dll=new DLList();
        dll.append(element(1));
        dll.append(null);
        dll.append(element(2));
        dll.append(null);        
        assertTimeout(Duration.ofMillis(200), () -> { dll.sort(); });
        assertEquals(null,dll.pop());
        assertEquals(null,dll.pop());
        assertEquals(element(1),dll.pop());
        assertEquals(element(2),dll.pop());
    }
}
