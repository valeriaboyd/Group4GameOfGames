public class EvenOdd {
    public static void main(String[] args) {
        //REQUIRES GetInput.java FILE!
        GetInput getInputClass = new GetInput();
        int min = 1;
        int max = 5;
        int player1Score = 0;
        int player2Score = 0;

        System.out.println("Player 1, choose your alignment: Even (E) or Odd (O)");
        String choice1 = getInputClass.getInputStr();
        while (!choice1.equals("E") && !choice1.equals("O")) {
            System.out.println("You have made an incorrect pick!");
            choice1 = getInputClass.getInputStr();
        }

        System.out.println("How many matches would you like to play?");
        int rounds = getInputClass.getInputInt();
        while (rounds == -1 || rounds % 2 == 0) {
            System.out.println("Number of matches should be positive and odd. Please input a positive odd number:");
            rounds = getInputClass.getInputInt();
        }
        int threshold = rounds / 2 + 1;

        while (player1Score != threshold && player2Score != threshold) {
            System.out.println("Please input the throw for Player 1 (1-5):");
            int player1Throw = getThrow(min, max);

            System.out.println("Please input the throw for Player 2 (1-5):");
            int player2Throw = getThrow(min, max);

            int sum = getSum(player1Throw, player2Throw);
            System.out.println("Player 1 threw " + player1Throw + ", Player 2 threw " + player2Throw);
            System.out.println("Sum is " + sum);

            if (sum % 2 == 1) { //Sum is odd
                if (choice1.equals("O")) {
                    System.out.println("Player 1 has won this match.");
                    player1Score++;
                }
                else {
                    System.out.println("Player 2 has won this match.");
                    player2Score++;
                }
                System.out.println();
            }
            else { //Sum is even
                if (choice1.equals("E")) {
                    System.out.println("Player 1 has won this match.");
                    player1Score++;
                }
                else {
                    System.out.println("Player 2 has won this match.");
                    player2Score++;
                }
                System.out.println();
            }
        }
        if (player1Score == threshold) {
            System.out.println("Player 1 wins!");
        }
        else {
            System.out.println("Player 2 wins!");
        }
    }

    public static int getThrow(int min, int max) {
        GetInput getInputClass = new GetInput();
        int playerThrow = getInputClass.getInputInt();
        while (!getInputClass.isInRange(playerThrow, min, max)) {
            System.out.println("Throw out of range. Input a new throw.");
            playerThrow = getInputClass.getInputInt();
        }
        return playerThrow;
    }

    public static int getSum(int num1, int num2) {
        return num1 + num2;
    }
}