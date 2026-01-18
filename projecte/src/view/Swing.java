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
        
        panel.add(new JLabel("Selecciona una opción", SwingConstants.CENTER));
        
        JButton mfa = new JButton("Añadir cuenta MFA");
        JButton iniciarSesion = new JButton("Iniciar sesión");
        
        mfa.addActionListener(e -> controller.cambiarAProcesandoMFA());
        iniciarSesion.addActionListener(e -> controller.cambiarAProcesandoLogin());

        panel.add(mfa);
        panel.add(iniciarSesion);

    }

    private void menuProcesandoMFA(){
        
        JTextField correo = new JTextField();
        JTextField usuario = new JTextField();
        JButton botonMFA = new JButton("Activar MFA");
        JButton botonVolver = new JButton("Volver");
        
        botonMFA.addActionListener(e -> controller.añadirMFA(usuario.getText(), correo.getText()));
        botonVolver.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Usuario", SwingConstants.CENTER));
        panel.add(usuario);
        panel.add(new JLabel("Correo electrónico", SwingConstants.CENTER));
        panel.add(correo);
        panel.add(botonMFA);
        panel.add(botonVolver);
    }

    private void menuProcesandoLogin(){
        
        JTextField usuario = new JTextField();
        JTextField contraseña = new JPasswordField();
        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        JButton botonVolver = new JButton("Volver");
        
        botonIniciarSesion.addActionListener(e -> controller.procesarLogin(usuario.getText(),new String(contraseña.getText())));
        botonVolver.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Usuario", SwingConstants.CENTER));
        panel.add(usuario);
        panel.add(new JLabel("Contraseña", SwingConstants.CENTER));
        panel.add(contraseña);
        panel.add(botonIniciarSesion);
        panel.add(botonVolver);
    }

    private void menuProcesandoAutenticacion(){
        
        JTextField codigoOTP = new JTextField();
        JButton botonCodigo = new JButton("Confirmar");
        JButton botonCancelar = new JButton("Cancelar");
        
        botonCodigo.addActionListener(e -> controller.introducirCodigo(codigoOTP.getText()));
        botonCancelar.addActionListener(e -> controller.menuParaVolver());

        panel.add(new JLabel("Introduce el código de 6 digitos que se muestra en la ventana nueva", SwingConstants.CENTER));
        panel.add(codigoOTP);
        panel.add(botonCodigo);
        panel.add(botonCancelar);
    }

    private void menuSesionActiva(){
        
        panel.add(new JLabel("Has iniciado sesión"));

        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        JButton botonSalir = new JButton("Salir");

        botonCerrarSesion.addActionListener(e -> controller.cerrarSesion());
        botonSalir.addActionListener(e -> System.exit(0));

        panel.add(botonCerrarSesion);
        panel.add(botonSalir);

    }

    private void menuBloqueado(){
        
        panel.add(new JLabel("Se ha bloqueado la cuenta"));

        JButton botonVolverMenu = new JButton("Volver al menú");
        JButton botonSalir = new JButton("Salir");

        botonVolverMenu.addActionListener(e -> controller.menuParaVolver());
        botonSalir.addActionListener(e -> System.exit(0));

        panel.add(botonVolverMenu);
        panel.add(botonSalir);
    }

    private void mostrarVentanaMFA(){
        if (mfaFrame != null && mfaFrame.isDisplayable()){
            return;
        } 
        JLabel emailLabel = new JLabel(controller.email(), SwingConstants.CENTER);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mfaFrame = new JFrame("Código Multifactor");
        mfaFrame.setSize(360, 240);
        mfaFrame.setLocationRelativeTo(null);
        mfaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mfaCodeLabel = new JLabel("------", SwingConstants.CENTER);
        mfaCodeLabel.setFont(new Font("Arial", Font.BOLD, 48));

        mfaCountdownLabel = new JLabel("", SwingConstants.CENTER);

        JPanel p = new JPanel(new GridLayout(0,1,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        p.add(new JLabel("Código MFA actual de: "   , SwingConstants.CENTER));
        p.add(emailLabel);
        p.add(mfaCodeLabel);
        p.add(mfaCountdownLabel);
        
        mfaFrame.setContentPane(p);
        // Actualiza cada 1 segundo
        mfaTimer = new javax.swing.Timer(1000, e -> {
            controller.refrescarCodigoSiExpira();
            mfaCodeLabel.setText(controller.codigoActual());
            mfaCountdownLabel.setText("Cambia en " + controller.segundosRestantesCodigo() + " s");
        });
        mfaTimer.start();
        // Primera carga
        mfaCodeLabel.setText(controller.codigoActual());
        mfaCountdownLabel.setText("Cambia en " + controller.segundosRestantesCodigo() + " s");

        mfaFrame.setVisible(true);
    
    }

    private void cerrarVentanaMFA(){
        if (mfaTimer != null) {
            mfaTimer.stop();
            mfaTimer = null;
        }

        if (mfaFrame != null) {
            mfaFrame.dispose();
            mfaFrame = null;
        }

    }

}