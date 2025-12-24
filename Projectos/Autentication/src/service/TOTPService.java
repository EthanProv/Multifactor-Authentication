package service;

public class TOTPService {
    private static final int PeriodSecond = 45;
    private static final int Digits = 6;

    public String generar(String secret) {
        //Para generar el codigo TOTP
        return null;
    }

    public boolean validar(String secret, String code) {
        //Para validar el codigo TOTP
        return false;
    }
    
    
}
