package model;

public class AuthSession {
    private EstadoSesion estado;
    private User user;

    public AuthSession(User user) {
        this.user = user;
        this.estado = EstadoSesion.Esperando_Opcion;
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
        
    }

    
}
