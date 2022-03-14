package GUI;

import Sudoku.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Κλάση χειριμού συμβάντων για το γραφικό περιβάλλον.
 * Εμφανίζει στο JFrame την αρχική οθόνη,
 * τα JPanel ανάλογα με την επιλογή του χρήστη
 * και καθαρίζει το παράθυρο.
 * @author Paraskevi Papageorgiou and Giorgos Kakazianis
 */
public class EventHandling {

    private SudokuMenu menu; //το μενού που εμφανίζεται στο πάνω μέρος του παραθύρου
    private SudokuPanel sudokuPanel; //το πάνελ με τα κελιά για το classic και το duidoku
    private OptionsPanel optionsPanel; //πάνελ επιλογών εισόδου από το χρήστη
    private SignUpPanel signUpPanel; //πάνελ εγγραφής χρήστη
    private LogInPanel logInPanel;  //πάνελ σύνδεσης χρήστη
    private SudokuFrame frame; //το κεντρικό παράθυρο που τρέχει η εφαρμογή
    protected boolean wordoku; //boolean μεταβλητή αν έχει επιλέξει ο χρήστης την παραλλαγή wordoku
    private Statistics statistics; //πάνελ στατιστικών του συνδεδεμένου χρήστη 
    private JLabel welcome; //Label που καλωσορίζει το χρήστη 
    private KillerPanel killerPanel; //το πάνελ με τα κελιά για το killer
    private Locale locale; //internationalization
    private ResourceBundle r;

    private WordokuMapping wordokuMapping;
    protected GameInstance instance; //στιγμιότυπο της εφαρμογής

    /**
      *Constructor
      * @param instance το στιγμιότυπο της εφαρμογής
      */
    public EventHandling(GameInstance instance)
    {
        this.instance=instance;
        wordokuMapping=new WordokuMapping();
        wordokuMapping.createWordokuMap();
        locale=Locale.getDefault();
        r=ResourceBundle.getBundle("Sudoku/Bundle",locale);

        frame=new SudokuFrame(this);
        this.menu=new SudokuMenu(this);
        frame.setJMenuBar(menu);
        wordoku=false;

        showMainStartingPanel(); 
    }

    /**
      *Μέθοδος που εμφανίζει στο χρήστη στο option panel το Label με
      *τις τιμές της βοήθειας για το κελί (x,y).
      * @param x η γραμμή του κελιού που θα εμφανιστεί η βοήθεια
      * @param y η στήλη του κελιού που θα εμφανιστεί η βοήθεια
      */
    public void getHelp(int x,int y)
    {
        int[] help=instance.puzzle.getHelp(x,y);
        String str="";
        if (wordoku)
        for (int i=0;i<help.length;i++)
            str+=wordokuMapping.getWordoku(String.valueOf(help[i]))+", ";
        else
            for (int i=0;i<help.length;i++)
                str+=help[i]+", ";
        optionsPanel.hints.setText(str);
    }

    /**
      *Συνάρτηση που καθαρίζει το κεντρικό πάνελ του παραθύρου.
      * Θέτει κενή τιμή και το κρύβει από το χρήστη.
      */
    public void cleanMainFrame()
    {
        if (frame.main!=null)
        {
            frame.main.setVisible(false);
            frame.main=null;

        }

    }
    
     /**
      *Συνάρτηση που καθαρίζει το πλαϊνό δεξί πάνελ του παραθύρου.
      * Θέτει κενή τιμή και το κρύβει από το χρήστη.
      */
    private void cleanSideFrame()
    {
        if(frame.side!=null)
        {
            frame.side.setVisible(false);
            frame.side=null;

        }

       // frame.repaint();
    }
    /**
     * Αλλάζει on/off τη τιμή του wordoku ανάλογα με το αν ο χρήστης επιλέξει
     * να παίξει την παραλλάγή wordoku
     */
    public void toggleWordoku()
    {
        this.wordoku = !this.wordoku;
        if(wordoku) {
            changeToWordoku(instance.puzzle);
            showOptionPanel(instance.puzzle.type,0,0);
        }
        else{
            showValues(instance.puzzle);
            showOptionPanel(instance.puzzle.type,0,0);
    }}


