package service;

public class OTPService {

    private static final int EXPIRA_SEGUNDOS = 45;

    public String generarOTP() {
        int numero = (int)(Math.random() * 900000) + 100000; // 100000..999999
        return String.valueOf(numero);
    }

    public long ahoraSegundos() {
        return System.currentTimeMillis() / 1000L;
    }

    public boolean expirado(long creadoEnSegundos) {
        return (ahoraSegundos() - creadoEnSegundos) > EXPIRA_SEGUNDOS;
    }

    public int segundosRestantes(long creadoEnSegundos) {
        long usados = ahoraSegundos() - creadoEnSegundos;
        long restantes = EXPIRA_SEGUNDOS - usados;
        return (int)Math.max(0, restantes);
    }
}
