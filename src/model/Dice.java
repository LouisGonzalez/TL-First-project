package src.model;

public class Dice {
    
    public int turnDice(int firstValue, int secondValue){
        return (int) (Math.floor(Math.random() * secondValue + firstValue));
    }


}
