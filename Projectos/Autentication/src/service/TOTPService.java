package service;

public class TOTPService {
    private static final int PeriodSecond = 45;
    private static final int Digits = 6;

    public String generar(String secret) {
        //Para generar el codigo TOTP
        // Genera un numero aleatorio de 6 digitos
        int numero = (int)(Math.random() * 900000) + 100000;
        return numero + "";
    }

    public boolean validar(String secret, String code) {
        //Para validar el codigo TOTP
        return secret.equals(code);
    }


    public int segundosRestantes() {
        long now = System.currentTimeMillis() / 1000L;
        long next = ((now / PeriodSecond) + 1) * PeriodSecond;
        return (int)(next - now);
    }

    
    
}
