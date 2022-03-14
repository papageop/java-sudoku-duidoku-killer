package Sudoku;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Κλάση που υλοποιεί το παιχνίδι killer sudoku.
 * Φορτώνει τα cages με τα αθροίσματα από κάποιο
 * παζλ που υπάρχει στο αρχείο και κάνει τους
 * απαραίτητους ελέγχους έτσι ώστε να μην υπάρχει ο
 * ίδιος αριθμός στην ίδια γραμμή,στήλη και nonet και
 * να ικανοποιείται το άθροισμα ώστε να είναι έγκυρη
 * η κίνηση.
 * @author Paraskevi Papageorgiou
 */
public class Killer extends Puzzle {
    private ArrayList<KillerGroup> group; //λίστα με όλα τα cages του παζλ

    /**
     * Constructor που αρχικοποιεί το παζλ για το
     * killer sudoku παιχνίδι που θα παίξει ο χρήστης
     */
    public Killer()
    {
        super(); //κληρονομεί από την κλάση Puzzle
        group=new ArrayList<>();
        type=GameType.KILLER;
        setRows(9);
        setColumns(9);
        board=new Cell[9][9];
        //αρχικοποιεί όλα τα κελιά του board και θέτει τιμή 0 καθώς αρχικά είναι άδεια
        for (int i=0;i<81;i++) {
            Cell cell = new Cell(i / 9, i % 9);
            cell.setValue(0);
            this.board[i / 9][i % 9] = cell;
        }

        //αρχικοποιεί τον πίνακα empty σε τιμή true για όλες τις θέσεις του πίνακα αφού αρχικά
        //το παζλ είναι άδειο
        this.empty=new boolean[getRows()][getColumns()];
        for (int i=0;i<getRows();i++)
        {
            for (int j=0;j<getColumns();j++)
            {
                empty[i][j]=true;
            }
        }


    }


    /**
     * Μέθοδος που επιστρέφει το άθροισμα του cage που ανήκει ένακελί στη
     * θέση (x,y). Διατρέχει σε όλα τα cages που υπάρχουν στο παιχνίδι το
     * κελί που έχει τις συγκεκριμένες συντεταγμενες και το επιστρέφει.
     * @param x η γραμμή που βρίσκεται το κελί
     * @param y η στήλη που βρίσκεται το κελό
     * @return το άθροισμα του cage για το κελί στη θέση (x,y)
     */
    @Override
    public int getSum(int x,int y)
    {
        for (KillerGroup g:group)
        {
            for (Cell cell:g.getCells())
            {
                if (cell.getRow()==x && cell.getColumn()==y)
                {
                    //System.out.println(g.getSum());
                   return g.getSum();



                }
            }

        }
        return 0;
    }

    /**
     * Συνάρτηση που επιστρέφει αν το άθροισμα είναι
     * έγκυρο. Ελέγχει αν η τιμή μαζί με το τρέχον
     * άθροισμα έχουν αποτέλεσμα μικρότερο ή ίσο από το
     * ζητούμενο του cage. Αν είναι το τελευταίο κελί του cage
     * προς συμπλήρωση, ελέγχει αν έχει γτάσει στο άθροισμα.
     * @param x η γραμμή του κελιού που θέλουμε να θέσουμε τιμή
     * @param y η στήλη του κελιού που θέλουμε να θέσοουμε τιμή
     * @param value η τιμή που θέλουμε να θέσουμε
     * @return true αν είναι έγκυρη τιμή ως προς το άθροισμα αλλιώς false
     */
    public boolean isValidSum(int x,int y,int value)
    {
        KillerGroup cage=null;
        for (KillerGroup g:group)
        {
            for (Cell cell:g.getCells())
            {
                if (cell.getRow()==x && cell.getColumn()==y)
                {
                    cage=g;
                    break;

                }
            }

        }
        if(cage.remaining()==1 && cage.getSum()==cage.getCurrentSum()+value &&!cage.contains(value) ) //αν είναι
            // το τελευταίο κελί προς συμπλήρωση του cage
            return true;
        //αν υπάρχουν κι άλλα κενά κελιά ελέγχει αν δε ξεπερνά το αποτέλεσμα το ζητούμενο άθροισμα
         else if( cage.remaining()>1 && !cage.contains(value) && cage.getSum()>=cage.getCurrentSum()+value)
            return true;
        return false;
    }

