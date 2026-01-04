package app;

import controller.AuthContoller;
import model.EstadoSesion;
import view.Swing;

public class App {  
    //MAIN
    public static void main(String[] args) {
        EstadoSesion estado = EstadoSesion.Esperando_Opcion;
        AuthContoller controller = new AuthContoller();
        Swing ventana = new Swing(controller);
        ventana.mostrarVentana(estado);
        
    }

    
}


