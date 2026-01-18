package service;

import model.AuthSession;

public class FactorTOTP extends FactorAutenticacion {

    @Override
    public boolean verificar(AuthSession session, String input) {
        if (session == null)  {
            return false;
        }
        if (session.getOtpActual() == null){
            return false;
        } 

        String code;
        if(input == null){
            code = "";
        }else {
            code = input.trim();
        }
        return code.equals(session.getOtpActual());
    }

}

