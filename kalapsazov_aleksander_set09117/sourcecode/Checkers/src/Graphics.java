import java.util.Vector;

public class Graphics {
    public void hLine() {
        System.out.println("    _______________________________________________");
    }

    public void vLine() {
        System.out.print("|");
    }

    public void cIndex() {
        System.out.print("   ");
        for(int c = 0; c<8; c++)
        {
            System.out.print("   " + c + "  " );
        }
        System.out.println();
    }

    public void rIndex(int i) {
        System.out.print(" " + i + " ");
    }


    public static void DisplayGreetings(Color color)
    {
        Game.board.Display();
        System.out.println("___________________________________________________________________________");

        if (color.equals(Color.white)){
            System.out.println("White player won!!!");
            System.out.println(
                    "#_______AAAA_______________AAAA________\n" +
                    "#       VVVV               VVVV        \n" +
                    "#       (__)               (__)\n" +
                    "#        \\ \\               / /\n" +
                    "#         \\ \\   \\\\|||//   / /\n" +
                    "#          > \\   _   _   / <\n" +
                    "#          > \\ / \\ / \\ / <\n" +
                    "#            > \\\\_o_o_// <\n" +
                    "#             > ( (_) ) <\n" +
                    "#              >|     |<\n" +
                    "#             / |\\___/| \\\n" +
                    "#             / (_____) \\\n" +
                    "#             /         \\\n" +
                    "#              /   o   \\\n" +
                    "#               ) ___ (   \n" +
                    "#              / /   \\ \\  \n" +
                    "#             ( /     \\ )\n" +
                    "#             ><       ><\n" +
                    "#            ///\\     /\\\\\\\n" +
                    "#            '''       '''");
        }
        else{
            System.out.println("Black player won!!!");
            System.out.println(
                    "#_______AAAA_______________AAAA________\n" +
                    "#       VVVV               VVVV        \n" +
                    "#       (__)               (__)\n" +
                    "#        \\ \\               / /\n" +
                    "#         \\ \\   \\\\|||//   / /\n" +
                    "#          > \\   _   _   / <\n" +
                    "#          > \\ / \\ / \\ / <\n" +
                    "#            > \\\\_o_o_// <\n" +
                    "#             > ( (_) ) <\n" +
                    "#              >|     |<\n" +
                    "#             / |\\___/| \\\n" +
                    "#             / (_____) \\\n" +
                    "#             /         \\\n" +
                    "#              /   o   \\\n" +
                    "#               ) ___ (   \n" +
                    "#              / /   \\ \\  \n" +
                    "#             ( /     \\ )\n" +
                    "#             ><       ><\n" +
                    "#            ///\\     /\\\\\\\n" +
                    "#            '''       '''");
        }
    }

    public static void DisplayMoveSeq(Vector<Move> moveSeq){
        for(Move m:moveSeq)
        {
            m.display();
            System.out.print(" ");
        }
        System.out.println();
    }
}
