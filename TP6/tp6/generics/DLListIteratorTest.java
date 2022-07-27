package tp6.generics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class DLListIteratorTest extends DLListTestBase {

    private void anyIterator(Iterator<String> iter,int count) {
        for (int i=1;i<=count;i++) {
            assertTrue(iter.hasNext(),String.format("Getting %s",element(i)));
            assertEquals(element(i), iter.next());
        }
        assertFalse(iter.hasNext(),"There should be nothing left");
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    public void plainIterator() {
        final int count=3;
        appendNElements(dll, count);
        anyIterator(dll.iterator(),count);
    }

    @Test
    public void iteratorEmpty() {
        var iterator=dll.iterator();
        while (iterator.hasNext()) {
            fail("There should be no iteration");
            iterator.next();
        }
    }

    @Test
    public void independentIterators() {
        // Work two iterators side by side to demonstrate independence
        appendNElements(dll, 3);
        var i1 = dll.iterator();
        var i2 = dll.iterator();

        // Step i1 twice
        assertTrue(i1.hasNext());
        assertEquals(element(1), i1.next());
        assertTrue(i1.hasNext());
        assertEquals(element(2), i1.next());

        // Step i2 until end, should also start on 1 (and not 3)
        assertTrue(i2.hasNext());
        assertEquals(element(1), i2.next());
        assertTrue(i2.hasNext());
        assertEquals(element(2), i2.next());
        assertTrue(i2.hasNext());
        assertEquals(element(3), i2.next());
        assertFalse(i2.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            i2.next();
        });

        // I1 not finished yet, shoud still have one step to end.
        assertTrue(i1.hasNext());
        assertEquals(element(3), i1.next());
        assertFalse(i1.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            i1.next();
        });
    }

    @Test
    public void forLoop() {
        final int size = 5;
        appendNElements(dll, size);
        int i = 0;
        for (String is : dll) { // uses the iterator implicitly
            i += 1;
            assertEquals(is, element(i));
        }
        assertEquals(size,i);
    }

    @Test
    public void forLoopEmpty() {
        for (String is : dll) { // uses the iterator implicitly
            fail("There should be no iteration");
            assertEquals(is, "XYZ"); // never executed, just to suppress warning
        }
    }
}
