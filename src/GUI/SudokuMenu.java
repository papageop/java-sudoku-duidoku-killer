package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κλάση που δημιουργεί το κεντρικό μενού που
 * εμφανίζεται στο πάνω μέρος του παραθύρου της εφαρμογής.
 * @author Paraskevi Papageorgiou
 */

public class SudokuMenu extends JMenuBar {

    //private EventHandling handling;
    private JMenu userMenu; //το μενού του χρήστη
    private JMenuItem newUser; //αντικείμενο μενού για τη σύνδεση του χρήστη
    private JMenuItem info; //αντικείμενο μενού για την εμφάνιση των στοιχείων του χρήστη
    private JMenu gamesMenu; //μενού για τα παιχνίδια της εφαρμογής
    private JMenuItem classic; //αντικείμενο μενού για το κλασικό sudoku
    private JMenuItem killer; //αντικείμενο μενού για το killer sudoku
    private JMenuItem duidoku; //αντικείμενο μενού για τo duidoku
    private JCheckBoxMenuItem showWordokuItem; //αντικείμενο checkbox μενού για την εμφάνιση της παραλλαγής του wordoku
    private JMenu helpMenu; // μενού που παρέχει βοήθεια με τις οδηγίες του παιχνιδιού
    private JMenuItem rules; //αντικείμενο μενού για την εμφάνιση κανόνων του παιχνιδιού
    private JMenu exitMenu; //μενού για την ασφαλή έξοδο του χρήστη
    private JMenuItem exitGame; //αντικείμενο μενού για την ασφαλή έξοδο του χρήστη
    private Locale locale;

    /**
     * Constructor του JMenuBar της εφαρμογής του sudoku.
     * Για κάθςε μενού και αντικείμενο του μενού αρχικοποιεί
     * τα αντικείμενα και προσθέτει ActionListener
     * @param handling αντικείμενο της κλάσης χειρισμού του γραφικού περιβάλλοντος
     */
    public SudokuMenu(EventHandling handling)
    {
        super();
        locale=Locale.getDefault(); //ρυθμίζει τη γλώσα ανάλογα με τη γλώσσα του συστήματος
        ResourceBundle r=ResourceBundle.getBundle("Sudoku/Bundle",locale);

        //this.handling=handling;
        userMenu=new JMenu(r.getString("user"));

        newUser=new JMenuItem(r.getString("loginorsignup"));
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.showMainStartingPanel();
            }
        });
        userMenu.add(newUser);
        info=new JMenuItem(r.getString("statisticaliformation"));
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.showInfo();
            }
        });
        userMenu.add(info);
        gamesMenu=new JMenu(r.getString("games"));
        classic=new JMenuItem(r.getString("classic"));
        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.newClassic();
            }
        });
        gamesMenu.add(classic);
        killer=new JMenuItem("Killer Sudoku");
        killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.newKiller();
            }
        });
        gamesMenu.add(killer);
        duidoku=new JMenuItem("Duidoku");
        duidoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.newDuidoku();
            }
        });
        gamesMenu.add(duidoku);
        showWordokuItem=new JCheckBoxMenuItem("Wordoku");
        showWordokuItem.getModel().setSelected(false);
        showWordokuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.toggleWordoku();
            }
        });
        gamesMenu.add(showWordokuItem);
        helpMenu=new JMenu(r.getString("help"));
        rules=new JMenuItem(r.getString("gamerules"));
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.showRules();
            }

        });
        helpMenu.add(rules);
        exitMenu=new JMenu(r.getString("exit"));
        exitGame=new JMenuItem(r.getString("exitgame"));
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handling.exitGame();
            }
        });
        exitMenu.add(exitGame);
        add(userMenu);
        add(gamesMenu);
        add(helpMenu);
        add(exitMenu);

    }

}
