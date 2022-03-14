package GUI;


import GUI.EventHandling;
import Sudoku.GameType;
import Sudoku.Killer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Κλάση που δημιουργεί και εμφανίζει το πάνελ
 * για το κλασικό sudoku και το duidoku.
 *Εμφανίζει ένα πλέγμα 9x9
 * με Button που αποτελέιται από 81 κουτάκια.
 * @author Paraskevi Papageorgiou
 */
public class SudokuPanel extends JPanel {

    private EventHandling handling; //αντίκειμενο της κλάσης που χειρίζεται τα γραφικά
    //η γραμμή και η στήλη του κελιού που επιλέγει ο χρήστης
    private int column;
    private int row;
    
    private JButton[][] boxes; //πίνακας που αποτελέιται από τα κελιά
    protected JButton selected; //το κελί που επιλέγει ο χρήστης
 
    private GameType type;

    /**
     * Constructor της κλάσης
     * @param game ο τύπος του παιχνιδιού 
     * @param handling αντίκειμενο της κλάσης που χειρίζεται τα γραφικά
     */
    public SudokuPanel(GameType game, EventHandling handling)
    {
        super();
        this.handling=handling;
        type=game;
        setBackground(Color.getHSBColor(100,100,100));
        selected=null;
        
        if (game==GameType.CLASSIC )
            row=column=9;
        else if(game==GameType.DUIDOKU)
            row=column=4;
        boxes=new JButton[row][column];
        setLayout(new GridLayout(row,column,5,5));
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<column;j++)
            {
                boxes[i][j]=new JButton();
                boxes[i][j].setBackground(Color.getHSBColor(130, 70, 100));
                boxes[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                boxes[i][j].setEnabled(true);
                boxes[i][j].addActionListener(new Adapter(i,j));

                add(boxes[i][j]);
            }
        }
    }

    /**
     * Συνάρτηση που επιστρέφει το κελί στη θέση με συντεταγμένες (x,y).
     * @param x η γραμμή του κελιού
     * @param y η στήλη του κελιού
     * @return το κελί στη θέση με συντεταγμένες (x,y)
     */
    public JButton getJButtonValue(int i,int j)
    { return boxes[i][j];}

/**
     * Βοηθητική κλάση που χρειάζεται για γεγονότα ActionListener
     * στα κελιά του sudoku
     */
    private class Adapter implements ActionListener
    {
        //πεδία αποτελούν οι x και y συντεταγμένες
        private int i, j;
        /**
         * Constructor.
         * @param i the x-coordinate of the selected cell.
         * @param j the y-coordinate of the selected cell.
         */
        public Adapter(int i, int j)
        {


            this.i = i;
            this.j = j;
        }


        /**
         * Χειρισμός συμβάντος όταν ο χρήστης πιέζει
         * το ποντίκι πάνω από ένα από τo κελί
         * @param e mouse event
         */
        public void actionPerformed(ActionEvent e) {
            JButton b=(JButton)e.getSource();
            if(b.isEnabled())
            {
                selected=boxes[i][j];
                row= i;
                column=j;
                //System.out.println(row+" "+column);
                handling.showOptionPanel(type,row,column);

            }
        }

    }

}
