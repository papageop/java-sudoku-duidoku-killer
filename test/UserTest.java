import Sudoku.User;
import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user1;
    private User user2;
    public void setUp() throws Exception {
        user1=new User("nick23");
        user2=new User("kali");
    }

    public void tearDown() throws Exception {
    }

    public void testSetWins() {
        user1.setWins(-5);
        assertEquals(user1.getWins(),0,0.001);
        user1.setWins(2);
        assertEquals(user1.getWins(),2,0.001);

    }

    public void testSetLosses() {
        user2.setLosses(-1);
        assertEquals(user2.getLosses(),0,0.001);
        user2.setLosses(3);
        assertEquals(user2.getLosses(),3,0.001);
        user2.setLosses(0);
        assertEquals(user2.getLosses(),0,0.001);
    }

    public void testSetNickname() {
        user2.setNickname("mark");
        assertEquals(user2.getNickname(),"mark");
    }

    public void testAddWin() {
        user1.setWins(2);
        user1.addWin();
        assertEquals(user1.getWins(),3);
    }

    public void testAddLoss() {
        user2.setLosses(0);
        user2.addLoss();
        assertEquals(user2.getLosses(),1);
    }

    public void testSetPreviousClassic() {
        user1.setPreviousClassic(-1);
        assertEquals(user1.getPreviousClassic(),0);
        user1.setPreviousClassic(2);
        assertEquals(user1.getPreviousClassic(),2);
    }

    public void testSetPreviousKiller() {
        user2.setPreviousKiller(-3);
        assertEquals(user2.getPreviousKiller(),0);
        user2.setPreviousKiller(1);
        assertEquals(user2.getPreviousKiller(),1);
    }

    public void testGetNickname() {
        assertEquals(user1.getNickname(),"nick23");
    }

    public void testGetWins() {
        user2.setWins(0);
        assertEquals(user2.getWins(),0);
    }

    public void testGetLosses() {
        user1.setLosses(5);
        assertEquals(user1.getLosses(),5);
    }

    public void testGetPreviousClassic() {
        User user=new User("new");
        assertEquals(user.getPreviousClassic(),0);
    }

    public void testGetPreviousKiller() {
        User user =new User("new");
        user.setPreviousKiller(2);
        assertEquals(user.getPreviousKiller(),2);
    }
}