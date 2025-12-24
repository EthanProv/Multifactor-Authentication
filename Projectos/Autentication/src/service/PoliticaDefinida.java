package service;

public class PoliticaDefinida {
    private int MaxIntentos = 3;

    public boolean Comprobar(int intentosFallidos) {
        return intentosFallidos >= MaxIntentos;
    }
    
}
