package model;

public class MFACuenta {
    private String email;
    private String secret;
    private boolean activar;


    public MFACuenta(String email){
        this.email = email;
        this.secret = null;
        this.activar = false;
    }
    
    public String getEmail() {
        return email;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isActivada() {
        return activar;
    }
    
    public void Activar(String secreto){
        this.secret = secreto;
        this.activar = true;
        
    }

    public void Desactivar(){
        this.activar = false;
    }
    
}
