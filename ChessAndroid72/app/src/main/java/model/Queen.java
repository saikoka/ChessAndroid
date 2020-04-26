package model;
/**This class implements the Queen object which inherits the abstract piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Queen extends Piece {
    /**
     * Constructor that initializes Queen object.
     * @param color The color of the Queen(Black or white)
     * @param x The row position of the Queen on the board
     * @param y The column position for the Queen on the board
     */
    public Queen(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
        this.type='Q';
    }
    /**
     * Returns true if the Queen is capable of moving to given coordinate.
     * @param x2 The row position of the Queen's next move.
     * @param y2 The column position of the Queen's next move.
     * @return True if the Queen is capable of moving to the given coordinate, false if it can't
     */
    @Override
    public boolean movable(int x2, int y2) {
        if(x==x2 && y==y2){
            return false;
        }
        else if(x2==x || y2==y){
            return true;
        }
        else{
            return Math.abs(x2 - x) == Math.abs(y2 - y);
        }
    }
}
