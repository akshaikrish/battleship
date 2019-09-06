import java.util.Scanner;

public class AlienshipGameTester{

    private static  String userguess;
    private String expectedResult;
    private int[] alienships;
    private static String[][] gridmap = new String[AlienshipGame.numRows][AlienshipGame.numCols];
    private static char direction;
    private static int rowguess;
    private static int colguess;
    private static int statusCheck;

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        gridmap = AlienshipGame.createMap();
        Alienship[] ships = new Alienship[AlienshipGame.NUM_OF_SHIPS];
        determineLoc(ships);
        System.out.println();
        AlienshipGame.printmap();
//        AlienshipGame.startgame(ships);
        testGame(ships);
        System.out.println("Game Over!");




    }

    public static boolean acceptInput(int i){
//        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter initial coordinate of where you wanna place ship" + (i+1));
        String tempguess = input.nextLine();
        if (tempguess.length()>1) {
            char rowalpha = tempguess.charAt(0);
            char colalpha = tempguess.charAt(1);
            char rowCaps = Character.toUpperCase(rowalpha);
            rowguess = rowCaps - 'A';
            colguess = colalpha - '0';
            if (colguess < 8 && rowguess < 8){
                System.out.println("Input accepted");
                 userguess = tempguess;
                 return  true;}
            else{
                System.out.println("Size exceeding");
                return false;}
        }else{
            System.out.println("Enter valid coordinates !");
            return false;}
    }



    public static void determineDirection(int i){
        do {
            System.out.println();
            System.out.println("Choose the orientation of ship" + (i + 1) + " (H for Horizontal and V for Vertical)");
            String tempdir = input.nextLine();
            direction = tempdir.charAt(0);
            direction = Character.toUpperCase(direction);
            System.out.println(direction);
        }while (direction !='V' && direction!='H');
    }



    public static void determineLoc(Alienship[] ships){
       int i=0;

       while(i<AlienshipGame.NUM_OF_SHIPS) {
           determineDirection(i);
            boolean valid = acceptInput(i);
           if (valid){
               if((direction=='H' && colguess<6) || (direction=='V' && rowguess<6)) {
                   if (direction == 'H') {
                       if(setHorizontal(ships,i)) {
                           i++;
                       }else
                           System.out.println("Ships overlapping");
                   } else{
                       if(setVertical(ships, i)) {
                           i++;
                       }else
                           System.out.println("Ships overlapping");
                   }

               }else
                   System.out.println("Location incompitible !");

           }
           else
               System.out.println("Enter valid coordinates !");
       }

    }



    public static boolean setHorizontal(Alienship[] ships, int i){
        if ((AlienshipGame.proxygrid[rowguess][colguess]=="- ")&&
                (AlienshipGame.proxygrid[rowguess][colguess+1]=="- ")&&
                (AlienshipGame.proxygrid[rowguess][colguess+2]=="- ")){
            gridmap[rowguess][colguess] = gridmap[rowguess][colguess+1] = gridmap[rowguess][colguess+2] ="X ";
            int[] location = {0, rowguess , colguess};
            AlienshipGame.generateShip(ships, i, location);
            return true;
        }else
            return false;

    }



    public static boolean setVertical(Alienship[] ships, int i){
        if ((AlienshipGame.proxygrid[rowguess][colguess]=="- ")&&
                (AlienshipGame.proxygrid[rowguess+1][colguess]=="- ")&&
                (AlienshipGame.proxygrid[rowguess+2][colguess]=="- ")){
            gridmap[rowguess][colguess] = gridmap[rowguess+1][colguess] = gridmap[rowguess+2][colguess] ="X ";
            int[] location = {1, rowguess , colguess};
            AlienshipGame.generateShip(ships, i, location);
            return true;
        }else
            return false;
    }



    public static void testGame(Alienship[] ships){
        while(AlienshipGame.SHIPS_KILLED <3){
            statusCheck = 0;
            System.out.println();
//            System.out.println("Number of ships Killed : "+SHIPS_KILLED);
            System.out.println("Enter User guess: ");
            String playerguess = input.nextLine();
            if (playerguess.length()>1){
                char rowalpha = playerguess.charAt(0);
                char colalpha = playerguess.charAt(1);
                char rowCaps = Character.toUpperCase(rowalpha);
                int rowguess = rowCaps - 'A';
                int colguess = colalpha - '0';
//            System.out.println(rowalpha+" , "+colguess);
                if (colguess<8 && rowguess<8) {
                    System.out.println("Enter expected result : ");
                    String expectedresult = input.nextLine();
                    boolean isHit = AlienshipGame.takeguess(rowguess, colguess);
                    if (isHit) {
                        statusCheck = 1;
                        for (int i = 0; i < 3; i++) {
                            if (ships[i].ifHit(rowguess, colguess))
                                if (ships[i].isKilled()) {
                                    AlienshipGame.SHIPS_KILLED++;
                                    AlienshipGame.status = "Its a kill! You killed " + AlienshipGame.SHIPS_KILLED + " out of 3 Alienships !";
                                    statusCheck = 2;
                                }
                        }
                    }
                    System.out.println(AlienshipGame.status);
                    guessTester(playerguess, expectedresult, gridmap);
                    System.out.println("Number of Guesses : " + (++AlienshipGame.GUESS_COUNT));
                    System.out.println();
                    AlienshipGame.printmap();
                }else
                    System.out.println("Enter valid coordinates !");
            }else
                System.out.println("Enter valid coordinates !");
        }
    }



    public static void guessTester(String guess, String expresult, String[][] shiploc){
        String lower_expresult = expresult.toLowerCase();
        char exp_result = lower_expresult.charAt(0);
        char actualResult;
        if (guess.length()>1){
            char rowalpha = guess.charAt(0);
            char colalpha = guess.charAt(1);
            char rowCaps = Character.toUpperCase(rowalpha);
            int rowtest = rowCaps - 'A';
            int coltest = colalpha - '0';
//            System.out.println(rowalpha+" , "+colguess);
            if (coltest<AlienshipGame.numCols && rowtest<AlienshipGame.numRows){
                if (shiploc[rowtest][coltest]=="X "){
                    actualResult = 'h';
                    shiploc[rowtest][coltest] = "H ";
                }
                else if ((shiploc[rowtest][coltest]=="C " ) || (shiploc[rowtest][coltest]=="H ")){
                    actualResult = 'c';
                }
                else {
                    actualResult = 'm';
                    shiploc[rowtest][coltest] = "C ";
                }

                if (actualResult == exp_result){
                    System.out.println("Test case passed !");
                }else
                    System.out.println("Test case failed !");
            }else
                System.out.println("Enter valid codes !");
        }else
            System.out.println("Enter valid codes !");


    }

}
