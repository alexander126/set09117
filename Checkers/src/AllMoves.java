public class AllMoves {

    private Move moveList[];
    private int totalMoves;
    private int movesLimit;

    public void allMoves (int moveLimitParam)
    {
        movesLimit = moveLimitParam;
        moveList = new Move[movesLimit];
    }
    public int getTotalMoves() {
        return totalMoves;
    }

    public void reset(){
        totalMoves = 0;
    }
    public Move getMove(int index)
    {
        return moveList[index];
    }
    public void clearRedo(int currentMove)
    {
        totalMoves = currentMove;
    }

    public void copy(AllMoves dest)
    {
        for (int i = 0; i < totalMoves; i++) {
            dest.moveList[i] = moveList[i];
        }
        dest.totalMoves = totalMoves;
    }
    public void addMove(int moveNumber, Move m) {

        moveList[moveNumber] = m;
        moveNumber++;

        if (totalMoves < moveNumber) {
            totalMoves = moveNumber;
        }
    }
}
