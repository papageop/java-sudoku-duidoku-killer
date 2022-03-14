package GUI;

import Sudoku.*;

import java.util.Random;

/**
 * Στιγμιότυπο της εφαρμογής. Όσο ο
 * χρήστης τρέχει την εφαρμογή, η κλάση αυτή
 * φορτώνει καινούργια παιχνίδια, συνδέει καινούργιους
 * χρήστες
 * @author Giorgos Kakazianis
 */
public class GameInstance {

    private Random random; //γεννήτρια τυχαίων αριθμών
    protected GroupOfUsers groupOfUsers; //το σύνολο όλων των χρηστών που βρίσκονται στο αρχείο ή γράφονται
    // κατά τη διάρκεια που τρέχει η εφαρμογή
    protected User user; //ο χρήστης που είναι συνδεδεμένος στην εφαρμογή
    protected boolean anonymous; //μεταβλητή που δείχνει αν ο χρήστης είναι ανώνυμος ή έχει δώσει κάποιο
    //ψευδώνυμο
    protected Puzzle puzzle; //το παζλ που φορτώνεται
    private static volatile GameInstance instance; //το στιγμιότυπο της εφαρμογής

    /**
     * Constructor της κλάσης GameInstance
     * που αρχικοποιεί τις μεταβλητές. Φορτώνει
     * τους χρήστες που έχει γραφτεί το όνομά τους
     * στο αρχείο.
     */
    public GameInstance()
    {
        user=null;
        puzzle=null;
        anonymous=true;
        groupOfUsers=new GroupOfUsers();
        groupOfUsers.readUsersFile();
        random=new Random();
    }

    /**
     * Συνάρτηση που συνδέει ένα νέο χρήστη στην
     * εφαρμογή.
     * @param user ο χρήστης που συνδέεται
     */
    public void login(User user)
    {
        anonymous=false;
        this.user=user;

    }

    /**
     * Μέθοδος που φορτώνει ένα παιχνίδι τύπου
     * GameType που δίνεται ως παράμετρος. Για ανώνυμο
     * χρήστη φορτώνεται ένα τυχαίο παιχνίδι ενώ
     * για ένα συγκεκριμένο χρήστη το επόμενο από
     * αυτό που έπαιξε τελευταία φορά και τελείωσε.
     * @param type ο τύπος του παιχνιδιού που θα φορτωθεί
     */
    public void loadNewGame(GameType type) {
        if (anonymous==true || user==null) {
            if (type == GameType.CLASSIC) {
                puzzle = new Classic();
                puzzle.loadPuzzle(random.nextInt(10) + 1);

            } else if (type == GameType.KILLER) {
                puzzle = new Killer();
                puzzle.loadPuzzle(random.nextInt(10) + 1);
            }
            else if (type==GameType.DUIDOKU)
                puzzle=new Duidoku();

        }
        else
        {
            if (type == GameType.CLASSIC) {
                puzzle = new Classic();
                puzzle.loadPuzzle(user.getPreviousClassic()+1);

            } else if (type == GameType.KILLER) {
                puzzle = new Killer();
                puzzle.loadPuzzle(user.getPreviousKiller()+1);
            }
            else if (type==GameType.DUIDOKU)
                puzzle=new Duidoku();
        }
    }
    public void saveOnClose(boolean answer)
    {


            groupOfUsers.writeUsersFile();


    }

    /**
     * Μέθοδος που επιστρέφει το στιγμιότυπο της εφαρμογής.
     * @return στιγμιότυπο της κλάσης GameInstance
     */
    public static GameInstance getInstance()
    {
        if( instance == null)
        {
            synchronized (GameInstance.class)
            {
                if (instance == null)
                {
                    instance = new GameInstance();
                }
            }
        }
        return instance;
    }

    /**
     * Συνάρτηση που επιστρέφει το τρέχον χρήστη
     * που έχει συνδεθεί στο παιχνίδι ή κενό αν
     * ο χρήστης είναι ανώνυμος.
     * @return ο συδεδεμένος χρήστης
     */
    public User getUser(){return user;}
}



