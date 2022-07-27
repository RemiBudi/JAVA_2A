package tp7.rollinghash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.List;


/*
 * Generation des fichiers de test (bien entendu, ceci n'est un exemple...): 
 * 
 *     tr -dc '\0\1\2\3' </dev/urandom | pv -S -s 20M >chromosome.dat
 *     dd if=chromosome.dat skip=18 bs=1M count=1 >gene.dat
 */

public class RollingHashSearch {

    /*
     * Une implémentation possible
     */
    static class RollingHash {
        RollingHash(/* paramètres de constructeur */) {
        }
        
        /**
         * Full hash of byte array (gene or initial hash of chromosome)
         *
         * @param seq The bytes
         * @return
         */
        int hash(byte[] seq) {
            return 0;
        }

        /**
         * 
         * @param hash Current value of the hash
         * @param remove The byte that is shifted out (head byte in previous scan)
         * @param add The byte that is shifted in (tail byte in current)
         * @return
         */
        int roll(int hash,byte remove,byte add) {
            return 0;
        }    
    }
    
    
    
    /**
     * Read file as byte array
     * @param path
     * @return the file contents
     * @throws IOException
     */
    static byte[] slurp(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }
    
    /**
     * Does the full check that chromosome[start...] is the same as the full length gene
     * @param chromosome
     * @param gene
     * @param start
     * @return
     */
    static boolean fullCheck(byte[] chromosome,byte[] gene,int start) {
        // TODO: a completer
        for (int i=0;i<gene.length;i++) {
            if (gene[i]!=chromosome[i+start]) { 
                return false;  
            }
        }
        return true;
    }
     
    /**
     * Implementation of the brute force search
     *  
     * @param chromosome
     * @param gene
     */
    static List<Integer> bruteSearch(byte[] chromosome,byte[] gene) {
        //TODO: a compléter
        return null;
    }
    
    /**
     * Implementation of the rolling hash search
     *  
     * @param chromosome
     * @param gene
     */
    static List<Integer> RabinKarpSearch(byte[] chromosome,byte[] gene) {
        //TODO: @ compléter
        return null;
    }
    
    /**
     * Definition of Static function reference
     */
    public interface Search {
        List<Integer> search(byte[] chromosome,byte[] gene);
    }
    
    /**
     * Generic wrapper for a search, measures and displays search time, displays matches
     * 
     * @param search a static function reference
     * @param searchName
     * @param chromosome
     * @param gene
     */
    public static List<Integer> search(Search search,String searchName, byte[] chromosome, byte[] gene) {
        Instant beforeSearch=Instant.now();
        
        System.out.printf("Starting \"%s\"\n",searchName);        
        var matches=search.search(chromosome,gene);
        
        Instant afterSearch=Instant.now();
        Duration searchDuration=Duration.between(beforeSearch, afterSearch);
        System.out.printf("Done \"%s\": %3.3fs\n",searchName,searchDuration.getSeconds()+searchDuration.getNano()/1000000000.);  
        System.out.printf("Matches (%d):\n",matches.size());
        for (int loc:matches) {
            System.out.printf("%08x (%d)\n", loc,loc);
        }
        return matches;
    }
    
    
    public static void main(String[] args) {
        String dir=args.length==0 ? "." : args[0];
        Instant beforeLoad=Instant.now();
        byte[] chromosome=null;
        byte[] gene=null;
        try {
            chromosome = slurp(dir + "/chromosome.dat");
            gene = slurp(dir + "/gene.dat");
        }        
        catch(IOException e) {
            System.err.printf("%s\n",e.getMessage());
            System.exit(1);
        }
        Instant afterLoad=Instant.now();
        Duration loadDuration=Duration.between(beforeLoad, afterLoad);
        System.err.printf("Loading files: %3.3fs\n",loadDuration.getSeconds()+loadDuration.getNano()/1000000000.);
        
        search(RollingHashSearch::bruteSearch,"Brute search",chromosome,gene);
        search(RollingHashSearch::RabinKarpSearch,"Rabin Karp search",chromosome,gene);        
    }
}
