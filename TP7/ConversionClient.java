import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ConversionClient extends ConversionServices{
	
	//source = .csv file
	String source;
	
	ConversionServices cs;
	
	public ConversionClient(String s){
		super(s);
		this.source = s;
		cs =  new ConversionServices(source);
	}
	
	

	
	//Print all the names of the converters
	public void readNames(){
		
		System.out.println("Converters list\n");
		
		ArrayList<String> listC  = cs.getConverterNames();
		for(int i=0;i<listC.size();i++)
			System.out.println(listC.get(i));
	
	System.out.println("map :"+ converters);
	}
	
	
	//Print a "conversion table" for a converter
	public void printConversionTable(Converter c){
		
		System.out.println("**   input unit : "+c.getInputUnit()+"     output unit : "+c.getOutputUnit()+"   **");
		DecimalFormat df = new DecimalFormat("0.0");
		
		for(int i=0; i<=100; i=i+10)
			
			System.out.println( i +"----->"+ df.format(c.convert(i))); 
	}
	
	
	
	//Print the conversion tables of all converters of the map
	public void printTables(){
		
		Converter c;
		
		ArrayList<String> list = getConverterNames();

		for(int i=0; i<list.size();i++){
			
			c = getConverter(list.get(i));
				
			printConversionTable(c);
		}
	}
		
	public void convertUserValue(){
			
		Converter c;
		Scanner scan = new Scanner( System.in );
		
		while(true){
			
			String s = scan.nextLine();
			if(s.equals("q")) return;
			
			c = getConverter(s);
			if(c == null) System.out.println("No converter with this name");
			else{
				System.out.println("Enter the number to convert");
				double d = scan.nextDouble();
				
				double result = c.convert(d);
				
				System.out.println(d + " "+ c.getInputUnit()+ " = " + result + " " + c.getOutputUnit());
		}
	}
}
		
	
	
	public static void main(String[] args){
		
		
		ConversionClient cc = new ConversionClient(args[0]);
		cc.printTables();
		cc.convertUserValue();

	}
}
