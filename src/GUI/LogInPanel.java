package GUI;

import Sudoku.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Κλάση που υλοποιεί το panel για τη σύνδεση
 * ενός εγγεγραμμένου χρήστη στο σύστημα.
 * Περιέχει δύο label, ένα για τον τίτλο login
 * και ένα για να ζητήσει από το χρήστη να δώσει
 * είσοδο, ένα πεδίο που θα δώσει την είσοδο ο χρήστης
 * και ένα κουμπί υποβολής.
 * @author Giorgos Kakazianis
 */
public class LogInPanel extends JPanel {

    //private ArrayList<User> users;
  //  private EventHandling handling;
    private JLabel login;//label για τον τίτλο login
    private JLabel nickname; //label που ζητάει από το χρήστη να δώσει είσοδο
    private JTextField input; // πεδίο που γράφει ο χρήστης το ψευδώνυμο
    private JButton button; //κουμπί υποβολής
    private Locale locale; //ανάλογα τη default γλώσσα του συστήματος φορτώνει τα resources

    /**
     * Constructor του πάνελ σύνδεσης
     * @param handling αντικείμενο της κλάσης χειρισμού συμβάντων γραφικών
     */
    public LogInPanel(EventHandling handling)
    {
        super(); //κληρονομεί το JPanel
        setLayout(new GridLayout(0,1));
        locale=Locale.getDefault();
        ResourceBundle r=ResourceBundle.getBundle("Sudoku/Bundle",locale);
        //users=new ArrayList<>();
        //this.handling=handling;
        login=new JLabel(r.getString("login")); //θέτει τίτλο του πάνελ
        add(login); //προσθέτει το label στο Panel


        nickname=new JLabel(r.getString("giveyournickname")); //θέτει στο label πρόταση που ζητά από το χρήστη να δώσει ψευδώνυμο
        add(nickname); //προσθέτει το label στο Panel

        input=new JTextField(r.getString("write")); //πλαίσιο που δέχεται την είσοδο από το χρήστη
        input.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) //αν ο χρήστης πατήσει το ποντίκι στο textfield
                    //σβήνει το κείμενο που του ζητά να γράψει
            {
                input.setText("");
            }
        });
        input.addActionListener(new ActionListener() { //επιτρέπει την είσοδο του χρήστη με το πάτημα του enter
            @Override
            public void actionPerformed(ActionEvent e) {
                User userResult;
                userResult=handling.findNickname(input.getText());
                if(userResult!=null)
                {
                    handling.login(userResult);
                    handling.hideMainStartingPanel();


                    //System.out.println(users.size());
                }
                else
                {
                    handling.showNameNotExists(); //αν δεν υπάρχει το όνομα εμφανίζει μήνυμα
                }
            }
        });
        input.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        add(input);
        button=new JButton(r.getString("submit"));
        button.addActionListener(new ActionListener() { //μολις πατηθεί το κουμπί συνδέει το χρήστη
            @Override
            public void actionPerformed(ActionEvent e) {
                User userResult;
                userResult=handling.findNickname(input.getText());
                if(userResult!=null)
                {
                    handling.login(userResult);
                    handling.hideMainStartingPanel();


                   //System.out.println(users.size());
                }
                else
                {
                    handling.showNameNotExists(); //αν δεν υπάρχει το όνομα εμφανίζει μήνυμα
                }

            }
        });


        add(button);




    }


}
