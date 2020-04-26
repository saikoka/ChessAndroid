package model;
/**This class implements the Rook object which inherits the abstract piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Knight extends Piece {
    /**
     * Constructor that initializes Knight object.
     * @param color The color of the Knight(Black or white)
     * @param x The row position of the Knight on the board
     * @param y The column position for the Knight on the board
     */
    public Knight(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
        this.type='N';
    }
    /**
     * Returns true if the Knight is capable of moving to given coordinate.
     * @param x2 The row position of the Knight's next move.
     * @param y2 The column position of the Knight's next move.
     * @return True if the Knight is capable of moving to the given coordinate, false if it can't
     */
    @Override
    public boolean movable(int x2, int y2) {
        return ((Math.abs(y2-y)+Math.abs(x2-x)==3) && x2!=x && y2!=y && (Math.abs(x2 - x) != Math.abs(y2 - y)));//Closest square where distance is 3, column and row are not in common, and can not be diagonal.
    }
}
