package view;
import javax.swing.*;
import java.awt.*;

import controller.AuthContoller;
import model.EstadoSesion;

public class Swing {
    
    private JFrame frame;
    private JPanel panel;
    private AuthContoller controller;

    public Swing(AuthContoller controller){

        this.controller = controller;
        
        frame = new JFrame("Authenticator MFA"); //Nombre de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar la aplicacion se termina el programa
        frame.setSize(900, 600);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1)); //Para alinear los componentes de la vista, botones, campos de texto etc
        frame.add(panel);

        frame.setLocationRelativeTo(null); //La app se abre en el centro de la pantalla
        frame.setVisible(true); //Para que se muestre la ventana
    }

    public void mostrarVentana(EstadoSesion estado){
        panel.removeAll(); // Se hace para que al cambiar de estado se muestren otros componentes

        switch (estado) {
            case Esperando_Opcion:
                menuEsperandoOpcion();
                break;
            case Procesando_MFA:
                menuProcesandoMFA();
                break;
            case Procesando_Login:
                menuProcesandoLogin();
                break;
            case Procesando_Autenticacion:
                menuProcesandoAutenticacion();
                break;
            case Sesion_Activa:
                menuSesionActiva();
                break;
            case Bloqueado:
                menuBloqueado();
                break;
        }

        panel.revalidate();
        panel.repaint();
    }

    private void menuEsperandoOpcion(){
        //Para el estado Esperando_Opcion ponemos 2 botones
        JButton mfa = new JButton("Añadir cuenta MFA");
        JButton iniciarSesion = new JButton("Iniciar Sesion");
        /*hay que añadir las acciones de los botones, la clase authcontroller
        es la que debe de hacer las acciones correspondientes de los botones*/


        panel.add(mfa);
        panel.add(iniciarSesion);

    }

    private void menuProcesandoMFA(){
        JTextField correo = new JTextField();
        JButton boton = new JButton("Añadir");
        /*añadir la accion del boton*/


        panel.add(new JLabel("Correo electronico:"));
        panel.add(correo);
        panel.add(boton);
    }

    private void menuProcesandoLogin(){
        JTextField usuario = new JTextField();
        JTextField contraseña = new JPasswordField();
        JButton boton = new JButton("Confirmar");
        /*añadir la accion del boton */


        panel.add(new JLabel("Usuario:"));
        panel.add(usuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contraseña);
        panel.add(boton);
    }

    private void menuProcesandoAutenticacion(){
        JTextField codigoTOTP = new JTextField();
        JButton boton = new JButton("Confirmar");
        /*añadir la accion del boton */


        panel.add(new JLabel("Introducir codigo MFA:"));
        panel.add(codigoTOTP);
        panel.add(boton);
    }

    private void menuSesionActiva(){
        panel.add(new JLabel("Has iniciado sesion"));
    }

    private void menuBloqueado(){
        panel.add(new JLabel("Se ha bloqueado la cuenta"));
    }

}
