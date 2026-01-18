package model;

public class AuthSession {
    
    private EstadoSesion estado;
    private User user;
    
    private String otpActual;
    private long otpCreadoEnSegundos;

    public AuthSession() {
        this.user = null;
        this.estado = EstadoSesion.Esperando_Opcion;
    }

    public AuthSession(User user) {
        this();
        this.user = user;
    }

    public EstadoSesion getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void Start(User usuario){
        this.user = usuario; 
    }
    
    public void setOtp(String otp, long creadoEnSegundos) {
        this.otpActual = otp;
        this.otpCreadoEnSegundos = creadoEnSegundos;
    }

    public String getOtpActual() {
        return otpActual;
    }

    public long getOtpCreadoEnSegundos() {
        return otpCreadoEnSegundos;
    }

    public void clearOtp() {
        this.otpActual = null;
        this.otpCreadoEnSegundos = 0;
    }
    
}
