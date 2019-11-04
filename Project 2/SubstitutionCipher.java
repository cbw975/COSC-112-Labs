import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class SubstitutionCipher extends Cipher{
    public char[] origChar = new char[256]; //to hold the original letters (for conversion)
    private char[] newChar = new char[256]; //to hold the secret letters (for conversion)
    
    // public SubstitutionCipher(){ super(); } //default constructor --> not explicitly needed to be coded
    public SubstitutionCipher(long key){ 
        super(key); 
        Random r = new Random(key);
        //Fill in original characters (origChar), and temporarily the newChar
        for(int i=0; i<=255; i++){ origChar[i] = (char) i;     newChar[i] = (char) i; }

        //Shuffle characters for newChar (the encryptded/converted letters)
        char temp;  int rInd;
        for(i=0; i<=255; i++){ //shuffle each, from 0 to 255, with some other, so all get shuffled at least once
            temp=newChar[i]; 
            rInd=r.nextInt(256);
            newChar[i]=newChar[rInd];
            newChar[rInd]=temp; }
        }
    public List<Character> decrypt(List<Character> enctxt){
        List<Character> cleartxt = new LinkedList<Character>(); //will hold decrypted text
        for(char encChar: enctxt){ //for each char in encrypted text (enctxt):
            for(int i=0; i<newChar.length; i++){ //find it in origChar...
                if(encChar == newChar[i]) cleartxt.add(origChar[i]); } } //add corresponding newChar to enctxt
        return cleartxt; }
    
    public List<Character> encrypt(List<Character> cleartxt){
        List<Character> enctxt = new LinkedList<Character>(); //will hold encrypted text
        for(char oldChar: cleartxt){ //for each char in original text (cleartxt):
            for(int i=0; i<origChar.length; i++){ //find it in origChar...
                if(oldChar == origChar[i]) enctxt.add(newChar[i]); } } //add corresponding newChar to enctxt
        return enctxt; }
}