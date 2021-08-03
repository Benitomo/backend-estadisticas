# Backend-slim4

Back end para crear las interfaces en slim4, trae la información de Informix y la inserta en  la base de datos de slim4 ubicada en sql server.

## Comenzando 🚀

Clona el proyecto en tu máquina local, ten en cuenta las configuraciones de Maven y Java correspondientes para ejecutar un proyecto en Spring boot.

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋
```
- Java 
- Maven
- Tomcat
```
### Ejecución 🔧

Conectado a la vpn de Impocali tienes acceso a las bases de datos de Informix que contienen la información que el programa traera para exportar a la interface, o bien insertar en Sql Server (bases de datos de slim4).

Dentro la carpeta del proyecto abrir Git Bash, Poweshell o la terminal del sistema y ejecutas el siguiente comando:
```
mvn spring-boot:run
```
## Deployment 📦

El servidor de producción se encuentra en la platafora de Google cuya ip pública es "35.196.28.17" y su ip privada es "10.142.0.58".

## Primer paso 🛠️

Empaquetamiento del aplicativo: ubicados en la raiz del proyecto abrimos la consola o terminal y ejecutamos el siguiente comando:

```
* Power Shell
mvn clean package -Pjar
```

Luego, conectados a la vpn de Impocali, hay que enviar el empaquetado mediante scp al servidor de producción:
```
scp target/api-slim4.jar 10.142.0.59:/btw/api-new/api-new.jar
```

Luego, hay que conectarse al server de producción por ssh:
```
ssh 10.142.0.59
```

Monitoreamos si esta un tomcat corriendo:
```
ps -ef | grep java
kill -9 [process id devuelto por el proceso anterior]
```

Actualizar jar y correr la aplicación:

```
sudo su

cd /btw/
rm -f /btw/api-old/api-old.jar
mv /btw/api.jar /btw/api-old/api-old.jar
mv /btw/api-new/api-new.jar /btw/api.jar

nohup java -jar /btw/api-new.jar &

```

La aplicacion debería estar corriendo en la siguiente dirección publica:
```
http://35.196.28.17:8080/slim4/hello-world
```

Si algo salió mal con el deployment:
```
ps -ef | grep java
kill -9 [process id devuelto por el proceso anterior]

rm -f /btw/api-new.jar
mv /btw/api-old/api-old.jar /btw/api-new.jar

nohup java -jar /btw/api-new.jar &

```
