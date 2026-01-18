package service;

import model.AuthSession;

public class FactorPassword extends FactorAutenticacion {

    @Override
    public boolean verificar(AuthSession session, String input){
        if (session == null) return false;
        if (session.getUser() == null) return false;
        if (input == null) input = "";
        return session.getUser().checkPassword(input);
    }
}




