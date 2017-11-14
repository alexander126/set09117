/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Human Class: public class allowing
 * processing of user plays and supporting game rules.
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */
import java.util.List;
import java.util.Vector;

public class Human {

    public static void makeNextWhiteMoves(){
        boolean incorrectOption = true;
        while(incorrectOption)
        {
            Move move = Input.getNextMove(Color.white);
            if (isWMoveValid(move.initialRow, move.initialCol,
                    move.finalRow, move.finalCol))
            {
                incorrectOption = false;
            }
        }

    }


    public static void makeNextBlackMoves(){
        boolean incorrectOption = true;
        while(incorrectOption)
        {
            Move move = Input.getNextMove(Color.black);
            if (isBMoveValid(move.initialRow, move.initialCol,
                    move.finalRow, move.finalCol))
            {
                incorrectOption = false;
            }
        }

    }


    private static boolean isWMoveValid(int r1, int c1, int r2, int c2)
    {
        // Select Right Piece and Right Move
        if (Game.board.cellP[r1][c1].equals(CellProperty.invalid) || 
            !(Game.board.cellP[r1][c1].equals(CellProperty.white) || 
            Game.board.cellP[r1][c1].equals(CellProperty.whitek)) || 
            !Game.board.cellP[r2][c2].equals(CellProperty.empty))
        {
            Input.PrintSeparator('-');
            System.out.println("Check !!! Black/Invalid Piece Selected or Invalid Move..... Try Again.");
            Input.PrintSeparator('-');
            return false;
        }

        // Caution: Calculate forced moves at (r1,c1)-------------------------------------------
        List<Move> forcedJumps = White.whiteJumps(r1,c1,Game.board);

        // If Forced Move exists at (r1, c1)
        if (!forcedJumps.isEmpty())
        {
            Move move = new Move(r1,c1,r2,c2);

            // Caution: if the current move is a forced move -------------------------------------------
            if (move.ExistsInVector(forcedJumps))
            {
                // Check if  further capture is possible
                while (true)
                {
                    // Capture Black Piece
                    Game.board.whitePieceLogic(r1,c1,r2,c2);

                    // Update r1 to r2 and c1 to c2
                    r1 = r2;
                    c1 = c2;

                    // Calculate further capture at (r1,c1)
                    List<Move> furtherCapture = White.whiteJumps(r1, c1, Game.board);

                    // No further capture
                    if (furtherCapture.isEmpty()) {
                        break;
                    }

                    // Caution: Further capture exists ----> Make sure owner gives correct input
                    boolean incorrectOption = true;
                    while (incorrectOption)
                    {
                        Input.PrintSeparator('-');
                        System.out.println("Further capture exists!!!!!");
                        System.out.println("You have the following moves at: (r1: " + r1 + ", c1: " + c1 + ")");
                        for (int i=0; i<furtherCapture.size();i++){
                            System.out.print("Option "+(i+1)+" : ");
                           System.out.print("------>(r2: " + furtherCapture.get(i).finalRow+", ");
                            System.out.println("c2: " + furtherCapture.get(i).finalCol+")");
                        }
                        Input.PrintSeparator('-');

                        // Take input from owner
                        Move furtherMove = Input.TakeUserInput(r1,c1);

                        // Caution: Valid owner input -----------------------------------------------------
                        if (furtherMove.ExistsInVector(furtherCapture)) {
                            // Update r2 and c2 and set the incorrect flag to be false
                            r2 = furtherMove.finalRow;
                            c2 = furtherMove.finalCol;
                            incorrectOption = false;
                        }
                    }
                }

                return true;
            }
            else
            {
                Input.PrintSeparator('-');
                System.out.println("Check!!!Wrong Move....Try Again.");
                System.out.println("You have the following moves at: (r1: " + r1 + ", c1: " + c1 + ")");
                for (int i=0; i<forcedJumps.size();i++){
                    System.out.print("Option "+(i+1)+" : ");
                    System.out.print("------>(r2: " + forcedJumps.get(i).finalRow+", ");
                   System.out.println("c2: " + forcedJumps.get(i).finalCol+")");
                }

                Input.PrintSeparator('-');
                return false;
            }
        }

        // If no forced move exists at (r1,c1)
        if (forcedJumps.isEmpty())
        {
            // Caution: Calculate all forced moves for white at this state of the board-------------------
            List<Move> forcedMoves = White.whitePossibleJumps(Game.board);

            // No forced move exists at this state of the board for white
            if (forcedMoves.isEmpty())
            {
                // Forward Move
                if (r2 - r1 == 1 && Math.abs(c2 - c1) == 1) {
                    Game.board.placeAMove(r1, c1, r2, c2);
                    return true;
                }

                // Backward Move For WhiteKing
                else if (Game.board.cellP[r1][c1].equals(CellProperty.whitek)) {
                    if (r2 - r1 == -1 && Math.abs(c2 - c1) == 1) {
                        Game.board.placeAMove(r1, c1, r2, c2);
                        return true;
                    }
                }

                else{
                    Input.PrintSeparator('-');
                    System.out.println("Check!!!Only Unit Step Move Allowed.......Try Again.\n");
                    Input.PrintSeparator('-');
                    return false;
                }
            }
            else
            {
                Input.PrintSeparator('-');

                System.out.println("Forced Move exists!!!!!!!!!!!");
                System.out.println("You have the following options.");
                for (int i=0; i<forcedMoves.size();i++)
                {
                    System.out.print((i+1) + ". ");
                    System.out.print("(r1: " + forcedMoves.get(i).initialRow + ", ");
                    System.out.print("c1: " + forcedMoves.get(i).initialCol + ")");
                    System.out.print("------> (r2: " + forcedMoves.get(i).finalRow + ", ");
                    System.out.println("c2: " + forcedMoves.get(i).finalCol+")");
                }

                Input.PrintSeparator('-');
                return false;
            }
        }

        return false;
    }




