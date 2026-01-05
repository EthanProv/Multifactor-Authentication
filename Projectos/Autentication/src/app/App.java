package app;
import controller.*;
import model.*;
import service.*;
import view.*;
public class App {
    
    //MAIN
    public static void main(String[] args) {

        UserCuentas cuentas = new UserCuentas();
        AuthSystem auth = new AuthSystem(cuentas);
        
        AuthContoller controller = new AuthContoller(auth);

        Swing ventana = new Swing(controller);
        controller.setVentana(ventana);

        ventana.mostrarVentana(auth.getSession().getEstado());
    }

}