    /**
      *Μέθοδος που αλλάζει την τιμή του κελιού.
      * @param val η τιμή που δίνεται στο κελί
      * @param row η γραμμή που βρίσκεται το κελί
      * @param column η στήλη που βρίσκεται το κελί
      */
    public void changeSudokuCell(int val,int row,int column)
    {
        if (frame.main instanceof SudokuPanel) //αν στο main panel έχει φορτωθεί classic ή duidoku
        {
            SudokuPanel panel =(SudokuPanel) frame.main;

                if(instance.puzzle.getUserInput( row, column,val)) //αν η είσοδος είναι αποδεκτή
                {
                  //  String value=Integer.toString(val);
                    if (instance.puzzle instanceof Duidoku){ //αν το παιχνίδι είναι το Duidoku
                        showValues(instance.puzzle); //εμφάνισε τις τιμές
                    if(wordoku) { //αν επιλεχθεί το wordoku
                      //  value=wordokuMapping.getWordoku(value);
                       changeToWordoku(instance.puzzle); //άλλαξε τους αριθμούς με γράμματα
                    }
                      //  panel.selected.setText(value);
                    Duidoku duidoku=(Duidoku) instance.puzzle; 
                    lockButtons(duidoku.getEmpty()); //κλείδωσε τις τιμές που έχουν δοθεί
                    boolean user=duidoku.userWins();

                    if (duidoku.isADraw()){ //αν υπάρχει ισοπαλία
                        JOptionPane.showMessageDialog(frame,r.getString("isADraw"));
                        return;
                    }
                    if(user ) //αν κερδίσει ο χρήστης
                    {

                        JOptionPane.showMessageDialog(frame, r.getString("congratulationsto")+" " +instance.getUser().getNickname());
                        if (instance.getUser()!=null)
                        instance.getUser().addWin();
                        // this.cleanUpFinishedGame();
                    }
                    else if(duidoku.isOver() && !user) //αν τελειώσει το παιχνίδι και χάσει ο χρήστηε
                    {
                        JOptionPane.showMessageDialog(frame, r.getString("congratulationsto")+" computer" );
                        if (instance.getUser()!=null)
                        instance.getUser().addLoss();

                    }

                }
                else{ //αν το παιχνίδι είναι το κλασικό
                    String value=Integer.toString(val);
                    //instance.puzzle.getUserInput(row,column,val);
                    if(wordoku) //αν ο χρήστης επιλέξει το wordoku
                    {
                       // changeToWordoku(instance.puzzle);
                        value=wordokuMapping.getWordoku(value);
                    }
                    panel.selected.setText(value); //τοποθετεί την τιμή στο κελί που επιλέχθηκε
                    if (instance.puzzle.isOver()) //αν το παιχνίδι τελειώσει
                    {
                        JOptionPane.showMessageDialog(frame,r.getString("youwon"));
                        if (instance.getUser()!=null)
                        instance.getUser().setPreviousClassic(instance.puzzle.getId()); //προσθέτει το παιχνίδι σε αυτά που έπαιξε
                    }
                    }
                  // System.out.println(instance.puzzle.getBoard(row,column).getValue());
                }
                else //αλλιώς αν δεν είναι αποδεκτή είσοδος εμφανίζει μήνυμα λάθους
                {
                    //System.out.println(instance.puzzle.getUserInput(row,column,val));
                    JOptionPane.showMessageDialog(frame,r.getString("invalidmove"));
                }

        }
        else if (frame.main instanceof KillerPanel) //αν το παιχνίδι είναι το killer
        {
            KillerPanel panel =(KillerPanel) frame.main;
            if(instance.puzzle.getUserInput( row, column,val))
            {

                    String value=Integer.toString(val);
                    //instance.puzzle.getUserInput(row,column,val);

                    panel.selected.setCell(value);
                    if (instance.puzzle.isOver())
                    {
                        JOptionPane.showMessageDialog(frame,r.getString("youwon"));
                        if (instance.getUser()!=null)
                        instance.getUser().setPreviousKiller(instance.puzzle.getId());
                    }

                //System.out.println(instance.puzzle.getBoard(row,column).getValue());
            }
            else
            {
                //System.out.println(instance.puzzle.getUserInput(row,column,val));
                JOptionPane.showMessageDialog(frame,r.getString("invalidmove"));
            }
        }
    }

