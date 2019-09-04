import java.util.*;

public class AlienshipGame {

    public static int numRows = 8;
    public static int numCols = 8;
    public static int playerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    private static final int[] SHIP_LENGTHS = {3, 3, 3};
    private static final int NUM_OF_SHIPS = 3;
    private static int SHIPS_KILLED = 0;
    private static int GUESS_COUNT = 0;
    private static int[] location;


    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        createMap();
        deployships();
        while(SHIPS_KILLED <3){
            System.out.println();
            System.out.println("Enter your guess: ");
            String playerguess = input.nextLine();
            char rowalpha = playerguess.charAt(0);
            char colalpha = playerguess.charAt(1);
            int colguess = colalpha - '0';
//            System.out.println(rowalpha+" , "+colguess);
            takeguess(rowalpha,colguess);
            System.out.println();
            printmap();
            System.out.println("Number of Guesses : "+(++GUESS_COUNT));


        }
    }

    public static void createMap(){

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String alpha = getCharForNumber(i);
                grid[i][j] = "- ";
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
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

    public static void deployships(){
        if (NUM_OF_SHIPS != 3) // Num of ships must be 3
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 3");
        }



        Alienship[] ships = new Alienship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            int[] location = getdirection();
            Alienship tempShip = new Alienship(SHIP_LENGTHS[i], location);
            ships[i] = tempShip;
            System.out.println("Ship"+(i+1)+" has been deployed");
            System.out.println(location[0]);
            tempShip.displaylocation();
            showships(location);
        }printmap();

    }
    public static void showships(int[] loc) {
        int row = loc[1];
        int col = loc[2];
        int dir = loc[0];

        if(dir==0){
            grid[row][col] = "O ";
            grid[row][col+1]= "O ";
            grid[row][col+2]= "O ";
        }else{
            grid[row][col] = "O ";
            grid[row+1][col]= "O ";
            grid[row+2][col]= "O ";
        }
    }

    private static String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char)(i + 65)) : null;
    }

    public static void takeguess(char rowalpha, int col) {
        int row = rowalpha - 'A';
        grid[row][col] = "O ";
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

    public static int getrandom(){
        Random rand = new Random();
        int r = rand.nextInt(60);
        int randomnum = r%6;
        return randomnum;
    }

    public static int[] sethorizontal(){
        int temprow, tempcol;
        do {
            temprow= getrandom();
            tempcol= getrandom();
        }while ((grid[temprow][tempcol]!="- ")||(grid[temprow][tempcol+1]!="- ")||(grid[temprow][tempcol+2]!="- "));
        int[] check= {0,temprow,tempcol};
        return check;
    }

    public static int[] setvertical(){
        int temprow, tempcol;
        do {
            temprow= getrandom();
            tempcol= getrandom();
        }while ((grid[temprow][tempcol]!="- ")||(grid[temprow+1][tempcol]!="- ")||(grid[temprow+2][tempcol]!="- "));
        int[] check= {1,temprow,tempcol};
        return check;
    }
}
