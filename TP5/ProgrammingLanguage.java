


import java.util.SortedMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Collections;

public class ProgrammingLanguage {
    public static final Object[][] languages= {
            {1985,"C++"},
            {1979,"Rexx"},
            {1990,"Python"},
            {1998,"Java"},
            {1964,"Basic"},
            {1972,"Smalltalk"},
            {1995,"JavaScript"},
            {1970,"Forth"},
            {1957,"Fortran"},
            {1959,"Cobol"},
            {1960,"Algol"},
            {1964,"PL/1"},
            {1995,"PHP"},
            {1987,"Perl"},
            {1993,"Lua"},
            {1966,"APL"},
            {1972,"Prolog"},
            {1972,"C"},
            {1980,"Ada"},
            };
    final String name;
    final int year;
       
    
    public ProgrammingLanguage(String name,int year) {
        this.name=name;
        this.year=year;       
    }
    
    public String toString() {
        return String.format("[%4d]: %s",year,name);
    }
    
    
		
	//4.
	public static void printMap(SortedMap<?, ProgrammingLanguage> map, String var){
			
		System.out.println("-- "+var);
	
		for(Map.Entry<?, ProgrammingLanguage> entry : map.entrySet()){
			System.out.println(entry.getValue().toString());
			
		}
				
	}
    
    
    //5.
    public static <T> void initMap(SortedMap<T, ProgrammingLanguage> map, T type){
		
		for(int i=0; i<languages.length; i++){
			if(type instanceof Integer) map.put((T)languages[i][0], new ProgrammingLanguage((String)languages[i][1],(int) languages[i][0]));
			if(type instanceof String) map.put((T)languages[i][1], new ProgrammingLanguage((String)languages[i][1], (int)languages[i][0]));
		}
	}
	

    
    
    public static void main(String[] args) {
		
			
		//1.
		SortedMap<Integer, ProgrammingLanguage>  byYear = new TreeMap<Integer, ProgrammingLanguage>();
		SortedMap<String, ProgrammingLanguage>  byNameAlphabetic = new TreeMap<String, ProgrammingLanguage>();
		SortedMap<String, ProgrammingLanguage>  byNameReversed = new TreeMap<String, ProgrammingLanguage>(Collections.reverseOrder());
		
		
		initMap(byYear,0);	
		initMap(byNameAlphabetic,"");
		initMap(byNameReversed,"");
		
		
		printMap(byYear,"byYear");
		
		printMap(byNameAlphabetic,"byNameAlphabetic");
		
		printMap(byNameReversed,"byNameReversed");

		
		
		//8. Dans la map byYear, il manque le langage Javascript, parce qu'il a la même clé que le PHP, or chaque clé d'une map est unique.
		
		
		//9.
		SortedMap<Integer, ProgrammingLanguage>  eighties = byYear.subMap(1980,1990);
		
		printMap(eighties,"eighties");
		
		
     }
}
