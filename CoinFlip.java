import java.util.Scanner;
import java.util.Random;

public void coinFlip(){
    Scanner scnr = new Scanner(System.in);
    int numOfGames;
    int who;
    int player1Score = 0;
    int player2Score = 0;
    int test = 0;
    String guesser;
    String flipper;
    String hand;
    String guess;

    //generating random number
    Random rand = new Random();



    //to get number of games
    System.out.println("Player 1, enter the number of games you want to play out of: ");
    // to catch strings if entered
    while (!scnr.hasNextInt()) {
        System.out.println("Invalid input. Please enter an odd integer.");
        scnr.next(); // discard bad input
    }
    numOfGames = scnr.nextInt();
    while (numOfGames % 2 == 0) {
        System.out.println("Invalid input. Please enter an odd integer.");

        while (!scnr.hasNextInt()) {
            System.out.println("Invalid input. Please enter an odd integer.");
            scnr.next();
        }

        numOfGames = scnr.nextInt();
    }
    scnr.nextLine();

    //asks for who will be guesser
    System.out.println("Who will be the guesser? Enter 1 for Player 1 and 2 for Player 2, Enter 100 for TEST mode");
    //to catch strings if enterded
    while (!scnr.hasNextInt()) {
        System.out.println("Invalid input. Please enter 1 or 2.");
        scnr.next();
    }
    who = scnr.nextInt();
    scnr.nextLine();

    //For test mode
    if (who == 100){
        test = 1;
    }
    if (test == 1){
        System.out.println("Test mode is activated");
    }

    while ((who != 1) && (who != 2)) {
        System.out.println("Invalid menu choice. Please enter 1 or 2");

        while (!scnr.hasNextInt()) {
            System.out.println("Invalid input. Please enter 1 or 2");
            scnr.next();
        }

        who = scnr.nextInt();
        scnr.nextLine();
    }
    if (who == 1){
        guesser = "Player 1";
        flipper = "Player 2";
    }
    else{
        guesser = "Player 2";
        flipper = "Player 1";
    }

    System.out.println("The guesser is " + guesser + ", and the flipper is " + flipper);



    //Game begins, it asks the hider to hide
    while ((player1Score <= (numOfGames/2)) && (player2Score <= (numOfGames/2))){

        //flips coin to get hand
        int random = rand.nextInt(2) + 1;
        if (random == 1){
            hand = "H";
        }
        else{
            hand = "T";
        }

        //asks guesser
        System.out.println(guesser + ", Enter 'H' for Heads or 'T' for Tails");
        //applying TEST mode
        if (test == 1){
            if (hand.equals("H")){
                System.out.println("The coin is heads");
            }
            else{
                System.out.println("The coin is tails");
            }
        }
        System.out.println();
        guess = scnr.nextLine().toUpperCase();

        while ((!guess.equals("H")) && (!guess.equals("T"))){
            System.out.println("Invalid input. Please enter H or T");
            guess = scnr.nextLine().toUpperCase();
        }

        // says its flipping and reveals coin
        System.out.println(flipper + " is flipping the coin");
        if (hand.equals("H")){
            System.out.println("The coin is heads");
        }
        else{
            System.out.println("The coin is tails");
        }

        //checks if correct and gives point to winner
        if (hand.equals(guess)){
            System.out.println(guesser + " guessed correctly");
            System.out.println(guesser + " earned 1 point");
            if (guesser.equals("Player 1")){
                player1Score += 1;
            }
            else{
                player2Score += 1;
            }

        }
        //checks if wrong and gives point to winner
        if (!hand.equals(guess)){
            System.out.println(guesser + " guessed incorrectly");
            System.out.println(flipper + " earned 1 point");
            if (flipper.equals("Player 1")){
                player1Score += 1;
            }
            else{
                player2Score += 1;
            }

        }

        // says who won
        if ((player1Score > (numOfGames/2))){
            System.out.println("Player 1 wins the whole game");
        }
        if ((player2Score > (numOfGames/2))){
            System.out.println("Player 2 wins the whole game");
        }

    }

}





//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    coinFlip();

}
