import Sudoku.Duidoku;
import junit.framework.TestCase;

public class DuidokuTest extends TestCase {
    Duidoku duidoku;

    public void setUp() throws Exception {
        duidoku=new Duidoku();

    }

    public void tearDown() throws Exception {
    }

    public void testInBox() {
        duidoku.getUserInput(0,0,1);
        assertTrue(duidoku.inBox(1,1,1));
        duidoku=new Duidoku();
        duidoku.getUserInput(3,3,4);
        assertTrue(duidoku.inBox(2,2,4));
    }

    public void testValidMove() {
        duidoku.getUserInput(0,0,1);
    }

    public void testGetHelp() {
    }

    public void testGetBoard() {
    }

    public void testIsEmpty() {
        duidoku.getUserInput(1,1,2);
        assertFalse(duidoku.isEmpty(1,1));
    }

    public void testGetUserInput() {
    }

    public void testComputerInput() {
    }

    public void testIsOver() {
    }

    public void testUserWins() {
    }
}