    private static boolean isBMoveValid(int r1, int c1, int r2, int c2)
    {
        // Select Right Piece and Right Move
        if (Game.board.cellP[r1][c1].equals(CellProperty.invalid) ||
           !(Game.board.cellP[r1][c1].equals(CellProperty.black) ||
           Game.board.cellP[r1][c1].equals(CellProperty.blackk)) ||
           !Game.board.cellP[r2][c2].equals(CellProperty.empty))
                {
                    Input.PrintSeparator('-');
                    System.out.println("Check !!! White/Invalid Piece Selected or Invalid Move..... Try Again.");
                    Input.PrintSeparator('-');
                    return false;
                }

        // Caution: Calculate forced moves at (r1,c1)-------------------------------------------
        List<Move> forcedJumps = Black.blackJumps(r1,c1,Game.board);

        // If Forced Move exists at (r1, c1)
        if (!forcedJumps.isEmpty())
        {
            Move move = new Move(r1,c1,r2,c2);
            // Caution: if the current move is a forced move -------------------------------------------
            if (move.ExistsInVector(forcedJumps))
            {
                // Check if  further capture is possible
                while (true)
                {
                    // Capture White Piece
                    Game.board.blackPieceLogic(r1,c1,r2,c2);

                    // Update r1 to r2 and c1 to c2
                    r1 = r2;
                    c1 = c2;

                    // Calculate further capture at (r1,c1)
                    List<Move> furtherCapture = Black.blackJumps(r1, c1, Game.board);

                    // Caution: No further capture--------------------------------------------
                    if (furtherCapture.isEmpty())
                    {
                        break;
                    }

                    // Caution: Further capture exists ----> Make sure owner gives correct input
                    boolean incorrectOption = true;
                    while (incorrectOption)
                    {
                        Input.PrintSeparator('-');
                        System.out.println("Further capture exists!!!!!");
                        System.out.println("You have the following moves at: (r1: " + r1 + ", c1: " + c1 + ")");
                        for (int i=0; i<furtherCapture.size();i++){
                            System.out.print("Option "+(i+1)+" : ");
                            System.out.print("------>(r2: " + furtherCapture.get(i).finalRow+", ");
                            System.out.println("c2: " + furtherCapture.get(i).finalCol+")");
                        }
                        Input.PrintSeparator('-');

                        // Take input from owner
                        Move furtherMove = Input.TakeUserInput(r1,c1);

                        // Caution: Valid owner input -----------------------------------------------------
                        if (furtherMove.ExistsInVector(furtherCapture)) {
                            // Update r2 and c2 and set the incorrect flag to be false
                            r2 = furtherMove.finalRow;
                            c2 = furtherMove.finalCol;
                            incorrectOption = false;
                        }
                    }
                }

                return true;
            }
            else
            {
                Input.PrintSeparator('-');

                System.out.println("Forced Move exists!!!!!!!!!!!");
                System.out.println("You have the following moves at: (r1: " + r1 + ", c1: " + c1 + ")");
                for (int i=0; i<forcedJumps.size();i++)
                {
                    System.out.print("Option "+(i+1)+" : ");
                    System.out.print("------>(r2: " + forcedJumps.get(i).finalRow+", ");
                    System.out.println("c2: " + forcedJumps.get(i).finalCol+")");
                }

                Input.PrintSeparator('-');
                return false;
            }
        }

        // If no forced move exists at (r1,c1)
        if (forcedJumps.isEmpty())
        {
            // Caution: Calculate all forced moves for black at this state of the board-------------------
            List<Move> forcedMoves = Black.blackPossibleJumps(Game.board);

            // No forced move exists at this state of the board for black
            if (forcedMoves.isEmpty())
            {
                // Forward Move for Black
                if (r2 - r1 == -1 && Math.abs(c2 - c1) == 1) {
                    Game.board.placeAMove(r1, c1, r2, c2);
                    return true;
                }

                // Backward Move For BlackKing
                else if (Game.board.cellP[r1][c1].equals(CellProperty.blackk)) {
                    if (r2 - r1 == 1 && Math.abs(c2 - c1) == 1) {
                        Game.board.placeAMove(r1, c1, r2, c2);
                        return true;
                    }
                }

                else{
                    Input.PrintSeparator('-');
                    System.out.println("Check!!!Only Unit Step Move Allowed.......Try Again.");
                    Input.PrintSeparator('-');
                    return false;
                }
            }
            else
            {
                Input.PrintSeparator('-');
                System.out.println("Forced Move exists!!!!!!!!!!!");
                System.out.println("You have the following options.");
                for (int i=0; i<forcedMoves.size();i++){
                    System.out.print((i+1) + ". ");
                    System.out.print("(r1: " + forcedMoves.get(i).initialRow + ", ");
                    System.out.print("c1: " + forcedMoves.get(i).initialCol + ")");
                    System.out.print("------> (r2: " + forcedMoves.get(i).finalRow + ", ");
                    System.out.println("c2: " + forcedMoves.get(i).finalCol+")");
                }

                Input.PrintSeparator('-');
                return false;
            }
        }

        return false;
    }



}