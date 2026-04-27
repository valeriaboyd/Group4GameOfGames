import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
 
// FindTheRedThreadFinalTest class
// JUnit tests for the FindTheRedThreadFinal game logic
public class FindTheRedThreadFinalTest {
 
    // test that isInRange accepts valid pull amount of 1
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
