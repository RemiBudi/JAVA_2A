

import java.util.*;
import java.lang.*;

public class ComputingPersonality{
    public static String[][] personalities={
            {"Alan","Turing"}, 
            {"Ken","Thompson"}, 
            {"Brian","Kernighan"},
            {"Dennis","Ritchie"},
            {"Ada","Lovelace"},
            {"Guido","Van Rossum"},
            {"Bjarne","Stroustrup"},
            {"Grace","Hopper"},
            {"Charles","Babbage"},
            {"Edsger","Dijkstra"},
            {"Hedy","Lamarr"},
            {"Larry","Wall"},
            {"Alan","Turing"}, 
            {"John","Backus"},
            {"Gary","Kildall"},
            {"Jean","Ichbiah"},
            {"Katherine","Johnson"},
            {"John","McCarthy"},
            {"Steve","Jobs"},
            {"Richard","Stallman"},
            {"Richard","Stallman"},
            {"Niklaus","Wirth"},
            {"Linus","Torvalds"},
            };

    final String surname; // nom de famille
    final String givenname; // prénom
    
    public ComputingPersonality(String surname,String givenname) {
        this.surname=surname;
        this.givenname=givenname;
    }     
    
    public String toString() {
        return String.format("%-9s %-20s",givenname,surname);
    }
    
    //4.
    public static void printSet(Set<ComputingPersonality> set, String var){
		
		System.out.println("-- "+var);
		
		for(ComputingPersonality c : set)
			System.out.println(c.toString());
		
	}
		
	
	//5.
	public static void initSet(Set<ComputingPersonality> set){
		
		for(int i=0; i<	personalities.length;i++)
			set.add(new ComputingPersonality(personalities[i][0], personalities[i][1]));
		
	}
		
    
    public static final void main(String[] args) {
   
		//Q1
		Set<ComputingPersonality> setPers = new HashSet<ComputingPersonality>();
		initSet(setPers);
	
		printSet(setPers,"setPers");

		//Q2
		SortedSet<ComputingPersonality> sortedSurname = new TreeSet<ComputingPersonality>(new Comparator<ComputingPersonality>(){
			public int compare(ComputingPersonality c1, ComputingPersonality c2){
				return c1.surname.compareTo(c2.surname);
			}
		});
		initSet(sortedSurname);

		printSet(sortedSurname,"sortedSurname");
		
		
		//Q3
		SortedSet<ComputingPersonality> sortedGivenname =  new TreeSet<ComputingPersonality>(new Comparator<ComputingPersonality>(){
			public int compare(ComputingPersonality c1, ComputingPersonality c2){
				return c1.givenname.compareTo(c2.givenname);
			}
		});
		initSet(sortedGivenname);

		printSet(sortedGivenname,"sortedGivenname\n");
   
    }
}
