package service;

import model.AuthSession;
import model.MFACuenta;
import model.User;


public class FactorTOTP extends FactorAutenticacion {


    @Override
    public boolean verificar(AuthSession session, String input) {
        if (session == null)  {
            return false;
        }
        if (session.getOtpActual() == null){
            return false;
        } 

        String code = (input == null) ? "" : input.trim();
        return code.equals(session.getOtpActual());
    }

}