    /**
      *Συνάρτηση που αδειάζει το κελί από την τιμή του εφόσον 
      * μπορεί να αλλάξει η τιμή του.
      * @param row η γραμμή του κελιού
      * @param column η στήλη του κελιού
      */
    public void clearSudokuBox(int row,int column)
    {
        if(frame.main instanceof KillerPanel)
        {
            instance.puzzle.getUserInput(row,column,0);
            KillerPanel panel = (KillerPanel) frame.main;
            panel.selected.setBlank();
        }
        if (frame.main instanceof SudokuPanel)
        {
            if (instance.puzzle.isEmpty(row,column)){
            instance.puzzle.getUserInput(row,column,0);
            SudokuPanel panel = (SudokuPanel) frame.main;
            panel.selected.setText("");}
        }

    }

    /**
     * Συνάρτηση που εμφανίζει στο χρήστη την αρχική σελίδα όπου
     * μπορεί να γραφτεί ή να συνδεθεί στο παιχνίδι.
     * Εμφανίζεται στα αριστερά το LogInPanel και δεξιά το
     * SignUpPanel.
     */
    public void showMainStartingPanel()
    {

        signUpPanel();
        login(instance.user);
        frame.setVisible(true);

    }

    /**
     * Συνάρτηση που καθαρίζει το παράθυρο και κρύβει το
     * αρχικό πάνελ όπου μπορεί να συνδεθελι ο χρήστης.
     * Εμφανίζει ένα label όπου καλωσορίζει το χρήστη
     * με το ψευδώνυμο που έδωσε.
     */
    public void hideMainStartingPanel()
    {
        hideSignUpPanel();
        hideLoginPanel();
        welcome=new JLabel();
        welcome.setText(r.getString("welcome")+" "+instance.getUser().getNickname());
        welcome.setPreferredSize(new Dimension(200,200));
        frame.main=new JPanel();
        frame.main.add(welcome);
        frame.main.setBounds(0,0,500,500);

        frame.add(frame.main);
        frame.setVisible(true);

    }

    /**
     * Συνάρτηση που εμφανίζει το πάνελ εγγραφής ενός χρήστη.
     */
    public void signUpPanel()
    {
        cleanSideFrame();
        signUpPanel=new SignUpPanel(this);
        frame.side=signUpPanel;
        frame.side.setBounds(400, 0, 350, 350);

        frame.add(frame.side);
        frame.setVisible(true);
        frame.repaint();
    }
    public void hideSignUpPanel()
    {
        if (signUpPanel!=null)
        {
            cleanSideFrame();
            signUpPanel=null;


        }
    }


    /**
     * Συνάρτηση που εμφανίζει στο χρήστη που έχει συνδεθεί
     * το πάνελ με τα στατιστικά, δηλαδή τις νίκες, τις ήττες
     * και τα προηγούμενα παιχνίδια.
     */
    public void showInfo()
    {
        cleanSideFrame();
        cleanMainFrame();
        if (statistics==null && instance.getUser()!=null){
           statistics=new Statistics(instance.getUser(),this);
        frame.main=statistics;
        frame.main.setBounds(0,0,500,500);
        frame.add(frame.main);
        frame.setVisible(true);
        frame.main.setVisible(true);
        frame.repaint();}
    }

    /**
     * Συνάρτηση που κλειδώνει τα κελιά του πάνελ
     * για το τρέχον παζλ στις θέσεις όπου ο πίνακας empty
     * του puzzle έχει την τιμή false
     * @param matrix ο πίνακας boolean empty που αντιστοιχεί στο Puzzle
     */
    public void lockButtons(boolean[][] matrix)
    {
        for (int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix[i].length;j++)
            {
                if (matrix[i][j]==false)
                {
                    sudokuPanel.getJButtonValue(i,j).setEnabled(false);
                }
            }
        }
    }

