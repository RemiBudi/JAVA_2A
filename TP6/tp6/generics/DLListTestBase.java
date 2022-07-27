package tp6.generics;

import org.junit.jupiter.api.BeforeEach;

public class DLListTestBase {
    
    DLList dll;
    
    @BeforeEach
    public void initEach() {
        dll=new DLList();
    }
    
    protected static String element(int i) {
        return String.format(String.format("Element %03d",i));
    }
    
    
    protected void appendNElements(DLList dll,int n) {
        for (int i=0; i<n;i++) {
            dll.append(element(i+1));
        }
    }
    
    protected void pushNElements(DLList dll,int n) {
        for (int i=0; i<n;i++) {
            dll.push(element(i+1));
        }
    }
    
    protected void appendRandomElements(DLList dll,int n) {
        // Not completely ramdom elements
        String[] elems= {
                "Turing", 
                "Thompson", 
                "Kernighan",
                "Ritchie",
                "Van Rossum",
                "Stroustrup",
                "Babbage",
                "Dijkstra",
                "Wall",
                "Turing", 
                "Backus",
                "Ichbiah",
                "McCarthy",
                "Stallman",
                "Stallman",
                "Wirth",
                "Torvalds"};
        for (int i=0; i<Math.min(n,elems.length);i++) {
            dll.append(elems[i]);
        }
    }
}
