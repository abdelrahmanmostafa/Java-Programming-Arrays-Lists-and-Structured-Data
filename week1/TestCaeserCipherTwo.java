import edu.duke.*;

/**
 * Write a description of TestCaeserCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaeserCipherTwo {
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
    
    private  String halfOfString(String message, int start){
        StringBuilder str = new StringBuilder();
        if (start == 0)
            for (int i=0; i < message.length(); i+=2)
                str.append(message.charAt(i));
        else if (start == 1)
            for (int i=1; i < message.length(); i+=2)
                str.append(message.charAt(i));
        else
            System.out.println("not valid start index!");
        return str.toString();
       }
    
    public int getKey(String s){
        int[] freqs =  countLetters(s);
        int maxIndex = getMaxIndex(freqs);
        int dkey = maxIndex - 4;
        if (maxIndex < 4)
            dkey = 26 -(4-maxIndex);
        return dkey;
    }
          
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        String encryptedMessage = cc.encrypt(message);
        System.out.println("Encrypted Messsage: " + encryptedMessage);
        String decryptedMessage = breakCaesarCipher(message);
        System.out.println("decrypted Messsage: " + decryptedMessage);                      
        CaesarCipherTwo cc1 = new CaesarCipherTwo(14, 24);
        String decryptedMessage1 = cc1.decrypt("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        System.out.println("decrypted Messsage1 : " + decryptedMessage1);                      
        
    }
    
    public String breakCaesarCipher(String input){
        String halfStr1 = halfOfString(input, 0);
        String halfStr2 = halfOfString(input, 1);
        int key1 = getKey(halfStr1);
        int key2 = getKey(halfStr2);
        System.out.println(key1 + "" + key2);
        CaesarCipherTwo caesarCipher = new CaesarCipherTwo(key1, key2); 
        String decrypted =caesarCipher.decrypt(input);
        return decrypted; 
    }
    
}