/**
  * Συνάρτηση που εμφανίζει τις τιμές του παζλ για το κλασικό και το killer, τις 
  *οποίες διαβάζει από το αρχείο
  */
    public void showValues(Puzzle puzzle)
    {
        int size;

     if (puzzle instanceof Killer)
        {
            Killer sudoku=(Killer) puzzle;
            size=9;
            for (int i=0;i<size;i++)
            {
                for (int j=0;j<size;j++)
                {
                   KillerCell cell=killerPanel.getValue(i,j);
                   cell.setCell(String.valueOf(sudoku.getBoard(i,j).getValue()));
                    if (cell.getCell().getValue()==0)
                    {
                        cell.setCell("");
                    }

                }

            }
        }
       else if (puzzle instanceof Classic)
        {
            size=9;
            Classic sudoku=(Classic) puzzle;
            for (int i=0;i<size;i++)
            {
                for (int j=0;j<size;j++)
                {
                    JButton button=sudokuPanel.getJButtonValue(i,j);
                    button.setText(String.valueOf(sudoku.getBoard(i,j).getValue()));
                    if (button.getText().equals("0"))
                    {
                        button.setText("");
                    }
                }
            }
        }
        else if (puzzle instanceof Duidoku)
        {
            Duidoku sudoku=(Duidoku) puzzle;
            size=4;
            for (int i=0;i<size;i++)
            {
                for (int j=0;j<size;j++)
                {
                    JButton button=sudokuPanel.getJButtonValue(i,j);
                    button.setText(String.valueOf(sudoku.getBoard(i,j).getValue()));
                    if (button.getText().equals("0"))
                    {
                        button.setText("");
                    }
                }
            }
        }
    }
    /**
      *Μέθοδος που φορτώνει ένα κλασικό παιχνίδι και το εμφανίζει.
      */
    public void newClassic()
    {
        instance.loadNewGame(GameType.CLASSIC);
        cleanMainFrame();
        cleanSideFrame();
        sudokuPanel=new SudokuPanel(GameType.CLASSIC,this);
        Classic classic=(Classic)instance.puzzle;
        if(wordoku)
            changeToWordoku(classic);
        showValues(instance.puzzle);
        lockButtons(classic.getEmpty());
        frame.main=sudokuPanel;
        frame.main.setBounds(0,0,600,600);
        frame.add(frame.main);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }
    
    /**
      *Μέθοδος που φορτώνει ένα killer παιχνίδι και το εμφανίζει.
      */
    public void newKiller()
    {
        instance.loadNewGame(GameType.KILLER);
        cleanMainFrame();
        cleanSideFrame();
        killerPanel=new KillerPanel(this);
        showValues(instance.puzzle);
        frame.main=killerPanel;
        frame.main.setBounds(0,0,600,600);
        frame.add(frame.main);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }
    /**
      *Μέθοδος που φορτώνει ένα duidoku παιχνίδι και το εμφανίζει.
      */
    public void newDuidoku()
    {
        instance.loadNewGame(GameType.DUIDOKU);
        cleanMainFrame();
        cleanSideFrame();
        sudokuPanel=new SudokuPanel(GameType.DUIDOKU,this);

        Duidoku classic=(Duidoku) instance.puzzle;

        if(wordoku)
            changeToWordoku(classic);

        showValues(instance.puzzle);
        lockButtons(classic.getEmpty());
        frame.main=sudokuPanel;
        frame.main.setBounds(0,0,600,600);
        frame.add(frame.main);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);

    }
    
    /**
      * Μέθοδος που διάβαζει από το αρχείο τους κανόνες του παιχνιδιού.
      */
    public void showRules()
    {

        cleanMainFrame();
        cleanSideFrame();
        BufferedReader input = null; //  buffered reader που ανοίγει και διαβάζει το αρχείο
        String fileRead=null, string=null;
        try {
        while (input == null){
            if (locale.getCountry().equals("GR"))
                input = new BufferedReader(new FileReader("Help_GR.txt"));
            else
                input = new BufferedReader(new FileReader("Help.txt"));

        }

            fileRead = input.readLine();
            string="\n";
            while (fileRead!=null)
            {


                string+=fileRead+"\n";
                fileRead=input.readLine();
                }
        }catch (Exception e){System.err.println("No line in file");}
        //System.out.println(string);
            JTextArea label=new JTextArea(150,50);

            label.setText(string);



            label.setEditable(false);
            JPanel panel=new JPanel();
            panel.add(label);

            frame.main=panel;

            frame.main.setBounds(0,0,900,500);
            frame.add(frame.main);

            frame.setVisible(true);



    }
    
    /**
      *Μέθοδος που αλλάζει στο παιχνίδι wordoku το παζλ.
      *Χρησιμοποιεί το hashmap που συνδέει γράμματα με αριθμούς και
      *αντίστροφα.
      */
    public void changeToWordoku(Puzzle puzzle)
    {
        int size=0;
        if (puzzle instanceof Classic)
            size=9;
        else if(puzzle instanceof Duidoku)
            size=4;
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                JButton button=sudokuPanel.getJButtonValue(i,j);
                if (!button.getText().equals(""))
                {
                    button.setText(wordokuMapping.getWordoku(button.getText()));
                }
            }
        }

    }
    
    /**
      *Μέθοδος που εκτελεί ασφαλή έξοδο από το παιχνίδι και 
      *αποθηκεύει στο αρχείο τα στοιχεία των παικτών.
      */
    public void exitGame()
    {
        if (instance.anonymous==false)
            instance.saveOnClose(true);
        System.exit(0);
    }
    
    /**
      *Συνάρτηση που προσθέτει ένα χρήστη στο σύστημα 
      *εφόσον δεν υπάρχει το όνομα.
      * @param nickname το ψευδώνυμο του νέου χρήστη
      */
    public void addUser(String nickname)
    {

        if (instance.groupOfUsers.contains(nickname)==false){
        User user=instance.groupOfUsers.addUser(nickname);
        if(user!=null)
        {
            instance.login(user);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(frame,r.getString("nameexists"));
        }

    }
    
    /**
      *εμφανίζει στο χρήστη ότι το όνομα δεν υπάρχει.
      */
    public void showNameNotExists()
    {
        JOptionPane.showMessageDialog(frame,r.getString("namenotfound"));
    }

    /**
      *Συνάρτηση που ψάχνει το ψευδώνυμο στο σύνολο των 
      * χρηστών που υπάρχουν στο σύστημα.
      * @param nickname το ψευδώνυμο που ψάχνει
      */
    public User findNickname(String nickname)
    {
        return instance.groupOfUsers.findUserNickname(nickname);

    }
    
    /**
      * Συνάρτηση που συνδέει το χρήστη user.
      * @param user ο χρήστης που συνδέεται
      */
    public void login(User user)
    {
        cleanMainFrame();
        instance.login(user);
        logInPanel=new LogInPanel(this);
        frame.main=logInPanel;
        frame.main.setBounds(0,0,300,300);
        frame.add(frame.main);
        frame.main.setVisible(true);
        frame.main.repaint();
    }
    
    /**
      * Μέθοδος που κρύβει το login panel και το θέτει κενό.
      */
    public void hideLoginPanel()
    {
        if (logInPanel!=null)
        {
            cleanMainFrame();
            logInPanel=null;

        }
    }



   /**
     *Μέθοδος που εμφανίζει το option panel στο side panel
     *στο δεξί μέρος του παραθύρου.
     */
    public void showOptionPanel(GameType type,int i,int j)
    {
        cleanSideFrame();

        if(type==GameType.CLASSIC || type==GameType.KILLER)
            optionsPanel=new OptionsPanel(i,j,this);
        else if(type==GameType.DUIDOKU)
            optionsPanel=new DuidokuOptionPanel(i,j,this);
        JComboBox choices=optionsPanel.getOptionBox();
        if (wordoku)
        {
            if(optionsPanel instanceof DuidokuOptionPanel)
            {
                choices.setModel(new DefaultComboBoxModel(new String[] {
                        "A", "B", "C", "D"
                }));
            }
            else  if(optionsPanel instanceof OptionsPanel){
                choices.setModel(new DefaultComboBoxModel(new String[] {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I"
                }));}
        }
        frame.side=optionsPanel;
        frame.add(frame.side);
        frame.side.setBounds(700, 300, 200, 250);
        frame.side.revalidate();
        frame.side.repaint();
        frame.side.setVisible(true);

    }
}
