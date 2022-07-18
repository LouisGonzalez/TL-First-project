package src.model.player.playerTTT;

import java.util.Scanner;

import src.interfaces.IPlayerTTT;
import src.model.player.Human;
import src.utils.Terminal;

public class HumanTTT extends Human implements IPlayerTTT {
    
    public HumanTTT(String name, int totalWins, int totalLoses){
        super(name, totalWins, totalLoses);
    }

    public int askPosition(){
        int number = Terminal.askNumber("Type a number");
        if(number < 1 || number > 3){
            Terminal.showMessage("Invalid range position");
            return askPosition();
        } else {
            return number - 1;
        }
    }

}
