package service;

import model.AuthSession;
import model.User;

public class FactorTOTP extends FactorAutenticacion {
    private final TOTPService totpService = new TOTPService();

    @Override
    public boolean verificar(AuthSession session, String input){
        User u = session.getUser();
        if(u == null || !u.isActiveMFA()){
            return false;
        }
       
       // despues de poner la contraseña que salga un input de el codigo mfa
       // como dijo ethan que salga otra ventana con el numero generado en la clase topservice
       // luego en el controller comparamos el texto con el de topservice y ya
        
        return totpService.validar(session.getUser().getMfa().getSecret(), input);
    }  
}
