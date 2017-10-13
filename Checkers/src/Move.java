import java.util.List;

public class Move {
    int firstRow;
    int firstColumn;
    int lastRow;
    int lastColumn;

    Move(int r1, int r2, int c1, int c2){
        this.firstRow = r1;
        this.lastRow = r2;
        this.firstColumn = c1;
        this.lastColumn = c2;
    }

    public boolean placeAMove(Move move){
        return (this.firstRow == move.firstRow
                && this.lastRow == move.lastRow
                && this.firstColumn == move.firstColumn
                && this.lastColumn == move.lastColumn) ? true:false;
    }

    public boolean availableCells(List<Move> moves){
        for (int i = 0; i<moves.size(); i++){
            if(this.placeAMove(moves.get(i))){
                return true;
            }
        }
        return false;
    }
    public void display(){
        System.out.println("("+ this.firstRow + " , " + this.firstColumn + ")" + " (" + this.lastRow + " , " + this.lastColumn + ")");
    }
}
