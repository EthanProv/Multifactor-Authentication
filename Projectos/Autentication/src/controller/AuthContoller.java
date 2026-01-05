package controller;

import javax.swing.JOptionPane;

import model.*;
import view.Swing;
import service.*;
import service.OTPService;


public class AuthContoller {
    
    private AuthSession sesion;
    private AuthSystem auth;
    private Swing ventana;

    public AuthContoller(AuthSystem auth){
        this.auth = auth;
        this.sesion = auth.getSession();
    }

    public void setVentana(Swing ventana){
        this.ventana = ventana;
    }

    public void actualizarVentana(){
        ventana.mostrarVentana(sesion.getEstado());
    }

    public void menuParaVolver(){
        sesion.setEstado(EstadoSesion.Esperando_Opcion);
    }

    public void cerrarSesion(){
        auth.logout();
        sesion.setEstado(EstadoSesion.Esperando_Opcion);
        actualizarVentana();
    }
    
    public void cambiarAProcesandoLogin(){
        sesion.setEstado(EstadoSesion.Procesando_Login);
        actualizarVentana();
    }

    public void cambiarAProcesandoMFA(){
        sesion.setEstado(EstadoSesion.Procesando_MFA);
        actualizarVentana();
    }
    
    public void añadirMFA(String mail, String usuario){

        boolean ok = auth.anadirMFA(usuario.trim(), mail.trim());

        if(usuario == null || usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduce tu nombre de usuario", "MFA", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(mail == null || mail.isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduce tu mail correspondiente", "MFA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ok) {
            JOptionPane.showMessageDialog(null, "El usuario no existe", "MFA", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "MFA activado para el usuario --> " + username, "MFA", JOptionPane.INFORMATION_MESSAGE);
        }
        sesion.setEstado(EstadoSesion.Esperando_Opcion);
        actualizarVentana();
    }

    public void procesarLogin(String usuario, String contraseña){

        User u = sesion.getUser();
        boolean ok = auth.login(usuario.trim(), contraseña);

        if(usuario == null || usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduce tu nombre de usuario", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(contraseña == null){
            contraseña = "";
        }

        if(u == null){
            JOptionPane.showMessageDialog(null, "El usuario no existe", "Login", JOptionPane.ERROR_MESSAGE);
            sesion.setEstado(EstadoSesion.Procesando_Login);
            actualizarVentana();
            return;
        }
        if(!ok){
            if(u.isLocked()){
                sesion.setEstado(EstadoSesion.Bloqueado);
            }else{
                sesion.setEstado(EstadoSesion.Procesando_Login);
            }
            actualizarVentana();
            return;
        }

        if(u.isActiveMFA()){
            sesion.setEstado(EstadoSesion.Procesando_Autenticacion);
        } else {
            sesion.setEstado(EstadoSesion.Sesion_Activa);
        }
        actualizarVentana();
        
    }

    public void introducirCodigo(String codigo){
        String c = codigo.trim();
        boolean ok = auth.verificarMFA(c);
        User u = sesion.getUser();

        if(c.length()!= 6 || !c.matches("\\d{6}")){//ESTO CON CHAT que nos ayudo a decir como es el formato
            JOptionPane.showMessageDialog(null, "Tiene que tener 6 digitos", "MFA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Para comprobar si el usuario esta bloqueado
        if(u != null && u.isLocked()){
            sesion.setEstado(EstadoSesion.Bloqueado);
            actualizarVentana();
            return;
        }
        //Para verificar que tenga MFA y el codigo introducido sea el correcto
        if(ok){
            sesion.setEstado(EstadoSesion.Sesion_Activa);
        }else{
            sesion.setEstado(EstadoSesion.Procesando_Autenticacion);
        }
        actualizarVentana();

    }

    public String ventanaMFA(){
        User u = sesion.getUser();
        if(u == null || !u.isActiveMFA() || u.getMfaCuenta() == null){
            return "-";
        }
        return auth.getOtp().generarOTP(u.getMfaCuenta().getSecret());
    }

    public int duracionDelCodigo(){
        return auth.getOtp().segundosRestantes();
    }
    
}

