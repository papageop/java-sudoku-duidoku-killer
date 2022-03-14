package GUI;

import Sudoku.WordokuMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κλάση που υλοποιεί το πάνελ που δίνει
 * τιμές ο χρήστης στο παιχνίδι σουντόκου.
 * Το πάνελ εμφανίζεται δεξιά στο παράθυρο
 * και αποτελείται από ένα JComboBox με τις
 * διαθέσιμες επιλογές του χρήστη, ένα κουμπί
 * για να αδειάσει το κελί και ένα κουμπί για να δοθεί
 * βοήθεια για ένα συγκεκριμένο κελί.
 * @author Paraskevi Papageorgiou
 */
public class OptionsPanel extends JPanel {

    private int row; //η γραμμή που βρίσκεται το κελί
    private int column; //η στήλη που βρίσκεται το κελί
    private EventHandling handling; //αντικείμενο της κλάσης χειρισμού των γραφικών
    private JLabel label; //label για τον τίτλο του πάνελ
    private JComboBox optionBox; //το combo box με τις επιλογές του χρήστη
    protected JButton clear; //το κουμπί που αδειάζει το κελί
    private JButton help; //κουμπί που εμφανίζει βοήθεια
    protected JLabel hints; //label που εμφανίζει το αποτέλεσμα της βοήθειας για το κελί που επιλέχθηκε
    WordokuMapping wordoku; //αντικείμενο κλάσης αντιστοίχισης αριθμών με γράμματα
    private Locale locale; //μεταβλητή για την τοπική γλώσσα του συστήματος
    private ResourceBundle resource; //resource που αναφέρεται στην τοπική γλώσσα που φορτώνεται

    /**
     * Constructor της κλάσης του μενού επιλογών
     * για το sudoku που παίζει ο χρήστης.
     * @param r η γραμμή που βρίσκεται το κελί που θέλει ο χρήστης να τοποθετήσει τιμή
     * @param c η στήλη που βρίσκεται το κελί που θέλει ο χρήστης να τοποθετήσει τιμή
     * @param h αντικείμενο που αναφέρεται στην κλάση χειρισμού των γραφικών
     */
    public OptionsPanel(int r,int c, EventHandling h)
    {
        super();
        locale=Locale.getDefault();
        wordoku=new WordokuMapping();
        wordoku.createWordokuMap();
        resource=ResourceBundle.getBundle("Sudoku/Bundle",locale);
        this.row=r;
        this.column=c;
        this.handling=h;
        hints=new JLabel();
        label=new JLabel(resource.getString("optionpanel"));
        optionBox=new JComboBox();
        optionBox.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9"}));
        optionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox)e.getSource();
                String value = (String)comboBox.getSelectedItem();
                Integer integerValue;

                if(handling.wordoku)
                    integerValue = Integer.parseInt(wordoku.getWordoku(value));
                else
                    integerValue = Integer.parseInt(value);
                handling.changeSudokuCell(integerValue, row,column);

            }
        });

        clear=new JButton(resource.getString("clear"));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.clearSudokuBox(row,column);
            }
        });
        help=new JButton(resource.getString("helpgame"));
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.getHelp(row,column);
            }
        });

        setLayout(new GridLayout(0,1));
        add(label);
        add(optionBox);
         clear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        clear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                handling.clearSudokuBox(row,column);
            }
        });
        add(clear);
        add(help);
        add(hints);
    }

    /**
     * Συνάρτηση που επιστρέφει το combo box με τις επιλογές του χρήστη
     * @return το μενού επιλογών για το κελί
     */
    public JComboBox getOptionBox()
    {
        return optionBox;
    }

}
