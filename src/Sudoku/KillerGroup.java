package Sudoku;

import java.util.ArrayList;



/**
 * Κλάση που υλοποιεί την ομάδα των κελιών στο killer sudoku που
 * έχουν ένα επιθυμητό άθροισμα.
 * @author Giorgos Kakazianis
 */
public class KillerGroup {
    private int sum; //το επιθυμητό άθροισμα των τιμών των κελιών της ομάδας
    private ArrayList<Cell> cells; //λίστα με όλα τα κελιά που ανήκουν στην ομάδα


    /**
     * Κατασκευαστής αντικειμένου της κλάσης KillerGroup που αρχικοποιεί τις μεταβλητές
     * @param sum το αθροισμα του KillerGroup
     */
    public KillerGroup(int sum)
    {
        super();
        this.sum=sum;
        this.cells=new ArrayList<>();

    }

    /**
     * Συνάρτηση που ελέγχει αν ένα αριθμός έχει τοποθετηθεί ξανά σε κελί του KillerGroup ελέγχοντας
     * αν υπάρχει στο σύνολο των τιμών values
     * @param value ο αριθμός που θέλουμε να ελέγξουμε αν υπάρχει
     * @return true αν υπάρχει αλλιώς false
     */
    public boolean contains(int value)
    {
        for (Cell cell:cells)
            if (cell.getValue()==value)
                return true;
        return false;
    }

    /**
     * Συνάρτηση που επιστρέφει αν το KillerGroup είναι γεμάτο, δηλαδή έχουν τιμές όλα
     * του τα κελιά.
     * @return true αν όλα τα κελιά έχουν τιμή αλλιώς false
     */
    public boolean isFull() {
        if (remaining() == 0)
            return true;
        else return false;
    }

    /**
     * Συνάρτηση που επιστρέφει τη λίστα με τα κελιά
     * του KillerGroup
     * @return η λίστα με τα κελιά
     */
    public ArrayList<Cell> getCells()
    {return cells;}

    /**
     * Συνάρτηση που επιστρέφει το πλήθος των κελιών του KillerGroup.
     * @return το πλήθος των κελιών
     */
    public int getLength()
    {return cells.size();}

    /**
     * Συνάρτηση που επιστρέφει το άθροισμα του KillerGroup.
     * @return το άθροισμα του KillerGroup
     */
    public int getSum()
    {return sum;}

    /**
     * Συνάρτηση που θέτει το άθροισμα του KillerGroup.
     * @param sum το άθροισμα που θέλουμε να θέσουμε
     */
    public void setSum(int sum)
    {
        if(sum>0)
            this.sum=sum;
    }

    /**
     * Συνάρτηση που προσθέτει ένα κελί στο KillerGroup.
     * @param cell το κελί που προστίθεται
     */
    public void addCell(Cell cell){
        cells.add(cell);
    }


    /**
     * Συνάρτηση που επιστρέφει το τρέχον άθροισμα των τιμών των κελιών
     * @return το τρέχον άθροισμα
     */
    public int getCurrentSum()
    {
        int s=0;
        for (Cell cell:cells)
            s+=cell.getValue();
        return s;
    }

    /**
     * Συνάρτηση που επιστρέφει πόσα κελιά είναι άδεια, δηλαδή έχουν τιμή 0
     * @return ο αριθμός των άδειων κελιών
     */
    public int remaining()
    {
        int count=0;
        for (Cell cell:cells)
        {
            if (cell.getValue()==0)
                count++;
        }
        return count;
    }


}
