package model;
//ENUM que ira por cada estado
public enum EstadoSesion {
    Esperando_Opcion,
    Procesando_MFA,
    Procesando_Login,
    Procesando_Autenticacion,
    Sesion_Activa,
    Bloqueado
}