package src.model.player.playerHangman;

import src.interfaces.IPlayerHangman;
import src.model.Dice;
import src.model.player.Bot;

public class BotHangman extends Bot implements IPlayerHangman {

    private Dice dice = new Dice();
    private final int DECIDE_GUESS = 1;
    private final int DECIDE_SELECT = 2;

    private String[] words = new String[12];
    private String[] letters = new String[28];

    public BotHangman(String name, int totalWins, int totalLoses){
        super(name, totalWins, totalLoses);
        initStrings();
        initiLetters();
    }

    private void initStrings(){
        words[0] = "bienvenido";
        words[1] = "amistad";
        words[2] = "programacion";
        words[3] = "metodologia";
        words[4] = "lenguaje";
        words[5] = "futbol";
        words[6] = "estructura";
        words[7] = "compilador";
        words[8] = "medicina";
        words[9] = "felicitaciones";
        words[10] = "computadora";
    }

    private void initiLetters(){
        letters[0] = "a";
        letters[1] = "b";
        letters[2] = "c";
        letters[3] = "d";
        letters[4] = "e";
        letters[5] = "f";
        letters[6] = "g";
        letters[7] = "h";
        letters[8] = "i";
        letters[9] = "j";
        letters[10] = "k";
        letters[11] = "l";
        letters[12] = "m";
        letters[13] = "n";
        letters[14] = "o";
        letters[15] = "p";
        letters[16] = "q";
        letters[17] = "r";
        letters[18] = "s";
        letters[19] = "t";
        letters[20] = "u";
        letters[21] = "v";
        letters[22] = "w";
        letters[23] = "x";
        letters[24] = "y";
        letters[25] = "z";
    } 
    
    public boolean chooseGuessOrSelect(){
        int value  = dice.turnDice(DECIDE_GUESS,DECIDE_SELECT);
        if(value == DECIDE_GUESS) return true;
        return false;
    }

    public String selectWord(){
        int positionWord = dice.turnDice(0,9);
        return words[positionWord];
    }

    public String guessWord(){
        int positionLetter = dice.turnDice(0, 25);
        return letters[positionLetter];
    }

}
