public class Alienship {
    /* Instance Variables */
    private int row =- 1;
    private int col = -1;
    private int length;
    private int direction;

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
        if(direction==0){
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

    // Set the location of the ship
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

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
