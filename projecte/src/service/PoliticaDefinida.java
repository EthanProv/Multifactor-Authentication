package service;

public class PoliticaDefinida {
    private final int maxIntentos;

    public PoliticaDefinida(){
        this(3);
    }

    public PoliticaDefinida(int maxIntentos){
        this.maxIntentos = Math.max(1, maxIntentos);
    }
    
    public boolean comprobar(int intentosFallidos) {
        return intentosFallidos >= maxIntentos;
    }

}



