import java.util.Scanner;

public class AlienshipGameTester{

    private static  String userguess;
    private String expectedResult;
    private int[] alienships;
    private static String[][] gridmap = new String[AlienshipGame.numRows][AlienshipGame.numCols];

    public static void main(String[] args) {
        gridmap = AlienshipGame.createMap();


    }

    public static void acceptGuess(){
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter guess : ");
        userguess = input.nextLine();
    }

    public static void guessTester(String guess, String result, int[] shiploc){


    }

}
