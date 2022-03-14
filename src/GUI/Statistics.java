package GUI;

import Sudoku.User;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κλάση που αναπαριστά το πάνελ που αποτελείται
 * από τα στατιστικά στοιχεία που κρατούνται
 * για κάθε παίκτη.
 * @author Giorgos Kakazianis
 */
public class Statistics extends JPanel {
    private EventHandling handling; //αντικείμενο της κλάσης χειρισμού συμβάντων γραφικών
    //private User user; //
    private JLabel classic; //label που δείχνει το προηγούμενο κλασικό παιχνίδι του παίκτη
    private JLabel killer; //label που δείχνει το προηγούμενο killer παιχνίδι του παίκτη
    private JLabel wins;  //label που δείχνει τις νίκες του παίκτη στο παιχνίδι duidoku
    private JLabel losses; //label που δείχνει τις ήττες του παίκτη στο παιχνίδι duidoku
    private JLabel nickname; ////label που δείχνει το ψευδώνυμο του χρήστη
    private Locale locale; //ανάλογα τη default γλώσσα του συστήματος φορτώνει τα resources

    /**
     * Constructor που δημιουργεί το Panel με τα στοιχείο του χρήστη που είναι
     * συνδεδεμένος
     * @param user ο συνδεδεμένος χρήστης
     * @param handling αντικείμενο της κλάσης που χειρίζεται το γραφικό περιβάλλον
     */
    public Statistics(User user,EventHandling handling)
    {
        super(); //κληρονομεί από την κλάση JPanel
        locale=Locale.getDefault();
        ResourceBundle r=ResourceBundle.getBundle("Sudoku/Bundle",locale);

        setLayout(new GridLayout(0,1));
     //   this.user=user;
        nickname=new JLabel(r.getString("nickname")+": "+user.getNickname());
        add(nickname);
        this.handling=handling;

        classic=new JLabel(r.getString("previousclassic")+": "+user.getPreviousClassic());
        add(classic);
        killer=new JLabel(r.getString("previouskiller")+": "+user.getPreviousKiller());
        add(killer);
        wins=new JLabel(r.getString("wins")+": "+user.getWins());
        add(wins);
        losses=new JLabel(r.getString("losses")+": "+user.getLosses());
        add(losses);
    }

}
