package src;

import src.repositories.RPlayer;
import src.statistics.SPlayer;
import src.utils.Terminal;
import java.util.ArrayList;

import src.games.hangman.Hangman;
import src.games.tictactoe.TicTacToe;
import src.model.Game;
import src.model.Player;

public class Menu {

    private RPlayer crudPlayer;
    private ArrayList<Player> players = new ArrayList<>();
    private TicTacToe ttt;
    private Hangman hangman;
    private SPlayer statisticsPlayer;
    private Game games = new Game();
    private final int TYPE_TTT = 1;
    private final int TYPE_HANGMAN = 2;

    public Menu(){
        crudPlayer = new RPlayer(players);
        statisticsPlayer = new SPlayer(players);
    }


    public void showMenu(){
        int option = 0;
        do {
            Terminal.clearScreen();
            Terminal.decorate();
            Terminal.showMessage("   1. Manage players\n   2. Player statistics\n   3. Game statistics\n   4. Play TicTacToe\n   5. Play Hangman\n   6. Close game\n");
            option = Terminal.askNumber("Select an option");
            switch(option){
                case 1:
                    crudPlayer.menu();
                    break;
                case 2:
                    //Player statistics
                    statisticsPlayer.showData();
                    break;
                case 3:
                    //Game statistics
                    this.games.showReports();
                    break;
                case 4:
                    //Tictactoe
                    this.games.addNewGameGeneral(TYPE_TTT);
                    ttt = new TicTacToe(players);
                    ttt.go();
                    break;
                case 5:
                    //Hangman
                    this.games.addNewGameGeneral(TYPE_HANGMAN);
                    hangman = new Hangman(players);
                    hangman.go();
                    break;
                case 6: 
                    Terminal.showMessage("See you soon!");
                    break;
                default:   
                    Terminal.showMessage("Invalid option");
                    break;
            }
            Terminal.decorate();
        } while(option != 6);
    }


}
