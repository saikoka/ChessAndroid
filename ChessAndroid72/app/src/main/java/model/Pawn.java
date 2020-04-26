package chess;
/**This class implements the Pawn object which inherits the abstract piece class
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Pawn extends Piece {
    /**
     * Constructor that initializes Pawn object.
     * @param color The color of the Pawn(Black or white)
     * @param x The row position of the Pawn on the board
     * @param y The column position for the Pawn on the board
     */
    public Pawn(boolean color, int x, int y){
        this.color=color;
        this.x=x;
        this.y=y;
        this.type='p';
        this.special=false;
    }

    /**
     * Returns true if the Pawn is capable of moving to given coordinate.
     * @param x2 The row position of the Pawn's next move.
     * @param y2 The column position of the Pawn's next move.
     * @return True if the Pawn is capable of moving to the given coordinate, false if it can't
     */

    @Override
    public boolean movable(int x2, int y2) {
        if(x2==x && y2==y){
            return false;
        }

        if(color==false){
            if(y==y2 && (x2+2)==x && x==6){
                return true;
            }
            else if(y==y2 && (x2+1==x)){
                return true;
            }
            else if(x2+1==x){
                if(Math.abs(y-y2)==1){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            if(y==y2 && (x2-2)==x && x==1){
                return true;
            }
            else if(y==y2 && (x2-1==x)){
                return true;
            }
            else if(x2-1==x){
                if(Math.abs(y-y2)==1){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        return false;
    }
}
