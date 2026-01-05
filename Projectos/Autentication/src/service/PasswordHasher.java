package service;

public final class PasswordHasher  {
    
    public static String hash(String password){
        if (password == null) return "";
        return String.valueOf(password.hashCode());
        
    }

    public static boolean verify(String password, String guardarPasswor ){
        if (hash(password).equals(guardarPasswor)) return true;
        else return false;
    }

    

}



