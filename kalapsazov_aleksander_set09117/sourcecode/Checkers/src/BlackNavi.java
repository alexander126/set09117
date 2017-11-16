/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of BlackNavi Class: public class supporting
 * Black class
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */

public class BlackNavi {

    //Setting up logic behind a move
    public static Move blackMoveFL(int r, int c, Board board){
        Move forwardLeft = null;
        assert(board.cellP[r][c] == CellProperty.black || board.cellP[r][c] == CellProperty.blackk);
        if(r>=1 && c<Board.column-1 &&
           board.cellP[r-1][c+1] == CellProperty.empty)
        {
            forwardLeft = new Move(r,c,r-1, c+1);
        }
        return forwardLeft;
    }

    //Setting up logic behind a move
    public static Move blackCaptFL(int r, int c, Board board) {
        Move forwardLeftCapture = null;
        if(r>=2 && c<Board.column-2 &&
          (board.cellP[r-1][c+1].equals(CellProperty.white) ||
          board.cellP[r-1][c+1].equals(CellProperty.whitek)) &&
          board.cellP[r-2][c+2].equals(CellProperty.empty))
        {
            forwardLeftCapture = new Move(r,c,r-2,c+2);
        }
        return forwardLeftCapture;
    }

    //Setting up logic behind a move
    public static Move blackMoveFR(int r, int c, Board board){
        Move forwardRight = null;
        if(r>=1 && c>=1 &&
          board.cellP[r-1][c-1] == CellProperty.empty)
        {
            forwardRight = new Move(r,c, r-1, c-1);
        }
        return forwardRight;
    }

    //Setting up logic behind a move
    public static Move blackCaptFR(int r, int c, Board board){
        Move forwardRightCapture = null;
        if(r>=2 && c>=2 &&
          (board.cellP[r-1][c-1].equals(CellProperty.white) ||
          board.cellP[r-1][c-1].equals(CellProperty.whitek)) &&
          board.cellP[r-2][c-2].equals(CellProperty.empty))
        {
            forwardRightCapture = new Move(r,c,r-2,c-2);
        }
        return forwardRightCapture;
    }

    //Setting up logic behind a move
    public static Move blackMoveBL(int r, int c, Board board){
        Move backwardLeft = null;
        assert(board.cellP[r][c].equals(CellProperty.blackk));
        if(r<Board.row-1 && c<Board.column-1 &&
          board.cellP[r+1][c+1] == CellProperty.empty)
        {
            backwardLeft = new Move(r,c, r+1, c+1);
        }
        return backwardLeft;
    }

    //Setting up logic behind a move
    public static Move blackCaptBL(int r, int c, Board board){
        Move backwardLeftCapture = null;
        if(r<Board.row-2 && c<Board.column-2 &&
          (board.cellP[r+1][c+1].equals(CellProperty.white) ||
          board.cellP[r+1][c+1].equals(CellProperty.whitek)) &&
          board.cellP[r+2][c+2].equals(CellProperty.empty))
        {
            backwardLeftCapture = new Move(r,c,r+2,c+2);
        }
        return backwardLeftCapture;
    }

    //Setting up logic behind a move
    public static Move blackMoveBR(int r, int c, Board board){
        Move backwardRight = null;
        assert(board.cellP[r][c].equals(CellProperty.blackk));
        if(r<Board.row-1 && c>=1 &&
           board.cellP[r+1][c-1].equals(CellProperty.empty))
        {
            backwardRight = new Move(r,c, r+1, c-1);
        }
        return backwardRight;
    }

    //Setting up logic behind a move
    public static Move blackCaptBR(int r, int c, Board board){
        Move backwardRightCapture = null;
        if(r<Board.row-2 && c>=2 &&
          (board.cellP[r+1][c-1].equals(CellProperty.white) ||
          board.cellP[r+1][c-1].equals(CellProperty.whitek)) &&
          board.cellP[r+2][c-2].equals(CellProperty.empty))
        {
            backwardRightCapture = new Move(r,c,r+2,c-2);
        }
        return backwardRightCapture;
    }
}
