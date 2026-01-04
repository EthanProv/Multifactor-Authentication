package service;

import model.AuthSession;

public class FactorPassword extends FactorAutenticacion {

    @Override
    public boolean verificar(AuthSession session, String input){
        return session.getUser().checkPassword(input);
    }
}
