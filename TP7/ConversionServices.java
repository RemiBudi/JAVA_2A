

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;



public class ConversionServices {
    
    /*
     * Interface for the converters
     */
    interface Converter {
        double convert(double in);
        String getSource();
        String getInputUnit();
        String getOutputUnit();
    }
    

	// source = csv file
    String source;

			
			
		
    
    public class InitializationException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        //TODO: Ajouter les constructeurs necessaires
        public InitializationException(){
			System.out.println("error line : \n");
		}
    }
    
    // TODO: initialiser. mais ça soit rester une Map<String,Converter>
    // Et ne contenir que des instances de classes locales ou anonymes
    Map<String,Converter> converters = new HashMap<String,Converter>();
    
    /**
     * Create and initialize the ConversionServices objet
     * @param source The name of a ressource CSV file with the converter data
     * @throws ConversionServices.InitializationException if there is any problem reading the CSV
     */
    public ConversionServices(String source) {        
        // TODO initialiser la map
       /* InputStream s = this.getClass().getClassLoader().getRessourceAsStream("./Converters.csv");
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(s));
        */
        
        this.source = source;
        
        String line="";
        
        //Array containing a line of the .csv = a converter
		String conv[] = null;
        
        //read the csv
        try( BufferedReader br = new BufferedReader(new FileReader(source))){ 
        
			while ((line=br.readLine()) != null){
			
				conv = line.split(",");
				
				final String inputUnit = conv[1];
				final String outputUnit = conv[2];
				final double scale = Double.parseDouble(conv[3]);
				final double offset  = Double.parseDouble(conv[4]);
			
				//For each line of .csv, add a <K,V> to the map, creating a new converter
				converters.put(conv[0],new Converter(){
		
			public double convert(double in){
				
			return (in * scale) + offset;
			}
			
			public String getSource(){
				return ConversionServices.this.source;
			}
			
			public String getInputUnit(){
				return inputUnit;
			}
			
			public String getOutputUnit(){
				return outputUnit;
			}
			
		}); 
			}
		}
		catch (IOException e) {  e.printStackTrace();}
	
}
    
    /**
     * Obtain a Converter by name
     * @param name Converter name
     * @return The chosen Converter
     * @throws NoSuchElementException is there is no converter with the given name
     */
    public Converter getConverter(String name) {
        var cv=converters.get(name);
        if (cv==null) { 
				return null;
        }
        return cv;
    }
    
    /**
     * Obtain a sorted Iterable of the available Converter names
     * @return A sorted Iterable of the available Converter names
     */
    /*public Iterable<String> getConverterNames() {
        // TODO: 
        return null;
    } */   
    
    public ArrayList<String> getConverterNames() {
        // TODO: 
        
        //ArrayList to stock the names of the converters
        ArrayList<String> listConv = new ArrayList<String>();
        
        for (Map.Entry<String,Converter> entry : converters.entrySet()){  
			listConv.add(entry.getKey());
		}	
		Collections.sort(listConv);			 
        return listConv;
	}



	
	
}
