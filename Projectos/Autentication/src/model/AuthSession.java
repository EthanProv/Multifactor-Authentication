package model;

public class AuthSession {
    
    private EstadoSesion estado;
    private User user;

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
        this.user = user; 
    }

    
}
