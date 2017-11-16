/*
 *This software has been created in accordance with
 *the coursework requirements of the Algorithms and
 *Data Structures module.
 *
 * Description of Game Class: public class allowing
 * game initialization.
 *
 * @author  Aleksandar Kalapsazov
 * @version 1.0
 * @date : 13.10.2017
 *
 * */
public class Game {

    static Board board;

    Game()
    {
        this.Initialize(Input.gameType().charAt(0));
    }

    public void playGame()
    {
        while(!Game.board.isGameFinished())
        {
            if(Game.board.isGameDraw(Color.white))
            {
                break;
            }
            White.Move();
            if(Game.board.isGameFinished())
            {
                Input.DisplayGreetings(Color.white);
                Game.board.Display();
                break;
            }

            if(Game.board.isGameDraw(Color.black))
            {
                break;
            }
            Game.board.Display();
            Black.Move();
            if(Game.board.isGameFinished())
            {
                Input.DisplayGreetings(Color.black);
                Game.board.Display();
                break;
            }
        }
    }

    //Function that initialize which game mode to activate based on user input
    private void Initialize(char human)
    {
        assert(human=='w' || human=='b' || human == 'h');

        board  = new Board();
        switch(human)
        {
            case 'w':
                White.player = Player.HUMAN;
                Black.player = Player.ROBOT;
                break;
            case 'b':
                White.player = Player.ROBOT;
                Black.player = Player.HUMAN;
                break;
            case 'h':
                White.player = Player.HUMAN;
                Black.player = Player.HUMAN;
                break;
        }
    }
}