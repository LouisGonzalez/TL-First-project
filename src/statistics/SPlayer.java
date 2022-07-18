package src.statistics;

import java.util.ArrayList;

import src.model.Player;
import src.repositories.RPlayer;
import src.utils.Terminal;

public class SPlayer {

    private ArrayList<Player> players;
    private RPlayer controllerPlayer;

    public SPlayer(ArrayList<Player> players){
        this.players = players;
        this.controllerPlayer = new RPlayer(players);
    }

    public void showData(){
        controllerPlayer.showAll();
        Player selected = controllerPlayer.getOne();
        controllerPlayer.read(selected);
        Terminal.pressEnter();
    }

    
}
