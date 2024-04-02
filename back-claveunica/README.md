# Backend Clave Única
Este es un proyecto de ejemplo para la implementación de clave única en el backend con springboot.

## Requisitos

- Java JDK 17
- Maven
- IDE compatible con Spring Boot (Eclipse, IntelliJ IDEA, etc.)

## Configuración
- Clonar este repositorio en tu máquina local.
- Importar el proyecto en tu IDE como un proyecto Maven existente.
- Verificar la configuración del archivo `application.properties` para ajustar la configuración de `claveunica.client.secret`.

## Ejecución
- Ejecutar la aplicación desde tu IDE como una aplicación Spring Boot.
- Alternativamente, desde la línea de comandos, puedes ejecutar mvn spring-boot:run en el directorio raíz del proyecto.

## Uso
- La aplicación se ejecutará en http://localhost:8080 de forma predeterminada (puedes cambiar el puerto en application.properties si es necesario).
- Accede a la API a través de las rutas definidas en los controladores. Por ejemplo, http://localhost:8080/auth/loginClaveUnica.