package tp6.generics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class DLListSimpleOpsTest extends DLListTestBase {
   
    @Test 
    public void empty() {
        final String stored="Something";
        assertTrue(dll.isEmpty());
        dll.append(stored);
        assertTrue(stored==dll.first());
        assertTrue(stored==dll.last());
        assertFalse(dll.isEmpty());
        var retrieved=dll.pop();
        assertTrue(stored==retrieved); // works because it is the same object all along.
        assertEquals(stored,retrieved); 
        assertTrue(dll.isEmpty());
    }

    @Test
    public void append1() {
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
        dll.append(element(1));
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(1),dll.pop());
        assertEquals(null,dll.first());
        assertEquals(null,dll.last());
        assertTrue(dll.isEmpty());
    }
    
    @Test
    public void append2() {
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
        dll.append(element(1));
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        dll.append(element(2));
        assertEquals(element(1),dll.first());
        assertEquals(element(2),dll.last());
        assertEquals(element(1),dll.pop());
        assertEquals(element(2),dll.first());
        assertEquals(element(2),dll.last());
        assertEquals(element(2),dll.pop());
        assertEquals(null,dll.first());
        assertEquals(null,dll.last());
        assertTrue(dll.isEmpty());
    }
    
    @Test
    public void append() {
        appendNElements(dll,3);
        for (int i=1;i<4;i++) {
            assertEquals(String.format("Popping %s",element(i)),element(i),dll.pop());
        }
        assertTrue(dll.isEmpty());
    }
    
    @Test
    public void push1() {
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
        dll.push(element(1));
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(1),dll.pop());
        assertEquals(null,dll.first());
        assertEquals(null,dll.last());
        assertTrue(dll.isEmpty());
    }

    @Test
    public void push2() {
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
        dll.push(element(1));
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        dll.push(element(2));
        assertEquals(element(2),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(2),dll.pop());
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(1),dll.pop());
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
    }

    @Test
    public void push3() {
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
        dll.push(element(1));
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        dll.push(element(2));
        dll.push(element(3));
        assertEquals(element(3),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(3),dll.pop());
        assertEquals(element(2),dll.pop());
        assertEquals(element(1),dll.first());
        assertEquals(element(1),dll.last());
        assertEquals(element(1),dll.pop());
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
    }

    @Test
    public void push() {
        final int count=200;
        pushNElements(dll,count);
        for (int i=count;i>0;i--) {
            assertEquals(String.format("Popping %s",element(i)),element(i),dll.pop());
        }
        assertTrue(dll.isEmpty());
    }

    @Test
    public void pushAndAppend() {
        dll.push(element(3));
        dll.append(element(4));
        dll.push(element(2));
        dll.append(element(5));
        dll.push(element(1));
        for (int i=1;i<6;i++) {
            assertEquals(String.format("Popping %s",element(i)),element(i),dll.pop());
        }
        assertEquals(null,dll.last());
        assertEquals(null,dll.first());
        assertTrue(dll.isEmpty());
    }
}
