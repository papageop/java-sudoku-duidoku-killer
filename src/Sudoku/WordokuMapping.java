package Sudoku;

import java.util.HashMap;

/**
 * Κλάση που δημιουργεί μια σχέση key-value μεταξύ
 * αριθμών 1-9 και γραμμάτων Α-I με τη χρήση HashMap
 * για το παιχνίδι Wordoku
 * @author Kakazianis Giorgos
 */
public class WordokuMapping {

    private HashMap<String, String> wordokuMap;
    public void createWordokuMap() {

        int var = 65;
        wordokuMap = new HashMap<>();
        for (Integer i = 1; i <= 9; i++) {
            wordokuMap.put(i.toString(), String.valueOf(((char) var)));
            var++;
        }
        var=65;
        for (Integer i = 1; i <= 9; i++) {
            wordokuMap.put(String.valueOf(((char) var)), i.toString());
            //System.out.println(String.valueOf(((char) var))+" "+ i.toString());
            var++;
        }
    }
    public String getWordoku(String s)
    {
        return wordokuMap.get(s);
    }
}


