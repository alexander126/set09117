import java.util.Vector;

public class Graphics {
    public void DrawHorizontalLine() {
        System.out.println("    _______________________________________________");
    }

    public void DrawVerticalLine() {
        System.out.print("|");
    }
    public static void PrintSeparator(char ch)
    {
        switch(ch){
            case '_':
                System.out.println("___________________________________________________________________________");
                break;
            case '-':
                System.out.println("---------------------------------------------------------------------------");
                break;
            case '#':
                System.out.println("###########################################################################");
                break;
        }
    }

    public static void DisplayGreetings(Color color) {

        Game.board.Display();
        PrintSeparator('_');

        if (color.equals(Color.white)){
            System.out.println("Congrats!!!!!!!!!! White has Won.");
        }
        else{
            System.out.println("Congrats!!!!!!!!!! Black has Won.");
        }
    }

    public static void DisplayMoveSeq(Vector<Move> moveSeq){
        for(Move m:moveSeq){
            m.display();
            System.out.print(", ");
        }

        System.out.println();
    }
}
