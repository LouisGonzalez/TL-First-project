package src.model.player.playerHangman;

import src.interfaces.IPlayerHangman;
import src.model.player.Human;
import src.utils.Terminal;

public class HumanHangman extends Human implements IPlayerHangman {
    
    private final int DECIDE_GUESS = 1;
    private final int DECIDE_SELECT = 2;

    public HumanHangman(String name, int totalWins, int totalLoses){
        super(name, totalWins, totalLoses);
    }

    public boolean chooseGuessOrSelect(){
        Terminal.clearScreen();
        Terminal.decorate();
        Terminal.showMessage("1. Guess\n2. Select");
        int value = Terminal.askNumber("Decide what you prefer");
        if(value == DECIDE_GUESS) return true;
        else if(value == DECIDE_SELECT) return false;
        else {
            Terminal.showMessage("Incorrect option");
            Terminal.pressEnter();
            return chooseGuessOrSelect();
        }
    }

    public String selectWord(){
        return Terminal.askString("Write the word to begin the game");
    }

    public String guessWord(){
        String letter = Terminal.askString("Write a letter");
        if(letter.length() > 1){
            Terminal.showMessage("You can only write one letter");
            return guessWord();
        }
        return letter;
    }

}
