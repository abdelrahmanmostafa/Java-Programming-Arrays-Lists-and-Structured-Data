import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    // extract slices from a string
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        StringBuilder result = new StringBuilder();
        for (int i = whichSlice; i< message.length(); i+= totalSlices){
            result.append(message.charAt(i));
        }
        return result.toString();
    }
    // return key list of slices
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] keys = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i=0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);    
            int key = caesarCracker.getKey(slice);
            keys[i] = key;
       }
        return keys;
    }
    // decrypt the Vigenre encrypted message
    public void breakVigenere () {
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        System.out.println("Select dictionary directory");
        DirectoryResource directory = new DirectoryResource();
        for (File f :directory.selectedFiles()){
            System.out.println(f.getName());
            FileResource fileResource = new FileResource("dictionaries/"+f.getName());
            dictionaries.put(f.getName(), readDictionary(fileResource));
        }
        System.out.println("Reading Dictioanries Done!");
        System.out.println("Select the message to be decrypted");
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        breakForAllLanguages(encryptedMessage, dictionaries);
    }
    // read list of certain language words from a file
    public HashSet<String> readDictionary(FileResource fr){
       HashSet<String> set = new HashSet<String>(); 
       for (String word : fr.lines()) {
           set.add(word.toLowerCase());
        }
       return set;
    }
    // count how many matching words in dictionary
    public int countWords(String message, HashSet<String> dictionary){
        int realWordsCount = 0;
        String [] words = message.split("\\W");
        for (int i=0; i< words.length; i++){
            if(dictionary.contains(words[i].toLowerCase()))
                realWordsCount++;
        }
        return realWordsCount; 
    }
    // get the decrypted String by trying multiple keylength
    public String breakForLanguage(String encrypted, HashSet<String> dictionary,char mostCommonChar){
        int score = 0;
        int maxScore = 0;
        int keyLength = 1;
        String decryptedString = "";
        for(int i=1; i <= 100; i++){
            int key =i;
            int [] keys = tryKeyLength(encrypted, key, mostCommonChar);
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String decrypted = vigenereCipher.decrypt(encrypted);
            score = countWords(decrypted, dictionary);
            if(score > maxScore){
                maxScore = score;
                decryptedString = decrypted;
                keyLength = key;
            }
        }
        System.out.println("The highest count is " + maxScore);
        System.out.println("The keyLength is " + keyLength);
        return decryptedString; 
    }
    // return the most frequent character in a language
    public char mostCommonCharIn(HashSet<String> dictionary){
        int max = 0;
        int count = 0;
        char mostcommon =' ';
        HashMap<Character, Integer> characterMap = new HashMap<Character, Integer>(); 
        for (String word : dictionary) {
            char[]char_array =word.toCharArray();
            for(int i=0; i<word.length(); i++){
                char ch = char_array[i]; 
                if(characterMap.containsKey(ch)){
                    characterMap.put(ch, characterMap.get(ch)+1);
                }
                else
                {
                    characterMap.put(ch, 1);
                }
           } 
        }
        for (Character c : characterMap.keySet()) {
             count = characterMap.get(c);
             if(count > max ){
                max = count;
                mostcommon = c;   
            }
        } 
        return mostcommon;
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxStore= 0;
        int languageScore = 0;
        String trueLanguage = "";
        String decryptedMessage = "";
        for(String language : languages.keySet()){
            HashSet<String> dictionary = languages.get(language);
            char mostCommon = mostCommonCharIn(dictionary);
            String decrypted = breakForLanguage(encrypted, dictionary, mostCommon);
            languageScore = countWords(decrypted, dictionary);
            if (languageScore > maxStore){
                maxStore = languageScore;
                trueLanguage = language;
                decryptedMessage = decrypted;
            }
        }
        System.out.println("Language " + trueLanguage);
        System.out.println("Decrypted Message :");
        System.out.println(decryptedMessage);
    }
}
