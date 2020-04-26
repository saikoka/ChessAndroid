package model;
/**This class implements the Bishop object which inherits the abstract piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Bishop extends Piece {
    /**
     * Constructor that initializes Bishop object.
     * @param color The color of the Bishop(Black or white)
     * @param x The row position of the Bishop on the board
     * @param y The column position for the Bishop on the board
     */
    public Bishop(boolean color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.type = 'B';
    }
    /**
     * Returns true if the Bishop is capable of moving to given coordinate.
     * @param x2 The row position of the Bishop's next move.
     * @param y2 The column position of the Bishop's next move.
     * @return True if the Bishop is capable of moving to the given coordinate, false if it can't
     */
    @Override
    public boolean movable(int x2, int y2) {
        if(x2==x && y2==y){
            return false;
        }
        else return Math.abs(x2 - x) == Math.abs(y2 - y);
    }
}