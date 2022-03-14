package Sudoku;


/**
 * Κλάση που υλοποιεί ένα χρήστη του παιχνιδιού.
 * Για το χρήστη αποθηκεύεται ένα id, το ψευδώνυμο,
 * οι νίκες, οι ήττες του και τα παιχνίδια
 * που έχει παίξει.
 * @author Paraskevi Papageorg;iou
 */

public class User  {

    private String nickname; //το ψευδώνυμο του χρήστη
    private int wins; //οι νίκες του χρήστη στο παιχνίδι duidoku
    private int losses; //οι ήττες του χρήστη στο παιχνίδι duidoku
    private int previousClassic; //το προηγούμενο κλασικό παιχνίδι που έπαιξε
    private int previousKiller; //το προηγούμενο Killer που έπαιξε

    /**
     * Κατασκευαστής της κλάσης User με παράμετρο το
     * ψυδώνυμο του χρήστη. Αρχικοποιεί τις υπόλοιπες
     * μεταβλητές στο μηδέν.
     * @param nickname
     */
    public User(String nickname)
    {

        this.nickname=nickname;
        this.wins=0;
        this.losses=0;
        this.previousClassic=0;
        this.previousKiller=0;

    }

    /**
     *Setter για το πεδίο wins.
     * Θέτει τιμή μεγαλύτερη ίση του μηδέν ίση με
     * τη παραμετρό.
     * @param wins οι νίκες που είχε ο χρήστης στο duidoku
     */
    public void setWins(int wins)
    {
        if (wins>=0)
            this.wins=wins;
    }

    /**
     *Setter για το πεδίο losses.
     * Θέτει τιμή μεγαλύτερη ίση του μηδέν ίση με
     * τη παραμετρό.
     * @param losses οι ήττες που είχε ο χρήστης στο duidoku
     */
    public void setLosses(int losses)
    {
        if (losses>=0) //πρέπει οι ήττες να είναι θετικός ακέραιος αριθμός
            this.losses=losses;
    }
    /**
     *Setter για το πεδίο nickname.
     * Θέτει τιμή ίση με tη παραμετρό.
     * @param nickname το ψυδώνυμο που θέλει να δώσει ο χρήστης
     */
    public void setNickname(String nickname)
    {
        this.nickname=nickname;
    }

    /**
     * Προσθέτει μια νίκη στο χρήστη
     * σε περίπτωση που κερδίσει στο παιχνίδι
     * duidoku
     */
    public void addWin()
    {
        this.wins++;
    }

    /**
     * Προσθέτει μια ήττα στο χρήστη
     * σε περίπτωση που χάσει στο παιχνίδι
     * duidoku
     */
    public void addLoss()
    {
        this.losses++;
    }

    /**
     * Θέτει το προηγούμενο κλασικό σουντόκου
     * που έπαιξε ο χρήστης για να μην
     * εμφανιστεί ξανά. Δέχεται παράμετρο
     * τον αριθμό του προηγούμενου παιχνιδιού
     * που πρέπει να ανήκει στο διάστημα [0,10]
     * @param previousGame το προηγούμενο παιχνίδι για το κλασικό
     */
    public void setPreviousClassic(int previousGame)
    {
        if(previousGame>=0)
            this.previousClassic=previousGame;
    }

    /**
     * Θέτει το προηγούμενο κίλερ σουντόκου
     * που έπαιξε ο χρήστης για να μην
     * εμφανιστεί ξανά. Δέχεται παράμετρο
     * τον αριθμό του προηγούμενου παιχνιδιού
     * που πρέπει να ανήκει στο διάστημα [0,10]
     * @param previousGame το προηγούμενο παιχνίδι για το κίλερ
     */
    public void setPreviousKiller(int previousGame)
    {
        if(previousGame>=0)
            this.previousKiller=previousGame;
    }

    /**
     * Επιστρέφει το ψευδώνυμου του χρήστη.
     * @return το ψευδώνυμο του χρήστη
     */
    public String getNickname()
    {
        return this.nickname;
    }

    /**
     * Επιστρέφει τις νίκες του χρήστη
     * @return οι νίκες του χρήστη στο duidoku
     */
    public int getWins()
    {
        return this.wins;
    }

    /**
     * Επιστρέφει τις ήττες του χρήστη
     * @return οι ήττες του χρήστη duidoku
     */
    public int getLosses()
    {
        return this.losses;
    }

    /**Επιστρέφει το προηγούμενο κλασικό παιχνίδι
     * που έπαιξε ο χρήστης
     * @return το προηγούμενο κλασικό παιχνίδι που έπαιξε
     */
    public int getPreviousClassic()
    {
        return this.previousClassic;
    }

    /**Επιστρέφει το προηγούμενο κίλερ παιχνίδι
     * που έπαιξε ο χρήστης
     * @return το προηγούμενο κίλερ παιχνίδι που έπαιξε
     */
    public int getPreviousKiller()
    {
        return this.previousKiller;
    }
}

