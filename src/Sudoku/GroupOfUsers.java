package Sudoku;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Κλάση που αναπαριστά το σύνολο των
 * χρηστών που είναι εγγεγραμμένοι στο αρχείο
 * ή έχουν γραφτεί κατά τη διάρκεια του παιχνιδιού
 * @author Paraskevi Papageorgiou
 */
public class GroupOfUsers  {

    private HashSet<User> users; //το σύνολο των μοναδικών χρηστών
    private HashSet<String> nicknames; //το σύνολο των μοναδικών ονομάτων

    /**
     * Κατασκευαστής της κλάσης GroupOfUsers.
     * Δημιουργεί τα αντικείμενα των πεδίων
     */
    public GroupOfUsers()
    {
        users=new HashSet<>();
        nicknames=new HashSet<>();
    }

    /**
     * Συνάρτηση που δέχεται ως όρισμα μια συμβολοσειρά
     * που αποτελεί ψευδώνυμο και επιστρέφει
     * αν υπάρχει ήδη στο αρχείο ή οχι.
     * @param nickname το ψευδώνυμο που ψάχνω
     * @return αν υπάρχει true αλλιώς false
     */
    public boolean contains(String nickname)
    {
        if (nicknames.contains(nickname))
            return true;
        return false;
    }

    /**
     * Συνάρτηση που προσθέτει ένα χρήστη στο
     * αρχείο με όλους τους εγγεγραμμένους χρήστες.
     *
     * @param nickname το ψευδώνυμο του νέου χρήστη
     * @return αντικείμενο της κλάσης χρήστη για το ψευδώνυμο που δίνεται
     */
    public User addUser(String nickname)
    {
        User user=new User(nickname); //δημιουργεί αντικείμενο της κλάσης User
        nicknames.add(nickname); //προσθέτει το όνομα στο hashset
        users.add(user);
        return user;
    }

    /**
     * Συνάρτηση που ψάχνει ένα χρήστη με βάση το
     * ψευδώνυμο που έχει καταχωρήσει και τον
     * επιστρέφει.
     * @param nickname το ψευδώνυμο που ψάχνω
     * @return αν υπάρχει το χρήστη αλλιώς κενό
     */
    public User findUserNickname(String nickname)
    {

        Iterator<User> it=users.iterator();
        while (it.hasNext()) //όσο υπάρχουν χρήστες
        {
            User user=it.next();
            if(user.getNickname().equals(nickname)) //αν το όνομα του τρέχοντος χρήστη είναι ίδιο με αυτό της παραμέτρου
                //επιστρέφει το χρήστη
                return user;
        }
        return null; //αν δεν υπάρχει χρήστης με το ψευδώνυμο nickname επιστρέφει null
    }


    /**
     * Επιστρέφει το σύνολο των χρηστών που
     * είναι καταγεγραμμένοι.
     * @return το hashset των χρηστών
     */
    public HashSet<User> getUsers(){return users;}

    /**
     * Συνάρτηση που διαβάζει από το αρχείο των χρηστών
     * όλους τους χρήστες που έχουν αποθηκευτεί
     * κατά την ασφαλή έξοδο από το παιχνίδι.
     * Στη συνέχεια δημιουργεί το σύνολο
     * των χρηστών. Αν συμβεί σφάλμα κατά την
     * ανάγνωση του αρχείου ρίχνει εξαίρεση.
     */
    public void readUsersFile(){
    {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Users.txt"));  //διαβάζει το αρχείο
            String fileread = bufferedReader.readLine(); //διαβάσει την πρώτη γραμμή
            String[] tokenize; //πίνακας συμβολοσειρών για την αποθήκευση των τιμών που διαβάζει στο αρχείο
            User u; //βοηθητική μεταβλητή για να φορτωθούν οι χρήστες
            while (fileread != null){
                tokenize = fileread.split(" "); //αγνοεί τα κενά
                nicknames.add(tokenize[0]); //προσθέτει το όνομα στο σύνολο των ονομάτων
                u=new User(tokenize[0]); //φορτώνει ένα νέο χρήστη με το όνομα του που βρίσκεται στη πρώτη θέση
               // System.out.println(u.getNickname());
                int prevClassic=Integer.valueOf(tokenize[1].trim()).intValue(); // το προηγούμενο κλασικό παιχνίδι που έπαιξε
                int prevKiller=Integer.valueOf(tokenize[2].trim()).intValue();// το προηγούμενο κίλερ παιχνίδι που έπαιξε
                int win=Integer.valueOf(tokenize[3].trim()).intValue(); //οι νίκες του παίκτη στο duidoku
                int loss=Integer.valueOf(tokenize[4].trim()).intValue(); //οι ήττες του παίκτη στο duidoku

            //    System.out.println(prev);
                u.setPreviousClassic(prevClassic);
                u.setPreviousKiller(prevKiller);
                u.setWins(win);
                u.setLosses(loss);
                users.add(u); //προσθέτει το χρήστη
                fileread=bufferedReader.readLine();//διαβάζει την επόμενη γραμμή
        }
        bufferedReader.close();//κλείνει το αρχείο μόλις φτάσει στο τέλος
        }catch (IOException e) //αν υπάρξει πρόβλημα με το αρχείο εμφανίζει μήνυμα λάθους
        {System.err.println("Problem with file");}

    }}

    /**
     * Γράφει στο αρχείο των χρηστών όλους τους
     * χρήστες και τις πληροφορίες για αυτούς.
     * Η εγγραφή γίνεται κατά την ασφαλή έξοδο από
     * το παιχνίδι επιλέγοντας από το μενού την έξοδο.
     */
    public void writeUsersFile()
    {
        try {
        BufferedWriter output=new BufferedWriter(new FileWriter("Users.txt")); //ανοίγει το αρχείο
        output.flush();
        for (User user:users)
        {
           // System.out.println(user.getNickname()+" "+user.getPreviousGame()+" "+user.getWins()+" "+user.getLosses()+"\n");
            output.write(user.getNickname()+" "+user.getPreviousClassic()+" "+user.getPreviousKiller()+" "+user.getWins()+" "+user.getLosses()+"\n");
            //γράφει το όνομα, τα προηγούμενα παιχνίδια στο κλασικό και στο κίλερ, τις νίκες και τις
            //ήττες στο duidoku για κάθε χρήστη
        }
            output.close();//κλείνει το αρχείο
        }catch (IOException e){System.err.println("Error writing file");}//εμφανίζει μήνυμα λάθους σε περίππτωση
        //που υπάρχει κάποιο θέμα με το αρχείο
    }

}
