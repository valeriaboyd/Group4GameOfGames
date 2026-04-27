import java.util.Scanner;


public void findTheThimble(){
    Scanner scnr = new Scanner(System.in);
    int numOfGames;
    int who;
    int player1Score = 0;
    int player2Score = 0;
    int test = 0;
    String guesser;
    String hider;
    String hand;
    String guess;


    //to get number of games
    System.out.println("Player 1, enter the number of games you want to play out of: ");
    //this it to catch strings if it's entered
    while (!scnr.hasNextInt()) {
        System.out.println("Invalid input. Please enter an odd integer.");
        scnr.next(); // discard invalid input
    }
    numOfGames = scnr.nextInt();
    while ((numOfGames% 2 == 0)){
        System.out.println("Invalid menu choice. Please enter an odd integer");
        numOfGames = scnr.nextInt();
        scnr.nextLine();
    }

    //asks for who will be guesser
    System.out.println("Who will be the guesser? Enter 1 for Player 1 and 2 for Player 2, Enter 100 for TEST mode");
    //this is to catch strings if its entered
    while (!scnr.hasNextInt()) {
        System.out.println("Invalid input. Please enter 1 or 2.");
        scnr.next(); // discard invalid input
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

    while ((who != 1) && (who !=2)){
        System.out.println("Invalid menu choice. Please enter 1 or 2.");
        who = scnr.nextInt();
        scnr.nextLine();
    }
    if (who == 1){
        guesser = "Player 1";
        hider = "Player 2";
    }
    else{
        guesser = "Player 2";
        hider = "Player 1";
    }

    System.out.println("The guesser is " + guesser + ", and the hider is " + hider);

    //Game begins, asks to hider to hide
    while ((player1Score <= (numOfGames/2)) && (player2Score <= (numOfGames/2))){
        System.out.println(hider + ", hide the thimble. Enter 'L' for Left or 'R' for Right ");

        hand = scnr.nextLine().toUpperCase();

        //checks if its L or R
        while ((!hand.equals("L")) && (!hand.equals("R"))){
            System.out.println("Invalid input. Please enter L or R");
            hand = scnr.nextLine().toUpperCase();
        }

        //asks guesser
        System.out.println(guesser + ", which hand holds the thimble? (L/R)");
        //applying TEST mode
        if (test == 1){
            if (hand.equals("L")){
                System.out.println("The thimble is in the left hand");
            }
            else{
                System.out.println("The thimble is in the right hand");
            }
        }
        System.out.println();
        guess = scnr.nextLine().toUpperCase();

        while ((!guess.equals("L")) && (!guess.equals("R"))){
            System.out.println("Invalid input. Please enter L or R");
            guess = scnr.nextLine().toUpperCase();
        }

        //checks if correct and gives point to winner
        if (hand.equals(guess)){
            System.out.println(guesser + " guessed correctly");//doesn't say to say anything 10.
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
            System.out.println(hider + " earned 1 point");
            if (hider.equals("Player 1")){
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
    findTheThimble();

}
