import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;	
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;



public class Metrics{



//words counter
public static int wordCount(String file) throws IOException{
	
	
	int count;
	File f = new File(file);
try(Scanner sc = new Scanner(new FileInputStream(f))){
    count=0;
    while(sc.hasNext()){
        sc.next();
        count++;
	}
}
	return count;

}


//Check is a String is only made of letter
public static boolean isWord(String s) {
    char[] chars = s.toCharArray();

    for (char c : chars) {
        if(!Character.isLetter(c)) {
            return false;
        }
    }

    return true;
}


public static String letterFrequency(String file) throws IOException {
	
	// An array of 26 cells that represents the alphabet. Each cell contains the number of appearance of its letter (represented by its index)
	int[] alph = new int[26];
		
	int line;
	String tmpLower;
	char tmpLower2;
	
	String result="";
	
	BufferedReader code = new BufferedReader(new FileReader(file));

	//Read the message char by char
    while ((line = code.read()) != -1) {
		
		char charTmp = (char) line;
		
		if((charTmp >= 'A' && charTmp <= 'Z') || (charTmp >= 'a' && charTmp <= 'z')){

			//Converting the uppercase to lowercase
			tmpLower = (String.valueOf(charTmp)).toLowerCase();

			tmpLower2 = tmpLower.charAt(0);
			
			
			// 'a' is the 97 character in ASCII, so we substract 97 to have 'a' in the first cell of the array
			int tmp = (int)tmpLower2;
			int value = tmp - 97;
			
			// Increase the occurrence of the letter in the array
			alph[value]++;
		}
	}
		
		int charTot=0;
		for(int i=0; i<26; i++) charTot+= alph[i];
		
		
		char ch = 'a';
		for(int i=0; i<26; i++){
			result+= "["+ch+"] "+((float)alph[i]/(float)charTot*100+"\n");
			ch++;
		}

	return result;
	
}

	
//20 most used words
public static String mostUsedWords(String file) throws IOException {
	
		
	// We use a Map to stock each word and its number of appearance	
	Map<String, Integer> freq = new TreeMap<String, Integer>();
	
	
	File f = new File(file);
	
	String tmp, tmpLowerCase;
	String result="";
	 
	try(Scanner sc = new Scanner(new FileInputStream(f))){
        while(sc.hasNext()){
        tmp = sc.next();
        tmpLowerCase= tmp.toLowerCase();
        
			if(isWord(tmpLowerCase)) {
        
			//If the map contains this word, we increase its key (occurrence), otherwise we add it to the Map
				if (freq.containsKey(tmpLowerCase)) freq.put(tmpLowerCase, (freq.get(tmpLowerCase))+1);
				else freq.put(tmpLowerCase, 1);
        
			}	
		}
	}
	
	 // Sorting the Map by the number of occurrences of words (the Values in the Map)
	  Map<String, Integer> sorted = freq
        .entrySet()
        .stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(
         toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
	
	
	int count = 0;
	
	// Taking only the 20 most used words
	for (Map.Entry mapentry : sorted.entrySet()){
		//	System.out.println(mapentry.getKey() +" "+mapentry.getValue());
			count++;
			result+= mapentry.getKey() +" : "+mapentry.getValue()+"\n";
			if(count==20) break;
		}
	return result;
	
}
	
	


public static void main(String[] args) throws IOException {
	
	try{
	
		System.out.println("This text contains "+wordCount(args[0])+" words");
	
		System.out.println("\nFrequency of each character (in %) : \n"+letterFrequency(args[0]));
	
		System.out.println("\nThe 20 most used words are : \n\n"+mostUsedWords(args[0]));
	
	} catch (FileNotFoundException|ArrayIndexOutOfBoundsException e) 
		{
			System.out.println("La commande d'éxécution doit être de la forme : java Metrics NomDuFichier\n");
			return;
		}
		
	
}

}
