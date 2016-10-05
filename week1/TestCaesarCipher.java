import edu.duke.*;

/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TestCaesarCipher{
    
    public TestCaesarCipher(){ };    
   
    public int[] countLetters(String input){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int[26];
        for(int i=0; i<input.length(); i++){
            char ch = Character.toUpperCase(input.charAt(i));
            int index = alphabet.indexOf(ch);
            if (index != -1){
                counts[index]+=1;
            }
        }
        return counts;
    }
    
    public int getMaxIndex(int[] vals){
        int maxIndex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k]  > vals[maxIndex]){
                maxIndex = k;
            }  
        }
        return maxIndex;
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaeserCipher cc = new CaeserCipher(15);
        String encryptedMessage = cc.encrypt(message1);
        System.out.println("Encrypted Messsage: " + encryptedMessage);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println("decrypted Messsage: " + decryptedMessage);       
        int key = breakCaesarCipher(encryptedMessage);
        CaeserCipher c = new CaeserCipher(key);
        String decryptedMessage1 = c.decrypt(encryptedMessage);
        System.out.println("decrypted Messsage: " + decryptedMessage1);       
        
    }
    // works on assumption that E is the most frequent letter in English text
    public int breakCaesarCipher(String message){
        int freqs [] = countLetters(message);
        int maxIndex = getMaxIndex(freqs);
        int dkey = maxIndex - 4;
        if (maxIndex < 4)
            dkey = 26 -(4-maxIndex);
        return dkey;   
    }
}
