package chess;
/**This class implements the King object which inherits the abstract piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class King extends Piece {
    /**
     * Constructor that initializes King object.
     * @param color The color of the King(Black or white)
     * @param x The row position of the King on the board
     * @param y The column position for the King on the board
     */
    public King(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
        this.type='K';
        this.special=false;
    }

    /**
     * Returns true if the King is capable of moving to given coordinate.
     * @param x2 The row position of the King's next move.
     * @param y2 The column position of the King's next move.
     * @return True if the King is capable of moving to the given coordinate, false if it can't
     */
    @Override
    public boolean movable(int x2, int y2) {
        if(x2 == x && y2 == y) {
            //System.out.println(1);
            return false;
        }
        else if(x == x2) {
            if ((y2 + 1) == y || (y2 - 1) == y) {
                return true;
            }
        }
        else if(y == y2) {
            if ((x2 + 1) == x || (x2 - 1) == x) {
                return true;
            }
        }
        else if((x2+1==x && y2+1==y) || (x2+1==x && y2-1==y) || (x2-1==x && y2-1==y) || (x2-1==x && y2+1==y)){
            return true;
        }
        else{
            //System.out.println(2);
            return false;
        }
        //System.out.println(3);
        return false;
    }
}
