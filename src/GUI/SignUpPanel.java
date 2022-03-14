package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κλάση που υλοποιεί το panel για την εγγραφή
 * ενός χρήστη στο σύστημα.
 * Περιέχει δύο label, ένα για τον τίτλο signUp
 * και ένα για να ζητήσει από το χρήστη να δώσει
 * είσοδο, ένα πεδίο που θα δώσει την είσοδο ο χρήστης
 * και ένα κουμπί υποβολής.
 * @author Paraskevi Papageorgiou
 */
public class SignUpPanel extends JPanel {
    private EventHandling handling; //αντικείμενο της κλάσης χειρισμού συμβάντων γραφικών
    private JLabel signUp; //label για τον τίτλο
    private JLabel nickname;//label που ζητάει από το χρήστη να δώσει είσοδο
    private JTextField text; // πεδίο που γράφει ο χρήστης το ψευδώνυμο
    private JButton button; //κουμπί υποβολής
    private Locale locale; //ανάλογα τη default γλώσσα του συστήματος φορτώνει τα resources

    /**
     * Constructor του πάνελ εγγραφής
     * @param h αντικείμενο της κλάσης χειρισμού συμβάντων γραφικών
     */
    public SignUpPanel(EventHandling h)
    {
        super(); //κληρονομεί το JPanel
        handling=h;
        locale=Locale.getDefault();
        ResourceBundle r=ResourceBundle.getBundle("Sudoku/Bundle",locale);

        signUp=new JLabel(r.getString("signup")); //θέτει τίτλο του πάνελ
        add(signUp);//προσθέτει το label στο Panel
        setLayout(new GridLayout(0,1));
        nickname=new JLabel(r.getString("giveyournickname"));
        add(nickname);
        text=new JTextField(r.getString("write"));//πλαίσιο που δέχεται την είσοδο από το χρήστη
        text.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e)//αν ο χρήστης πατήσει το ποντίκι στο textfield
            //σβήνει το κείμενο που του ζητά να γράψει
            {
                text.setText("");
            }
        });
        text.addActionListener(new ActionListener() {//επιτρέπει την είσοδο του χρήστη με το πάτημα του enter
            @Override
            public void actionPerformed(ActionEvent e) {

                handling.addUser(text.getText());

                if (handling.instance.getUser()!=null)
                    handling.hideMainStartingPanel();

            }
        });
        add(text);
        button=new JButton(r.getString("signmeup"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                handling.addUser(text.getText());

                if (handling.instance.getUser()!=null)
                handling.hideMainStartingPanel();

            }
        });
        add(button);
    }
}
