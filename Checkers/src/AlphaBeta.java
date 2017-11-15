/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 * Data Structures module.
 *
 * Description of AlphaBeta Class: public class applying algorithm which supports
 * the decision making of the computer in making the best move possible.
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */

import java.util.List;
import java.util.Vector;

public class AlphaBeta {
    public final static int VICTORY = 1000;
    public final static int KING_PIECE = 20;
    public final static int PIECE = 10;

    static int MAX_DEPTH = 6;

    public static void makeNextWhiteMoves(){

        Vector<Move> moveSeq = new Vector<Move>();

        alphaBeta(Game.board, Color.white, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, moveSeq);

        for(Move m:moveSeq)
        {
            Game.board.validMovesW(m);
        }

        System.out.print("Andy the Ape's Move was ");
        Input.DisplayMoveSeq(moveSeq);
        System.out.println();
    }


    public static void makeNextBlackMoves(){

        Vector<Move> moveSeq = new Vector<Move>();

        alphaBeta(Game.board, Color.black, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, moveSeq);

        for(Move m:moveSeq)
        {
            Game.board.validMovesB(m);
        }

        System.out.print("Andy the Ape's Move was ");
        Input.DisplayMoveSeq(moveSeq);
        System.out.println();


    }

    private static int alphaBeta(Board board, Color player, int depth, int alpha, int beta, Vector<Move> resultMoveSeq){

        if(!isDepthReached(board, player, depth)){
            int value = evaluateBoard(board, player);
            return value;
        }

        Vector<Vector<Move>> possibleMoveSeq = getMoves(board, player);
        Vector<Board> boardD = boardDepth(board, possibleMoveSeq, player);

        Vector<Move> bestMoveSeq = null;

        if(player == Color.white){

            for(int i=0; i<boardD.size(); i++){

                Board b = boardD.get(i);
                Vector<Move> moveSeq = possibleMoveSeq.get(i);

                int value = alphaBeta(b, Color.black, depth+1, alpha, beta, resultMoveSeq);

                if(value > alpha){
                    alpha = value;
                    bestMoveSeq = moveSeq;
                }
                if(alpha>beta){
                    break;
                }
            }

            if(depth == 0 && bestMoveSeq!=null){
                resultMoveSeq.addAll(bestMoveSeq);
            }

            return alpha;

        }else{
            assert(player == Color.black);

            for(int i=0; i<boardD.size(); i++){

                Board b = boardD.get(i);
                Vector<Move> moveSeq = possibleMoveSeq.get(i);

                int value = alphaBeta(b, Color.white, depth+1, alpha, beta, resultMoveSeq);
                if(value < beta){
                    bestMoveSeq = moveSeq;
                    beta = value;
                }
                if(alpha>beta){
                    break;
                }
            }

            if(depth == 0 && bestMoveSeq!=null){
                resultMoveSeq.addAll(bestMoveSeq);
            }

            return beta;
        }
    }

    public static Vector<Vector<Move>> getMoves(Board board, Color player){

        Vector<Vector<Move>> outerVector = new Vector<>();

        if(player == Color.black){

            List<Move> moves = null;
            moves = Black.blackPossibleJumps(board);

            if(moves.isEmpty()){
                moves = Black.blackNonJumpMoves(board);

                for(Move m:moves){
                    Vector<Move> innerVector = new Vector<>();
                    innerVector.add(m);
                    outerVector.add(innerVector);
                }

            }else{
                for(Move m:moves){

                    int r = m.finalRow;
                    int c = m.finalCol;
                    Vector<Move> innerVector = new Vector<>();

                    innerVector.add(m);

                    Board boardCopy = board.duplicate();
                    boardCopy.validMovesB(m);
                    blackMoveRecursion(boardCopy, outerVector, innerVector, r, c);

                    innerVector.remove(m);

                }
            }

        }else if(player == Color.white){

            List<Move> moves = null;

            moves = White.whitePossibleJumps(board);
            if(moves.isEmpty()){
                moves = White.whiteNonJumpMoves(board);
                for(Move m:moves){
                    Vector<Move> innerVector = new Vector<>();
                    innerVector.add(m);
                    outerVector.add(innerVector);
                }
            }else{
                for(Move m:moves){

                    int r = m.finalRow;
                    int c = m.finalCol;
                    Vector<Move> innerVector = new Vector<>();

                    innerVector.add(m);

                    Board boardCopy = board.duplicate();
                    boardCopy.validMovesW(m);
                    whiteMoveRecursion(boardCopy, outerVector, innerVector, r, c);

                    innerVector.remove(m);

                }

            }
        }
        return outerVector;
    }

