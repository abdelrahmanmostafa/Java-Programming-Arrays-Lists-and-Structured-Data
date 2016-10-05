import edu.duke.*;
/**
 * Write a description of CaeserCipher here.
 *  Using OOP 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaeserCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaeserCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar))
                    newChar = Character.toLowerCase(newChar); 
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
         
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        shiftedAlphabet = alphabet.substring(26 - mainKey)+ alphabet.substring(0, 26 - mainKey);
        return encrypt(input);    
     }
     
}

