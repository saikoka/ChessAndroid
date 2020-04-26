package model;

import java.util.Scanner;

/**This class runs the Chess game, and contains methods related to the board
 * @author Shanmukh Koka and Marc Fortich
 *
 */
public class Chess {
    public static void main(String[] args){
        Piece[][] board = new Piece[8][8];
        board[0][0]= new Rook(true, 0,0);
        board[0][1]= new Knight(true,0,1);
        board[0][2]= new Bishop(true,0,2);
        board[0][3]= new Queen(true,0,3);
        board[0][4]= new King(true,0,4);
        board[0][5]= new Bishop(true,0,5);
        board[0][6]= new Knight(true,0,6);
        board[0][7]= new Rook(true,0,7);
        board[1][0]= new Pawn(true,1,0);
        board[1][1]= new Pawn(true,1,1);
        board[1][2]= new Pawn(true,1,2);
        board[1][3]= new Pawn(true,1,3);
        board[1][4]= new Pawn(true,1,4);
        board[1][5]= new Pawn(true,1,5);
        board[1][6]= new Pawn(true,1,6);
        board[1][7]= new Pawn(true,1,7);




        board[7][0]= new Rook(false,7,0);
        board[7][1]= new Knight(false,7,1);
        board[7][2]= new Bishop(false,7,2);
        board[7][3]= new Queen(false,7,3);
        board[7][4]= new King(false,7,4);
        board[7][5]= new Bishop(false,7,5);
        board[7][6]= new Knight(false,7,6);
        board[7][7]= new Rook(false,7,7);
        board[6][0]= new Pawn(false,6,0);
        board[6][1]= new Pawn(false,6,1);
        board[6][2]= new Pawn(false,6,2);
        board[6][3]= new Pawn(false,6,3);
        board[6][4]= new Pawn(false,6,4);
        board[6][5]= new Pawn(false,6,5);
        board[6][6]= new Pawn(false,6,6);
        board[6][7]= new Pawn(false,6,7);
        printBoard(board);


        boolean check=false;//true mean active happening
        boolean turn=false; //True means black's turn, false means white's.
        boolean draw = false;
        while(true) {
            System.out.println();

            if (check){
                System.out.println("Check");
            }


            if(turn==false) {
                System.out.print("White's Move: ");

            }
            else{
                System.out.print("Black's Move: ");

            }
            Scanner myObj = new Scanner(System.in);
            String temp=myObj.nextLine();
            if(temp.equals("draw") && draw){
                break;
            }
            if(draw){
                draw=false;
            }
            int y1=((int) temp.charAt(0)-97);//x is rows, y is columns
            int x1=8-Character.getNumericValue(temp.charAt(1));
            int y2=((int) temp.charAt(3)-97);
            int x2=8-Character.getNumericValue(temp.charAt(4));
            char promotionPiece= ' ';

            if(temp.equals("resign")){
                if(turn==false){
                    System.out.println("Black wins");
                }
                else{
                    System.out.println("White Wins");
                }
                break;
            }
            if(temp.length()==11 && temp.substring(6).equals("draw?")){
                draw=true;
            }
            if(temp.length()==7){
                promotionPiece=temp.charAt(6);
            }

            Piece temp1 = board[x1][y1];
            char tempT1=board[x1][y1].type;
            boolean tempC1=board[x1][y1].color;
            boolean tempSp1=board[x1][y1].special;
            int tempX1=board[x1][y1].x;
            int tempY1=board[x1][y1].y;

            Piece temp2 = board[x2][y2];
            int tempX2=0;
            int tempY2=0;
            char tempT2=0;
            boolean tempC2=false;
            boolean tempSp2=false;
            if (board[x2][y2]!=null){
                tempX2=board[x2][y2].x;
                tempY2=board[x2][y2].y;
                tempT2=board[x2][y2].type;
                tempC2=board[x2][y2].color;
                tempSp2=board[x2][y2].special;

            }

            boolean result=movePiece(x1,y1,x2,y2,board,turn, promotionPiece);


            if(check) {//already in check

                boolean newCheck = inCheck(board, turn);
                if (newCheck){//did not fix

                    System.out.println("Illegal move, try again");

                    board[x1][y1]=temp1;
                    board[x1][y1].type=tempT1;
                    board[x1][y1].special=tempSp1;
                    board[x1][y1].color=tempC1;
                    board[x1][y1].x=tempX1;
                    board[x1][y1].y=tempY1;

                    board[x2][y2]=temp2;
                    if (temp2!=null){
                        board[x2][y2].x=tempX2;
                        board[x2][y2].y=tempY2;
                        board[x2][y2].type=tempT2;
                        board[x2][y2].special=tempSp2;
                        board[x2][y2].color=tempC2;

                    }
                    //System.out.println("SPECIAL BOARD PRINT");
                    //printBoard(board);//temp to check that move is undone
                    result=false;
                }else{//fixed
                    //System.out.println("you moved out of check");

                    check=false;
                }


            }else{//wasnt in check
                boolean newCheck = inCheck(board, turn);
                if (newCheck){
                //System.out.println("moved into check");
                System.out.println("Illegal move, try again");

                    board[x1][y1]=temp1;
                    board[x1][y1].type=tempT1;
                    board[x1][y1].special=tempSp1;
                    board[x1][y1].color=tempC1;
                    board[x1][y1].x=tempX1;
                    board[x1][y1].y=tempY1;

                    board[x2][y2]=temp2;
                    if (temp2!=null){
                        board[x2][y2].x=tempX2;
                        board[x2][y2].y=tempY2;
                        board[x2][y2].type=tempT2;
                        board[x2][y2].special=tempSp2;
                        board[x2][y2].color=tempC2;

                    }
                    result=false;
                   // System.out.println("SPECIAL BOARD PRINT");
                    //printBoard(board);//temp to check that move is undone
                }else{
                    //System.out.println("didnt put self in check");
                }
            }

            if(result){
                turn=!turn;
                //if(turn){
                    //System.out.println("checking if black is in check");
                //}else{
                    //System.out.println("checking if white is in check");
                //}

                check=inCheck(board,turn);
                if (check){
                    if (checkMate(board, turn)){
                        if(!turn){
                            System.out.println("Black wins");
                        }else {
                            System.out.println("White Wins");
                        }
                        break;
                    }
                }
            }
            else{
                continue;
            }
            System.out.println();
            printBoard(board);
        }
    }

