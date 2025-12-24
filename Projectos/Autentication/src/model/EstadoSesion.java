package model;
//ENUM que ira por cada estado
public enum EstadoSesion {
    Esperando_Opcion,
    AñadirMFA,
    Procesando_Login,
    Procesando_Autenticacion,
    Sesion_Activa,
    BLOQUEADO
}