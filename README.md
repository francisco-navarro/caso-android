Introducción

En este desarrollo se pretenden cubrir las especificaciones planteadas en el caso de android, creando una aplicación que realice:
-	Pantalla de login que valide las credenciales contra el servidor de Dropbox.
-	Pantalla de listado con los libros que se encuentren almacenados en la cuenta de Dropbox con funcionalidades de ordenación.
-	Pantalla de detalle del libro con la portada del mismo.

Flujo de pantallas en la aplicación
En este gráfico se muestra de manera sencilla la navegación que realizará la aplicación entre las diferentes pantallas.




Diseño técnico
Para implementar la aplicación se ha dividido el desarrollo en las siguientes capas. De esta manera trabajaremos programando la lógica de cada elemento por separado e integrándola con las actividades de la aplicación.






Actividades android
Las pantallas para las actividades android están en este paquete:
app.prueba.caso_android.android

Dropbox API
La aplicación tendrá como interfaz externo la API de dropbox. Dicha API viene definida en la url del propietario https://www.dropbox.com/developers. 
Se han incluido los permisos necesarios y el servicio en el manifest, así como la APP_KEY que son necesarios para el uso de la API.
Las clases que vamos a usar para esta implementación están en  este paquete:
app.prueba.caso_android.dropbox


Lectura de epub
He usado la librería http://www.siegmann.nl/epublib/android, después de buscar varias opciones debido a que es sencilla y cubrimos el alcance para estos requisitos.
Las clases que vamos a usar para la lectura de epub están bajo este paquete:
app.prueba.caso_android.epub
