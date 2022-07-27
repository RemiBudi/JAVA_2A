

import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 *********************************************  
 * TODO: Expliquez ici vos choix de paramètres
 *********************************************  
 */
public class DLList<T> implements Iterable<T> {

    private Node<T> head = null;
    private Node<T> tail = null;

/**
   ******************************************************************  
   TODO: Mettez ici la déclaration de la version non-statique de Node
   ******************************************************************     
*/   

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        final T payload;

        Node(T payload, Node<T> prev, Node<T> next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Standard "inner member" iterator
     * @author bd
     *
     */
    private class DLIterator implements Iterator<T> {
        private Node<T> nextReturned = head; // points to the one we return on some future next()

        public boolean hasNext() {
            return nextReturned != null;
        }

        public T next() {
            if (nextReturned == null) {
                throw new NoSuchElementException();
            } else {
                T returned = nextReturned.payload;
                nextReturned = nextReturned.next;
                return returned;
            }
        }

        /* 
         * remove() method, removed
         * Not a problem since it is already payload-class agnostic
         */
        @Override
        public void remove() { throw new UnsupportedOperationException(); };
    }
        
    public Iterator<T> iterator() {
        return new DLIterator();
    }
    
    public T last() {
        return tail == null ? null : tail.payload;
    }

    public T first() {
        return head == null ? null : head.payload;
    }

    public void append(T payload) {
        Node<T> node = new Node<T>(payload, tail, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void push(T payload) {
        Node<T> node = new Node<T>(payload, null, head);
        if (tail == null) {
            tail = node;
        } else {
            head.prev = node;
        }
        head = node;
    }

    public boolean isEmpty() {
        return head==null;
    }
    
    public T pop() {
        if (head == null) { // empty list
           throw new EmptyStackException();
        }
        T s = head.payload;
        head = head.next;
        // tail remains the same until we pop the last
        if (head == null) {
            tail = null; // popped last one
        } else {
            head.prev=null; // the new first has no predecessor
        }
        return s;
    }

    /**
     * reverse() method removed
     * like remove(), it is already payload-class agnostic
     */ 
    public void reverse() { throw new UnsupportedOperationException(); };

   /* public void sort() {
        // Bubble sort

        // Skip the cases where the list is empty (head==tail==null)
        // or the list is a singleton (head==tail==element)
        if (head == tail) {
            return;
        }

        class StringComparatorWithNulls { // sorts null first 
            int compare(String s1,String s2) {
                if (s1==null) return -1; // s1 smaller and "stable" sort
                if (s2==null) return 1; // null smaller than anything non null
                return s1.compareTo(s2);
            }
        }
        
        var comparator=new StringComparatorWithNulls(); 
        Node last = null; // no need to sort that one
        Node n1, n2; // the two nodes in the bubble, n1 closer to head
        while (head != last) {    
            for (n1 = head, n2 = head.next; n2 != last; n1 = n2, n2 = n2.next) {
                if (comparator.compare(n1.payload,n2.payload) > 0) { // Swap the two nodes
                    
                    // The two nodes before and after the bubble
                    Node before = n1.prev;
                    Node after = n2.next;

                    // n1 becomes last
                    if (after == null) {
                        tail = n1;
                    } else {
                        after.prev = n1;
                    }
                    n1.next = after;
                    n1.prev = n2;

                    // n2 becomes first
                    if (before == null) {
                        head = n2;
                    } else {
                        before.next = n2;
                    }
                    n2.next = n1;
                    n2.prev = before;

                    // swap the nodes in the iteration variables
                    Node temp = n1;
                    n1 = n2;
                    n2 = temp;
                }
            }

            // and not n2, because the 3rd part of the for(...;...;...)
            // has already shifted the bubble
            last = n1;
        }
    } */

    void dump(PrintStream s) {
        for (Node<T> n = head; n != null; n = n.next) {
            s.println(n.payload);
        }
    }
    
    public static void main(String[] args){
		
		DLList<Integer> test = new DLList<Integer>();
		test.push(1);
		test.append(2);
		test.append(3);
		test.dump(System.out);
	}
    
}
