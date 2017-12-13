package social;

import java.io.Serializable;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitrios Simopoulos
 */
public class SocialNetwork implements Serializable {

    public Vector<User> users;

    public SocialNetwork() {
        users = new Vector<User>();
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void removeUser(User u) {
        users.remove(u);
    }

    public Vector<User> getUsers() {
        return users;
    }

    public int numberOfUsers() {
        return users.size();
    }
}
