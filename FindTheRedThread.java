import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// FindTheRedThreadFinal class
// Runs the Find the Red Thread game.

public class FindTheRedThreadFinal {

// this will be removed after implemeting the playgames is it just for testing purposes!!!
public static void main(String[] args) {
        play();
    }
//-------------------------------------------------------------------------------------


 // play method - contains all game logic
public static void play() {

 // game constants
    int totalSpools = 20;
    int minPull = 1;
    int maxPull = 10;
    String redSpool = "red";
    String Player1 = "Player 1";
    String Player2 = "Player 2";
    int testCode = 100;

    GetInput input = new GetInput();
    Scanner scnr = new Scanner(System.in);

// display game introduction
    System.out.println("===========================================");
    System.out.println("        FIND THE RED THREAD - GAME        ");
    System.out.println("===========================================");
    System.out.println("There are 20 spools in the box. One of them is red.");
    System.out.println("You are playing against another player.");
    System.out.println("You will choose a number X between 1 and 10.");
    System.out.println("Both players will pull X spools per turn.");
    System.out.println("Spools are NOT returned after being pulled.");
    System.out.println("If the red spool is pulled, that player wins immediately.");
    System.out.println("===========================================");
    System.out.println(" ");


// get number of spools to pull per turn
    System.out.print("Player 1, enter the number of spools to pull per turn (1-10): ");
    int pullAmount = input.getInputInt();
    while (!input.isInRange(pullAmount, minPull, maxPull)) {
        System.out.print("Invalid input. Please enter a number between 1 and 10: ");
        pullAmount = input.getInputInt();
        }

// get who pulls first & entering 100 activates test mode
        System.out.println("");
        System.out.println("Who will pull first? Enter 1 for Player 1 or 2 for Player 2. Enter 100 for TEST mode.");
        System.out.print("Enter your choice: ");
        int firstPuller = input.getInputInt();
        while (firstPuller != 1 && firstPuller != 2 && firstPuller != testCode) {
        System.out.print("Invalid input. Please enter 1 or 2 or Enter 100 for TEST mode: ");
        firstPuller = input.getInputInt();
}

// activate test mode if 100 was entered
        boolean testMode = (firstPuller == testCode);
        if (testMode) {
            System.out.println("Test mode is activated.");
            firstPuller = 1;
        }

        String activePullerName = (firstPuller == 1) ? Player1 : Player2;
        String otherPullerName = (firstPuller == 1) ? Player2 : Player1;

        System.out.println("\n" + activePullerName + "will pull first. " +  otherPullerName + " will pull second.\n");

// build the box with 19 white spools and 1 red spool then shuffle
        ArrayList<String> box = new ArrayList<>();
        for (int i = 0; i < totalSpools - 1; i++) {
        box.add("white");
        }
        box.add(redSpool);
        Collections.shuffle(box);

// test mode: reveal box contents before game starts
        if (testMode) {
            System.out.println("[TEST MODE] Box contents: " + box);
            System.out.println("[TEST MODE] Red spool is at position: " + (box.indexOf(redSpool) + 1));
            System.out.println();
        }

// main game loop that continues until red spool is found
        while (!box.isEmpty()) {

            System.out.println("-------------------------------------------");

// player 1 presses enter, player 2 pulls automatically
            if (activePullerName.equals(Player1)) {
                System.out.print(activePullerName + ", press ENTER to pull " + pullAmount + " spool(s)...");
                scnr.nextLine();
            } else {
                System.out.println(activePullerName + " is pulling " + pullAmount + " spool(s)...");
            }

            boolean foundRed = false;
            int actualPulled = 0;

 // pull spools one by one from the box
            for (int i = 0; i < pullAmount; i++) {
            if (box.isEmpty()) break;
                String spool = box.remove(0);
                actualPulled++;
// test mode: show each spool as it is pulled
            if (testMode) {
                System.out.println("[TEST MODE] Pulled: " + spool);
                }
            if (spool.equals(redSpool)) {
                foundRed = true;
                break;
                }
            }

// check result and declare winner or continue
            if (foundRed) {
                System.out.println(activePullerName + " found the Red Spool!");
                System.out.println(activePullerName + " wins the game!");
                System.out.println("===========================================");
                break;
            } else {
            System.out.println(activePullerName + " pulled " + actualPulled + " spool(s) - no red spool found.");
            System.out.println("There are " + box.size() + " spool(s) left in the box.");
            }

// swap players for next turn
            String temp = activePullerName;
            activePullerName = otherPullerName;
            otherPullerName = temp;
        }
    }
}
