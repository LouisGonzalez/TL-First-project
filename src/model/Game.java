package src.model;

import src.utils.Terminal;

public class Game {
    
    private int contTTT = 0;
    private int contHG = 0;
    private int contGeneral = 0;
    private final int TYPE_TTT = 1;
    private final int TYPE_HANGMAN = 2;

    public void showReports(){
        Terminal.clearScreen();
        Terminal.decorate();
        Terminal.showMessage("Total games general: " + this.contGeneral);
        Terminal.showMessage("Total TicTacToe games: " + this.contTTT);
        Terminal.showMessage("Total Hangman games: " + this.contHG);
        Terminal.pressEnter();
    }

    public void addNewGameTTT(){
        this.contTTT = this.contTTT + 1;
    }

    public void addNewGameHG(){
        this.contHG = this.contHG + 1;
    }

    public void addNewGameGeneral(int typeGame){
        this.contGeneral = this.contGeneral + 1;
        if(typeGame == TYPE_TTT) addNewGameTTT();
        else if(typeGame == TYPE_HANGMAN) addNewGameHG();
    }

    public void setContTTT(int contTTT){
        this.contTTT = contTTT;
    }

    public int getContTTT(){
        return this.contTTT;
    }

    public void setContHG(int contHG){
        this.contHG = contHG;
    }

    public int getContHG(){
        return this.contHG;
    }

    public void setContGeneral(int contGeneral){
        this.contGeneral = contGeneral;
    }

    public int getContGeneral(){
        return this.contGeneral;
    }

}
