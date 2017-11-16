/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Board Class: public class implementing
 * the appearance and logic behind the game board.
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */
import java.util.Vector;

public class Board extends Graphics {

    int blackPieces;
    int whitePieces;
    //protected AllMoves allmoves;
    //protected Move legalMoves[];

    static final int row = 8;
    static final int column = 8;
    CellProperty cellP[][];

    //Initializing the board
    Board(){
        this.blackPieces = this.whitePieces = 12;

        this.cellP = new CellProperty[][]{
                {CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid},
                {CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white},
                {CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid, CellProperty.white, CellProperty.invalid},
                {CellProperty.invalid, CellProperty.empty, CellProperty.invalid, CellProperty.empty, CellProperty.invalid, CellProperty.empty, CellProperty.invalid, CellProperty.empty},
                {CellProperty.empty, CellProperty.invalid, CellProperty.empty, CellProperty.invalid, CellProperty.empty, CellProperty.invalid, CellProperty.empty, CellProperty.invalid},
                {CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black},
                {CellProperty.black,CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid},
                {CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black, CellProperty.invalid, CellProperty.black}
        };
    }

    Board(CellProperty[][] board){
        this.blackPieces = this.whitePieces = 12;

        this.cellP = new CellProperty[row][column];
        for(int i = 0; i<row; i++)
        {
            System.arraycopy(board[i], 0, this.cellP[i], 0, column);
        }
    }

    //logic behind placing a move
    public void placeAMove(int r1, int c1, int r2, int c2)
    {
        this.cellP[r2][c2] = this.cellP[r1][c1];
        this.cellP[r1][c1] = CellProperty.empty;

        if(this.cellP[r2][c2].equals(CellProperty.white) && r2==row-1)
        {
            this.cellP[r2][c2] = CellProperty.whitek;
        }
        else if(this.cellP[r2][c2].equals(CellProperty.black) && r2==0)
        {
            this.cellP[r2][c2] = CellProperty.blackk;
        }
    }

    public void whitePieceLogic(int r1, int c1, int r2, int c2)
    {
        assert(Math.abs(r2-r1)==2 && Math.abs(c2-c1)==2);

        MoveDir dir = r2>r1?(c2>c1?MoveDir.forwardRight:MoveDir.forwardLeft)
                      :(c2>c1?MoveDir.backwardRight:MoveDir.backwardLeft);

        switch(dir)
        {
            case forwardLeft:
                this.cellP[r1+1][c1-1] = CellProperty.empty;
                break;
            case forwardRight:
                this.cellP[r1+1][c1+1] = CellProperty.empty;
                break;
            case backwardLeft:
                this.cellP[r1-1][c1-1] = CellProperty.empty;
                break;
            case backwardRight:
                this.cellP[r1-1][c1+1] = CellProperty.empty;
                break;
        }
        this.blackPieces--;

        this.placeAMove(r1, c1, r2, c2);
    }


    public void blackPieceLogic(int r1, int c1, int r2, int c2)
    {
        assert(Math.abs(r2-r1)==2 && Math.abs(c2-c1)==2);

        MoveDir dir = r2<r1?(c2<c1?MoveDir.forwardRight:MoveDir.forwardLeft)
                      :(c2<c1?MoveDir.backwardRight:MoveDir.backwardLeft);

        switch(dir)
        {
            case forwardLeft:
                this.cellP[r1-1][c1+1] = CellProperty.empty;
                break;
            case forwardRight:
                this.cellP[r1-1][c1-1] = CellProperty.empty;
                break;
            case backwardLeft:
                this.cellP[r1+1][c1+1] = CellProperty.empty;
                break;
            case backwardRight:
                this.cellP[r1+1][c1-1] = CellProperty.empty;
                break;
        }
        this.whitePieces--;

        this.placeAMove(r1, c1, r2, c2);
    }


    public void validMovesW(Move move){
        int r1 = move.initialRow;
        int c1 = move.initialCol;
        int r2 = move.finalRow;
        int c2 = move.finalCol;

        if((Math.abs(r2-r1)==2 && Math.abs(c2-c1)==2))
        {
            whitePieceLogic(r1, c1, r2, c2);

        }else
        {
            placeAMove(r1, c1, r2, c2);
        }
    }

    public void validMovesB(Move move){
        int r1 = move.initialRow;
        int c1 = move.initialCol;
        int r2 = move.finalRow;
        int c2 = move.finalCol;

        if(Math.abs(r2-r1)==2 && Math.abs(c2-c1)==2)
        {
            blackPieceLogic(r1, c1, r2, c2);
        }else
        {
            placeAMove(r1, c1, r2, c2);
        }
    }

    //Displaying the board
    public void Display()
    {
        this.cIndex();
        this.hLine();

        for(int i = row-1; i >=0; i--)
        {
            this.rIndex(i);
            this.vLine();

            for(int j = 0; j< column; j++)
            {
                System.out.print(this.pieceAppearance(i,j));
                this.vLine();
            }
            this.rIndex(i);
            System.out.println();
            this.hLine();
        }
        this.cIndex();
        System.out.println();
    }

    //Providing appearance of the pieces on the board
    private String pieceAppearance(int i, int j) {
        assert(i>0 && i<row && j>0 && j< column);
        String str = new String();

        if(this.cellP[i][j] == CellProperty.invalid)
        {
            str = "     ";
        }
        else if(this.cellP[i][j] == CellProperty.empty)
        {
            str = "  _  ";
        }
        else if(this.cellP[i][j] == CellProperty.white)
        {
            str = "  W  ";
        }
        else if(this.cellP[i][j] == CellProperty.black)
        {
            str = "  B  ";
        }
        else if(this.cellP[i][j] == CellProperty.whitek)
        {
            str = "  Kw ";
        }
        else if(this.cellP[i][j] == CellProperty.blackk)
        {
            str = "  Kb ";
        }
        return str;
    }

    public Board duplicate(){
        Board newBoard = new Board(this.cellP);
        newBoard.blackPieces = this.blackPieces;
        newBoard.whitePieces = this.whitePieces;

        return newBoard;
    }

    //Checks if the game is over
    public boolean isGameFinished() {
        return (this.blackPieces==0 || this.whitePieces == 0)?true:false;
    }

    //Checks if the ended draw
    public boolean isGameDraw(Color color){

        Vector<Vector<Move>> possibleMoveSeq = AlphaBeta.getMoves(this.duplicate(), color);

        if(possibleMoveSeq.isEmpty())
        {
            return true;

        }else
        {
            return false;
        }
    }

    //Checks if white player is a winner
    public boolean isWhiteWinner(){
        boolean res = false;
        if(this.blackPieces == 0)
        {
            res = true;
        }
        return res;
    }

    //Checks if black player is a winner
    public boolean isBlackWinner(){
        boolean res = false;
        if(this.whitePieces == 0)
        {
            res = true;
        }
        return res;
    }
}