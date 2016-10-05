import edu.duke.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of WordsInFiles here.
 * determine which words occur in the greatest number of files,
 * and for eachword, which files they occur in
 * @author (Abdelrahman) 
 * @version (2/10/2016)
 */
public class WordsInFiles {
    HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String word : fr.words()){
            if (!map.containsKey(word)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(fileName);
				map.put(word, list);
			}
			else {
			    ArrayList<String> l = map.get(word);
			    int index = l.indexOf(fileName);
			    if (index == -1){
				    l.add(fileName);
				    map.put(word,l);
			   }
			 }
			}        
    }
    
    private void buildWordFileMap(){
        DirectoryResource dr = new DirectoryResource();
        map.clear();
        for (File f : dr.selectedFiles()) {
             addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        if (map.size() == 0){
        System.out.println("MAP HAS NOT BEEN CONSTRUCTED YET!");
        return 0;
       }
        int max = 0;
        int count = 0;
        for(String w : map.keySet()){
			count = map.get(w).size();	
			if ( count > max ){
			    max = count;
			 }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordList = new ArrayList<String>();
        for(String w : map.keySet()){
			int count = map.get(w).size();
			if ( count == number){
			    wordList.add(w);
			 }
        }
        return wordList;
    }
    
    private void printFilesIn(String word){
        for(String fname : map.get(word)){
			System.out.println(fname);
       }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("Max :" + maxNumber());
        ArrayList<String> wordsList = wordsInNumFiles(map.get("tree").size());
        for (int k=0; k < wordsList.size(); k++){
            System.out.println(wordsList.get(k));
            System.out.println("\nfilename : \n");
            printFilesIn(wordsList.get(k));
        }
        System.out.println("Size "+wordsList.size());
        System.out.println("trea" + map.get("tree").size());
    }
}
