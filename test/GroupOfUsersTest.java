import Sudoku.GroupOfUsers;
import Sudoku.User;
import org.junit.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Iterator;

public class GroupOfUsersTest  {

    GroupOfUsers users;
    public void setUp() throws Exception {
        users=new GroupOfUsers();
        users.addUser("mike");
        users.addUser("sofia");

    }

    public void tearDown() throws Exception {
    }

    public void testContains() {
        assertTrue(users.contains("mike"));
        assertTrue(users.contains("sofia"));
        assertFalse(users.contains("maria"));

    }

    public void testFindUserNickname() {
        assertEquals("mike",users.findUserNickname("mike").getNickname());
    }
    public void testAddUser() {
        users.addUser("dimitris");
        assertEquals("dimitris",users.findUserNickname("dimitris").getNickname());
    }



    public void testGetUsers() {
        HashSet<User> set=new HashSet<>();
        users=new GroupOfUsers();
        users.addUser("mike");
        set.add(new User("mike"));
        assertEquals(set.iterator().next().getNickname(),users.getUsers().iterator().next().getNickname());
    }

    public void testReadUsersFile() {
        users=new GroupOfUsers();
        users.readUsersFile();
        assertTrue(users.contains("maira"));
        assertTrue(users.contains("nick"));
        HashSet<User> set=users.getUsers();
        Iterator<User> it=set.iterator();
        User user=it.next();
        assertEquals(0,user.getPreviousClassic());
        assertEquals(1,user.getWins());
        assertEquals(1,user.getWins());
        user=it.next();


    }

}