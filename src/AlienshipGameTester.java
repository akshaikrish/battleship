import java.util.Scanner;

public class AlienshipGameTester{

    private static  String userguess;
    private String expectedResult;
    private int[] alienships;
    private static String[][] gridmap = new String[AlienshipGame.numRows][AlienshipGame.numCols];
    private static char direction;
    private static int rowguess;
    private static int colguess;

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        gridmap = AlienshipGame.createMap();

        determineLoc();





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

        System.out.println();
        System.out.println("Choose the orientation of ship"+(i+1)+" (H for Horizontal and V for Vertical)");
        String tempdir = input.nextLine();
        direction = tempdir.charAt(0);
        direction = Character.toUpperCase(direction);
        System.out.println(direction);
       }

       public static void determineLoc(){
           int i=0;
           Alienship[] ships = new Alienship[AlienshipGame.NUM_OF_SHIPS];
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
        if ((gridmap[rowguess][colguess]=="- ")&&
                (gridmap[rowguess][colguess+1]=="- ")&&
                (gridmap[rowguess][colguess+2]=="- ")){
            gridmap[rowguess][colguess] = gridmap[rowguess][colguess+1] = gridmap[rowguess][colguess+2] ="X ";
            int[] location = {0, rowguess , colguess};
            AlienshipGame.generateShip(ships, i, location);
            return true;
        }else
            return false;

    }

    public static boolean setVertical(Alienship[] ships, int i){
        if ((gridmap[rowguess][colguess]=="- ")&&
                (gridmap[rowguess+1][colguess]=="- ")&&
                (gridmap[rowguess+2][colguess]=="- ")){
            gridmap[rowguess][colguess] = gridmap[rowguess+1][colguess] = gridmap[rowguess+2][colguess] ="X ";
            int[] location = {1, rowguess , colguess};
            AlienshipGame.generateShip(ships, i, location);
            return true;
        }else
            return false;
    }

    public static void guessTester(String guess, String result, int[] shiploc){


    }

}
