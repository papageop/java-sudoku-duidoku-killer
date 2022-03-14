package GUI;

import Sudoku.Cell;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Κλάση που αντιπροσωπεύει το κελί στο killer
 * sudoku. Κάθε κελί είναι ένα JPanel όπου στα όρια
 * του αποθηκεύεται το άθροισμα του κελιού και στο κέντρο
 * η τιμή που θέτει ο χρήστης.
 * @author Paraskevi Papageorgiou
 */
public class KillerCell extends JPanel {
    private Cell cell; //το κελί με τις συντεταγμένες και την τιμή
    private static final int CELL_SIZE = 60; //το μέγεθος του κελιου
    int sum,value; //το άθροισμα και η τιμή του κελιού

    //τα όρια του κελιού
    private static Border BORDER = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(18,0,0,0));

    /**
     * Constructor του κελιού του killer sudoku που αρχικοποιεί τις μεταβλητές και
     * δημιουργεί το κελί.
     * @param cell αντικείμενο της κλάσης Cell που αντιπροσωπεύει το κελί
     * @param sum //το άθροισμα του cage που ανήκει το κελί
     */
    public KillerCell(Cell cell,int sum)
    {
        super(new FlowLayout());

        this.cell = cell;
        this.value=cell.getValue();
        this.sum=sum;
        if(cell.getValue()!=0){ //αν το κελί είναι άδειο θέτει την τιμή του στο κέντρο
            this.setLayout(new FlowLayout());
            String value = ""+ cell.getValue();
            JLabel l = new JLabel(value);
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setVerticalAlignment(JLabel.CENTER);

            this.add(l);
        }
        this.setBorder(BORDER); //θέτει τα όρια του κελιού

        //τοποθετεί στα όρια και πάνω αριστερά το άθροισμα
        Border TITLED_BORDER = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), ""+sum, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
        this.setBorder(TITLED_BORDER); //προσθέτει το όριο με το άθροισμα στο κελί
    }

    /**
     * Μέθοδος που επιστρέφει αντικείμενο της κλάσης Cell
     * που αντιπροσωπέυει το κελί.
     * @return
     */
    public Cell getCell(){
        return cell;
    }

    /**
     * Μέθοδος που επιστρέφει το άθροισμα του cage που
     * ανήκει το κελί.
     * @return
     */
    public int getSum(){return sum;}

    /**
     * Μέθοδος που θέτει στο κελί την τιμή που δέχεται από το χρήστη.
     * @param value η τιμή που θέτει στο κελί
     */
    public void setCell(String value)
    {
        setVisible(false); //κρύβει το κελί
        setBlank(); //αδειάζει το κελί
        JLabel l = new JLabel(value);//δημιουργεί ένα label με την τιμή του κελιού
        l.setHorizontalAlignment(JLabel.CENTER); //τοποθετεί στο κέντρο το label
        l.setVerticalAlignment(JLabel.CENTER);
        add(l); //προσθέτει το label στο πανελ
        setVisible(true); //εμφανίζει το κελί
    }

    /**
     * Μέθοδος που αδειάζει το κελί από την τιμή που θέτει ο χρήστης
     */
    public void setBlank(){
        setVisible(false);
        this.removeAll();
        setVisible(true);
    }

    /**
     * Μέθοδος που επιστρέφει το επιθυμητό μέγεθος του κελιού
     * @return το επιθυμητό μέγεθος
     */
    public Dimension getPreferredSize(){
        return new Dimension(CELL_SIZE, CELL_SIZE);
    }

}
