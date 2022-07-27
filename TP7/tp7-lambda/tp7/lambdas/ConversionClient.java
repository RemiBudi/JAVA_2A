package tp7.lambdas;

import java.io.PrintStream;

public class ConversionClient {
    
    
    //TODO: remplacer AbstractConversionServices.SomeType et somethingGoeshere
    static void printTable(String converterName, AbstractConversionServices.SomeType converter,PrintStream out) {
        out.printf("-- %s\n",converterName);
        
        out.printf("+----------+----------+\n");                  
        for (double i=0;i<=100.;i+=10.) {
            out.printf("| %8.1f | %8.1f |\n",i, somethingGoeshere);
        }
        out.printf("+----------+----------+\n");
    }
       
    public static void main(String[] args) {
        FuncRefConversionServices fcs=new FuncRefConversionServices("Converters.csv");
        for (String converterName:fcs.getConverterNames()) {
            printTable(converterName,fcs.getConverter(converterName),System.out);
        }
        LambdaConversionServices lcs=new LambdaConversionServices("Converters.csv");
        for (String converterName:fcs.getConverterNames()) {
            printTable(converterName,lcs.getConverter(converterName),System.out);
        }
    }
}
