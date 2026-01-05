package view;
import javax.swing.*;
import java.awt.*;

import controller.AuthContoller;
import model.EstadoSesion;

public class Swing {
    
    private JFrame frame;
    private JPanel panel;
    private AuthContoller controller;
    
        
    private JFrame mfaFrame;
    private JLabel mfaCodeLabel;
    private JLabel mfaCountdownLabel;
    private javax.swing.Timer mfaTimer;

    public Swing(AuthContoller controller){

        this.controller = controller;
        
        frame = new JFrame("Authenticator MFA"); //Nombre de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar la aplicacion se termina el programa
        frame.setSize(900, 600);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,5,5)); //Para alinear los componentes de la vista, botones, campos de texto etc
        frame.add(panel);

        frame.setLocationRelativeTo(null); //La app se abre en el centro de la pantalla
        frame.setVisible(true); //Para que se muestre la ventana
    }

    public void mostrarVentana(EstadoSesion estado){
        panel.removeAll(); // Se hace para que al cambiar de estado se muestren otros componentes

        //Usar los 2 metodos que hay que crear para cuando se cambie el
        //estado a procesando_autenticacion

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
        //Poner un panel que muestre "Selecciona una opcion"

        //Para el estado Esperando_Opcion ponemos 2 botones
        JButton mfa = new JButton("Añadir cuenta MFA");
        JButton iniciarSesion = new JButton("Iniciar Sesion");
        /*hay que añadir las acciones de los botones, la clase authcontroller
        es la que debe de hacer las acciones correspondientes de los botones*/
        mfa.addActionListener(e -> controller.cambiarAProcesandoMFA());
        iniciarSesion.addActionListener(e -> controller.cambiarAProcesandoLogin());

        panel.add(mfa);
        panel.add(iniciarSesion);

    }

    private void menuProcesandoMFA(){
        //Cmbiar el metodo y poner 2 campos de texto 
        //y 2 botones con "activar MFA" y "volver"
        //Tambein poner la accion del boton de volver
        JTextField correo = new JTextField();
        JButton boton = new JButton("Añadir");
        /*añadir la accion del boton*/
        boton.addActionListener(e -> controller.añadirMFA(correo.getText()));

        panel.add(new JLabel("Correo electronico:"));
        panel.add(correo);
        panel.add(boton);
    }

    private void menuProcesandoLogin(){
        //Añadir el boton de volver
        JTextField usuario = new JTextField();
        JTextField contraseña = new JPasswordField();
        JButton boton = new JButton("Confirmar");
        /*añadir la accion del boton */
        boton.addActionListener(e -> controller.procesarLogin(usuario.getText(),contraseña.getText()));

        panel.add(new JLabel("Usuario:"));
        panel.add(usuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contraseña);
        panel.add(boton);
    }

    private void menuProcesandoAutenticacion(){
        //Añadir boton de volver pero que ponga cancelar operacion
        JTextField codigoTOTP = new JTextField();
        JButton boton = new JButton("Confirmar");
        /*añadir la accion del boton */
        boton.addActionListener(e -> controller.introducirCodigo(codigoTOTP.getText()));

        panel.add(new JLabel("Introducir codigo MFA:"));
        panel.add(codigoTOTP);
        panel.add(boton);
    }

    private void menuSesionActiva(){
        //Añadir 2 botones con cerrar sesion (este boton volvera para atras) y salir
        panel.add(new JLabel("Has iniciado sesion"));
    }

    private void menuBloqueado(){
        //2 botones de volver para al menu inicial y para terminar el programa
        panel.add(new JLabel("Se ha bloqueado la cuenta"));
    }

    //Añadir metodos para la ventana que se genera una vez el usuario
    //pasa al estado de autenticacion


}

