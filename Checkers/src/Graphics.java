import java.util.Vector;

public class Graphics {
    public void hLine() {
        System.out.println("    _______________________________________________");
    }

    public void vLine() {
        System.out.print("|");
    }
    public static void gLines(char ch)
    {
        switch(ch){
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
        gLines('_');

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
        for(Move m:moveSeq){
            m.display();
            System.out.print(", ");
        }

        System.out.println();
    }
}
