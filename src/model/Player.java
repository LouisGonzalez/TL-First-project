package src.model;

public abstract class Player {
    
    private String name;
    private int totalWins;
    private int totalLoses;

    public Player(String name, int totalWins, int totalLoses){
        this.name = name;
        this.totalWins = totalWins;
        this.totalLoses = totalLoses;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setTotalWins(int totalWins){
        this.totalWins = totalWins;
    }

    public int getTotalWins(){
        return this.totalWins;
    }

    public void setTotalLoses(int totalLoses){
        this.totalLoses = totalLoses;
    }

    public int getTotalLoses(){
        return this.totalLoses;
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }

}
