package model;

import java.util.ArrayList;

import service.PasswordHasher;

public class User {
    //FALTA CREAR EL ARRAYLIST
    private int id;
    private String name;
    private String passwordHash;
    private String password;
    private int intentosFallidos;
    private boolean ActiveMFA;
    private boolean locked;
    // AQUI PODEMOS PONER LA VARIABLE SESSION y borramos la clase
    private MFACuenta mfa;

    private String mfaEmail;
    
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.passwordHash = PasswordHasher.hash(password);
        this.password = password;
        mfaEmail = name + "@enti.cat";
        this.intentosFallidos = 0;
        this.locked = false;
        this.mfa = null;
        this.ActiveMFA = false;
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

    public MFACuenta getMfa() {
        return mfa;
    }

    public String getMFAEmail(){
        return mfaEmail;
    }

    public void activarMFA(String mail){
        this.mfaEmail = mail;
        this.ActiveMFA = true;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    //Metodos:
    public boolean checkPassword(String Password) {
        if (locked){
            return false;
        }
        return this.password.equals(Password);
    
    }

    public boolean checkUser(String usuario) {
        if (locked){
            return false;
        }
        return this.name.equals(usuario);
    
    }

    public void IncrementarIntentosFallidos() {
        this.intentosFallidos++;
    }

    public void ResetIntentosFallidos() {
        this.intentosFallidos = 0;
    }

    public boolean lock(){
        return locked;
    }    
}
