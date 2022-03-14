package GUI;

import Sudoku.Cell;
import Sudoku.GameType;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Κλάση που δημιουργεί και εμφανίζει το πάνελ
 * για το killer sudoku. Εμφανίζει ένα πλέγμα 9x9
 * με KillerCell που αποτελέιται από 81 κουτάκια
 * με το άθροισμα του cage να αναγράφεται σε κάθε κελί
 * πάνω αριστερά.
 * @author Paraskevi Papageorgiou
 */

public class KillerPanel extends JPanel  {
    EventHandling handling; //αντίκειμενο της κλάσης που χειρίζεται τα γραφικά
    private KillerCell[][] cells; //πίνακας που αποτελέιται από τα KillerCell
    public KillerCell selected; //το κελί που επιλέγει ο χρήστης
    private int row,column; //η γραμμή και η στήλη του κελιού που επιλέγει ο χρήστης
    //private int sum;


   // Killer killer;

    /**
     * Συνάρτηση που επιστρέφει το κελί στη θέση με συντεταγμένες (x,y).
     * @param x η γραμμή του κελιού
     * @param y η στήλη του κελιού
     * @return το κελί στη θέση με συντεταγμένες (x,y)
     */
    public KillerCell getValue(int x,int y)
    {return cells[x][y];}

    /**
     * Constructor της κλάσης
     * @param handling αντίκειμενο της κλάσης που χειρίζεται τα γραφικά
     */
    public KillerPanel(EventHandling handling) {

        super();
        this.handling=handling;
        selected = null;
      //  sum=0;
        setBackground(Color.getHSBColor(100,100,100));
        cells=new KillerCell[9][9];
        setLayout(new GridLayout(9,9,5,5));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(i,j);
                cell.setValue(handling.instance.puzzle.getBoard(i,j).getValue());
                //System.out.println(handling.instance.puzzle.getSum(i,j));
                 cells[i][j] = new KillerCell(cell,handling.instance.puzzle.getSum(i,j));
                cells[i][j].setBackground(Color.getHSBColor(130, 70, 100));
                cells[i][j].setOpaque(true);
                cells[i][j].addMouseListener(new CellAdapter(i,j));
                add(cells[i][j]);
            }
        }
        for(JPanel[] labelArray: cells)
        {
            for(JPanel x :labelArray)
            {
                add(x);
            }
        }
        setVisible(true);
    }

    /**
     * Βοηθητική κλάση που χρειάζεται για γεγονότα MouseEvent
     * στα κελιά του Killer σθδοκθ
     */
    private class CellAdapter implements MouseListener
    {
        //πεδία αποτελούν οι x και y συντεταγμένες
        private int i;
        private int j;

        /**
         * Constructor
         * @param i η x συντεταγμένη του επιλεγμένου κελιου
         * @param j η y συντεταγμένη του επιλεγμένου κελιού
         */
        public CellAdapter(int i,int j)
        {
            this.i=i;
            this.j=j;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        /**
         * Χειρισμός συμβάντος όταν ο χρήστης πιέζει
         * το ποντίκι πάνω από ένα από τα Jpanel
         * @param e mouse event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            JPanel b=(JPanel) e.getSource();
            if(b.isEnabled())
            {
                selected=cells[i][j];
                selected.setBackground(Color.getHSBColor(130, 100, 100)); //αλλάζει το χρώμα όταν πατηθεί
                // το κελί
                row= i;
                column=j;
                //sum=cells[i][j].getSum();
               // System.out.println(row+" "+column);
                handling.showOptionPanel(GameType.KILLER,row,column); //εμφανίζει το option panel

            }
        }


        /**
         * Μέθοδος που αλλάζει το χρώμα μόλις απελυθερωθεί το κελί
         * @param e mouse event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            selected.setBackground(Color.getHSBColor(130, 70, 100));
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


}
