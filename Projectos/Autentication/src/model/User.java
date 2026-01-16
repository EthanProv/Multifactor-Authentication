package model;

import service.PasswordHasher;


public class User {

    private final int id;
    private final String username;
    private String email;

    private String passwordHash;

    private int intentosFallidos;
    private boolean locked;

    // Composición 0..1
    private MFACuenta mfaCuenta;

    public User(int id, String username, String passwordPlain) {
        this.id = id;
        this.username = username;
        email = username + "@enti.cat";
        this.passwordHash = PasswordHasher.hash(passwordPlain);
        this.intentosFallidos = 0;
        this.locked = false;
        this.mfaCuenta = null;
    }

    public int getId(){ 
        return id; 
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){ 
        return username; 
    }

    public String getName() {
        return username;
    }

    public int getIntentosFallidos(){ 
        return intentosFallidos; 
    }

    public boolean isLocked(){ 
        return locked; 
    }

    public MFACuenta getMfaCuenta(){ 
        return mfaCuenta;
    }

    public boolean isActiveMFA(){
        return mfaCuenta != null && mfaCuenta.isActivada();
    }


    



    public void activarMFA(String email, String secret){
        if (mfaCuenta == null) {
            mfaCuenta = new MFACuenta(email);
        }
        mfaCuenta.activar(secret);
    }

    


    public String getMFAEmail() {
        if (mfaCuenta == null){
            return null;
        } 
        return mfaCuenta.getEmail();
    }

    public boolean checkPassword(String plainPassword) {
        if (locked) return false;
        return PasswordHasher.verify(plainPassword, passwordHash);
    }

    public void incrementarIntentosFallidos() {
        intentosFallidos++;
    }

    public void resetIntentosFallidos() {
        intentosFallidos = 0;
    }

    public void lock() {
        locked = true;
    }
}
