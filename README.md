# SimonSQLite
En este proyecto desarrollo una app para Android basada en el mítico juego Simon Says.

En la rama ViewModel podemos encontrar la versión más completa hasta el momento de esta aplicación.

La aplicación cuenta con hasta 3 pantallas:

Pantalla número 1 (MainActivity.kt):
En la pantalla de inicio o número 1 se le pedirá al usuario ingresar su alias o nick, y su contraseña.
Si los datos introducidos por el usuario son correctos (es decir, si coinciden con los encontrados en la base de datos) 
la aplicación le llevará directamente hasta la pantalla número 3 (que es la pantalla donde se encuentra el juego 
propiamente dicho).
En la pantalla número uno también hay un botón que da la opción a crearte una nueva cuenta (para aquellos que aún no se hayan registrado).
Al clickar dicho botón, la aplicación nos llevará a la pantalla número 2 (la pantalla para el registro).



Pantalla número 2 (SecondActivity.kt):
En la pantalla número 2 es la pantalla en que el usuario podrá crearse una nueva cuenta si aún no la tiene.
En dicha pantalla, se le pedirá al usuario que rellene los siguientes campos para el registro:
- Nickname.
- Nombre.
- Primer apellido.
- Contraseña.

Al hacer click en botón Registrarse, el programa primero comprobará que el nick introducido no exista ya en la base de datos.
Si el nick ya existe se le avisará al usuario para que lo cambie, y en caso contrario se registrarán todos los datos introducidos para crear la nueva cuenta de usuario en nuestra base de datos, y se llevará al usuario de nuevo a la pantalla número uno, donde se le pedirá iniciar sesión con su nueva cuenta.



Pantalla número 3 (ThirdActivity.kt):
En esta pantalla el usuario podrá comenzar a jugar con Simon Says.
En el layout se muestran cuatro colores (verde, amarillo, rojo, azul),
en la parte superior derecha un marcador que indica la ronda en la que se encuentra el usuario en cada momento 
y un botón empezar que al pulsarlo generará una nueva secuencia de colores
(se iluminarán los botones en el órden correspondiente que marque la secuencia aleatoria). 

Una vez se haya mostrado la secuencia, el usuario tiene que hacer click sobre los colores repitiendo 
la misma tal y como le fue mostrada.
Si falla al intentar repetir la secuencia, vuelve de nuevo a la ronda 0.
Si acierta la secuencia, pasa a la siguiente ronda, e irá subiendo rondas mientras siga acertando de forma consecutiva las secuencias que se le muestran.
Cuando el usuario supere un record establecido, éste se actualizará en la base de datos.



Esta aplicación se podrá ejecutar en los dispositivos Android de la versión Android 8.0 en adelante.


