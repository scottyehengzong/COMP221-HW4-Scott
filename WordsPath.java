import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsPath{

    /**
     * This function prints all the possible path from a source three letter word to a destination
     * three letter word using only intermediaries that differ by only one letter.
     * If the source three letter word is the same as the destination three word,
     * the function will print "No path is found".
     * @param file an input file
     * @param s a source three letter word
     * @param d a destination three letter word
     */
    public static void printWordTrack(File file, String s, String d){
        File myObj = file;
        List<String> words = createWordsList(myObj);
        //create a list of all the words in the file
        List<String> secondWords = new ArrayList<>();
        //a list that contains all the possible second words of the path
        List<String> thirdWords = new ArrayList<>();
        //a list that contains all the possible third words of the path

        if(s.length()!=3 || d.length()!=3 || !words.contains(s) || !words.contains(d)){
            System.out.println("Invalid input.");
        }
        else{ //inside the else statement, source and destination are all contained in words and their lengths are 3
            if(findDiff(s, d)==1){
                //if the number of different letter between source and destination is 1, just print these two words
                System.out.println(s + " " + d);
            }
            else if(findDiff(s, d) == 2){
                //if the number of different letter between source and destination is 2,
                //we only need to find the possible second words
                //if the list of possible second words is empty,
                //print "No path is found"
                //else print source, possible secodn words and destination.
                for(int i = 0; i< words.size(); i++){
                    if(findDiff(s, words.get(i))==1&&findDiff(d, words.get(i))==1){
                        secondWords.add(words.get(i));
                    }
                }
                if(secondWords.isEmpty()){
                    System.out.println("No path is found");
                }
                else{
                    for(int j = 0; j<secondWords.size(); j++){
                        System.out.println(s + " " + secondWords.get(j) + " " + d);
                    }
                }
            }
            else{
                //if the number of different letter is 3,
                //find possible second words and possible third words and add them to corresponding list
                //print all the possible path if none of the lists are empty.
                //this else also contain the situation source and destination are same,
                //when s and d are the same, the function will print "No path is found."
                for(int i = 0; i< words.size(); i++){
                    if(findDiff(s, words.get(i))==1&&findDiff(d, words.get(i))==2){
                        secondWords.add(words.get(i));
                    }
                    if(findDiff(s, words.get(i))==2&&findDiff(d, words.get(i))==1){
                        thirdWords.add(words.get(i));
                    }
                }
                int x = 0;
                for(int i = 0; i<secondWords.size(); i++){
                    for(int j = 0; j<thirdWords.size(); j++){
                        if(findDiff(secondWords.get(i), thirdWords.get(j))==1){
                            x = x+1;
                            System.out.println(
                                s + " "+ secondWords.get(i) + " " + thirdWords.get(j) + " " + d);
                        }
                    }
                }
                if(x == 0){
                    System.out.println("No path is found");
                }

            }
            
        }

    }

    /**
     * This function return a list of string. The string is each line of the input file.
     * @param file an input file
     * @return a list of string, the string is each line of the file
     */
    private static List<String> createWordsList(File file){
        List<String> words = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                words.add(myReader.nextLine());
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    } 

    /**
     * This function find the number of different letters between two strings that have the same length.
     * @param a a String of length n
     * @param b a String of length n
     * @return the number of different letters between a and b
     */
    private static Integer findDiff (String a, String b){
        if(a.length()!=b.length()){
            return -1;
        }
        int length = a.length();
        int diff = 0;
        for(int i = 0; i<length; i++){
            String a_i = a.substring(i, i+1);
            String b_i = b.substring(i, i+1);
            if(!a_i.equals(b_i)){
                diff = diff + 1;
            }
        }
        return diff;
    }
    public static void main(String[] args) {
      File myObj = new File("/Users/dell/Desktop/COMP 221/HW4/COMP221-HW4-Scott/threeletterwords.txt");
      printWordTrack(myObj, "dog", "cat");

    

}
}