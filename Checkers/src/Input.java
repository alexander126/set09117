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
        System.out.println("Enter 'w/W' if you want to play as white.");
        System.out.println("Enter 'b/B' if you want to play as black.");
        System.out.println("Enter 'a/A' for two player game.");
        System.out.println("Enter 'n/N' for auto play.");
        PrintSeparator('#');

        String choice = new String();

        while (true)
        {
            try {
                System.out.print("Enter your Choice (w/W/b/B/a/A/n/N): ");
                choice = br.readLine().toLowerCase();

                if (choice.equals("w")||choice.equals("b")||choice.equals("a")||choice.equals("n")){
                    break;
                }
            } catch (Exception ex) {}

            System.out.println("\nWrong Choice...Type again(0-7): ");
        }

        return choice;
    }

    public static Move getNextMove(Color player){
        return TakeUserInput(-1,-1);
    }

    public static Move TakeUserInput(int r1, int c1){
        // Display the game board
        Game.board.Display();
        PrintSeparator('-');

        // Ask for user input
        System.out.println("Enter your Move.");
        System.out.println("Piece To Move:");

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


        System.out.println("Where To Move:");
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

            System.out.print("Wrong Choice...Type again(0-7): ");
        }

        return num;
    }


}
