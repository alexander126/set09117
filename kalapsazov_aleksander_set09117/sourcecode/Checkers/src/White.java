/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of White Class: public class implemeting the logic
 * behind the movement of the white pieces on the board
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class White extends WhiteNavi
{
    static Player player;

    public static void Move()
    {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("\t\tWhite Player Move");
        System.out.println("---------------------------------------------------------------------------");

        if (player.equals(Player.HUMAN))
        {
            Human.placeWhiteMove();
        }
        else
        {
            assert(player.equals(Player.ROBOT));
            AlphaBeta.placeWhiteMove();
        }
    }

    //Store jumps of the white player
    public static List<Move> whiteJumps(int r, int c, Board board){

        List<Move> jumps = new ArrayList<Move>();

        if (board.cellP[r][c].equals(CellProperty.white) || board.cellP[r][c].equals(CellProperty.whitek))
        {
            if (whiteCaptFL(r,c,board)!=null)
                jumps.add(whiteCaptFL(r,c,board));
            if (whiteCaptFR(r,c,board)!=null)
                jumps.add(whiteCaptFR(r,c,board));
        }

        if(board.cellP[r][c].equals(CellProperty.whitek))
        {
            if (whiteCaptBL(r,c,board)!=null)
                jumps.add(whiteCaptBL(r,c,board));
            if (whiteCaptBR(r,c,board)!=null)
                jumps.add(whiteCaptBR(r,c,board));
        }
        return jumps;
    }

    public static List<Move> whitePossibleJumps(Board board){

        List<Move> jumpMoves = new ArrayList<Move>();

        for(int r = 0; r<Board.row; r++)
        {
            int c = (r%2==0)?0:1;
            for(; c<Board.column; c+=2)
            {
                assert(!board.cellP[r][c].equals(CellProperty.invalid));
                if(board.cellP[r][c].equals(CellProperty.white) || board.cellP[r][c].equals(CellProperty.whitek))
                {
                    if (r<Board.row-2)
                    {
                        if (whiteCaptFL(r,c, board)!=null)
                            jumpMoves.add(whiteCaptFL(r,c, board));

                        if (whiteCaptFR(r,c, board)!=null)
                            jumpMoves.add(whiteCaptFR(r,c, board));
                    }
                }

                if(board.cellP[r][c].equals(CellProperty.whitek))
                {
                    if (r>=2)
                    {
                        if (whiteCaptBL(r,c,board)!=null)
                            jumpMoves.add(whiteCaptBL(r,c, board));

                        if (whiteCaptBR(r,c,board)!=null)
                            jumpMoves.add(whiteCaptBR(r,c,board));
                    }
                }
            }
        }
        return jumpMoves;
    }

    //Stores simple moves of the white pieces
    public static Vector<Move> whiteNonJumpMoves(Board board) {

        Vector<Move> normalMoves = new Vector<Move>();

        for(int r = 0; r<Board.row; r++)
        {
            int c = (r%2==0)?0:1;
            for(; c<Board.column; c+=2)
            {
                assert(!board.cellP[r][c].equals(CellProperty.invalid));

                if( board.cellP[r][c].equals(CellProperty.white))
                {
                    Move move = null;
                    move = whiteCaptFL(r, c, board);
                    assert(move == null);
                    move = whiteCaptFR(r, c, board);
                    assert(move == null);

                    move = whiteMoveFL(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }

                    move = whiteMoveFR(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }
                }

                if(board.cellP[r][c] == CellProperty.whitek){
                    Move move = null;
                    move = whiteCaptFL(r, c, board);
                    assert(move == null);
                    move = whiteCaptFR(r, c, board);
                    assert(move == null);

                    move = whiteCaptBL(r, c, board);
                    assert(move == null);
                    move = whiteCaptBR(r, c, board);
                    assert(move == null);

                    move = whiteMoveFL(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }

                    move = whiteMoveFR(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }

                    move = whiteMoveBL(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }

                    move = whiteMoveBR(r, c, board);
                    if(move!=null)
                    {
                        normalMoves.add(move);
                    }
                }
            }
        }
        return normalMoves;
    }

}