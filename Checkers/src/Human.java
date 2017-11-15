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
                Input.gLines('-');
                System.out.print("Player's move was ");
                System.out.print("("+move.initialRow+","+move.initialCol+") -->"+" ("+move.finalRow+", "+move.finalCol+")");
                System.out.println("");
                Input.gLines('-');
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
                Input.gLines('-');
                System.out.print("Player's move was");
                System.out.print("("+move.initialRow+","+move.initialCol+") -->"+" ("+move.finalRow+", "+move.finalCol+")");
                System.out.println("");
                Input.gLines('-');
            }
        }

    }


    private static boolean isWMoveValid(int r1, int c1, int r2, int c2)
    {
        if (Game.board.cellP[r1][c1].equals(CellProperty.invalid) || 
            !(Game.board.cellP[r1][c1].equals(CellProperty.white) || 
            Game.board.cellP[r1][c1].equals(CellProperty.whitek)) || 
            !Game.board.cellP[r2][c2].equals(CellProperty.empty))
        {
            Input.gLines('-');
            System.out.println("Invalid Piece or Move, Please try again");
            Input.gLines('-');
            return false;
        }

        List<Move> forcedJumps = White.whiteJumps(r1,c1,Game.board);

        if (!forcedJumps.isEmpty())
        {
            Move move = new Move(r1,c1,r2,c2);

            if (move.isMoveExisting(forcedJumps))
            {
                while (true)
                {
                    Game.board.whitePieceLogic(r1,c1,r2,c2);

                    r1 = r2;
                    c1 = c2;

                    List<Move> furtherCapture = White.whiteJumps(r1, c1, Game.board);

                    if (furtherCapture.isEmpty()) {
                        break;
                    }

                    boolean incorrectOption = true;
                    while (incorrectOption)
                    {
                        Input.gLines('-');
                        System.out.println("Mantadory jump available");
                        System.out.println("Your options are : (r1: " + r1 + ", c1: " + c1 + ")");
                        for (int i=0; i<furtherCapture.size();i++)
                        {
                           System.out.print("Option "+(i+1)+" : ");
                           System.out.print("------>(r2: " + furtherCapture.get(i).finalRow+", ");
                           System.out.println("c2: " + furtherCapture.get(i).finalCol+")");
                        }
                        Input.gLines('-');

                        Move furtherMove = Input.getFollowingInput(r1,c1);

                        if (furtherMove.isMoveExisting(furtherCapture)) {
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
                Input.gLines('-');
                System.out.println("Wrong Move");
                System.out.println("Your options are: (r1: " + r1 + ", c1: " + c1 + ")");
                for (int i=0; i<forcedJumps.size();i++)
                {
                   System.out.print("Option "+(i+1)+" : ");
                   System.out.print("------>(r2: " + forcedJumps.get(i).finalRow+", ");
                   System.out.println("c2: " + forcedJumps.get(i).finalCol+")");
                }

                Input.gLines('-');
                return false;
            }
        }

        if (forcedJumps.isEmpty())
        {
            List<Move> forcedMoves = White.whitePossibleJumps(Game.board);

            if (forcedMoves.isEmpty())
            {
               if (r2 - r1 == 1 && Math.abs(c2 - c1) == 1) {
                    Game.board.placeAMove(r1, c1, r2, c2);
                    return true;
                }

                else if (Game.board.cellP[r1][c1].equals(CellProperty.whitek)) {
                    if (r2 - r1 == -1 && Math.abs(c2 - c1) == 1) {
                        Game.board.placeAMove(r1, c1, r2, c2);
                        return true;
                    }
                }

                else{
                    Input.gLines('-');
                    System.out.println("Impossible move!!\n");
                    Input.gLines('-');
                    return false;
                }
            }
            else
            {
                Input.gLines('-');

                System.out.println("Mantadory jump available!");
                System.out.println("Your options are");
                for (int i=0; i<forcedMoves.size();i++)
                {
                    System.out.print((i+1) + ". ");
                    System.out.print("(r1: " + forcedMoves.get(i).initialRow + ", ");
                    System.out.print("c1: " + forcedMoves.get(i).initialCol + ")");
                    System.out.print("------> (r2: " + forcedMoves.get(i).finalRow + ", ");
                    System.out.println("c2: " + forcedMoves.get(i).finalCol+")");
                }

                Input.gLines('-');
                return false;
            }
        }

        return false;
    }




    private static boolean isBMoveValid(int r1, int c1, int r2, int c2)
    {
        if (Game.board.cellP[r1][c1].equals(CellProperty.invalid) ||
           !(Game.board.cellP[r1][c1].equals(CellProperty.black) ||
           Game.board.cellP[r1][c1].equals(CellProperty.blackk)) ||
           !Game.board.cellP[r2][c2].equals(CellProperty.empty))
                {
                    Input.gLines('-');
                    System.out.println("Invalid Piece or Move, Please try agaim");
                    Input.gLines('-');
                    return false;
                }

        List<Move> forcedJumps = Black.blackJumps(r1,c1,Game.board);

        if (!forcedJumps.isEmpty())
        {
            Move move = new Move(r1,c1,r2,c2);

            if (move.isMoveExisting(forcedJumps))
            {
                while (true)
                {
                    Game.board.blackPieceLogic(r1,c1,r2,c2);

                    r1 = r2;
                    c1 = c2;

                    List<Move> furtherCapture = Black.blackJumps(r1, c1, Game.board);

                    if (furtherCapture.isEmpty())
                    {
                        break;
                    }


                    boolean incorrectOption = true;
                    while (incorrectOption)
                    {
                        Input.gLines('-');
                        System.out.println("Mantadory jumps available");
                        System.out.println("Your options are: (r1: " + r1 + ", c1: " + c1 + ")");
                        for (int i=0; i<furtherCapture.size();i++)
                        {
                           System.out.print("Option "+(i+1)+" : ");
                           System.out.print("------>(r2: " + furtherCapture.get(i).finalRow+", ");
                           System.out.println("c2: " + furtherCapture.get(i).finalCol+")");
                        }
                        Input.gLines('-');

                        Move furtherMove = Input.getFollowingInput(r1,c1);


                        if (furtherMove.isMoveExisting(furtherCapture)) {
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
                Input.gLines('-');

                System.out.println("Mantadory jumps available");
                System.out.println("Your options are: (r1: " + r1 + ", c1: " + c1 + ")");
                for (int i=0; i<forcedJumps.size();i++)
                {
                   System.out.print("Option "+(i+1)+" : ");
                   System.out.print("------>(r2: " + forcedJumps.get(i).finalRow+", ");
                   System.out.println("c2: " + forcedJumps.get(i).finalCol+")");
                }

                Input.gLines('-');
                return false;
            }
        }

        if (forcedJumps.isEmpty())
        {
            List<Move> forcedMoves = Black.blackPossibleJumps(Game.board);

            if (forcedMoves.isEmpty())
            {
                if (r2 - r1 == -1 && Math.abs(c2 - c1) == 1) {
                    Game.board.placeAMove(r1, c1, r2, c2);
                    return true;
                }

                else if (Game.board.cellP[r1][c1].equals(CellProperty.blackk)) {
                    if (r2 - r1 == 1 && Math.abs(c2 - c1) == 1) {
                        Game.board.placeAMove(r1, c1, r2, c2);
                        return true;
                    }
                }

                else{
                    Input.gLines('-');
                    System.out.println("Impossible Move!!");
                    Input.gLines('-');
                    return false;
                }
            }
            else
            {
                Input.gLines('-');
                System.out.println("Mantadory jump available!");
                System.out.println("Your options are");
                for (int i=0; i<forcedMoves.size();i++){
                    System.out.print((i+1) + ". ");
                    System.out.print("(r1: " + forcedMoves.get(i).initialRow + ", ");
                    System.out.print("c1: " + forcedMoves.get(i).initialCol + ")");
                    System.out.print("------> (r2: " + forcedMoves.get(i).finalRow + ", ");
                    System.out.println("c2: " + forcedMoves.get(i).finalCol+")");
                }

                Input.gLines('-');
                return false;
            }
        }

        return false;
    }



}