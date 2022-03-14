import Sudoku.Cell;
import Sudoku.Killer;
import Sudoku.KillerGroup;
import junit.framework.TestCase;

public class KillerTest extends TestCase {

    Killer kill;
    public void setUp() throws Exception {
        kill = new Killer();
        kill.loadPuzzle(2);

    }

    public void tearDown() throws Exception {

    }

    public void testGetSum() {
        assertEquals(16,kill.getSum(0,0));
        assertEquals(13,kill.getSum(0,1));
        assertEquals(12,kill.getSum(0,8));
    }

    public void testIsValidSum() {
        kill.getUserInput(0,0,9);
        assertFalse(kill.isValidSum(0,0,8));
        kill.getUserInput(0,0,0);
        assertTrue(kill.isValidSum(0,0,4));
    }

    public void testGetUserInput() {
        assertFalse(kill.getUserInput(1,1,8));
        assertTrue(kill.getUserInput(1,1,1));
    }

    public void testGetHelp() {
        int[] matrix=new int[]{1,2,3,4,5,6,7};
        for(int i=0; i<7; i++)
        assertEquals(matrix[i],kill.getHelp(1,1)[i]);
    }

    public void testGetEmpty() {
        assertEquals(true,kill.getEmpty()[0][0]);
    }

    public void testLoadPuzzle() {
        KillerGroup group=new KillerGroup(13);
        Cell cell1=new Cell(0,1);
        Cell cell2=new Cell(0,2);
        group.addCell(cell1);
        group.addCell(cell2);
        assertEquals(group.getSum(),kill.getSum(0,1));
        assertEquals(group.getSum(),kill.getSum(0,2));

    }

    public void testIsOver() {
        assertFalse(kill.isOver());
    }

    public void testValidMove() {
        kill.getUserInput(0,1,5);
        assertEquals(kill.validMove(0,0,5),kill.getUserInput(0,0,5));
    }

    public void testGetBoard() {
        kill.getUserInput(0,0,5);
        assertEquals(5,kill.getBoard(0,0).getValue());
        kill.getUserInput(1,1,7);
        assertEquals(7,kill.getBoard(1,1).getValue());
    }
}