package service;

import java.util.Map;

import model.AuthSession;
import model.UserCuentas;

public class AuthSystem {
    
  private PoliticaDefinida politica;
  private AuthSession session;
  private TOTPService totp;
  private UserCuentas Cuentas;
  //Luego se impelemtna lo de faoctrAutneticaicon

    public AuthSystem(){
        this.session = new AuthSession();
        this.politica = new PoliticaDefinida();
        this.totp = new TOTPService();
        this.Cuentas = new UserCuentas();
    }

   
    public 









    

    
}



