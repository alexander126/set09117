/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Black Class: public class implemeting the logic
 * behind the movement of the black pieces on the board
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Black extends BlackNavi {

    static Player player;

    public static void Move(){

        Input.gLines('-');
        System.out.println("\t\tBlack player move");
        Input.gLines('-');

        if (player.equals(Player.HUMAN)){

            Human.makeNextBlackMoves();

        }
        else{

            assert(player.equals(Player.ROBOT));
            AlphaBeta.makeNextBlackMoves();

        }
    }



    public static List<Move> blackJumps(int r, int c, Board board)
    {
        List<Move> jumps = new ArrayList<Move>();

        if (board.cellP[r][c].equals(CellProperty.black) || board.cellP[r][c].equals(CellProperty.blackk))
        {
            if (blackCaptFL(r,c,board)!=null)
                jumps.add(blackCaptFL(r,c,board));
            if (blackCaptFR(r,c,board)!=null)
                jumps.add(blackCaptFR(r,c,board));
        }

        if(board.cellP[r][c].equals(CellProperty.blackk))
        {
            if (blackCaptBL(r,c,board)!=null)
                jumps.add(blackCaptBL(r,c,board));
            if (blackCaptBR(r,c,board)!=null)
                jumps.add(blackCaptBR(r,c,board));
        }

        return jumps;
    }

    public static List<Move> blackPossibleJumps(Board board)
    {
        List<Move> jumpMoves = new ArrayList<Move>();


        for(int r = 0; r<Board.row; r++)
        {

            int c = (r%2==0)?0:1;
            for(; c<Board.column; c+=2)
            {
                assert(!board.cellP[r][c].equals(CellProperty.invalid));

                if(
                        board.cellP[r][c].equals(CellProperty.black) ||
                                board.cellP[r][c].equals(CellProperty.blackk)
                        )
                {

                    if (r>=2)
                    {
                        if (blackCaptFL(r,c, board)!=null)
                            jumpMoves.add(blackCaptFL(r,c, board));

                        if (blackCaptFR(r,c, board)!=null)
                            jumpMoves.add(blackCaptFR(r,c, board));
                    }
                }

                if(board.cellP[r][c].equals(CellProperty.blackk))
                {
                    if (r<Board.row-2)
                    {
                        if (blackCaptBL(r,c,board)!=null)
                            jumpMoves.add(blackCaptBL(r,c, board));

                        if (blackCaptBR(r,c,board)!=null)
                            jumpMoves.add(blackCaptBR(r,c,board));
                    }
                }
            }
        }

        return jumpMoves;
    }

    public static Vector<Move> blackNonJumpMoves(Board board){
        Vector<Move> normalMoves = new Vector<Move>();

        for(int r = 0; r<Board.row; r++)
        {
            int c = (r%2==0)?0:1;
            for(; c<Board.column; c+=2)
            {
                assert(!board.cellP[r][c].equals(CellProperty.invalid));


                if( board.cellP[r][c].equals(CellProperty.black) ){

                    Move move = null;
                    move = blackCaptFL(r, c, board);
                    assert(move == null);
                    move = blackCaptFR(r, c, board);
                    assert(move == null);

                    move = blackMoveFL(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }

                    move = blackMoveFR(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }
                }

                if(board.cellP[r][c] == CellProperty.blackk){
                    Move move = null;
                    move = blackCaptFL(r, c, board);
                    assert(move == null);
                    move = blackCaptFR(r, c, board);
                    assert(move == null);

                    move = blackCaptBL(r, c, board);
                    assert(move == null);
                    move = blackCaptBR(r, c, board);
                    assert(move == null);

                    move = blackMoveFL(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }

                    move = blackMoveFR(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }

                    move = blackMoveBL(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }

                    move = blackMoveBR(r, c, board);
                    if(move!=null){
                        normalMoves.add(move);
                    }

                }


            }
        }

        return normalMoves;
    }

}