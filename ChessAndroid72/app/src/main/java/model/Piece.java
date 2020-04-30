package model;

/**This abstract class provides the general structure of a Chess piece
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public abstract class Piece {
    public boolean color;//True for black, false for white
    public char type;
    public int x;
    public int y;
    public boolean special;
    /*public Piece(boolean color, char type){
        this.color=color;
        this.type=type;
        int colorVal;
        if(color==true){
            colorVal=1;
        }
        else{
            colorVal=0;
        }

        switch(colorVal){
            case 0:
                switch(type){
                    case 'p':
                        this.pos= new int[]{0, 0};
                }

        }

    }*/

    /**
     * Gets the printout/ASCII text of the specific chess piece
     * @return ASCII text as string
     */
    public String getPiece(){
        if(color){
            return "b"+type;
        }
        else{
            return "w"+type;
        }
    }

    /**
     * Generic method which all chess pieces implement
     * @param x2 row coordinate of the piece's next move
     * @param y2 column coordinate of the piece's next move
     * @return true if piece can move to x2,y2, false otherwise.
     */
    public abstract boolean movable(int x2, int y2);


}
