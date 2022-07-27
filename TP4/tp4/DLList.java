package tp4;

/*
 * Questions:
 * 
 * Rendez la classe Node "static"
 * 
 * 1) Est-ce que ça change quelque chose au code, et pourquoi?
 * 
 * (votre réponse ici)
 * 
 * 2) Serait-ce préférable à une version non-static, et pourquoi?
 * 
 * (votre réponse ici)
 * 
 * Dupliquez la classe DLIterator sous un nouveau nom: StaticDLIterator et marquez la "static":
 * 
 * 3) Pouquoi toutes les erreurs?
 * 
 * (votre réponse ici)
 * 
 * 4) Que faut-il faire pour supprimer les erreurs en gardant le "static"?
 * 
 * (votre réponse ici)
 * 
 * 5) Peut-on définir l'itérateur comme une classe interne "locale", et pourquoi?
 * 
 * (votre réponse ici)
 * 
 * 6) Peut-on définir l'itérateur comme une classe interne "anonyme, et pourquoi"?
 *
 * (votre réponse ici)
 * 
 */

import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DLList implements Iterable<String> {

    private Node head = null;
    private Node tail = null;

    private static class Node {
        private Node prev;
        private Node next;
        final String payload; // "final" so we can't move it across nodes in reverse() and sort()

        Node(String payload, Node prev, Node next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DLIterator implements Iterator<String> {
		
		private int index = 0;
		private Node current=head;
		private Node lastAccessed=null;
		
        // TODO:
        public boolean hasNext() {
        	
			return index < size();
        }

        // TODO:
        public String next() {
			
            if(!(hasNext())) throw new NoSuchElementException("No next in the list");
			
            	lastAccessed = current;
				String s = current.payload;
				current=current.next;
				index++;
				return s;
			
        }

        // TODO:
        public void remove() {
        	
        	if(lastAccessed==null)   throw new IllegalStateException("Call the methode next before using remove");
        	
        	Node a = lastAccessed.prev;
        	Node b = lastAccessed .next;
        	a.next = b;
        	b.prev = a;
        	lastAccessed = null;
        	index--;
          
        }
    } 
    
    
    public Iterator<String> iterator() {
        return new DLIterator();
    } 

    // TODO:
    public String last() {
        return this.tail.payload;
    }

    // TODO:
    public String first() {
        return this.head.payload;
    }

    // TODO: push the String as last element of the list
    public void append(String payload) {
		
		if(isEmpty()) head = tail = new Node(payload,null,null);
		
		else{
			this.tail.next =  new Node(payload, this.tail, null);
			this.tail = this.tail.next;
		}
    }
    

    // TODO: push the String as first element of the list
    public void push(String payload) {
		
		if(isEmpty()) head = tail = new Node(payload,null,null);
		
		else{
			this.head.prev = new Node(payload,null,this.head);
			this.head = this.head.prev;
		}
    }
    
    // TODO:
    public boolean isEmpty() { 
		
		if(this.head == null && this.tail == null) return true;
		else return false;
    }
    
    
    // TODO:
    public String pop() { 
		
			if(isEmpty()) throw new EmptyStackException();
			
			else if(this.size()==1){
				String s =this.head.payload;
				this.head = null;
				this.tail = null;
				return s;
			}
				
			
		else{
			String s = this.head.payload;
			this.head = this.head.next;
			this.head.prev = null;
			
			return s;
		}
     }
    
    // TODO:
    public void reverse() {
		
		Node n = tail;
		tail = head;
		head = n;
		
		Node tmp = tail;
		while(tmp!=null){
			n = tmp.prev;
			tmp.prev = tmp.next;
			tmp.next=n;
			
			tmp = tmp.prev;
		}
		
    }
    
    // TODO:
    public void sort() {
    	
    }
    
    void dump(PrintStream s) {
        for (Node n=head;n!=null;n=n.next) {
            s.println(n.payload);
        }
    }
    
    public int size(){
    	
    	if(isEmpty()) return 0;
    	
		int n=1;
		Node current=head;
		while(current.next != null){
			n++;
			current = current.next;
		}
		return n;	
}

public static void main(String[] args){
	

	DLList test = new DLList();
	test.push("Tête");
	test.append("Milieu");
	test.append("Queue");
	test.push("Tête 2");
	test.dump(System.out);
	
	System.out.println("\nSize :"+test.size());
	
		
	System.out.println("\nPop:");
	String s = test.pop();
	test.dump(System.out);
	
	System.out.println("\nReverse:");
	test.reverse();
	test.dump(System.out);
	
	
	System.out.println("\nIterator:");
	
	Iterator<String> ite = test.iterator();
	
	
	

	

}


}
