import java.lang.Object;
import java.util.Vector;
public class Board extends Graphics{
    int blackPiece;
    int whitePiece;

    public static final int rows = 8;
    public static final int columns = 8;

    CellProperty cellP [][];

    Board(){

       this.whitePiece = 12;
       this.blackPiece = 12;

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
        this.whitePiece = 12;
        this.blackPiece = 12;

        this.cellP = new CellProperty[rows][columns];
        for(int i = 0;i < rows; i++) {
            System.arraycopy(board[i], 0, this.cellP[i], 0, columns);
        }
    }
    public void placeAMove(int r1,int r2,int c1,int c2){

        this.cellP[r1][c1] = CellProperty.empty;
        this.cellP[r2][c2] = this.cellP[r1][c1];

        if(this.cellP[r2][c2].equals(CellProperty.white) && r2 == rows-1){
            this.cellP[r2][c2] = CellProperty.whiteK;
        }else if(this.cellP[r2][c2].equals(CellProperty.black) && r2 == 0){
            this.cellP[r2][c2] = CellProperty.blackK;
        }
    }

    public void whitePieceLogic(int r1,int r2,int c1,int c2){

        assert(Math.abs(r2-r1) == 2 && Math.abs(c2-c1) == 2);

        MoveDir dir = r2>r1?(c2>c1?MoveDir.right_forward:MoveDir.left_forward)
                :(c2>c1?MoveDir.right_backward:MoveDir.left_backward);
        switch(dir){
            case right_forward:
                this.cellP[r1+1][c1+1] = CellProperty.empty;
                break;
            case right_backward:
                this.cellP[r1-1][c1+1] = CellProperty.empty;
                break;
            case left_forward:
                this.cellP[r1+1][c1-1] = CellProperty.empty;
                break;
            case left_backward:
                this.cellP[r1-1][c1-1] = CellProperty.empty;
                break;
        }

        this.blackPiece--;
        this.placeAMove(r1,c1,r2,c2);
    }
    public void Display(){
        this.hLine();
        this.columnIndex();

        for (int i = rows -1; i >=0; i--){
            this.rowIndex(i);
            this.vLine();
            for(int j = 0;j < columns; j++){
                System.out.println(this.PieceAppearance(i,j));
            }
            this.rowIndex(i);
            this.hLine();
        }
        this.columnIndex();

    }
    public void columnIndex(){
        System.out.println("   ");
        for(int c = 0; c<columns;c++){
            System.out.println(" " + c + " ");
        }
        System.out.println();
    }
    public void rowIndex(int i){
        System.out.print(" " + i + " ");
    }
    private String PieceAppearance(int i,int j){
        assert(i>0 && i < rows && j > 0 && j < columns);
        String string = new String();

        if(this.cellP[i][j] == CellProperty.invalid){
            string = "     ";
        } else if(this.cellP[i][j] == CellProperty.empty){
            string = "  -  ";

        }else if(this.cellP[i][j] == CellProperty.white){
            string = "  W  ";
        }else if(this.cellP[i][j] == CellProperty.whiteK){
            string = "  wK  ";
        }else if(this.cellP[i][j] == CellProperty.black){
            string = "  B  ";
        }else if(this.cellP[i][j] == CellProperty.blackK){
            string = "  bK  ";
        }
        return string;
    }}



