/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Input Class: public class taking and
 * processing user input, while interacting with the user.
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input extends Graphics {
    public static String gameType()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Ask for user input
        gLines('#');
        System.out.println("               _\n" +
                "#            ,.-\" \"-.,\n" +
                "#          /   ===   \\\n" +
                "#          /  =======  \\\n" +
                "#       __|  (o)   (0)  |__      \n" +
                "#      / _|    .---.    |_ \\         \n" +
                "#     | /.----/ O O \\----.\\ |       \n" +
                "#      \\/     |     |     \\/        \n" +
                "#      |                   |            \n" +
                "#      |                   |           \n" +
                "#      |                   |          \n" +
                "#      _\\   -.,_____,.-   /_         \n" +
                "#  ,.-\"  \"-.,_________,.-\"  \"-.,\n" +
                "# /          |       |          \\  \n" +
                "#|           l.     .l           | \n" +
                "#|            |     |            |\n" +
                "#l.           |     |           .l             \n" +
                "# |           l.   .l           | \\,     \n" +
                "# l.           |   |           .l   \\,    \n" +
                "#  |           |   |           |      \\,  \n" +
                "#  l.          |   |          .l        |\n" +
                "#   |          |   |          |         |\n" +
                "#   |          |---|          |         |\n" +
                "#   |          |   |          |         |\n" +
                "#   /\"-.,__,.-\"\\   /\"-.,__,.-\"\\\"-.,_,.-\"\\\n" +
                "#  |            \\ /            |         |\n" +
                "#  |             |             |         |\n" +
                "#  \\__|__|__|__/ \\__|__|__|__/ \\_|__|__/");
        gLines('-');
        System.out.println("Welcome to Checkers Game");
        gLines('-');
        System.out.println("Please select game mode");
        gLines('-');
        System.out.println("To play versus Andy the Ape");
        System.out.println("Select a color for you by typing");
        System.out.println("W for white or B for black");
        gLines('-');
        System.out.println("Or if you are too scared to face the ape");
        System.out.println("enter A and face a friend");
        gLines('-');
        String choice = new String();

        while (true)
        {
            try {
                System.out.print("Enter your choice (W / B / A): ");
                choice = br.readLine().toLowerCase();

                if (choice.equals("w")||choice.equals("b")||choice.equals("a")||choice.equals("n")){
                    break;
                }
            } catch (Exception ex) {}

            System.out.println("\n Please enter one of the following options: W / B / A");
        }

        return choice;
    }

    public static Move getNextMove(Color player)
    {
        return getFollowingInput(-1,-1);
    }

    public static Move getFollowingInput(int r1, int c1)
    {
        // Display the game board
        Game.board.Display();
        gLines('-');

        // Ask for user input
        System.out.println("Its your turn!");
        System.out.println("Select a piece");

        System.out.print("\tRow(0-7): ");
        if (r1==-1){
            r1 = takeInput();
        }
        else{
            System.out.println(r1);
        }

        System.out.print("\tCol(0-7): ");
        if (c1==-1){
            c1 = takeInput();
        }
        else{
            System.out.println(c1);
        }


        System.out.println("Select a destination");
        System.out.print("\tRow(0-7): ");
        int r2 = takeInput();

        System.out.print("\tCol(0-7): ");
        int c2 = takeInput();

        return new Move(r1,c1,r2,c2);
    }

    private static int takeInput(){

        int num = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            try {
                num = Integer.parseInt(br.readLine());

                if (num>=0 && num < Board.row){
                    break;
                }
            } catch (Exception ex) {}

            System.out.print("You have to choose between 0 and 7!");
        }

        return num;
    }


}
