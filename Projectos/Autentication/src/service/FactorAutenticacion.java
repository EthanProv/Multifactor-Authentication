package service;

import model.AuthSession;

public abstract class FactorAutenticacion {
    private String nombre;
    public abstract boolean verificar(AuthSession session, String input);

}
