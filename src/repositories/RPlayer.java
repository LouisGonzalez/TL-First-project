package src.repositories;

import java.util.ArrayList;

import src.model.Player;
import src.model.player.Bot;
import src.model.player.Human;
import src.utils.Terminal;

public class RPlayer {

    private ArrayList<Player> players = new ArrayList<>();
    private final int TYPE_HUMAN = 1;
    private final int TYPE_BOT = 2;

    public RPlayer(ArrayList<Player> players){
        this.players = players;
    }

    public void menu() {
        Terminal.clearScreen();
        Terminal.decorate();
        Terminal.showMessage("   1. Create\n   2. Read\n   3. Update\n   4. Delete\n   5. Back");
        int option = Terminal.askNumber("   Select an option");
        Terminal.clearScreen();
        Terminal.decorate();
        switch(option){
            case 1:
                create();
                break;
            case 2:
                showAll();
                read(getOne());
                break;
            case 3:
                showAll();
                update(getOne());
                break;
            case 4: 
                showAll();
                delete();
                break;
            case 5:
                break;
            default:
                Terminal.showMessage("  Invalid option");
                break;
        }
        Terminal.pressEnter();
    }

    public void showAll(){
        int cont = 1;
        Terminal.decorate();
        for (Player player : players) {
            Terminal.showMessage("  " + cont + ". " + player.getName());
            cont++;
        }
        Terminal.decorate();
    }

    public Player getOne(){
        int playerPosition = Terminal.askNumber("Select one player");
        if(playerPosition - 1 < players.size())
            return this.players.get(playerPosition - 1);
        Terminal.showMessage("The position is non-existent");
        return null;
    }
    
    public void create(){
        String name = Terminal.askString("Write your nickname");
        Terminal.showMessage("1. Human\n2. Bot");
        int typePl = Terminal.askNumber("Select your type player");
        if(typePl >=1 && typePl <=2){
            if(typePl == TYPE_HUMAN)
                this.players.add(new Human(name, 0, 0));
            else if(typePl == TYPE_BOT)
                this.players.add(new Bot(name, 0, 0));        
            Terminal.showMessage("Player succesfully created");
        } else 
            Terminal.showMessage("Invalid player number");
    }

    public void read(Player player){
        if(player != null){
            Terminal.decorate();
            Terminal.showMessage("          PLAYER INFO         ");
            Terminal.showMessage("Name: " + player.getName());
            Terminal.showMessage("Total victories: " + player.getTotalWins());
            Terminal.showMessage("Total losses: " + player.getTotalLoses());
            Terminal.decorate();
        }
    }

    public void update(Player player){
        if(player != null){
            String name = Terminal.askString("Write a new name");
            player.setName(name);
            Terminal.showMessage("Player updated succesfully");
        }
    }

    public void delete(){
        int playerPosition = Terminal.askNumber("Select one player");
        if(playerPosition - 1 < players.size()){
            this.players.remove(playerPosition - 1);
            Terminal.showMessage("Player deleted succesfully");
        } else
            Terminal.showMessage("The player position is non-existent");
    }
}
