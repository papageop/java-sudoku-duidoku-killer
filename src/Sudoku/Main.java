package Sudoku;

import GUI.EventHandling;
import GUI.GameInstance;

import java.io.IOException;

/**
 * H main συνάρτηση της εφαρμογής από όπου
 * τρέχει.
 * @author Paraskevi Papageorgiou
 */
public class Main {
    public static void main(String[] args) throws IOException {
        
        GameInstance instance=GameInstance.getInstance();
        EventHandling handling=new EventHandling(instance);

         }
}
