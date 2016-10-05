import edu.duke.*;
import java.util.*;

/**
 * how many times each codon occurs in a strand of DNA
 *  Assignment 1
 * @author (Abdelrahman) 
 * @version (2/10/2016)
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();        
    }
    
    private void buildCodonMap(int start, String dna){
        for (int i= start; i < dna.length(); i+=3){
            if (i+3 < dna.length()){
                String codon = dna.substring(i, i+3);
                if (!map.containsKey(codon)){
        				map.put(codon,1);
        			}
        			else {
        				map.put(codon,map.get(codon)+1);
        			 }
                }
    }
    }
    
    private String getMostCommonCodon(){
        String mostCommonCodon = " ";
        if (map.size() == 0 )
            return "EMPTY MAP!";
        else{
            int max = 0;
            for(String s : map.keySet()){
                int value = map.get(s);
                if (value > max){
                    max = value;
                    mostCommonCodon = s;
                } 
            }
        }
        return mostCommonCodon;
   }
   
   private void printCodonCounts(int start, int end){
       System.out.println("Counts of codons between " + start + " and " + end  + " " +  "inclusive are: ");
       for(String s : map.keySet()){
           int value = map.get(s);
           if (value >= start && value <= end){
                System.out.println(s + " " + value);
           } 
       }
    }
    
   public void tester(){
       FileResource fr = new FileResource();
       String dnaStrand = fr.asString();
       dnaStrand = dnaStrand.toUpperCase().trim();
       for (int i=0; i < 3; i++){
            map.clear();
            buildCodonMap(i, dnaStrand);
            System.out.println("Number of unique Codons in frame " + i + ": " + map.size());
            String commonCodon = getMostCommonCodon();
            System.out.println("Most Common Codon :" + commonCodon + " " + map.get(commonCodon));
            printCodonCounts(5, 8);
        }
    }
}
