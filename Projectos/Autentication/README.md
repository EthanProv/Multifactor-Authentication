# Multifactor-Authentication
## Quina es la funcio del programa

Hem creat aquest programa existeix garantir un accés segur al sistema mitjançant autenticació MFA i verificant la identitat dels usuaris amb un doble factor i protegint les dades davant accessos no autoritzats.

L’MFA (Multi-Factor Authentication) és un mètode de seguretat que requereix més d’un altre factor de verificació per confirmar la identitat d’un usuari, En aquest cas surtira un numero aleatori a la pantalla com a segon factor. Això augmenta la protecció contra accessos no autoritzats.

## Informacio important previa
No pots crear un usuari per tu mateix, a continuacio et deixo els usuaris que hi han disponibles
> - Usuari:  Jordig  Password: admin1
> - Usuari:  Ethan  Password: admin1
> - Usuari:  Jordib  Password: admin1
> - Usuari:  Marti  Password: admin1
> - Usuari:  a  Password: a

## Que es fa en el programa
Primer de tot dir que aquest programa es un autenticacio basica d'usuaris. Cada usuari te:
- Nom usuari
- Correu usuari
- Contrasenya (Per seguretat el programa usara un hash de contrasenya)
  
Dins del programa el que podras fer es:
- L’usuari pot afegir un compte MFA per inciciar sesio o iniciar sessió sense MFA
- Si inicia sessió sense credencials, el usuari unicament podra tancar sessio o sortir (tancar programa)
- Si l'usuari accedes a "Añadir cuenta MFA"
- Despues d'iniciar sessio podra activar el MFA
- Al sortir podra inciciar sessio amb MFA
- Tancar sessio
- Sortir

> [!IMPORTANT]
> Els correus dels usuaris son tots @enti.cat, exemple ethan@enti.cat

## Com esta fet el programa i que es necesita instalar
Multifactor-Authentication es un programa fet amb codi Java i Swing (librería gráfica de Java). A part per poder executar el programa cal instalar:
- Cal instal·lar Java (JRE o JDK)
Swing ja ve inclòs amb Java, no cal res més.

## Estructura del projecte
El codi java utilitza la arquitectura MVC (Model-Vista-Controlador) + Service i aixi separar logica, vista i models.
- App -> Main
- Controller -> Controlador del programa
- Model -> Totes les clases model
- Service -> Logica
- View -> Tot el codi de l'entorn grafic

## Execucio programa
Per executar el programa ara mateix s'ha de fer al Visual Studio com si fos un programa normal.

## Autors
Jordi Gomez Tome
Ethan Provencio Grau
Jordi Barberan Villa

