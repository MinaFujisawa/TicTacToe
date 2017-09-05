package selectionSort;

import java.util.Random;

/**
 * Created by MinaFujisawa on 2017/09/05.
 */
public class GameController {
    private int gameMode;
    private int turn = 0; //0 or 1
    private boolean gameOver;
    private Player player1;
    private Player player2;
    private int gameBoard[][] = new int[3][3]; // 1 or 2
    private int winner;


    public GameController(int gameMode) {
        player1 = new Player("Player1");
        this.gameMode = gameMode;
        if(gameMode == 1){
            player2 = new Player("Player2");
        } else if(gameMode == 2){
            player2 = new Player("COM");
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void selectSquare(String selectNum){
        selectNum = selectNum.replaceAll("\\s","");
        int x = Integer.valueOf(selectNum.substring(0, 1)) - 1;
        int y = Integer.valueOf(selectNum.substring(1, 2)) - 1;
        System.out.println("x :" + x);
        System.out.println("y :" + y);
        if(isValid(x,y)){
            gameBoard[x][y] = turn+1;
            System.out.print("Good! ");
            displayStatus();
            checkGameOver(x,y);

            turn++;
            if(turn > 1){
                turn = 0;
            }

            //For Com
            if(gameMode == 2 && !gameOver) {
                setSquareAuto();
            }

        } else {
            System.out.println("Invalid inputs");
        }
    }

    private void setSquareAuto(){
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (gameBoard[x][y] != 0);

        gameBoard[x][y] = 2;

        turn++;
        if(turn > 1){
            turn = 0;
        }
        displayStatus();
        checkGameOver(x,y);
    }


    private boolean isValid(int x, int y){
        if(x > 2 || y > 2){
            return false;
        } else if( 0 > x || 0 > y ){
            return false;
        } else if(gameBoard[x][y] != 0){
            return false;
        }
        return true;
    }

    private void checkGameOver(int x, int y){
        if(matchHorizon(x) || matchVertical(y) || matchDiagonal()){
            System.out.println("true horizon or vertical or diagonal");
            gameOver = true;
        } else {
            gameOver = false;
        }
    }
    private boolean matchDiagonal(){
        if(gameBoard[0][0] != 0 && gameBoard[1][1] != 0 && gameBoard[2][2] != 0){
            if(gameBoard[0][0] == gameBoard[1][1] &&  gameBoard[1][1] == gameBoard [2][2]) {
                winner = gameBoard[0][0];
                System.out.println("true from gameBoard[0][0]");

                return true;
            } else {
                return false;
            }
        }
        if(gameBoard[0][2] != 0 && gameBoard[1][1] != 0 && gameBoard[2][0] != 0){
            if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) {
                winner = gameBoard[0][2];
                System.out.println("true from gameBoard[0][2]");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean matchVertical(int y){
        int x;
        for(x = 0 ; x < 2; x++){
            System.out.println("x " + x + " y " + y + gameBoard[x][y]);
            if(gameBoard[x][y] != gameBoard[x+1][y]){
                gameOver = false;
                System.out.println("vertical false");
                return false;
            }
        }
        System.out.println("vertical true");
        winner = gameBoard[x][y];
        return true;
    }

    private boolean matchHorizon(int x){
        int y;
        for(y = 0 ; y < 2; y++){
            System.out.println("x " + x + " y " + y + gameBoard[x][y]);
            if(gameBoard[x][y] != gameBoard[x][y+1]){
                gameOver = false;
                System.out.println("horizon false");
                return false;
            }
        }
        System.out.println("horizon true");
        winner = gameBoard[x][y];
        return true;
    }

//        for(x = 0 ; x < 3; x++){
//            for(y = 0 ; y < 2; y++){
//                System.out.println("x " + x + " y " + y + gameBoard[x][y]);
//                if(gameBoard[x][y] != gameBoard[x][y+1]){
//                    gameOver = false;
//                    System.out.println("horizon false");
//                    return false;
//                }
//            }
//            System.out.println("horizon true");
//            winner = gameBoard[x][y];
//            return true;
//        }


    public void displayWinner(){
        String winnerName = null;
        if(winner == 1){
            winnerName = player1.getName();
        } else if(winner == 2){
            winnerName = player2.getName();
        }
        System.out.println("Winner is " + winnerName);
    }

    private Player getCurrentUser(){
        if(turn == 0){
            return player1;
        } else if(turn == 1){
            return player2;
        }
        return null;
    }
    public void displayYourTurn(){
        Player currentUser = getCurrentUser();
        System.out.println(currentUser.getName()+  ": make your move");
    }


    public void displayStatus(){
        System.out.println("The current status is :");
        System.out.println("+-----------+");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print("|");
                if(gameBoard[i][j] == 1){
                    System.out.print(" X ");
                } else if(gameBoard[i][j] == 2){
                    System.out.print(" 0 ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");
            System.out.println("+-----------+");
        }
    }
}
