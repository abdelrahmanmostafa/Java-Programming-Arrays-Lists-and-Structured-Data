import edu.duke.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testCaesarCipher(){
         CaesarCipher caesarCipher = new CaesarCipher(13);
         FileResource fr = new FileResource();
         String input = fr.asString();
         String encrypted = caesarCipher.encrypt(input);
         System.out.println("Encrypted: " + encrypted);
         String decrypted = caesarCipher.decrypt(encrypted);
         System.out.println("Decrypted: " + decrypted);  
    }
    
    public void testCaesarCracker(){
         CaesarCracker caesarCracker = new CaesarCracker();
         FileResource fr = new FileResource();
         String input = fr.asString();
         String decrypted = caesarCracker.decrypt(input);
         System.out.println("Decrypted: " + decrypted); 
    }
    
    public void testVigenereCipher(){
        int [] key = {17, 14, 12, 4};
         VigenereCipher vigenereCipher = new VigenereCipher(key);
         FileResource fr = new FileResource();
         String input = fr.asString();
         String encrypted = vigenereCipher.encrypt(input);
         System.out.println("Encrypted: " + encrypted);
         String decrypted = vigenereCipher.decrypt(encrypted);
         System.out.println("Decrypted: " + decrypted);  
    }
    
    public void testVigenereBreaker(){
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        System.out.println("Testing sliceString");
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vigenereBreaker.sliceString("abcdefghijklm", 4, 5));
        System.out.println("Testing try key length");
        FileResource fr = new FileResource();
        String input = fr.asString();
        int keys [] = vigenereBreaker.tryKeyLength(input, 4, 'e');
        for (int i=0; i < keys.length; i++){
        System.out.print(keys[i] + " ");
        }
        
        System.out.println("\n" + "Testing breakVigenere");
        vigenereBreaker.breakVigenere();
    }
    
    public void testbreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("\n" + "Testing breakVigenere");
        vb.breakVigenere();
    }
    
}
