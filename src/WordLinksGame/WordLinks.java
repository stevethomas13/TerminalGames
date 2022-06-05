package WordLinksGame;
/* SELF ASSESSMENT
1. readDictionary
- I have the correct method definition [Mark out of 5:5 ] 
- Comment: self explanatory
- My method reads the words from the "words.txt" file. [Mark out of 5:5 ] 
- Comment: self explanatory 
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5 ] 
- Comment: self explanatory 

2. readWordList
- I have the correct method definition [Mark out of 5:5 ] 
- Comment: self explanatory 
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5 ] 
- Comment: self explanatory 

3. isUniqueList
- I have the correct method definition [Mark out of 5:5 ] 
- Comment: self explanatory 
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5 ] 
- Comment:  it sorts the arrayList and then compares every element with the next one
- Exits the loop when a non-unique word is found. [Mark out of 5:5 ] 
- Comment: self explanatory 
- Returns true if all the words are unique and false otherwise. [Mark out of 5:5 ] 
- Comment: self explanatory 

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: self explanatory 
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: self explanatory 
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: self explanatory 

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: self explanatory 
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: self explanatory 

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: self explanatory 
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment: self explanatory 

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: self explanatory 
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: self explanatory 

 Total Mark out of 100 (Add all the previous marks): 100
*/
import java.util.*;
import java.io.*;
import static java.util.Collections.binarySearch;

public class WordLinks {
    static FileReader fread;
    static BufferedReader bread;
    static Scanner input;
    static ArrayList<String> dictionary;

    public static ArrayList<String> readDictionary() throws Exception{
        fread = new FileReader( "src\\WordLinksGame\\words.txt" );
        bread = new BufferedReader( fread );
        ArrayList<String> myWords = new ArrayList<String>();
        String word = bread.readLine();
        while( word != null ){
            myWords.add( word );
            word = bread.readLine();
        }
        return myWords;
    }
    
    public static ArrayList<String> readWordList( Scanner input ){
        ArrayList<String> myWords = new ArrayList<String>();
        String inputString = input.nextLine();
        if( inputString == "" ){
            return null;
        }
        else {
            String arrayOfWords[] = inputString.split( "," );
            for ( String word: arrayOfWords ){
                myWords.add( word.trim() );
            }
            return myWords;
        }
    }

    public static boolean isUniqueList( ArrayList<String> myWords ){
        if ( myWords != null ){
            if( myWords.size() > 1 ){
                ArrayList<String> listOfWords = ( ArrayList<String> ) myWords.clone(); 
                Collections.sort( listOfWords );
                for( int index = 0; index < listOfWords.size() - 1; index++ ){
                    if ( listOfWords.get( index ).trim().equalsIgnoreCase( listOfWords.get( index + 1 ).trim() ) ) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isEnglishWord( String word ) throws Exception {
        if( word != null ) {
            if ( dictionary == null ){
                dictionary = readDictionary();
            }
            if( binarySearch( dictionary, word ) >= 0 )
                return true;
        }
        return false;
    }

    public static boolean isDifferentByOne( String word1, String word2 ){
        if ( word1 !=null && word2 !=null ){
            int charactersNotSame = 0;
            if ( word1.length() == word2.length() ){
                String[] arrayOfWord1 = word1.split( "" );
                String[] arrayOfWord2 = word2.split( "" );
                for( int index = 0; index < arrayOfWord1.length; index++ ){
                    if( !arrayOfWord1[ index ].equalsIgnoreCase( arrayOfWord2[ index ] ) )
                        charactersNotSame++;
                }
                if ( charactersNotSame == 1 ){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isWordChain( ArrayList<String> myWords ) throws Exception {
        if ( myWords != null ){
            if ( myWords.size() > 1 ){
                if ( !isUniqueList( myWords ) ){
                    System.out.println( "Not a valid chain of words from Lewis Carroll's word-links game." );
                    return false;
                }
                for( int index = 0; index < myWords.size(); index++ ){
                    String word =  myWords.get( index );
                    if( !isEnglishWord( word ) ){
                        System.out.println( "Not a valid chain of words from Lewis Carroll's word-links game." );
                        return false;
                    }
                }
                for( int index = 0; index < myWords.size() - 1; index++ ){
                    String word1 =  myWords.get( index );
                    String word2 = myWords.get( index + 1 );
                    if ( !isDifferentByOne( word1, word2 ) ){
                        System.out.println( "Not a valid chain of words from Lewis Carroll's word-links game." );
                        return false;
                    }
                }
                System.out.println( "Valid chain of words from Lewis Carroll's word-links game." );
                return true;
            }
        }
        System.out.println( "Not a valid chain of words from Lewis Carroll's word-links game." );
        return false;
    }


    public static void main( String[] args ) throws Exception {
        dictionary = readDictionary();
        boolean continueLoop = true;
        input = new Scanner( System.in );
        while( continueLoop ){
            System.out.println( "Enter a comma separated list of words (or an empty list to quit): " );
            ArrayList<String> myWords = readWordList( input );
            if ( myWords == null )
                continueLoop = false;
            else{
                isWordChain( myWords );
            }
        }
    }
}
