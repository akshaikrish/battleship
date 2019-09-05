import java.util.*;
import java.io.*;

public class AlienshipGame {

    public static int numRows = 8;
    public static int numCols = 8;
    public static int playerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static String[][] proxygrid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    private static final int[] SHIP_LENGTHS = {3, 3, 3};
    private static int NUM_OF_SHIPS = 3;
    private static int SHIPS_KILLED = 0;
    private static int GUESS_COUNT = 0;
    private static int[] location;
    private static String status;


    public static void main(String[] args) {

        createMap();
        deployships();

    }

    public static String[][] createMap(){

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String alpha = getCharForNumber(i);
                grid[i][j] = "- ";
                proxygrid[i][j]= "- ";
                if (j == 0)
                    System.out.print(alpha + "|" + grid[i][j]);
//
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i+" ");
        System.out.println();
        return grid;
    }



    public static void deployships(){
        if (NUM_OF_SHIPS != 3) // Num of ships must be 3
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 3");
        }
        Alienship[] ships = new Alienship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            location = getdirection();
            Alienship tempShip = new Alienship(SHIP_LENGTHS[i], location);
            ships[i] = tempShip;
            ships[i].setLocation();
            System.out.println();
            System.out.println("Ship"+(i+1)+" has been deployed");
            tempShip.displaylocation();
            updateproxygrid(location);
        }

        System.out.println();
        printmap();
        startgame(ships);
        System.out.println("Game Over!");

    }


    public static void startgame( Alienship[] ships){

        Scanner input  = new Scanner(System.in);
        while(SHIPS_KILLED <3){
            System.out.println();
//            System.out.println("Number of ships Killed : "+SHIPS_KILLED);
            System.out.println("Enter your guess: ");
            String playerguess = input.nextLine();
            char rowalpha = playerguess.charAt(0);
            char colalpha = playerguess.charAt(1);
            char rowCaps = Character.toUpperCase(rowalpha);
            int rowguess = rowCaps - 'A';
            int colguess = colalpha - '0';
//            System.out.println(rowalpha+" , "+colguess);
            if (colguess<8 && rowguess<8) {
                boolean isHit = takeguess(rowguess, colguess);
                if (isHit) {
                    for (int i = 0; i < 3; i++) {
                        if (ships[i].ifHit(rowguess, colguess))
                            if (ships[i].isKilled()) {
                                SHIPS_KILLED++;
                                status = "Its a kill! You killed " + SHIPS_KILLED + " out of 3 Alienships !";
                            }
                    }
                }
                System.out.println(status);
                System.out.println("Number of Guesses : " + (++GUESS_COUNT));
                System.out.println();
                printmap();
            }else
                System.out.println("Enter valid coordinates !");
        }
    }

    public static void updateproxygrid(int[] loc) {
        int row = loc[1];
        int col = loc[2];
        int dir = loc[0];
//        System.out.println("This is proxy grid");
        if(dir==0){
            proxygrid[row][col] = "X ";
            proxygrid[row][col+1]= "X ";
            proxygrid[row][col+2]= "X ";
        }else{
            proxygrid[row][col] = "X ";
            proxygrid[row+1][col]= "X ";
            proxygrid[row+2][col]= "X ";
        }
    }


    private static String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char)(i + 65)) : null;
    }



    public static boolean takeguess(int row, int col) {

        if (proxygrid[row][col]=="X "){
            status="Its a hit";
            grid[row][col]="X ";
            proxygrid[row][col]="C ";
            return true;
        }
        else if (proxygrid[row][col]=="C "){
            status = "Its already clicked";
            GUESS_COUNT--;
            return false;
        }
        else{
            status= "Its a miss";
            grid[row][col]="O ";
            proxygrid[row][col]="C ";
            return false;
        }
    }



    public static void printmap(){
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String alpha = getCharForNumber(i);
                if (j == 0)
                    System.out.print(alpha + "|" + grid[i][j]);
//
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i+" ");
        System.out.println();
    }

    public static int[] getdirection(){
        Random rand = new Random();
        int random = rand.nextInt(50);
//        System.out.println(r);
        if(random%2==0)
            return sethorizontal();
        else
            return setvertical();
    }

    public static int getrandom6(){
        Random rand = new Random();
        int r = rand.nextInt(60);
        int randomnum = r%6;
        return randomnum;
    }
    public static int getrandom8(){
        Random rand = new Random();
        int r = rand.nextInt(80);
        int randomnum = r%8;
        return randomnum;
    }

    public static int[] sethorizontal(){
        int temprow, tempcol;
        do {
            temprow= getrandom8();
            tempcol= getrandom6();
        }while ((proxygrid[temprow][tempcol]!="- ")||(proxygrid[temprow][tempcol+1]!="- ")||(proxygrid[temprow][tempcol+2]!="- "));
        int[] check= {0,temprow,tempcol};
        return check;
    }

    public static int[] setvertical(){
        int temprow, tempcol;
        do {
            temprow= getrandom6();
            tempcol= getrandom8();
        }while ((proxygrid[temprow][tempcol]!="- ")||(proxygrid[temprow+1][tempcol]!="- ")||(proxygrid[temprow+2][tempcol]!="- "));
        int[] check= {1,temprow,tempcol};
        return check;
    }
}
