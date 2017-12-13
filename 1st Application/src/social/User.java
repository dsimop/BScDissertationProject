package social;

import java.util.Vector;

public class User {

    String name;
    String date;
    String mail;
    Vector<User> friends;

    public User(String name, String date, String mail) {
        this.name = name;
        this.date = date;
        this.mail = mail;
        friends = new Vector<User>();
    }

    public Vector<User> getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMail() {
        return mail;
    }

    public void setName(User u) {
        this.name = u.name;
    }

    public void setDate(User u) {
        this.date = u.date;
    }

    public void setEMail(User u) {
        this.mail = u.mail;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addFriend(User u) {
        if ((!friends.contains(u)) && (this != u)) {
            //***Συμμετρική σχέση φίλων!***//
            u.request(this);
            this.request(u);
        }
    }

    public void removeFriend(User u) {
        u.dlt(this);
        this.dlt(u);
    }

    public void request(User w) {
        friends.add(w);
    }

    public void dlt(User w) {
        friends.remove(w);
    }
}
