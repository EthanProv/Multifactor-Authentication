package model;

public class User {

    private int id;
    private String name;
    private String passwordHash;
    private String password;
    private  int intentosFallidos;
    private boolean ActiveMFA;
    private boolean locked;
    public User(int id, String name, String passwordHash, String password, int intentosFallidos, boolean ActiveMFA) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.password = password;
        this.intentosFallidos = intentosFallidos;
        this.ActiveMFA = ActiveMFA;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public String getPassword() {
        return password;
    }
    public int getIntentosFallidos() {
        return intentosFallidos;
    }
    public boolean isActiveMFA() {
        return ActiveMFA;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    //Metodos:
    public boolean ChecckPassword(String Password) {
        if (locked){
            return false;
        }
        return this.password.equals(Password);
    
    }

    public void IncrementarIntentosFallidos() {
        this.intentosFallidos++;
    }

    public void ResetIntentosFallidos() {
        this.intentosFallidos = 0;
    }

    public void lock(){
        this.locked = true;
    }
    




    
}
