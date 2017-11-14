/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Move Class: public class providing
 * funcionallity of the movement of the pieces around
 * the board
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */
import java.util.List;
import java.util.Vector;

public class Move {
    int initialRow;
    int initialCol;
    int finalRow;
    int finalCol;

    Move(int r1, int c1, int r2, int c2)
    {
        this.initialRow = r1;
        this.initialCol = c1;
        this.finalRow = r2;
        this.finalCol = c2;
    }

    public boolean Equals(Move move)
    {
        return (this.initialRow == move.initialRow
                && this.initialCol == move.initialCol
                && this.finalRow == move.finalRow
                && this.finalCol == move.finalCol)?true:false;
    }

    public boolean ExistsInVector(List<Move> moves)
    {
        for (int i = 0; i<moves.size(); i++)
        {
            if (this.Equals(moves.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public void display()
    {
        System.out.print("("+this.initialRow+","+this.initialCol+") -->"+" ("+this.finalRow+", "+this.finalCol+")");
    }
}