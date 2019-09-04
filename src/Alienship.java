public class Alienship {
    /* Instance Variables */
    private int row =- 1;
    private int col = -1;
    private int length;
    private int direction;
    private String[][] coordinates;
    private int shipCellLeft=3;
    private int r1,r2,r3,c1,c2,c3;
   private boolean killed = false;

    // Direction Constants
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    // Constructor
    public Alienship(int length, int[] location)
    {
//        System.out.println("Ship has been deployed");
        this.length = length;
        this.row = location[1];
        this.col = location[2];
        this.direction = location[0];

    }
    public void displaylocation(){
        System.out.println("Coordinates of the ship are : ");
        if(direction==HORIZONTAL){
            System.out.println("("+row+","+col+")");
            System.out.println("("+row+","+(col+1)+")");
            System.out.println("("+row+","+(col+2)+")");
        }
        else{
            System.out.println("("+row+","+col+")");
            System.out.println("("+(row+1)+","+col+")");
            System.out.println("("+(row+2)+","+col+")");
        }
    }

    // Has the location been init
    public boolean isLocationSet()
    {
        if (row == -1 || col == -1)
            return false;
        else
            return true;
    }

    // Has the direction been init
    public boolean isDirectionSet()
    {
        if (direction == UNSET)
            return false;
        else
            return true;
    }

    public boolean isKilled(){
        if (!killed){
            if (shipCellLeft==0){
                killed = true;
                return killed;
            }else
                return false;
        }else
            return false;
    }

    public boolean ifHit(int r, int c){
        if((r==r1 && c==c1)||(r==r2 && c==c2)||(r==r3 && c==c3)){
            shipCellLeft--;
            return true;
        }
        else
            return false;
    }

    public void setLocation(){

        if (direction==HORIZONTAL){
            r1=r2=r3=row;
            c1=col;
            c2=col+1;
            c3=col+2;
        }else {
            c1=c2=c3=col;
            r1=row;
            r2=row+1;
            r3=row+2;
        }
    }
//    public boolean isClicked(int r, int c) {
//        for (int i=0;i<64;i++){
//            for (int j=0;j<2;j++){
//                if (clickGrid[i][j])
//            }
//        }
//    }

    // Set the location of the ship
//    public void setLocation()
//    {
//        if(direction==0){
//            int i=0;
//            for(int j=0;j<3;j++)
//                clickGrid[row][col+i]="X ";
//        }else {
//            int i=0;
//            for(int j=0;j<3;j++)
//                clickGrid[row+i][col]="X ";
//        }
//    }

    // Set the direction of the ship
    public void setDirection(int direction)
    {
        if (direction != UNSET && direction != HORIZONTAL && direction != VERTICAL)
            throw new IllegalArgumentException("Invalid direction. It must be -1, 0, or 1");
        this.direction = direction;
    }

    // Getter for the row value
    public int getRow()
    {
        return row;
    }

    // Getter for the column value
    public int getCol()
    {
        return col;
    }

    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }

    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }

    // Helper method to get a string value from the direction
//    private String directionToString()
//    {
//        if (direction == UNSET)
//            return "UNSET";
//        else if (direction == HORIZONTAL)
//            return "HORIZONTAL";
//        else
//            return "VERTICAL";
//    }
}
