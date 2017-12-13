package gr.aegean.math.sn;

import java.util.Vector;

public class User {

    String username;
    String birthday;
    String mail;

    public String getUsername() {
        return this.username;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getMail() {
        return this.mail;
    }

    public User(String username, String birthday, String mail) {
        this.username = username;
        this.birthday = birthday;
        this.mail = mail;
    }
    Vector<User> friends = new Vector<User>();

    public void addFriend(User other) {
        if (this == other) {
            return;
        }
        other.request(this);
        friends.add(other);
    }

    private void request(User first) {
        friends.add(first);
    }

    public Vector<User> getFriends() {
        return this.friends;
    }

    @Override
    public String toString() {
        return username;
    }

}
