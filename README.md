# SimonSQLite
En este proyecto desarrollo una app para Android basada en el mítico juego Simon Says.

En la rama viewmodel podemos encontrar la versión más completa hasta el momento de esta aplicación.

La aplicación cuenta con hasta 3 pantallas:ç

Pantalla número uno (MainActivity.kt):
En la pantalla de inicio o número 1 se le pedirá al usuario ingresar su alias o nick, y su contraseña.
Si los datos introducidos por el usuario son correctos (es decir, si coinciden con los encontrados en la base de datos) 
la aplicación le llevará directamente hasta la pantalla número 3 (que es la pantalla donde se encuentra el juego 
propiamente dicho).
En la pantalla número uno también hay un botón que da la opción a crearte una nueva cuenta (para aquellos que aún no se hayan registrado).
Al clickar dicho botón, la aplicación nos llevará a la pantalla número 2 (la pantalla para el registro).

Pantalla número dos (SecondActivity.kt):
En la pantalla número 2 es la pantalla en que el usuario podrá crearse una nueva cuenta si aún no la tiene.
En dicha pantalla, se le pedirá al usuario que rellene los siguientes campos para el registro:
- Nickname.
- Nombre.
- Primer apellido.
- Contraseña.
Al hacer click en botón Registrarse, se registran los datos introducidos para crear la nueva cuenta de usuario en nuestra base de datos, y se llevará al usuario de nuevo a la pantalla número uno, donde se le pedirá iniciar sesión con su nueva cuenta.

Editando README....

