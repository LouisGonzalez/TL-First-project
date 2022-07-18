package src.games.hangman;

import src.model.WordHG;
import src.utils.Terminal;

public class BoardHG {

    private final String ERROR_1 = "|\n"+
                                   "|\n"+
                                   "|\n"+
                                   "|\n"+
                                   "|\n";

    private final String ERROR_2 = "|------\n"+
                                   "|\n"+
                                   "|\n"+
                                   "|\n"+
                                   "|\n";

    private final String ERROR_3 = "|------\n"+
                                   "|      |\n"+
                                   "|\n"+
                                   "|\n"+
                                   "|\n";

    private final String ERROR_4 = "|------\n"+
                                   "|      |\n"+
                                   "|      O\n"+
                                   "|\n"+
                                   "|\n";

    private final String ERROR_5 = "|------\n"+
                                   "|      |\n"+
                                   "|      O\n"+
                                   "|      |\n"+
                                   "|\n";

    private final String ERROR_6 = "|------\n"+
                                   "|      |\n"+
                                   "|      O\n"+
                                   "|    \\|/\n"+
                                   "|\n";

    private final String ERROR_7 = "|------\n"+
                                   "|      |\n"+
                                   "|      O\n"+
                                   "|    \\|/\n"+
                                   "|      |\n";

    private final String ERROR_8 = "|------\n"+
                                   "|      |\n"+
                                   "|      O\n"+
                                   "|    \\|/\n"+
                                   "|      |\n"+
                                   "|     / \\";

    public void printHangman(String word, int numError){
        switch(numError){
            case 1:
                Terminal.showMessage(ERROR_1);
                break;
            case 2:
                Terminal.showMessage(ERROR_2);
                break;
            case 3:
                Terminal.showMessage(ERROR_3);
                break;
            case 4:
                Terminal.showMessage(ERROR_4);
                break;
            case 5:
                Terminal.showMessage(ERROR_5);
                break;
            case 6:
                Terminal.showMessage(ERROR_6);
                break;
            case 7:
                Terminal.showMessage(ERROR_7);
                break;
            case 8:
                Terminal.showMessage(ERROR_8);
                break;
            default:
                break;
        }
    }

    public void printWord(String wordGuess){
        String word = "", script = "";
        for (int i = 0; i < wordGuess.length(); i++) {
            word += wordGuess.charAt(i);
            script += "_";
        }
        Terminal.showMessage(word);
        Terminal.showMessage(script);
    }

    public String repaintWordGuess(WordHG words, char character){
        String wordGuess = ""; 
        for (int i = 0; i < words.getWordOriginal().length(); i++) {
            if(words.getWordOriginal().charAt(i) == character){
                wordGuess += words.getWordOriginal().charAt(i);
            } else {
                if(words.getWordGuess().charAt(i) != '*'){
                    wordGuess += words.getWordGuess().charAt(i);
                } else {
                    wordGuess += "*";
                }
            }
        }
        return wordGuess;
    }

}
