package service;

import model.AuthSession;

public class FactorTOTP extends FactorAutenticacion {
    private TOTPService totpService;

    @Override
    public boolean verificar(AuthSession session, String input){

        return false;
    }

    
    
}
