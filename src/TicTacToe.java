import java.util.*;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    
    public TicTacToe(){
        board = new char[3][3];
        currentPlayer = X;
        gameOver = false;

        // Initialize the board with empty cells
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void play(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter '1' to go first, '2' to go second: ");
        int choice = input.nextInt();

        switch(choice){
            case 1:
                currentPlayer = X;
                break;
            case 2:
                currentPlayer = O;
                break;
            default:
                System.out.println("Invalid choice, defaulting to player 1.");
                currentPlayer = X;
                break;
        }

        while(!gameOver){
            printBoard();
            makeMode();
            checkGameStatus();
            switchPlayer();
        }
        input.close();
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private void makeMode(){
        int row, col;
        Scanner input = new Scanner(System.in);
        do{
            System.out.print("Enter the row (0-2) and column (0-2) to make your move: ");
            row = input.nextInt();
            col = input.nextInt();
        }while (!isValidMode(row,col));

        board[row][col] = currentPlayer;
    }

    private boolean isValidMode(int row,int col){
        if(row < 0 || row >3 || col <0 || col>3){
            System.out.println("Invalid move! Row and column should be between 0 and 2.");
            return false;
        }

        if(board[row][col]!=EMPTY){
            System.out.println("Invalid move! The cell is already occupied.");
            return false;
        }
        return true;
    }

    private void checkGameStatus(){
        if(checkWin(X)){
            System.out.println("Player 'X' wins!");
            gameOver = true;
        }
        else if(checkWin(O)){
            System.out.println("Player 'O' wins!");
            gameOver = true;
        }
        else if(isBoardFull()){
            System.out.println("Game was draw!");
            gameOver = true;
        }
    }

    private boolean checkWin(char player){
        for(int row=0; row<3 ; row++){
            if(board[row][0]==player && board[row][1]==player && board[row][2]==player)
                return true;

        }

        for(int col=0;col<3;col++){
            if(board[0][col]==player && board[1][col]==player  && board[2][col]==player)
                return true;
        }

        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }

        if(board[2][0] == player && board[1][1] == player && board[0][2] == player){
            return true;
        }

        return false;
    }

    private boolean isBoardFull(){
        for(int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                if(board[row][col]==EMPTY)
                    return false;
            }
        }

        return true;
    }

    private void switchPlayer(){
        if(currentPlayer==X)
            currentPlayer = O;
        else
            currentPlayer = X;
    }

}
