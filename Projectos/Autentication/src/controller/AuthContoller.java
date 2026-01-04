package controller;

import model.*;
import view.Swing;
import service.*;

public class AuthContoller {
    
    private AuthSession sesion;
    private Swing ventana;
    private UserCuentas usuarioCuentas;

    private TOTPService totp = new TOTPService();

    private FactorAutenticacion factorContraseña = new FactorPassword();
    private FactorAutenticacion factorTOTP = new FactorTOTP();
    private PoliticaDefinida politica = new PoliticaDefinida();

    public AuthContoller(AuthSession sesion, UserCuentas usuarioCuentas){
        this.sesion = sesion;
        this.usuarioCuentas = usuarioCuentas;
    }

    public void setVentana(Swing ventana){
        this.ventana = ventana;
    }

    public void actualizarVentana(){
        ventana.mostrarVentana(sesion.getEstado());
    }
    

    public void cambiarAProcesandoLogin(){
        sesion.setEstado(EstadoSesion.Procesando_Login);
        actualizarVentana();
    }

    public void cambiarAProcesandoMFA(){
        sesion.setEstado(EstadoSesion.Procesando_MFA);
        actualizarVentana();
    }
    
    public void añadirMFA(String mail){
        MFACuenta cuenta = new MFACuenta(mail);
        sesion.setEstado(EstadoSesion.Esperando_Opcion);
        actualizarVentana();
    }

    public void procesarLogin(String usuario, String contraseña){
        
        User u = usuarioCuentas.findUser(usuario);

        if(u.checkPassword(contraseña) && u.checkUser(usuario)){
            if(u.isActiveMFA()){
                sesion.setEstado(EstadoSesion.Procesando_Autenticacion);
            }else{
                sesion.setEstado(EstadoSesion.Sesion_Activa);
            }
        }else{
            u.IncrementarIntentosFallidos();
            if(u.lock()){
                sesion.setEstado(EstadoSesion.Bloqueado);
            }
        }
        actualizarVentana();
        
    }

    public void introducirCodigo(String codigo){
        if(factorTOTP.verificar(sesion, codigo)){
            sesion.setEstado(EstadoSesion.Sesion_Activa);
        }else{
            sesion.setEstado(EstadoSesion.Bloqueado);
        }
        actualizarVentana();
    }
    
}
