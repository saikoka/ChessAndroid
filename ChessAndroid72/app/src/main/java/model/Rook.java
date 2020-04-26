package model;
/**This class implements the Rook object, which extends the Piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Rook extends Piece {
    /**
     * Constructor that initializes Rook object.
     * @param color The color of the Rook(Black or white)
     * @param x The row position of the Rook on the board
     * @param y The column position for the Rook on the board
     */
    public Rook(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
        this.type='R';
        this.special=false;
    }

    /**
     * Returns true if the Rook is capable of moving to given coordinate.
     * @param x2 The row position of the Rook's next move.
     * @param y2 The column position of the Rook's next move.
     * @return True if the Rook is capable of moving to the given coordinate, false if it can't
     */
    @Override
    public boolean movable(int x2, int y2) {
        if(x2==x && y2==y){
            return false;
        }
        else if(x2==x || y2==y){
            return true;
        }
        else{
            return false;
        }
    }
}
