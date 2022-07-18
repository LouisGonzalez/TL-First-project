package src.model.player.playerTTT;

import src.interfaces.IPlayerTTT;
import src.model.player.Bot;

public class BotTTT extends Bot implements IPlayerTTT{
    
    public BotTTT(String name, int totalWins, int totalLoses){
        super(name, totalWins, totalLoses);
    }

    public int askPosition(){
        return (int) (Math.floor(Math.random() * 3 + 1)) - 1;
    }

}
