import Sudoku.Classic;
import junit.framework.TestCase;
import org.junit.Before;

public class ClassicTest extends TestCase {
    Classic sudoku;
    int[] grid={5, 0, 3, 6, 7, 0, 0, 9, 8, 0, 6, 0, 0, 0, 0, 1, 0, 5 ,0 ,0 ,4 ,3 ,1, 0, 7, 0, 0, 0, 0, 0, 0, 6, 1, 0, 8, 0, 3, 0, 0, 0, 2, 0, 0, 0, 4, 0, 5, 0, 4, 3, 0, 0, 0, 0, 0, 0, 5, 0, 8, 3, 6, 0, 0 ,9 ,0, 6, 0, 0, 0, 0, 4, 0, 2, 3, 0, 0, 4, 6, 8 ,0, 9};

    @Before
    public void setUp() throws Exception {
        sudoku=new Classic();
    }

    public void testLoadPuzzle() {
        sudoku.loadPuzzle(1);
        for (int i=0;i<81;i++)
        {assertEquals(grid[i],sudoku.getBoard(i/9,i%9).getValue());}
    }

    public void testGetBoard() {
        sudoku.loadPuzzle(2);
        assertEquals(8,sudoku.getBoard(2,3).getValue());
    }

    public void testValidMove() {
        sudoku.loadPuzzle(1);
        assertFalse(sudoku.validMove(1,4,5));
        assertTrue(sudoku.validMove(1,4,9));
    }

    public void testIsEmpty() {
        sudoku.loadPuzzle(1);
        assertTrue(sudoku.isEmpty(0,1));
        assertTrue(sudoku.isEmpty(0,5));
    }

    public void testGetHelp() {
        int[] help=new int[]{1,2};
        sudoku.loadPuzzle(1);
        for(int i = 0 ; i < 2 ; i++)
        assertEquals(help[i],sudoku.getHelp(0,1)[i]);
    }

    public void testSetEmpty() {
        sudoku.loadPuzzle(1);
        sudoku.setEmpty();
        assertFalse(sudoku.getEmpty()[0][0]);
    }

    public void testGetUserInput() {
        sudoku.loadPuzzle(1);
        sudoku.getUserInput(0,1,1);
        assertEquals(1,sudoku.getBoard(0,1).getValue());
    }

    public void testIsOver() {
        sudoku.loadPuzzle(1);
        assertFalse(sudoku.isOver());
    }
}