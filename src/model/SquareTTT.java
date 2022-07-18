package src.model;

import src.dictionaries.SquareValue;

public class SquareTTT {
    
    public SquareValue myValue;

    public SquareTTT(){
        myValue = SquareValue.EMPTY;
    }

    public void setMyValue(SquareValue value){
        this.myValue = value;
    }

    public SquareValue getMyValue(){
        return this.myValue;
    }

}
