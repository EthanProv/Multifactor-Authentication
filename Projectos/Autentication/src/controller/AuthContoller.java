package controller;
import java.util.Scanner;

import model.AuthSession;

public class AuthContoller {
    Scanner sc = new Scanner(System.in);
    AuthSession authSession = new AuthSession();
    int opcion;
    
    public void programa() {
        do {
            System.out.println("1. Inicuar session");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Inserta tu nombre de usuario: ");
                    String usuario = sc.nextLine();
                    System.out.print("Inserta tu contraseña: ");
                    String contrasena = sc.nextLine();

                    break;
                
                
                    case 0:
                    System.out.println("Adios");
                    break;

                default:
                    System.out.println("Opcion incorrecta");
            }

            System.out.println();

        } while (opcion != 0);

        sc.close();
    }
}
