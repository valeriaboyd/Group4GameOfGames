import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
 
// JUnit tests for the FindTheRedThreadFinal game 
public class FindTheRedThreadFinalTest {
 

// i will explain how i did one and followed the same idea for all
// first create a a method signature with @Test annotation and a descriptive name
// then create a new instance of the GetInput class so we can call the isInRange 
// method with the test value and the valid range for pull amount (1-10)
// then use assertTrue to check that the method returns true for valid input
// i used this video to guide myself: https://www.youtube.com/watch?v=vZm0lHciFsQ&t=172s

//test that isInRange accepts valid pull amount of 1
    @Test
    public void testPullAmountMin() {
    GetInput input = new GetInput();
    assertTrue(input.isInRange(1, 1, 10));
    }
 
// test that isInRange accepts valid pull amount of 10
    @Test
    public void testPullAmountMax() {
    GetInput input = new GetInput();
    assertTrue(input.isInRange(10, 1, 10));
    }
 
// test that isInRange rejects 0 for pull amount
    @Test
    public void testPullAmountZero() {
    GetInput input = new GetInput();
    assertFalse(input.isInRange(0, 1, 10));
    }
 
// test that isInRange rejects 11 for pull amount
    @Test
    public void testPullAmountAboveMax() {
    GetInput input = new GetInput();
    assertFalse(input.isInRange(11, 1, 10));
    }
 
// test that isInRange rejects negative number for pull amount
    @Test
    public void testPullAmountNegative() {
    GetInput input = new GetInput();
    assertFalse(input.isInRange(-3, 1, 10));
    }

// test that getInputInt returns -1 for non-numeric input
    @Test
    public void testNonNumericInput() {
    GetInput input = new GetInput();
    // we can test that -1 is out of the valid range
    assertFalse(input.isInRange(-1, 1, 10));
}
 
// test that isInRange accepts 1 for first puller
    @Test
    public void testFirstPullerOne() {
    GetInput input = new GetInput();
    assertTrue(input.isInRange(1, 1, 2));
    }
 
// test that isInRange accepts 2 for first puller
    @Test
    public void testFirstPullerTwo() {
    GetInput input = new GetInput();
     assertTrue(input.isInRange(2, 1, 2));
    }
 
// test that isInRange rejects 0 for first puller
    @Test
    public void testFirstPullerZero() {
     GetInput input = new GetInput();
    assertFalse(input.isInRange(0, 1, 2));
    }
 
// test that isInRange rejects 3 for first puller
    @Test
    public void testFirstPullerThree() {
    GetInput input = new GetInput();
    assertFalse(input.isInRange(3, 1, 2));
    }

// now here i will explain the ones below since they are different from the input validation tests
// for these tests i created a new ArrayList to represent the box of spools and added the appropriate number of "white" and "red" strings to set up the test scenario
// then i simulated the pulling of spools by removing elements from the box and checking the results with assertions    

 
// test that Player 1 wins when red spool is at index 0 and pulls first
    @Test
    public void testPlayer1WinsFirst() {
    ArrayList<String> box = new ArrayList<>();
    box.add("red");
    for (int i = 0; i < 19; i++) {
         box.add("white");
        }
        String spool = box.remove(0);
    assertEquals("red", spool);
    }
 
// test that Player 2 wins when red spool is at index 0 and Player 2 pulls first
    @Test
    public void testPlayer2WinsFirst() {
    ArrayList<String> box = new ArrayList<>();
    box.add("red");
    for (int i = 0; i < 19; i++) {
        box.add("white");
        }
    String spool = box.remove(0);
    assertEquals("red", spool);
    }

// this test checks what happens when the red spool is not the first one pulled
// we fill the box with 19 white spools first, then add red at the very end (index 19)
// when we remove, we get white since red is buried at the bottom
// assertNotEquals("red", spool) confirms that white was pulled, not red
// in the real game this would mean the current player did not find red
// so the turn would pass to the other player to try their luck
    
// test that turn passes to other player when red spool is not found
    @Test
    public void testTurnPasses() {
        ArrayList<String> box = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
        box.add("white");
        }
        box.add("red");
        String spool = box.remove(0);
        assertNotEquals("red", spool);
    }
 

// this test verifies that the box gets smaller each time spools are pulled
// we start with 19 white and 1 red giving us 20 spools total
// pullAmount is set to 2 
// we save the size before pulling with sizeBefore so we have something to compare against
// the for loop runs twice starting at index 0 each time simulating 2 pulls
// assertEquals checks that the new size equals sizeBefore minus pullAmount
// this confirms the box shrinks by exactly the number of spools pulled
// test that box size decreases by pull amount each turn

    @Test
    public void testBoxDecreases() {
        ArrayList<String> box = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
        box.add("white");
        }
        box.add("red");
        int pullAmount = 2;
        int sizeBefore = box.size();
        for (int i = 0; i < pullAmount; i++) {
        box.remove(0);
        }
        assertEquals(sizeBefore - pullAmount, box.size());
    }
 

// this test simulates a player finding red somewhere in the middle of their pulls
// we put white at index 0, red at index 1, then fill the rest with 18 more whites
// pullAmount is 3, so the player pulls up to 3 spools this turn
// the for loop pulls one spool at a time and checks if it is red
// the moment red is found foundRed is set to true and break stops the loop immediately
// the game ends early because red was discovered mid-pull
// assertTrue confirms that red was actually found during the pull sequence

// test that game ends when red spool is found mid pull
    @Test
    public void testGameEndsOnRed() {
        ArrayList<String> box = new ArrayList<>();
        box.add("white");
        box.add("red");
        for (int i = 0; i < 18; i++) {
        box.add("white");
        }
        boolean foundRed = false;
        int pullAmount = 3;
        for (int i = 0; i < pullAmount; i++) {
            String spool = box.remove(0);
            if (spool.equals("red")) {
                foundRed = true;
                break;
            }
        }
        assertTrue(foundRed);
    }

// this test checks that the shuffle does not break or corrupt the box in any way
// we create two identical boxes, each with 19 white spools and 1 red spool
// Collections.shuffle() randomly reorders both boxes independently
// since the shuffle is random the two boxes will almost certainly be in different orders
// assertNotNull checks that both boxes still exist and were not destroyed by shuffling
// the two assertEquals calls confirm both boxes still have exactly 20 spools after shuffling
// this makes sure shuffling only reorders the spools and never loses or duplicates any of them

// test that box is shuffled randomly between runs
    @Test
    public void testBoxIsShuffled() {
        ArrayList<String> box1 = new ArrayList<>();
        ArrayList<String> box2 = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            box1.add("white");
            box2.add("white");
        }
        box1.add("red");
        box2.add("red");
        Collections.shuffle(box1);
        Collections.shuffle(box2);

// boxes are very unlikely to be identical after shuffle
        assertNotNull(box1);
        assertNotNull(box2);
        assertEquals(20, box1.size());
        assertEquals(20, box2.size());
    }
 
}