    private static void whiteMoveRecursion(Board board, Vector<Vector<Move>> outerVector, Vector<Move> innerVector, int r, int c){

        List<Move> jumps = White.whiteJumps(r, c, board);

        if(jumps.isEmpty()){
            Vector<Move> innerCopy = (Vector<Move>)innerVector.clone();
            outerVector.add(innerCopy);
            return;

        }else{
            for(Move m:jumps){

                Board boardCopy = board.duplicate();
                boardCopy.validMovesW(m);

                innerVector.add(m);
                whiteMoveRecursion(boardCopy, outerVector, innerVector, m.finalRow, m.finalCol);
                innerVector.remove(m);

            }
        }


    }

    private static void blackMoveRecursion(Board board, Vector<Vector<Move>> outerVector, Vector<Move> innerVector, int r, int c){

        List<Move> jumps = Black.blackJumps(r, c, board);

        if(jumps.isEmpty()){
            Vector<Move> innerCopy = (Vector<Move>)innerVector.clone();
            outerVector.add(innerCopy);
            return;

        }else{
            for(Move m:jumps){

                Board boardCopy = board.duplicate();
                boardCopy.validMovesB(m);

                innerVector.add(m);
                blackMoveRecursion(boardCopy, outerVector, innerVector, m.finalRow, m.finalCol);
                innerVector.remove(m);

            }
        }
    }


    private static boolean isDepthReached(Board board, Color player, int depth){
        boolean res = true;
        if(board.isGameFinished()  || board.isGameDraw(player)){
            res = false;
        }
        if(depth == MAX_DEPTH){
            res = false;
        }
        return res;
    }


    public static Vector<Board> boardDepth(Board board, Vector<Vector<Move>> possibleMoveSeq, Color player){
        Vector<Board> boardD= new Vector<>();

        for(Vector<Move> moveSeq:possibleMoveSeq){
            Board boardCopy = board.duplicate();
            for(Move move:moveSeq){
                if(player == Color.black){
                    boardCopy.validMovesB(move);

                }else{
                    boardCopy.validMovesW(move);
                }
            }
            boardD.add(boardCopy);
        }

        return boardD;
    }
    public static int evaluateBoard(Board board, Color player) {
        int value = 0;

        if (player == Color.black) {
            value = blackValue(board);
        } else {
            value = whiteValue(board);
        }

        return value;
    }

    private static int whiteValue(Board board) {

        int wValue = 0;
        if (board.isWhiteWinner()) {
            wValue += VICTORY;
            return wValue;
        } else {
            wValue = assignValues(board);
            wValue /= board.blackPieces;
        }

        return wValue;
    }

    private static int blackValue(Board board) {

        int bValue = 0;
        if (board.isBlackWinner()) {
            bValue -= VICTORY;
            return bValue;
        } else {
            bValue = assignValues(board);
            bValue /= board.whitePieces;
        }

        return bValue;
    }

    private static int assignValues(Board board) {

        int value = 0;
        for (int r = 0; r < Board.row; r++) {
            int c = (r % 2 == 0) ? 0 : 1;
            for (; c < Board.column; c += 2) {
                assert (!board.cellP[r][c].equals(CellProperty.invalid));
                CellProperty entry = board.cellP[r][c];

                if (entry == CellProperty.white) {
                    value += PIECE;
                } else if (entry == CellProperty.whitek) {
                    value += KING_PIECE;
                } else if (entry == CellProperty.black) {
                    value -= PIECE;
                } else if (entry == CellProperty.blackk) {
                    value -= KING_PIECE;
                }
            }
        }
        return value;
    }
}
