package GUI;


import javax.swing.*;


/**
 * Κλάση που υλοποιεί το πάνελ με τις επιλογές εισόδου
 * που έχει ο χρήστης στο παιχνίδι duidoku.
 * Κληρονομεί την κλάση OptionPanel.
 * Στο χρήστη εμφανίζεται δεξιά στο πλάι ένα
 * JComboBox με τις επιλογές από 1-4, και δύο κουμπιά,
 * ένα για να καθαρίσει ο χρήστης το κελί και ένα για να
 * εμφανιστεί βοήθεια.
 * @author Giorgos Kakazianis
 */
public class DuidokuOptionPanel extends OptionsPanel {
    public DuidokuOptionPanel(int row, int column, EventHandling handling)
    {
        super(row,column,handling);
        getOptionBox().setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4"}));
        clear=null;

    }
}
