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
        //estado a procesando_autenticacion HECHO
        if(estado == EstadoSesion.Procesando_Autenticacion){
            mostrarVentanaMFA();
        }else{
            cerrarVentanaMFA();
        }

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
        //Poner un panel que muestre "Selecciona una opcion" HECHO
        panel.add(new JLabel("Selecciona una opcion", SwingConstants.CENTER));
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
        //Tambein poner la accion del boton de volver HECHO
        JTextField correo = new JTextField();
        JTextField usuario = new JTextField();
        JButton botonMFA = new JButton("Activar MFA");
        JButton botonVolver = new JButton("Volver");
        /*añadir la accion del boton HECHO*/
        botonMFA.addActionListener(e -> controller.añadirMFA(correo.getText(), usuario.getText()));
        botonVolver.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Usuario", SwingConstants.CENTER));
        panel.add(usuario);
        panel.add(new JLabel("Correo electronico:"));
        panel.add(correo);
        panel.add(botonMFA);
        panel.add(botonVolver);
    }

    private void menuProcesandoLogin(){
        //Añadir el boton de volver HECHO
        JTextField usuario = new JTextField();
        JTextField contraseña = new JPasswordField();
        JButton botonIniciarSesion = new JButton("Iniciar sesion");
        JButton botonVolver = new JButton("Volver");
        /*añadir la accion del boton HECHO*/
        botonIniciarSesion.addActionListener(e -> controller.procesarLogin(usuario.getText(),contraseña.getText()));
        botonVolver.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Usuario", SwingConstants.CENTER));
        panel.add(usuario);
        panel.add(new JLabel("Contraseña", SwingConstants.CENTER));
        panel.add(contraseña);
        panel.add(botonIniciarSesion);
        panel.add(botonVolver);
    }

    private void menuProcesandoAutenticacion(){
        //Añadir boton de volver pero que ponga cancelar operacion HECHO
        JTextField codigoOTP = new JTextField();
        JButton botonCodigo = new JButton("Confirmar");
        JButton botonCancelar = new JButton("Cancelar");
        /*añadir la accion del boton HECHO*/
        botonCodigo.addActionListener(e -> controller.introducirCodigo(codigoOTP.getText()));
        botonCancelar.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Introduce el codigo de 6 digitos que se muestra en la ventana nueva", SwingConstants.CENTER));
        panel.add(codigoOTP);
        panel.add(botonCodigo);
        panel.add(botonCancelar);
    }

    private void menuSesionActiva(){
        //Añadir 2 botones con cerrar sesion (este boton volvera para atras) y salir HECHO
        panel.add(new JLabel("Has iniciado sesion"));

        JButton botonCerrarSesion = new JButton("Cerrar sesion");
        JButton botonSalir = new JButton("Salir");

        botonCerrarSesion.addActionListener(e -> controller.cerrarSesion());
        botonSalir.addActionListener(e -> System.exit(0));

        panel.add(botonCerrarSesion);
        panel.add(botonSalir);

    }

    private void menuBloqueado(){
        //2 botones de volver para al menu inicial y para terminar el programa HECHO
        panel.add(new JLabel("Se ha bloqueado la cuenta"));

        JButton botonVolverMenu = new JButton("Volver al menu");
        JButton botonSalir = new JButton("Salir");

        botonVolverMenu.addActionListener(e -> controller.menuParaVolver());
        botonSalir.addActionListener(e -> System.exit(0));

        panel.add(botonVolverMenu);
        panel.add(botonSalir);
    }

    //Añadir metodos para la ventana que se genera una vez el usuario
    //pasa al estado de autenticacion
    private void mostrarVentanaMFA(){

    }

    private void cerrarVentanaMFA(){

    }

}

