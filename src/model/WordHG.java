package src.model;

public class WordHG {
    
    private String wordOriginal;
    private String wordGuess;

    public WordHG(){

    }

    public void setWordOriginal(String wordOriginal){
        this.wordOriginal = wordOriginal;
    }

    public String getWordOriginal(){
        return this.wordOriginal;
    }

    public void setWordGuess(String wordGuess){
        this.wordGuess = wordGuess;
    }

    public String getWordGuess(){
        return this.wordGuess;
    }

    public void createWordGuess(){
        this.wordGuess = "";
        for (int i = 0; i < wordOriginal.length(); i++) {
            wordGuess += "*";
        }
    }


}
