##Readme


##Introducción

En este desarrollo se pretenden cubrir las especificaciones planteadas en el caso de android, creando una aplicación que realice:
-	Pantalla de login que valide las credenciales contra el servidor de Dropbox.
-	Pantalla de listado con los libros que se encuentren almacenados en la cuenta de Dropbox con funcionalidades de ordenación.
-	Pantalla de detalle del libro con la portada del mismo.

##Flujo de pantallas en la aplicación
En este gráfico se muestra de manera sencilla la navegación que realizará la aplicación entre las diferentes pantallas.




##Diseño técnico
Para implementar la aplicación se ha dividido el desarrollo en los siquientes puntos. De esta manera trabajaremos programando la lógica de cada elemento por separado y de forma ordenada integrándola con las actividades de la aplicación.



###Actividades android
Las pantallas para las actividades android están en este paquete:
app.prueba.caso_android.android

En el paquete app.prueba.caso_android.android.adapters se ha usado un adapter personalizado ListBookAdapter para la realización de la lista de libros. A este se le pasan una lista de clases BookItem y se encarga de crear las filas y hacer el inflate.


###Dropbox API
La aplicación tendrá como interfaz externo la API de dropbox. Dicha API viene definida en la url del propietario https://www.dropbox.com/developers. 
Se han incluido los permisos necesarios y el servicio en el manifest, así como la APP_KEY que son necesarios para el uso de la API. En este caso hemos usado la API Sync que es a más alto nivel y más sencilla. Un punto importante es que nos permite hacer login de manera transparente a nosotros.

Las clases que usamos para esta implementación están en  este paquete:
app.prueba.caso_android.dropbox
La clase IDBoxConnection se ha usado como interfaz para implementar la funcionalidad de DropBox.


###Lectura de epub
He usado la librería http://www.siegmann.nl/epublib/android, después de buscar varias opciones debido a que es sencilla y cubrimos el alcance para estos requisitos.

El manejo de esta librería se basa en el paso de objetos de tipo InpuStream, que se obtienen de manera sencilla a través de dropbox.
Las clases que usamos para la lectura de epub están bajo este paquete:
app.prueba.caso_android.epub


##Entregables

El código de la aplicación está disponible en la rama principal de github, que se puede acceder a través de:
 https://github.com/francisco-navarro/caso-android/tree/master/app-caso-android.
