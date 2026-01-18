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
    
    public String getEmail(){
        return email;
    }

    public String getSecret(){
        return secret;
    }

    public boolean isActivada(){
        return activar;
    }
    
    public void activar(String secreto){
        this.secret = secreto;
        this.activar = true;
        
    }

    public void desactivar(){
        this.activar = false;
        this.secret= null; //Ahora con el OPT ya no es importante
    }
    
}


