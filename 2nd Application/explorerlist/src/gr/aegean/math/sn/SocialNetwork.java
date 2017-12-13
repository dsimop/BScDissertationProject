package gr.aegean.math.sn;

import java.util.Vector;

public class SocialNetwork {

    Vector<User> users;

    public SocialNetwork() {
        users = new Vector<User>();
    }

    public Vector<User> getUsers() {
        return users;
    }
}
