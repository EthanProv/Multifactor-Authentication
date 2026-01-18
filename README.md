# Multifactor-Authentication
## Quina és la funció del programa?
Hem creat aquest programa que garanteix un accés segur al sistema mitjançant autenticació MFA, aconseguint la verificació de la identitat dels usuaris amb un doble factor i protegint les dades davant accessos no autoritzats.

El MFA (Multi-Factor Authentication) és un mètode de seguretat que requereix més d’un altre factor de verificació per confirmar la identitat d’un usuari, en aquest cas sortirà un número aleatori a la pantalla com a segon factor. Això augmenta la protecció contra accessos no autoritzats.

## Informació important previa
No és possible crear un usuari per tu mateix, a continuació es mostren els usuaris que hi ha disponibles.
> - Usuari:  Jordig  Password: admin1
> - Usuari:  Ethan  Password: admin2
> - Usuari:  Jordib  Password: admin3
> - Usuari:  Marti  Password: admin4

## Quin procediment segueix el programa?
Per començar, aquest programa és una autenticació bàsica d'usuaris. Cada usuari te:
- Nom d'usuari
- Correu electrònic
- Contrasenya (per seguretat el programa utilitza un hash de contrasenya)
  
Un cop arranca el programa, hi ha diverses opcions:
- Iniciar sessió --> Al pressionà el botó s'ha d'introduir nom d'usuari i contrasenya
- Afegir compte MFA --> Al pressionà el botó s'ha d'introduir nom d'usuari i correu
- Sortir --> Al pressionà el botó finalitza el programa

Casos a tindre en compte:
- Si s'inicia sessió sense afegir compte MFA, no saltarà el procediment de doble factor i es podrà tancar la sessió o sortir del programa
- En cas contrari, saltarà el procediment de doble factor per introduir el codi que apareix en una nova finestra, i un cop s'ha introduït el codi es podrà tancar la sessió o sortir del programa

> [!IMPORTANT]
> Els correus dels usuaris son tots @enti.cat, exemple ethan@enti.cat

## Com està fet el programa i que cal instal·lar?
Multifactor-Authentication és un programa fet amb codi Java i Swing (llibreria gràfica de Java). A part per poder executar el programa cal instal·lar:
- Java (JRE o JDK), Swing ja ve instalat per defecte amb Java

## Estructura del projecte
El codi java utilitza l'arquitectura MVC (Model-Vista-Controlador) + Service i així separar lògica, vista i models.
- App --> Main
- Controller --> Controlador del programa
- Model --> Totes les classes model
- Service --> Lògica
- View --> Tot el codi de l'entorn gràfic

## Execució del programa
Per executar el programa ara mateix s'ha de fer al Visual Studio Code com si fos un programa normal.

## Limitacions del programa
- Usuaris predefinits
  - No es permet crear, modificar ni eliminar usuaris, limitant així l'escalabilitat
- Interfície gràfica senzilla
  - Swing és funcional i permet realitzar el necessari, però és poc moderna i limitada
- Persistència de dades limitada
  - Els usuaris no es guarden en una base de dades, un cop finalitza el programa es perd la informació
 
## Possibles millores futures
- MFA real
  - Envia el codi MFA per múltiples vies com: correu electrònic, SMS o aplicacions d'autenticació (Google Atuhenticator)
- Millorar la interficie gràfica
  - A l'haver aplicat l'arquitectura MVC, seria possible migrar a altres interfícies gràfiques com JavaFX
- Logs
  - Registrar intents d'inici de sessió, que indiquin data, hora i estat
- Millorar l'algoritme de generació de codis
  - Aplicar un altre algoritme per generar els codis d'autenticació seria ideal per millorar la seguretat

## Com s'ha treballat?
En aquest projecte hem utilitzat GitHub per al control de versions i la gestió del codi, Visual Studio Code amb Github Desktop com a entorn de desenvolupament per programar, provar l’aplicació i anant fent actualitzacions de com ha anat avançant el programa, a més hem fet servir una extensió del Visual Studio Code anomenada Live Share per treballar de manera col·laborativa amb tots els membres de l’equip.

## Autors
- Jordi Gómez Tomé. jordi.gomez@estudiant.enti.cat
- Ethan Provencio Grau. ethan.provencio@estudiant.enti.cat
- Jordi Barberan Villa. jordi.barberan@estudiant.enti.cat
