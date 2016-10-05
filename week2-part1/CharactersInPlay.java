import edu.duke.*;
import java.util.ArrayList;

/**
 * CharactersInPlay class determines the characters in one of 
 * Shakespeare’s plays that have the mostspeaking parts.
 * 
 * @author (abdelrahman mostafa) 
 * @version ( 1 / 10 / 2016)
 */
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> charactersCount;
    
    public CharactersInPlay(){
        characters = new ArrayList<String>();
        charactersCount = new ArrayList<Integer>();
    }
    
    private void update(String person){
        if(!characters.contains(person)){
            characters.add(person);
            charactersCount.add(1);
        }
        else{
        int index = characters.indexOf(person);
        int count = charactersCount.get(index);
        charactersCount.set(index, count+1);
       }
    }
    
    private void findAllCharacters(){
        characters.clear();
        charactersCount.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
           int index =  line.indexOf(".");
           if (index != -1){
               String character = line.substring(0, index);
               update(character);
           }    
        }
   }
   
   private String getMainCharacter(){
        int max = charactersCount.get(0);
        int maxIndex = 0;
        for(int k=0; k < charactersCount.size(); k++){
            if (charactersCount.get(k) > max){
                max = charactersCount.get(k);
                maxIndex = k;
            }
        }
        return characters.get(maxIndex);
    }
    
    private void charactersWithNumParts(int num1, int num2){
       for(int i=0; i<characters.size(); i++){
            if(charactersCount.get(i) >= num1 && charactersCount.get(i) <= num2)
                System.out.println(characters.get(i) + " " + charactersCount.get(i) );
        } 
        
    }
   
   public void tester(){
        findAllCharacters();
        System.out.println("Testing findAllCharacters ");
        for(int i=0; i<characters.size(); i++){
            if(charactersCount.get(i) < 100)
                System.out.println(characters.get(i) + " " + charactersCount.get(i) );
        }
        System.out.println("Testing charactersWithNumParts");
        charactersWithNumParts(10, 15);
        System.out.println("Testing get main character");
        String mainCharacter = getMainCharacter();
        System.out.println("Main Character: " + mainCharacter);
    }
}
