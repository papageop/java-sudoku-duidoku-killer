import Sudoku.Cell;
import Sudoku.KillerGroup;
import junit.framework.TestCase;

import java.util.ArrayList;

public class KillerGroupTest extends TestCase {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    KillerGroup group;

    public void setUp() throws Exception {
        cell1=new Cell(0,0);
        cell2=new Cell(0,1);
        cell3=new Cell(1,0);
        group=new KillerGroup(15);
        group.addCell(cell1);
        group.addCell(cell2);
        group.addCell(cell3);
    }

    public void tearDown() throws Exception {
    }

    public void testContains() {
        group.getCells().get(1).setValue(5);
        assertTrue(group.contains(5));
    }

    public void testIsFull() {
        assertFalse(group.isFull());
    }

    public void testGetCells() {
        ArrayList<Cell> cells =new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        assertEquals(group.getCells(),cells);
}

    public void testGetLength() {
        assertEquals(group.getLength(),3);
    }

    public void testGetSum() {
        assertEquals(group.getSum(),15);
    }

    public void testSetSum() {
        group.setSum(12);
        assertEquals(group.getSum(),12);
        group.setSum(0);

    }

    public void testAddCell() {
        Cell cell4=new Cell(0,2);
        group.addCell(cell4);
        for (Cell cell:group.getCells())
            if (cell.getRow()==0 && cell.getColumn()==2)
                assertEquals(cell,cell4);
    }

    public void testGetCurrentSum() {
        group.getCells().get(1).setValue(5);
        group.getCells().get(0).setValue(2);
        assertEquals(group.getCurrentSum(),7);
    }

    public void testRemaining() {
        group.getCells().get(1).setValue(5);
        group.getCells().get(0).setValue(2);
        assertEquals(group.remaining(),1);
    }
}