    /**
     * Συνάρτηση που δέχεται την είσσοδο του χρήστη και επιστρέφει αν είναι
     * αποδεκτή ή όχι.
     * @param x η γραμμή του κελιού που δέχεται είσοδο
     * @param y η στήλη του κελιού που δέχεται είσοδο
     * @param number η τιμή που δέχεται το κελί ως είσοσδος
     * @return true αν είναι αποδεκτή η είσοδος αλλιώς false
     */
    @Override
    public boolean getUserInput(int x,int y,int number)
    {
        if (number==0)
            board[x][y].setValue(0);
        if (validMove(x, y, number)  && isValidSum(x,y,number)){
            board[x][y].setValue(number);
            return true;
            
        }
        return false;
    }

    /**
     * Συνάρτηση που επιστρέφει τις επιτρεπόμενες τιμές
     * για ένα συγκεκριμένο κελί στη θέση (x,y). Επιστρέφει
     * ένα πίνακα ακεραίων τιμών με τις τιμές
     * @param x η γραμμή του κελιού που ζητάμε βοήθεια
     * @param y η στήλη του κελιού που ζητάμε βοήθεια
     * @return ένας πίνακας ακεραίων με τις επιτρεπόμενες τιμές
     */
    public int[] getHelp(int x,int y)
    {
        HashSet<Integer> vals=new HashSet<>(); //σύνολο με τις τιμές που εμφανίζει κάθε τιμή μόνο μια φορά
        int r = x /(int) Math.sqrt(getRows());
        int col=y/(int) Math.sqrt(getColumns());
        int startRow=r*(int)Math.sqrt(getRows());
        int startCol=col*(int)Math.sqrt(getColumns());
        //ψάχνει σε στη γραμμή και τη στήλη που βρίσκεται το κελί
        //ποιες τιμές εμφανίζονται
        for (int i=0;i<getRows();i++)
        {
            if(board[i][y].getValue()!=0)
            {vals.add(board[i][y].getValue());}
            if(board[x][i].getValue()!=0)
            {vals.add(board[x][i].getValue());}

        }
        //ψάχνει σε στο nonet που βρίσκεται το κελί
        //ποιες τιμές εμφανίζονται
        for (int i=0;i<Math.sqrt(getRows());i++)
        {
            for (int j=0;j<Math.sqrt(getColumns());j++)
            {
                if(startRow+i==x || startCol+j==y) //αν βρεθεί στο ίδιο το κελί που ελέγχει συνεχίζει
                    continue;
                if (board[startRow+i][startCol+j].getValue()!=0)
                    vals.add(board[startRow+i][startCol+j].getValue());
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        //τοποθετεί στη λίστα temp με τις προσωρινές τιμές
        //ποιές τιμές δεν εμφανίζονται ξανά και ικανοποιούν
        //τη συνθήκη για το άθροισμα στο διάστημα [1,9]
        for (int i=1;i<=getRows();i++)
        {
            if (!vals.contains(i) && isValidSum(x,y,i))
            {
                temp.add(i);
            }
        }
        //προσθέτει στο πίνακα εξόδου τις επιστρεπόμενες τιμές.
        int[] output=new int[temp.size()];
        for (int i=0;i<temp.size();i++)
        {
            output[i]=temp.get(i);
        }
        return output;
    }

    /**
     * Συνάρτηση που επιστρέφει τον πίνακα που δείχνει τις κενές θέσεις
     * του πίνακα.
     * @return ο πίνακας empty
     */
    public boolean[][] getEmpty(){return empty;}

    /**
     * Συνάρτηση που φορτώνει από το αρχείο ένα συγκεκριμένο παζλ
     * killer sudoku
     * @param number ο αριθμός του παζλ από 1-10
     */
    @Override
    public void loadPuzzle(int number)  {
        super.setId(number); //θέτει ως id τον αριθμό του παζλ
        try {
        File file=new File("killerGames.txt"); //το αρχείο με τα killer games


        Scanner scanner = new Scanner(file);
        ArrayList<KillerGroup> cages = new ArrayList<>();

        int count=1;

        //διατρέχει το αρχείο ώσπου να φτάσει το παζλ με αριθμό number
            // τα παζλ διαχωρίζονται με *
        while (scanner!=null && count!=number) {
            Scanner line = new Scanner(scanner.nextLine());
            String tot=line.next();

            if (tot.equals("*"))
                count++;
            }
            //διαβάζει από το αρχείο το άθροισμα για κάθε cage και τις συντεταγμένες των
            //κελιών του
        while (scanner.hasNext() ) {

            Scanner line = new Scanner(scanner.nextLine());//παίρνει την πρώτη γραμμή του αρχείου
            String tot=line.next(); //παίρνει το άθροισμα που βρίσκεται στην πρώτη θέση της γραμμής

            if (tot.equals("*")) //αν βρεθεί ο χαρακτήρας * Που δηλώνει το τέλος του παζλ σταματά
                break;
            //System.out.println(tot);
            int total = Integer.parseInt(tot);
            KillerGroup cage = new KillerGroup(total);
            String coordinate = line.next();//διαβάζει την συντεταγμένη του κελιού
            int row = Integer.parseInt(coordinate.substring(0, 1));//αγνοεί το κόμμα μεταξύ των αριθμών και διαβάζει
            // γραμμή και στήλη
            int column = Integer.parseInt(coordinate.substring(2));
            Cell cell = new Cell(row, column); //δημιουργεί το κελί και το προσθέτει στο παζλ
            board[row][column] = cell;
            //System.out.println(row+" " +column+" "+total);
            cage.addCell(cell);
            while (line.hasNext()) {//προσθέτει αν υπάρχουν συντεταγμένες των υπολοίπων κελιών
                coordinate = line.next();
                row = Integer.parseInt(coordinate.substring(0, 1));
                column = Integer.parseInt(coordinate.substring(2));
                cell = new Cell(row, column);
               // System.out.println(row+" " +column+" "+total);
                board[row][column] = cell;
                cage.addCell(cell);
            }

            cages.add(cage); //προσθέτει το cage στο παζλ
        }
            this.group = cages; //φορτώνει το σύνολο με τα cages του παζλ
        }catch (IOException e){System.err.println("No line in file");} //μήνυμα λάθους αν συμβεί σφάλμα με το αρχείου


    }

    /**
     * Μέθοδος που επιστρέφει αν το παιχνίδι έχει τελειώσει
     * επειδή δεν υπάρχουν κενά κελιά.
     * @return true αν το παιχνίδι τελείωσε αλλιώς false
     */
    @Override
    public boolean isOver() {
        for (int i=0;i<getRows();i++)
        {
            for (int j=0;j<getColumns();j++)
            {
                if (board[i][j].getValue()==0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Μέθοδος που ελέγχει αν μια κίνηση είναι έγκυρη, δηλαδή αν ο αριθμός είναι στο διάστημα [1,4],
     * αν δεν υπάρχει στην ίδια γραμμή, στήλη και κουτί.
     * @param x η συντεταγμένη στον άξονα x που θέλουμε να τοποθετήσουμε τον αριθμο number
     * @param y η συντεταγμένη στον άξονα y που θέλουμε να τοποθετήσουμε τον αριθμο number
     * @param number η τιμή που θέλουμε να ελέγξουμε
     * @return true αν είναι έγκυρη αλλιώς false
     */
    @Override
    public boolean validMove(int x, int y, int number) {
        return !super.inRow(x,y,number) && !super.inColumn(x, y, number) && !super.inBox(x,y,number) &&super.inRange(x,y);
    }

    /**
     * Μέθοδος που επιστρέφει το κελί στη θέση (x,y) του παζλ
     * @param x η γραμμή του κελιού
     * @param y η στήλη του κελιού
     * @return το κελί
     */
    public Cell getBoard(int x, int y)
    {return super.getBoard(x,y);}
}
