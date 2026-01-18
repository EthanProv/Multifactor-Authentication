package service;

import model.AuthSession;
import model.User;
import model.UserCuentas;

public class AuthSystem {

  private final UserCuentas cuentas;
  private final AuthSession session;
  private final PoliticaDefinida politica;
  private final OTPService otp;
  private final FactorAutenticacion factorPassword;
  private final FactorAutenticacion factorTOTP;

  public AuthSystem(UserCuentas cuentas) {
    this.cuentas = cuentas;
    this.session = new AuthSession();
    this.politica = new PoliticaDefinida(3);
    this.otp = new OTPService();
    this.factorTOTP = new FactorTOTP();
    this.factorPassword = new FactorPassword();
        
  }

  public AuthSession getSession() { 
    return session; 
  }

  public PoliticaDefinida getPolitica() { 
    return politica; 
  }

  public OTPService getOtp() { 
    return otp; 
  }

  public User buscarUser(String username) {
    if (username == null) return null;
    return cuentas.findUser(username.trim());
  }

  public boolean anadirMFA(String username, String email) {
    User u = buscarUser(username);

    if (u == null) return false;

    if (email == null || email.trim().isEmpty()){
      return false;
    } 

    if(!email.contains("@") || !email.contains(".")){
      return false;
    }

    String secret = Integer.toHexString((u.getUsername() + ":" + System.nanoTime()).hashCode()).toUpperCase(); //Revisar 
    u.activarMFA(email.trim(), secret);

    return true;
  }

  
  public boolean login(String username, String password) {
    User u = buscarUser(username);
    session.setUser(u);

    if(u == null){
      return false;
    } 

    if(u.isLocked()) {
      return false;
    }

    boolean ok = factorPassword.verificar(session, password);

    if(ok){
      u.resetIntentosFallidos();

      if (u.isActiveMFA()) {
        String code = otp.generarOTP();
        session.setOtp(code, otp.ahoraSegundos());
      } else {
        session.clearOtp();
      }
    }else{
      u.incrementarIntentosFallidos();
      if(politica.comprobar(u.getIntentosFallidos())){
        u.lock();
      }
    }
    return ok;
  }

   
  public boolean verificarMFA(String code) {
    User u = session.getUser();

    if (u == null) return false;
    if (u.isLocked()) return false;

    if (session.getOtpActual() == null) return false;

    if(otp.expirado(session.getOtpCreadoEnSegundos())){
      String nuevo = otp.generarOTP();
      session.setOtp(nuevo, otp.ahoraSegundos());
      return false;
    }

    boolean ok = factorTOTP.verificar(session, code);

    if(ok){
      u.resetIntentosFallidos();
      session.clearOtp();
    }else{
      u.incrementarIntentosFallidos();
      if(politica.comprobar(u.getIntentosFallidos())){
        u.lock();
      }
    }

    return ok;
  }

  public String otpActual() {
    if (session.getOtpActual() == null) {
      return "------";
    }

    return session.getOtpActual();
  }

  public int segundosRestantesOTP() {
    if(session.getOtpActual() == null){
      return 0;
    }
    return otp.segundosRestantes(session.getOtpCreadoEnSegundos());
  }


  public void refrescarOtpSiExpira() { 
    // Solo si hay usuario y MFA activo
    User u = session.getUser();
    if (u == null) return;
    if (!u.isActiveMFA()) return;

    // Si no hay OTP, crea uno
    if(session.getOtpActual() == null){
      String nuevo = otp.generarOTP();
      session.setOtp(nuevo, otp.ahoraSegundos());
      return;
    }

    // Si ha expirado, genera otro
    if(otp.expirado(session.getOtpCreadoEnSegundos())){
      String nuevo = otp.generarOTP();
      session.setOtp(nuevo, otp.ahoraSegundos());
    }
}


    

  public void logout() {
    session.setUser(null);
    session.clearOtp();
  }

}