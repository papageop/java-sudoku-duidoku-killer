package GUI;

import javax.swing.*;


/**
 * Κλάση που δημιουργεί το frame της εφαρμογής,
 * δηλαδή το παράθυρο που εμφανίζει τα γραφικά
 * στο χρήστη.
 * @author Giorgos Kakazianis
 */
public class SudokuFrame extends JFrame {
   // private EventHandling handling;
    protected JPanel main; //το κύριο πάνελ όπου εμφανίζονται τα παζλ
    protected JPanel side;//το πάνελ το οποίο εμφανίζεται στο πλάι δεξιά

    /**
     * Constructor τηε κλάσης του frame
     * @param h αντικείμενο της κλάσης χειρισμού των γραφικών στοιχείων
     */
    public SudokuFrame(EventHandling h)
    {
       // handling=h;
        setTitle("Sudoku Java application"); //θέτει τον τίτλο
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //κλείνει το παράθυρο όταν ο χρήστης κλείσει την εφαρμογή
        setResizable(true); //επιτρέπει την αλλαγή του μεγέθους του παραθύρου

        setLayout(null);
        main=new JPanel();
        side=new JPanel();
        setSize(900,900);
    }

}
