package src.games.hangman;

import java.util.ArrayList;

import src.interfaces.IGame;
import src.interfaces.IPlayerHangman;
import src.model.Dice;
import src.model.Player;
import src.model.WordHG;
import src.model.player.playerHangman.BotHangman;
import src.model.player.playerHangman.HumanHangman;
import src.repositories.RPlayer;
import src.utils.Terminal;

public class Hangman implements IGame{

    private WordHG word = new WordHG();
    private IPlayerHangman firstPlayer, secondPlayer;
    private Player player1, player2;
    private RPlayer controllerPlayer;
    private Dice dice = new Dice();
    private boolean gameOver = false;
    private int playerSelect;
    private final BoardHG board = new BoardHG();
    private int contErrores = 0;
    private final int FIRST_PLAYER = 1;
    private final int SECOND_PLAYER = 2;
    private final int FIRST_VALUE_DICE = 1;
    private final int LAST_VALUE_DICE = 10;
    private boolean changePositionPlayers = false;

    public Hangman(ArrayList<Player> players){
        this.controllerPlayer = new RPlayer(players);
    }   

    public IPlayerHangman findTypePlayer(Player player){
        if(player.getType().equals("Human")){
            return new HumanHangman(player.getName(), player.getTotalWins(), player.getTotalLoses());
        } else if(player.getType().equals("Bot")){
            return new BotHangman(player.getName(), player.getTotalWins(), player.getTotalLoses());
        }
        return null;
    }

    public void selectPlayers(){    
        controllerPlayer.showAll();
        player1 = controllerPlayer.getOne();
        player2 = controllerPlayer.getOne();
        firstPlayer = findTypePlayer(player1);
        secondPlayer = findTypePlayer(player2);
        spinDices();
    }

    public void spinDices(){
        int valueDice1 = 0, valueDice2 = 0;
        valueDice1 += dice.turnDice(FIRST_VALUE_DICE, LAST_VALUE_DICE);
        valueDice2 += dice.turnDice(FIRST_VALUE_DICE, LAST_VALUE_DICE);
        showReportDices(valueDice1, valueDice2);
        int secondValue1 = dice.turnDice(FIRST_VALUE_DICE, LAST_VALUE_DICE);
        int secondValue2 = dice.turnDice(FIRST_VALUE_DICE, LAST_VALUE_DICE);
        showReportDices(secondValue1, secondValue2);
        valueDice1 += secondValue1;
        valueDice2 += secondValue2;
        verifyValueDices(valueDice1, valueDice2);
    }

    public void showReportDices(int value1, int value2){
        Terminal.showMessage(player1.getName() + " Ha lanzado su primer dado con un resultado de: " + value1);
        Terminal.showMessage(player2.getName() + " Ha lanzado su primer dado con un resultado de: " + value2);
        Terminal.pressEnter();
    }

    //Verifica que dado tiene mas valor para darle prioridad al momento de elegir
    public void verifyValueDices(int valueDice1, int valueDice2){
        if(valueDice1 < valueDice2){
            changePositionPlayers = true;
            IPlayerHangman aux = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = aux;
            Terminal.showMessage(player2.getName() + " ha ganado el duelo de dados por lo cual tiene derecho a elegir");
        } else Terminal.showMessage(player1.getName() + " ha ganado el duelo de dados por lo cual tiene derecho a elegir");
        Terminal.pressEnter();
        Terminal.clearScreen();
    }

    public void initialMoves(){
        selectPlayers();
        if(!firstPlayer.chooseGuessOrSelect()){
            this.word.setWordOriginal(firstPlayer.selectWord());
            this.word.createWordGuess();
            playerSelect = FIRST_PLAYER; //El primer jugador fue el que selecciono la palabra.
        } else {
            this.word.setWordOriginal(secondPlayer.selectWord());
            this.word.createWordGuess();
            playerSelect = SECOND_PLAYER;
        }
    }

    public void go(){
        Terminal.clearScreen();
        Terminal.decorate();
        initialMoves();
        do {
            round();
            Terminal.pressEnter();
        } while(!gameOver && contErrores < 8);
    }

    public void round(){
        Terminal.clearScreen();
        Terminal.decorate();
        board.printHangman(this.word.getWordOriginal(), this.contErrores);
        board.printWord(this.word.getWordGuess());
        String letter = "";
        if(playerSelect == FIRST_PLAYER)
            letter = secondPlayer.guessWord();
        else
            letter = firstPlayer.guessWord();
        verifyWrongAnswer(letter);
        verifyWordGuess();
    }

    //Verifica si la letra del jugador es correcta o no
    public void verifyWrongAnswer(String character){
        if(word.getWordOriginal().contains(character)){
            char c = character.charAt(0);
            this.word.setWordGuess(board.repaintWordGuess(this.word, c));
        } else {
            this.contErrores++;   
            if(this.contErrores >= 8){
                //El jugador ha perdido el juego
                showInfoLoser();
            }
        }
    }

    //Verifica si el jugador ya adivino por completo la palabra
    public void verifyWordGuess(){
        if(!this.word.getWordGuess().contains("*")){
            //El jugador ya adivino la palabra por completo
            showInfoWinner();
            gameOver = true;
            Terminal.pressEnter();
        }
    }

    public void showInfoWinner(){
        if(playerSelect == FIRST_PLAYER){
            if(changePositionPlayers){
                Terminal.showMessage(player1.getName()+" HAS GANADO EL JUEGO!");
                player1.setTotalWins(player1.getTotalWins()+1);
            } else {
                Terminal.showMessage(player2.getName()+" HAS GANADO EL JUEGO!");
                player2.setTotalWins(player2.getTotalWins()+1);       
            }   
        } else if(playerSelect == SECOND_PLAYER){
            if(changePositionPlayers){
                Terminal.showMessage(player2.getName()+" HAS GANADO EL JUEGO!");
                player2.setTotalWins(player2.getTotalWins()+1);
            } else {
                Terminal.showMessage(player1.getName()+" HAS GANADO EL JUEGO!");
                player1.setTotalWins(player1.getTotalWins()+1);       
            }   
        }
    }

    public void showInfoLoser(){
        if(playerSelect == FIRST_PLAYER){
            if(changePositionPlayers){
                Terminal.showMessage(player1.getName()+" HAS PERDIDO EL JUEGO!");
                player1.setTotalLoses(player1.getTotalLoses()+1);
            } else {
                Terminal.showMessage(player2.getName()+" HAS PERDIDO EL JUEGO!");
                player2.setTotalLoses(player2.getTotalLoses()+1);       
            }   
        } else if(playerSelect == SECOND_PLAYER){
            if(changePositionPlayers){
                Terminal.showMessage(player2.getName()+" HAS PERDIDO EL JUEGO!");
                player2.setTotalLoses(player2.getTotalLoses()+1);
            } else {
                Terminal.showMessage(player1.getName()+" HAS PERDIDO EL JUEGO!");
                player1.setTotalLoses(player1.getTotalLoses()+1);       
            }   
        }
    }
    
}
