import Sudoku.Cell;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;

public class CellTest  {

    Cell cell1;
    Cell cell2;
    @Before
    public void setUp() throws Exception {
        cell1=new Cell(1,1);
        cell2=new Cell(5,3);
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testGetRow() {
        assertEquals(1,cell1.getRow());
        assertEquals(5,cell2.getRow());
    }

    @Test
    public void testGetColumn() {
        assertEquals(1,cell1.getColumn());
        assertEquals(3,cell2.getColumn());
    }


    @Test
    public void testSetValue() {
        cell1.setValue(5);
        cell2.setValue(2);
        assertEquals(5,cell1.getValue());
        assertEquals(2,cell2.getValue());
    }

    @Test
    public void testGetValue() {
        assertEquals(0,cell2.getValue());
    }

    @Test
    public void testGetNonet() {
        assertEquals(1,cell1.getNonet());
        assertEquals(5,cell2.getNonet());
    }
}