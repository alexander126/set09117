/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of WhiteNavi Class: public class supporting
 * White class
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */

public class WhiteNavi {
    public static Move whiteMoveFL(int r, int c, Board board){
        Move forwardLeft = null;
        if( r<Board.row-1 && c>=1 &&
                board.cellP[r+1][c-1] == CellProperty.empty
                )
        {
            forwardLeft = new Move(r,c, r+1, c-1);
        }
        return forwardLeft;
    }

    // Forward Left Capture for White
    public static Move whiteCaptFL(int r, int c, Board board)
    {
        Move forwardLeftCapture = null;

        if(r<Board.row-2 && c>=2 &&
                (
                        board.cellP[r+1][c-1].equals(CellProperty.black)
                                || board.cellP[r+1][c-1].equals(CellProperty.blackk)
                )
                && board.cellP[r+2][c-2].equals(CellProperty.empty)
                )
        {
            forwardLeftCapture = new Move(r,c,r+2,c-2);
            //System.out.println("Forward Left Capture");
        }

        return forwardLeftCapture;
    }

    public static Move whiteMoveFR(int r, int c, Board board){
        Move forwardRight = null;
        if(r<Board.row-1 && c<Board.column-1 &&
                board.cellP[r+1][c+1] == CellProperty.empty
                )
        {
            forwardRight = new Move(r,c, r+1, c+1);
        }
        return forwardRight;
    }

    // Forward Right Capture for White
    public static Move whiteCaptFR(int r, int c, Board board)
    {
        Move forwardRightCapture = null;

        if(r<Board.row-2 && c<Board.column-2 &&
                (
                        board.cellP[r+1][c+1].equals(CellProperty.black)
                                || board.cellP[r+1][c+1].equals(CellProperty.blackk)
                )
                && board.cellP[r+2][c+2].equals(CellProperty.empty)
                )
        {
            forwardRightCapture = new Move(r,c,r+2,c+2);
            //System.out.println("Forward Right Capture");
        }

        return forwardRightCapture;
    }

    public static Move whiteMoveBL(int r, int c, Board board){
        Move backwardLeft = null;
        if( r>=1 && c>=1 &&
                board.cellP[r-1][c-1] == CellProperty.empty
                )
        {
            backwardLeft = new Move(r,c, r-1, c-1);
        }
        return backwardLeft;
    }

    // Backward Left Capture for White
    public static Move whiteCaptBL(int r, int c, Board board)
    {

        Move backwardLeftCapture = null;

        if(r>=2 && c>=2 && (
                board.cellP[r-1][c-1].equals(CellProperty.black)
                        || board.cellP[r-1][c-1].equals(CellProperty.blackk)
        )
                && board.cellP[r-2][c-2].equals(CellProperty.empty)
                )
        {
            backwardLeftCapture = new Move(r,c,r-2,c-2);
            //System.out.println("Backward Left Capture");
        }

        return backwardLeftCapture;
    }

    public static Move whiteMoveBR(int r, int c, Board board){
        Move backwardRight = null;
        if(r>=1 && c<Board.column-1 &&
                board.cellP[r-1][c+1] == CellProperty.empty
                )
        {
            backwardRight = new Move(r,c,r-1,c+1);
        }
        return backwardRight;
    }
    // Backward Right Capture for White
    public static Move whiteCaptBR(int r, int c, Board board)
    {
        Move backwardRightCapture = null;

        if(r>=2 && c<Board.column-2 && (
                board.cellP[r-1][c+1].equals(CellProperty.black) ||
                        board.cellP[r-1][c+1].equals(CellProperty.blackk)
        )
                && board.cellP[r-2][c+2].equals(CellProperty.empty)
                )
        {
            backwardRightCapture = new Move(r,c,r-2,c+2);
            //System.out.println("Backward Right Capture");
        }

        return backwardRightCapture;
    }
}
