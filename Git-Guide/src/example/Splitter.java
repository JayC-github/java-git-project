package example;
import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner s = new Scanner(System.in);

        String []split = s.nextLine().split(" ");

        for (int i = 0; i < split.length; i++) {
            // print all the words seperately
            System.out.println(split[i]);
        }
    }
}
