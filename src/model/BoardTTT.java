package src.model;

import src.dictionaries.SquareValue;
import src.games.tictactoe.TicTacToe;
import src.games.tictactoe.VerifyWin;
import src.utils.Terminal;

public class BoardTTT {
    
    public final int BOARD_SIZE = 3;
    private SquareTTT[][] myBoard = new SquareTTT[BOARD_SIZE][BOARD_SIZE];
    private VerifyWin verifier = new VerifyWin(this);
    private TicTacToe game;

    public BoardTTT(TicTacToe game){
        this.game = game;
    }
    


    public void initialValues(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                myBoard[i][j] = new SquareTTT();
            }
        }
    }

    public void printBoard(){
        String boardPrinted = "";
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(myBoard[i][j].getMyValue() == SquareValue.EMPTY){
                    boardPrinted += "|   |";
                } else if(myBoard[i][j].getMyValue() == SquareValue.X){
                    boardPrinted += "| X |";
                } else if(myBoard[i][j].getMyValue() == SquareValue.O){
                    boardPrinted += "| O |";
                }
            }
            boardPrinted += "\n";
        }
        System.out.println(boardPrinted);
    }

    public SquareTTT[][] getBoard(){
        return this.myBoard;
    }

    public boolean isEmptySpaces(int contMovements){
        if(contMovements >= 9) return true;
        return false;
    }

    public boolean isTicTacToe(int posX, int posY, SquareValue value){
        return verifier.verifyWinX(posX, posY, value);
    }

    public boolean putCoin(int posX, int posY, SquareValue value){
        if(myBoard[posX][posY].getMyValue() == SquareValue.EMPTY){
            myBoard[posX][posY].setMyValue(value);
        } else {
            Terminal.showMessage("Invalid move");
            if(this.game.getContMovements() < 8) return false;
        }
        return true;
    }


}
