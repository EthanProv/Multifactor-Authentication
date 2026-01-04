package app;
import controller.*;
import model.*;
import service.*;
import view.*;
public class App {
    
    //MAIN
    public static void main(String[] args) {

        AuthSession sesion = new AuthSession();
        UserCuentas cuentas = new UserCuentas();

        AuthContoller controller = new AuthContoller(sesion, cuentas);

        Swing ventana = new Swing(controller);
        controller.setVentana(ventana);

        ventana.mostrarVentana(sesion.getEstado());
    }

}


