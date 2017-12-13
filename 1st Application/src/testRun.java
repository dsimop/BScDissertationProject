/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dimitrios Simopoulos
 */
import social.SocialNetwork;
import social.User;
import userinterface.SocialNetworkWindow;

public class testRun {

    public static void main(String argv[]) {
        SocialNetwork sn = new SocialNetwork();
        User u = new User("U1", "18-05-1993", "u1@mail.com");
        User s = new User("U2", "15-03-1965", "u2@mail.com");
        User t = new User("U3", "22-01-1974", "u3@mail.com");
        sn.addUser(u);
        sn.addUser(s);
        sn.addUser(t);
        u.addFriend(s);
        u.addFriend(t);
        new SocialNetworkWindow(sn);
    }
}