    /**
     * Checks if King is in checkmate
     * @param board Current board
     * @param c boolean value used for getKing method
     * @return true if king is in mate, false otherwise
     */
    public static boolean checkMate(Piece[][] board, boolean c){

        //find needed king
        int king = getKing(board, c);

        int y = king%10;

        //System.out.println("ones is " + ones);

        int x = king - y;
        x = x/10;

        Piece temp1 = board[x][y];
        char tempT1=board[x][y].type;
        boolean tempC1=board[x][y].color;
        boolean tempSp1=board[x][y].special;
        int tempX1=board[x][y].x;
        int tempY1=board[x][y].y;

        //simple move out of the way
        if (x+1<=7){
            if (board[x+1][y]==null){//down

                board[x+1][y]=board[x][y];
                board[x+1][y].x=x+1;
                board[x+1][y].y=y;
                board[x][y]=null;

                if (inCheck(board, c)){
                    //would be check
                    board[x][y]=temp1;
                    board[x][y].type=tempT1;
                    board[x][y].special=tempSp1;
                    board[x][y].color=tempC1;
                    board[x][y].x=tempX1;
                    board[x][y].y=tempY1;
                    board[x+1][y]=null;

                }else{
                    // no check
                    board[x][y]=temp1;
                    board[x][y].type=tempT1;
                    board[x][y].special=tempSp1;
                    board[x][y].color=tempC1;
                    board[x][y].x=tempX1;
                    board[x][y].y=tempY1;
                    board[x+1][y]=null;
                    return false;
                }

            }
            if(y+1<=7){
                if (board[x+1][y+1]==null){//se

                    board[x+1][y+1]=board[x][y];
                    board[x+1][y+1].x=x+1;
                    board[x+1][y+1].y=y+1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x+1][y+1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x+1][y+1]=null;
                        return false;
                    }
                }
                if (board[x][y+1]==null){//right

                    board[x][y+1]=board[x][y];
                    board[x][y+1].x=x;
                    board[x][y+1].y=y+1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x][y+1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x][y+1]=null;
                        return false;
                    }
                }
            }
            if (y-1>=0){
                if (board[x+1][y-1]==null){//sw

                    board[x+1][y-1]=board[x][y];
                    board[x+1][y-1].x=x+1;
                    board[x+1][y-1].y=y-1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x+1][y-1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x+1][y-1]=null;
                        return false;
                    }
                }
                if (board[x][y-1]==null){//left

                    board[x][y-1]=board[x][y];
                    board[x][y-1].x=x;
                    board[x][y-1].y=y-1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x][y-1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x][y-1]=null;
                        return false;
                    }
                }
            }
        }
        if (x-1>=0){
            if (board[x-1][y]==null){//up

                board[x-1][y]=board[x][y];
                board[x-1][y].x=x-1;
                board[x-1][y].y=y;
                board[x][y]=null;

                if (inCheck(board, c)){
                    //would be check
                    board[x][y]=temp1;
                    board[x][y].type=tempT1;
                    board[x][y].special=tempSp1;
                    board[x][y].color=tempC1;
                    board[x][y].x=tempX1;
                    board[x][y].y=tempY1;
                    board[x-1][y]=null;

                }else{
                    // no check
                    board[x][y]=temp1;
                    board[x][y].type=tempT1;
                    board[x][y].special=tempSp1;
                    board[x][y].color=tempC1;
                    board[x][y].x=tempX1;
                    board[x][y].y=tempY1;
                    board[x-1][y]=null;
                    return false;
                }
            }
            if(y+1<=7){
                if (board[x-1][y+1]==null){//ne

                    board[x-1][y+1]=board[x][y];
                    board[x-1][y+1].x=x-1;
                    board[x-1][y+1].y=y+1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x-1][y+1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x-1][y+1]=null;
                        return false;
                    }
                }
            }
            if (y-1>=0){
                if (board[x-1][y-1]==null){//nw

                    board[x-1][y-1]=board[x][y];
                    board[x-1][y-1].x=x-1;
                    board[x-1][y-1].y=y-1;
                    board[x][y]=null;

                    if (inCheck(board, c)){
                        //would be check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x-1][y-1]=null;

                    }else{
                        // no check
                        board[x][y]=temp1;
                        board[x][y].type=tempT1;
                        board[x][y].special=tempSp1;
                        board[x][y].color=tempC1;
                        board[x][y].x=tempX1;
                        board[x][y].y=tempY1;
                        board[x-1][y-1]=null;
                        return false;
                    }
                }
            }
        }
            System.out.println("Checkmate");
            return true;
    }

    /**
     * Method used to determine if King is in check
     * @param board current board
     * @param c boolean value used for getKing method
     * @return True if king is in check, false otherwise.
     */
    public static boolean inCheck(Piece[][] board, boolean c){

        //find needed king
        int king = getKing(board, c);

        int y = king%10;

        //System.out.println("ones is " + ones);

        int x = king - y;
        x = x/10;

        //System.out.println("tens is " + tens);
        //right
        for (int i = y+1; i < 8; i++) {

            if (board[x][i]!=null){
                if (board[x][i].color!=board[x][y].color &&
                        (board[x][i].type=='Q' || board[x][i].type=='R') ){
                    //System.out.println("in check!");
                    return true;
                }else{
                    break;
                }
            }
        }
        //left
        for (int i = y-1; i >= 0; i--) {

            if (board[x][i]!=null){
                if (board[x][i].color!=board[x][y].color &&
                        (board[x][i].type=='Q' || board[x][i].type=='R') ){
                    //System.out.println("in check!");
                    return true;
                }else{
                    break;
                }
            }
        }
        //up
        for (int i = x-1; i >= 0; i--) {

            if (board[i][y]!=null){
                if (board[i][y].color!=board[x][y].color &&
                        (board[i][y].type=='Q' || board[i][y].type=='R') ){
                    //System.out.println("in check!");
                    return true;
                }else {
                    break;
                }
            }
        }
        //down
        for (int i = x+1; i <8; i++) {

            if (board[i][y]!=null){
                if (board[i][y].color!=board[x][y].color &&
                        (board[i][y].type=='Q' || board[i][y].type=='R') ){
                    //System.out.println("in check!");
                    return true;
                }else {
                    break;
                }
            }
        }
        //ne
        if (x>0){
            int right = y+1;
            int up= x-1;
            while (up>=0 && right<=7){
                if (board[up][right]!=null){
                    if ((board[up][right].type=='B' || board[up][right].type=='Q')&&
                            board[up][right].color!=board[x][y].color){
                        //System.out.println("in check!!!");
                        return true;
                    }else {
                        break;
                    }
                }
                right++;
                up--;
            }
        }

        //nw
        if (x>0){
            int left = y-1;
            int up = x-1;
            while (left>=0 && up>=0) {
                if (board[up][left] != null) {
                    if ((board[up][left].type == 'B' || board[up][left].type=='Q')&&
                            board[up][left].color != board[x][y].color) {
                        //System.out.println("in check!!!");
                        return true;
                    }else {
                        break;
                    }
                }
                left--;
                up--;
            }
        }

        //se
        if (x<7){
            int right = y+1;
            int down= x+1;
            while (down<=7 && right<=7){
                if (board[down][right]!=null){
                    if ((board[down][right].type=='B' || board[down][right].type=='Q')&&
                            board[down][right].color!=board[x][y].color){
                        //System.out.println("in check!!!");
                        return true;
                    }else {
                        break;
                    }
                }
                right++;
                down++;
            }
        }

        //sw
        if (x<7){
            int left = y-1;
            int down = x+1;
            while (left>=0 && down<=7) {
                if (board[down][left] != null) {
                    if ((board[down][left].type == 'B' || board[down][left].type=='Q')&&
                            board[down][left].color != board[x][y].color) {
                        //System.out.println("in check!!!");
                        return true;
                    }else {
                        break;
                    }
                }
                left--;
                down++;
            }
        }

        //knight
            //upright, x-2 y+1
            if ( (x-2>=0) && (y+1<=7) ){
                if ( board[x-2][y+1]!=null ){
                    if ( (board[x-2][y+1].type=='N') && (board[x-2][y+1].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //upleft, x-2 y-1
            if ( (x-2>=0) && (y-1>=0) ){
                if ( board[x-2][y-1]!=null ){
                    if ( (board[x-2][y-1].type=='N') && (board[x-2][y-1].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //rightup, x-1 y+2
            if ( (x-1>=0) && (y+2<=7) ){
                if ( board[x-1][y+2]!=null ){
                    if ( (board[x-1][y+2].type=='N') && (board[x-1][y+2].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //rightdown, x+1 y+2
            if ( (x+1<=7) && (y+2<=7) ){
                if ( board[x+1][y+2]!=null ){
                    if ( (board[x+1][y+2].type=='N') && (board[x+1][y+2].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //leftup, x-1 y-2
            if ( (x-1>=0) && (y-2>=0) ){
                if ( board[x-1][y-2]!=null ){
                    if ( (board[x-1][y-2].type=='N') && (board[x-1][y-2].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //leftdown, x+1 y-2
            if ( (x+1<=7) && (y-2>=0) ){
                if ( board[x+1][y-2]!=null ){
                    if ( (board[x+1][y-2].type=='N') && (board[x+1][y-2].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //downright, x+2 y+1
            if ( (x+2<=7) && (y+1<=7) ){
                if ( board[x+2][y+1]!=null ){
                    if ( (board[x+2][y+1].type=='N') && (board[x+2][y+1].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }
            //downleft, x+2 y-1
            if ( (x+2<=7) && (y-1>=0) ){
                if ( board[x+2][y-1]!=null ){
                    if ( (board[x+2][y-1].type=='N') && (board[x+2][y-1].color!=board[x][y].color) ){
                        //System.out.println("in check!");
                        return true;
                    }
                }
            }


        //pawn
          //black king


                    //downleft
                    if( (x+1<=7) && (y-1>=0) ){
                        if(board[x+1][y-1]!=null){
                            if ( (board[x+1][y-1].type=='p') && (board[x+1][y-1].color!=board[x][y].color) &&
                                    (board[x][y].color==true) ){
                               // System.out.println("Check");
                                return true;
                            }
                        }
                    }//downright
                    else if( (x+1<=7) && (y+1<=7) ){
                        if (board[x+1][y+1]!=null){
                            if ( (board[x+1][y+1].type=='p') && (board[x+1][y+1].color!=board[x][y].color) &&
                                    (board[x][y].color==true) ){
                                //System.out.println("Check");
                                return true;
                            }
                        }
                    }



                //white king

                    //upleft
                    if ( (x-1>=0) && (y-1>=0) ){
                        if (board[x-1][y-1]!=null){
                            if ( (board[x-1][y-1].type=='p') && (board[x-1][y-1].color!=board[x][y].color) &&
                                    (board[x][y].color==false)){
                                //System.out.println("Check");
                                return true;
                            }
                        }
                    }//upright
                    else if ( (x-1>=0) && (y+1<=7) ){
                        if (board[x-1][y+1]!=null){
                            if ( (board[x-1][y+1].type=='p') && (board[x-1][y+1].color!=board[x][y].color) &&
                                    (board[x][y].color==false) ){
                                //System.out.println("Check");
                                return true;
                            }
                        }
                    }








        return false;
    }


    /**
     *
     * @param board current chess board
     * @param c true if black king, false if white king
     * @return number where first digit is row coordinate and second is column coordinate of king
     */
    public static int getKing(Piece[][] board, boolean c){


        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if(board[i][j]!=null) {

                    String f = board[i][j].getPiece();
                    if(c==true) {
                        if (f.equals("bK")==true) {
                            //System.out.println("found black king at " + i + " and " + j);
                            return i*10+j;
                        }
                    }else{
                        if (f.equals("wK")==true) {
                            //System.out.println("found white king at " + i + " and " + j);
                            return i*10+j;
                        }
                    }

                }

            }

        }
        //System.out.println("no king");
        return -1;
    }

    /**
     * Print's current state of chess board in ASCII text.
     * @param x Current chess board state
     */
    public static void printBoard(Piece[][] x){

        for(int i=0;i<8;i++){
            int flip=0; //correct remainder of each column index to place ##
            if(i%2==0){
                flip=1;
            }
            for(int z=0;z<8;z++){
                if(z%2==flip && x[i][z]==null){ //checks for proper place to put ## and checks if that place is empty.
                    System.out.print("## ");
                }
                else if(x[i][z]==null){
                    System.out.print("   ");
                }
                else{
                    System.out.print(x[i][z].getPiece()+" ");
                }
            }
            System.out.println(8-i);
        }
        System.out.println(" a  b  c  d  e  f  g  h");
    }

    /**
     * Detects if piece can move to desired location. Checks for clear path, checks, etc.
     * @param x1 row coordinate of piece
     * @param y1 column coordinate of piece
     * @param x2 row coordinate of piece's destination
     * @param y2 column coordinate of piece's destination
     * @param board current board state
     * @param tf true for black, false for white
     * @param promotion Detects piece user wants to promote pawn to
     * @return true if errors in moving the piece, false otherwise
     */
    public static boolean movePiece(int x1, int y1, int x2, int y2,Piece[][] board,boolean tf, char promotion){//returns true if no errors, false if errors
        if((!tf && board[x1][y1].color)||(tf && !board[x1][y1].color)){//White's move, But select Black, or Black's move, but select White
            System.out.println("Illegal move, try again");
            return false;
        }
        switch(board[x1][y1].type){
            case 'R':
                if(board[x1][y1].movable(x2,y2)){
                    if(x1==x2){
                        if(y2>y1){//traversing right
                            for(int a=y1+1;a<y2;a++){//See if piece is in the way
                                if(board[x1][a]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){//If spot null, piece goes to spot and sets previous spot to null
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else{//If not null, check color of piece at that spot
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                        else{ //y1>y2-traversing left
                            for(int a=y1-1;a>=y2+1;a--){
                                if(board[x1][a]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                    }
                    else{
                        if(x2>x1){ //Traversing down
                            for(int a=x1+1;a<x2;a++){
                                if(board[a][y1]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                        else{//x1>x2-traversing up
                            for(int a=x1-1;a>=x2+1;a--){
                                if(board[a][y1]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("Illegal move, try again");
                    return false;
                }
                break;

            case 'K':
                /*System.out.println(board[x1][y1].type);
                System.out.println(board[x1][y1].color);
                System.out.println(board[x1][y1].special);
                System.out.println(board[x1][y1].x);
                System.out.println(board[x1][y1].y);*/
                if(board[x1][y1].movable(x2,y2)){
                    if(board[x2][y2]==null){

                        board[x2][y2]=board[x1][y1];
                        board[x2][y2].x=x2;
                        board[x2][y2].y=y2;
                        board[x1][y1]=null;

                        if (board[x2][y2].special==false){
                            board[x2][y2].special=true;
                        }


                        return true;
                    }
                    else{
                        if(board[x2][y2].color==board[x1][y1].color){
                            System.out.println("Illegal move, try again");
                            return false;
                        }
                        else{
                            board[x2][y2]=board[x1][y1];
                            board[x2][y2].x=x2;
                            board[x2][y2].y=y2;
                            board[x1][y1]=null;

                            if (board[x2][y2].special==false){
                                board[x2][y2].special=true;
                            }

                            return true;
                        }
                    }
                }
                else if( board[x1][y1].special==false &&
                        board[x1][y1+3].special==false &&
                        board[x1][y1+2]==null &&
                        board[x1][y1+1]==null &&
                        x2==x1 &&
                        y2==(y1+2) ){

                    board[x2][y2]=board[x1][y1];
                    board[x2][y2].x=x2;
                    board[x2][y2].y=y2;
                    board[x2][y2].special=true;
                    board[x1][y1]=null;

                    board[x1][y1+1]=board[x1][y1+3];
                    board[x1][y1+1].x=x1;
                    board[x1][y1+1].y=y1+1;
                    board[x1][y1+1].special=true;
                    board[x1][y1+3]=null;
                    //System.out.println("king side castle");

                    return  true;

                }
                else if( board[x1][y1].special==false &&
                        board[x1][y1-4].special==false &&
                        board[x1][y1-3]==null &&
                        board[x1][y1-2]==null &&
                        board[x1][y1-1]==null &&
                        x2==x1 &&
                        y2==(y1-2) ){

                    board[x2][y2]=board[x1][y1];
                    board[x2][y2].x=x2;
                    board[x2][y2].y=y2;
                    board[x2][y2].special=true;
                    board[x1][y1]=null;

                    board[x1][y1-1]=board[x1][y1-4];
                    board[x1][y1-1].x=x1;
                    board[x1][y1-1].y=y1-1;
                    board[x1][y1-1].special=true;
                    board[x1][y1-4]=null;
                    //System.out.println("queen side castle");

                    return  true;

                }

                else{
                    System.out.println("Illegal move, try again");
                    return false;
                }

            case 'B':

                if(board[x1][y1].movable(x2,y2)){
                    if(x1>x2){//Moving piece up
                        if(y1>y2){//Moving piece northwest
                            int xLoop=x1-1;
                            int yLoop=y1-1;
                            while(xLoop>=0 &&xLoop>x2 && yLoop>=0 && yLoop>y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop--;
                                yLoop--;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                        else{//Moving piece northeast
                            int xLoop=x1-1;
                            int yLoop=y1+1;
                            while(xLoop>=0 &&xLoop>x2 && yLoop>=0 && yLoop<y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop--;
                                yLoop++;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                    }
                    else{//Moving piece down
                        if(y1>y2){//Moving piece southwest
                            int xLoop=x1+1;
                            int yLoop=y1-1;
                            while(xLoop>=0 &&xLoop<x2 && yLoop>=0 && yLoop>y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop++;
                                yLoop--;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                        else{//Moving piece southeast
                            int xLoop=x1+1;
                            int yLoop=y1+1;
                            while(xLoop>=0 &&xLoop<x2 && yLoop>=0 && yLoop<y2){
                                if(board[xLoop][yLoop]!=null){

                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop++;
                                yLoop++;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                    }
                }
                else{
                    System.out.println("Movable value is: "+board[x1][y1].movable(x2,y2));
                    System.out.println("Illegal move, try again");
                    return false;
                }

            case 'Q':
                if(board[x1][y1].movable(x2,y2)){
                    if(x2==x1) {
                        if(y2>y1){//traversing right
                            for(int a=y1+1;a<y2;a++){//See if piece is in the way
                                if(board[x1][a]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){//If spot null, piece goes to spot and sets previous spot to null
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else{//If not null, check color of piece at that spot
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                        else{ //y1>y2-traversing left
                            for(int a=y1-1;a>=y2+1;a--){
                                if(board[x1][a]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                    }
                    else if(y2==y1){
                        if(x2>x1){ //Traversing down
                            for(int a=x1+1;a<x2;a++){
                                if(board[a][y1]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                        else{//x1>x2-traversing up
                            for(int a=x1-1;a>=x2+1;a--){
                                if(board[a][y1]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2]!=null){
                                if(board[x2][y2].color==board[x1][y1].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    return true;
                                }
                            }
                        }
                    }
                    else if(x1>x2){
                        if(y1>y2){//Moving piece northwest
                            int xLoop=x1-1;
                            int yLoop=y1-1;
                            while(xLoop>=0 &&xLoop>x2 && yLoop>=0 && yLoop>y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop--;
                                yLoop--;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                        else{//Moving piece northeast
                            int xLoop=x1-1;
                            int yLoop=y1+1;
                            while(xLoop>=0 &&xLoop>x2 && yLoop>=0 && yLoop<y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop--;
                                yLoop++;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                    }
                    else{
                        if(y1>y2){//Moving piece southwest
                            int xLoop=x1+1;
                            int yLoop=y1-1;
                            while(xLoop>=0 &&xLoop<x2 && yLoop>=0 && yLoop>y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop++;
                                yLoop--;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                        else{//Moving piece southeast
                            int xLoop=x1+1;
                            int yLoop=y1+1;
                            while(xLoop>=0 &&xLoop<x2 && yLoop>=0 && yLoop<y2){
                                if(board[xLoop][yLoop]!=null){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                xLoop++;
                                yLoop++;
                            }
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                            else if(board[x2][y2].color==board[x1][y1].color){
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                            else{
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                return true;
                            }
                        }
                    }
                }
                else{

                    System.out.println("Illegal move, try again");
                    return false;
                }
                break;

            case 'N':
                if(board[x1][y1].movable(x2,y2)){
                    if(board[x2][y2]==null){
                        board[x2][y2]=board[x1][y1];
                        board[x2][y2].x=x2;
                        board[x2][y2].y=y2;
                        board[x1][y1]=null;

                        if (board[x2][y2].special==false){
                            board[x2][y2].special=true;
                        }


                        return true;
                    }
                    else{
                        if(board[x2][y2].color==board[x1][y1].color){
                            System.out.println("Illegal move, try again");
                            return false;
                        }
                        else{
                            board[x2][y2]=board[x1][y1];
                            board[x2][y2].x=x2;
                            board[x2][y2].y=y2;
                            board[x1][y1]=null;

                            if (board[x2][y2].special==false){
                                board[x2][y2].special=true;
                            }


                            return true;
                        }
                    }
                }
                else{
                    System.out.println("Illegal move, try again");
                    return false;
                }


            case 'p':
                System.out.println("pog");
                if(board[x1][y1].movable(x2,y2)){
                    System.out.println("XD");
                    if(board[x1][y1].color==false){//white
                        if(y1==y2 && (x2+2)==x1 && x1==6){//Can move by two if at original spot
                            if(board[x2][y2]==null && board[x2+1][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                board[x2][y2].special=true;
                                //System.out.println("initial double move");
                                return true;
                            }
                            else{
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                        }
                        else if(y1==y2 && (x2+1==x1)){
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                if(x2==0){
                                    if(promotion=='N'){
                                        board[x2][y2]= new Knight(false, x2,y2);
                                    }
                                    else if(promotion=='B'){
                                        board[x2][y2]= new Bishop(false, x2,y2);
                                    }
                                    else if(promotion=='R'){
                                        board[x2][y2]= new Rook(false, x2,y2);
                                    }
                                    else{
                                        board[x2][y2]= new Queen(false, x2,y2);
                                    }
                                }
                                return true;
                            }
                            else{
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                        }
                        else if(x2+1==x1){
                            if(Math.abs(y1-y2)==1){

                                //implementing enpassant
                                if(y2>y1){
                                    if(board[x2][y2]==null &&
                                            board[x1][y1+1].color != board[x1][y1].color &&
                                            board[x1][y1+1].special==true &&
                                            board[x1][y1+1].type=='p' &&
                                            x1==3){

                                        //System.out.println("enpassant right");

                                        board[x2][y2]=board[x1][y1];
                                        board[x2][y2].x=x2;
                                        board[x2][y2].y=y2;
                                        board[x1][y1]=null;
                                        board[x1][y1+1]=null;

                                        return true;
                                    }
                                }
                                else if(y2<y1){
                                    if(board[x2][y2]==null &&
                                            board[x1][y1-1].color != board[x1][y1].color &&
                                            board[x1][y1-1].special==true &&
                                            board[x1][y1-1].type=='p' &&
                                            x1==3){

                                        //System.out.println("enpassant left");

                                        board[x2][y2]=board[x1][y1];
                                        board[x2][y2].x=x2;
                                        board[x2][y2].y=y2;
                                        board[x1][y1]=null;
                                        board[x1][y1-1]=null;

                                        return true;
                                    }

                                }


                                else if(board[x2][y2]==null || board[x1][y1].color==board[x2][y2].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    if(x2==0){
                                        if(promotion=='N'){
                                            board[x2][y2]= new Knight(false, x2,y2);
                                        }
                                        else if(promotion=='B'){
                                            board[x2][y2]= new Bishop(false, x2,y2);
                                        }
                                        else if(promotion=='R'){
                                            board[x2][y2]= new Rook(false, x2,y2);
                                        }
                                        else{
                                            board[x2][y2]= new Queen(false, x2,y2);
                                        }
                                    }
                                    return true;
                                }

                            }
                            else{
                                return false;
                            }
                        }
                    }
                    else{//Black pawns
                        System.out.println("rofl");
                        if(y1==y2 && (x2-2)==x1 && x1==1){
                            if(board[x2][y2]==null && board[x2-1][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                board[x2][y2].special=true;
                                //System.out.println("initial double move");
                                return true;
                            }
                            else{
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                        }
                        else if(y1==y2 && (x2-1==x1)){
                            if(board[x2][y2]==null){
                                board[x2][y2]=board[x1][y1];
                                board[x2][y2].x=x2;
                                board[x2][y2].y=y2;
                                board[x1][y1]=null;
                                if(x2==7){
                                    if(promotion=='N'){
                                        board[x2][y2]= new Knight(true, x2,y2);
                                    }
                                    else if(promotion=='B'){
                                        board[x2][y2]= new Bishop(true, x2,y2);
                                    }
                                    else if(promotion=='R'){
                                        board[x2][y2]= new Rook(true, x2,y2);
                                    }
                                    else{
                                        board[x2][y2]= new Queen(true, x2,y2);
                                    }
                                }
                                return true;
                            }
                            else{
                                System.out.println("Illegal move, try again");
                                return false;
                            }
                        }
                        else if(x2-1==x1){
                            if(Math.abs(y1-y2)==1){

                                //implementing enpassant
                                if(y2>y1){
                                    if(board[x2][y2]==null &&
                                            board[x1][y1+1].color != board[x1][y1].color &&
                                            board[x1][y1+1].special==true &&
                                            x1==4){

                                        //System.out.println("enpassant right");

                                        board[x2][y2]=board[x1][y1];
                                        board[x2][y2].x=x2;
                                        board[x2][y2].y=y2;
                                        board[x1][y1]=null;
                                        board[x1][y1+1]=null;

                                        return true;
                                    }
                                }
                                else if(y2<y1){
                                    if(board[x2][y2]==null &&
                                            board[x1][y1-1].color != board[x1][y1].color &&
                                            board[x1][y1-1].special==true &&
                                            x1==4){

                                        //System.out.println("enpassant left");

                                        board[x2][y2]=board[x1][y1];
                                        board[x2][y2].x=x2;
                                        board[x2][y2].y=y2;
                                        board[x1][y1]=null;
                                        board[x1][y1-1]=null;

                                        return true;
                                    }

                                }


                                else





                                if(board[x2][y2]==null || board[x1][y1].color==board[x2][y2].color){
                                    System.out.println("Illegal move, try again");
                                    return false;
                                }
                                else{
                                    board[x2][y2]=board[x1][y1];
                                    board[x2][y2].x=x2;
                                    board[x2][y2].y=y2;
                                    board[x1][y1]=null;
                                    if(x2==7){
                                        if(promotion=='N'){
                                            board[x2][y2]= new Knight(true, x2,y2);
                                        }
                                        else if(promotion=='B'){
                                            board[x2][y2]= new Bishop(true, x2,y2);
                                        }
                                        else if(promotion=='R'){
                                            board[x2][y2]= new Rook(true, x2,y2);
                                        }
                                        else{
                                            board[x2][y2]= new Queen(true, x2,y2);
                                        }
                                    }
                                    return true;
                                }

                            }
                            else{
                                return false;
                            }
                        }
                    }
                }
                else{
                    System.out.println("illegal move, try again");
                    return false;
                }
        }
        System.out.println("Illegal move, try again");
        return false;
    }
}