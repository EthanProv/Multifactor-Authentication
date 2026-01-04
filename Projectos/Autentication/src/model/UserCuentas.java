package model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class UserCuentas {

    private List<User> usuarios = new ArrayList<>();

    public UserCuentas(){
        usuarios.add(new User(0, "Jordig", "admin1"));
        usuarios.add(new User(1, "Ethan", "admin2"));
        usuarios.add(new User(2, "Jordib", "admin3"));
    }

    public User findUser(String us) {
        for (User u : usuarios) {
            if (u.getName().equals(us)) return u;
        }
        return null;
    }

    public User findMail(String mail) {
        for (User u : usuarios) {
            if (u.getMFAEmail().equals(mail)) return u;
        }
        return null;
    }
}
