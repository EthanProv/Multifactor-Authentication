package app;

import model.EstadoSesion;

public class App {  
    //MAIN
    public static void main(String[] args) {
        App app = new App();
        app.iniciar();
    }

    public void iniciar(){
        EstadoSesion estado = EstadoSesion.Esperando_Opcion;
        boolean continuar = true;
        new view.Swing();
        
        do{
            switch (estado) {
                case Esperando_Opcion:
                    
                    break;
                case Procesando_MFA:
                    
                    break;
                case Procesando_Login:

                    break;
                case Procesando_Autenticacion:

                    break;
                case Sesion_Activa:

                    break;
                case Bloqueado:
                    return;
            }
        }while(continuar);
    }
}


