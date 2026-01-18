package service;

import model.AuthSession;

public abstract class FactorAutenticacion {
    public abstract boolean verificar(AuthSession session, String input);

}
