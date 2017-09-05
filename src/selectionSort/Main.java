package selectionSort;

import java.util.Date;

/**
 * Created by MinaFujisawa on 2017/09/05.
 */
public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        while(true){
            System.out.println("======================");
            System.out.println("WELCOME TO TIC TAC TOE!");
            System.out.println("Designer: John Smith");
            System.out.println("Class: Cornerstone WMDP");
            System.out.println("Date:" + new Date () + "\n");

            System.out.println("1 --- person vs person");
            System.out.println("2 --- person vs random computer\n");

            System.out.println("Enter your choice (1 or 2)");
            System.out.println("======================");

//        Scanner scanner = new Scanner(System.in);
            String input = inputHandler.getInput();

            System.out.println("You have entered choice" + input);

            GameController gc = new GameController(Integer.valueOf(input));
            gc.displayStatus();


            //Main Game
            while(!gc.isGameOver()){
                gc.displayYourTurn();
                gc.selectSquare(inputHandler.getInput());
            }

            //After GameOver
            System.out.println("======================");
            gc.displayWinner();
            System.out.println("Do you want to play again?");
            System.out.println("1 - continue");
            System.out.println("2 - quite");
            if(Integer.valueOf(inputHandler.getInput()) == 2){
                break;
            }
        }
    }
}
