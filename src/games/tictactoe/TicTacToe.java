package src.games.tictactoe;

import java.util.ArrayList;

import src.dictionaries.SquareValue;
import src.interfaces.IGame;
import src.interfaces.IPlayerTTT;
import src.model.BoardTTT;
import src.model.Dice;
import src.model.Player;
import src.model.SquareTTT;
import src.model.player.Bot;
import src.model.player.Human;
import src.model.player.playerTTT.BotTTT;
import src.model.player.playerTTT.HumanTTT;
import src.repositories.RPlayer;
import src.utils.Terminal;

public class TicTacToe implements IGame{

    private Player firstPlayer;
    private Player secondPlayer;
    private RPlayer controllerPlayer;
    private Dice dice = new Dice();
    private int contMovements = 0;
    private boolean gameOver = false;
    private BoardTTT board = new BoardTTT(this);

    public TicTacToe(ArrayList<Player> players){
        this.controllerPlayer = new RPlayer(players);
    }

    public int selectRandom(){
        return dice.turnDice(1, 2);
    }

    public void selectPlayers(){
        controllerPlayer.showAll();
        Player player1 = controllerPlayer.getOne();
        Player player2 = controllerPlayer.getOne();
        if(selectRandom() == 1){
            firstPlayer = player1;
            secondPlayer = player2;
            Terminal.showMessage("The player "+player1.getName()+" will take the X and the player "+player2.getName()+" will take the O");
        } else {
            firstPlayer = player2;
            secondPlayer = player1;
            Terminal.showMessage("The player "+player2.getName()+" will take the X and the player "+player1.getName()+" will take the O");
        }
        Terminal.pressEnter();
        Terminal.clearScreen();
    }    

    public void initialValues(){
        selectPlayers();
        board.initialValues();
        board.printBoard();
    }

    public void go(){
        Terminal.clearScreen();
        Terminal.decorate();
        initialValues();
        do {
            Terminal.clearScreen();
            round();
        } while(!gameOver);
        Terminal.pressEnter();
    }

    public void round(){
        turn(SquareValue.X);
        board.printBoard();
        if(!gameOver){
            turn(SquareValue.O);
            board.printBoard();     
        }
    }

    public void turn(SquareValue valuePlayer){
        IPlayerTTT interfacePlayer;
        switch(valuePlayer){    
            case EMPTY:
                break;
            case X: //first player
                interfacePlayer = findTypePlayer(firstPlayer);
                executeTurn(interfacePlayer, valuePlayer);
                break;
            case O: //second player
                interfacePlayer = findTypePlayer(secondPlayer);
                executeTurn(interfacePlayer, valuePlayer);
                break;
            default:
                break;
        }
    }
    
    public void executeTurn(IPlayerTTT playerTTT, SquareValue value){
        int posX = playerTTT.askPosition();
        int posY = playerTTT.askPosition();
        if(!this.board.putCoin(posX, posY, value)) turn(value);
        else {
            contMovements++;
            if(!this.board.isTicTacToe(posX, posY, value)){
                gameOver = board.isEmptySpaces(contMovements);
                if(gameOver) Terminal.showMessage("No one could win");
            } else {
                gameOver = true;
                Terminal.showMessage("The player "+value+" has won");
            }
        }
    }

    public IPlayerTTT findTypePlayer(Player player){
        if(player.getType().equals("Human")){
            return new HumanTTT(player.getName(), player.getTotalWins(), player.getTotalLoses());
        } else if(player.getType().equals("Bot")){
            return new BotTTT(player.getName(), player.getTotalWins(), player.getTotalLoses());
        }
        return null;
    }

    public int getContMovements(){
        return contMovements;
    }

    public void setContMovements(int contMovements){
        this.contMovements = contMovements;
    }

    public boolean getGameOver(){
        return gameOver;
    }

}
