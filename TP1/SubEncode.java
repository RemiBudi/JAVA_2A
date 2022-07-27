import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SubEncode{



public static String cesar(char c, int i) {

     int character = (int)c; //Character.getNumericValue(c);
     int newInt = c + i;
     
          
     //if the new char crosses the 'z' or 'Z'
     if(c >= 'A' && c <= 'Z' && newInt> 'Z') newInt = 'A' + (i - ( 'Z' - c)); 
     
     if(c >= 'a' && c <= 'z' && newInt> 'z') newInt = 'a' + (i - ( 'z' - c)); 
       
     char newChar = (char) newInt;

     
     return String.valueOf(newChar);
     
}



//Build a uppercase Polybe square with a certain shift
public static int[][] buildPolybeUppercase(int shift){
	
	
	//Polybe square
	int[][] tab = new int[5][5];
	
	//new shift if shift > 26
	int shiftValue = shift%26;
	
	//First value of the square, 90 ( 'Z' in ASCII ) - the shift 
	int firstValue = 90 - shiftValue;
	
	//Filling the square
	for(int i=0; i<5; i++){
		for(int j=0;j<5;j++){
			
			//Remove the 'J' to put it in the same cell as 'I'
			if(firstValue == 74){
				firstValue++;
				tab[i][j] = firstValue;
			}
			
			else {
			tab[i][j] = firstValue;
			}
			
			firstValue ++;
		
			// if we cross 90 ( = 'Z', we return to 'A' (65))
			if(firstValue > 90) firstValue = 65;
		}
	}

	return tab;
}

//Build a lowercase Polybe square with a certain shift
public static int[][] buildPolybeLowercase(int shift){
	
	
	//Polybe square
	int[][] tab = new int[5][5];
	
	//new shift if shift > 26
	int shiftValue = shift%26;
	
	//First value of the square, 122 ( 'z' in ASCII ) - the shift 
	int firstValue = 122 - shiftValue;
	
	//Filling the square
	for(int i=0; i<5; i++){
		for(int j=0;j<5;j++){
			
			//Remove the 'j' to put it in the same cell as 'i'
			if(firstValue == 106){
				firstValue++;
				tab[i][j] = firstValue;
			}
			
			else {
			tab[i][j] = firstValue;
			}
			
			firstValue++;
			
			// if we cross 122 ( = 'z', we return to 'a' (97))
			if(firstValue > 122) firstValue = 97;
		}
	}
	return tab;
}



//Browse a character in Polybe square and return its position in a String
public static String browseChar(int[][] tab, int c){
	
	String position="";
	
	//if the character is 'j' or 'J', we use the same cell as 'i' or 'I'
	if(c == 74) position = browseChar(tab,73); 
	if(c == 106) position = browseChar(tab,105); 
	
	for(int i=0; i<5; i++){
		for(int j=0;j<5;j++){
			if (tab[i][j] == c){
				position =(new StringBuilder()).append(j+1).append(i+1).toString();
				return position;
			}
		}
	}
	
	return position;
}


//Polybe code
public static String Polybe(String file, int shift) throws IOException {
	
	String codedMessage="";
	String tmp;
	int[][] Pol;
	
	//Reading the file
	BufferedReader code = new BufferedReader(new FileReader(file));
	int line;
	
	// For each character, use the Polybe square (uppercase or lowercase) to determine its code
	while ((line = code.read()) != -1){
		if(line >= 'A' && line <= 'Z') {
			Pol = buildPolybeUppercase(shift);
			tmp = browseChar(Pol, line);
		}
		else if(line >= 'a' && line <= 'z'){
			Pol = buildPolybeLowercase(shift);
		    tmp= browseChar(Pol, line);
		}
		else tmp = String.valueOf(line);
		
		codedMessage += " "+tmp;
		
		}
	
	
	return codedMessage;

}



//read a file
public static String readMessage(String file) throws IOException  {
	    String originalMessage ="";
	    String mes;
	    
	    BufferedReader read = new BufferedReader(new FileReader(file));
	    
	  //Read the original message
	while ((mes=read.readLine())!=null){
		originalMessage+= mes;
	}
	read.close(); 
	
	return originalMessage;
}

//code a file
public static String codeMessageCesar(String file, int shift) throws IOException {
	
	String codedMessage = "";
	String newChar;
	int line;
	BufferedReader code = new BufferedReader(new FileReader(file));

	//Read the message and code it
    while ((line = code.read()) != -1) {
        char tmp = (char)line;
        if((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) newChar = cesar(tmp, shift);
        else newChar=String.valueOf(tmp);
        codedMessage += newChar;
	}
		return codedMessage;
}
		

public static void main(String[] args) throws IOException {


	int codedValue;
	
	try{
		//The shift value for Cesar method
		 codedValue = Integer.parseInt(args[1]);
	} catch (NumberFormatException e) 
		{ 
			System.out.println("La commande d'éxécution doit être de la forme : java SubEncode cesar|polybe ValeurDuShift NomDuFichier\n");
			return;
		}

	System.out.println("\nMessage original : "+ readMessage(args[2]) +"\n");
	
	if(args[0].equals("cesar"))
		System.out.println("Message chiffré avec César (décalage de "+args[1]+") : "+ codeMessageCesar(args[2],codedValue));
    
    else if(args[0].equals("polybe"))
		System.out.println("Message chiffré avec Polybe (décalage de "+args[1]+") : "+ Polybe(args[2],codedValue));
		
	else System.out.println("La commande d'éxécution doit être de la forme : java SubEncode cesar|polybe ValeurDuShift NomDuFichier\n"); 	

}    
    
}
