package tp7.lambdas;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class AbstractConversionServices {
        
    
    // TODO: classe/interface a remplacer par autre chose
    public interface SomeType { } 
    
    
    public class InitializationException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        InitializationException(String msg,Exception e) {super(msg,e);}
        InitializationException(String msg) {super(msg);}
    }
    
    String convertersSource;
    
    Map<String,SomeType> converters=new TreeMap<>();
    
    public AbstractConversionServices(String source) {
        
        convertersSource = source;

        InputStream is=getCsv(source);
        int line=0;
        try (Scanner fileScanner = new Scanner(is, "UTF-8").useDelimiter("\n")) {
            while (fileScanner.hasNext()) {
                line++;
                initConverter(fileScanner.next());
            }
        } 
        catch (Exception e) {
            String msgFormat= (e.getMessage()==null) ? 
                    "Error reading file %s, line %d" : "Error reading file %s, line %d: %s";
            throw new InitializationException(
                    String.format(msgFormat,source,line,e.getMessage()), e);
        }
    }

    private InputStream getCsv(String source) {
        InputStream s = getClass().getResourceAsStream(source);
        if (s == null)
            throw new InitializationException(String.format("Resource not found: %s", source));
        return s;
    }
    
    // remove quotes at both ends if present
    private String unquote(String s) {
        return (s.startsWith("\"") && s.endsWith("\"")) ? s.substring(1,s.length()-1) : s;
    }
    
    // Split a CSV line into its components and initialize a converter with that
    // Any errors are caught in the try/catch loop of our caller
    private void initConverter(String line) {
        try (Scanner columns = new Scanner(line).useDelimiter(",")) {
            String name = unquote(columns.next());
            @SuppressWarnings("unused")
            String inputUnit = unquote(columns.next());
            @SuppressWarnings("unused")
            String outputUnit = unquote(columns.next());
            double scale = Double.parseDouble(columns.next());
            double offset = Double.parseDouble(columns.next());
            converters.put(name, polynomConverter(scale, offset));
        }
    }
    
    abstract SomeType polynomConverter(double scale, double offset);
    
    public SomeType getConverter(String name) {
        var cv=converters.get(name);
        if (cv==null) { 
            throw new NoSuchElementException();
        }
        return cv;
    }
    
    public Collection<String> getConverterNames() {
        return converters.keySet();
    }    
}
