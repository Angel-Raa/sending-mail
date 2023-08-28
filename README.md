# Envío de Correos Electrónicos con Spring Boot y Java Mail Sender

Este proyecto demuestra cómo enviar correos electrónicos utilizando Spring Boot y Java Mail Sender. Proporciona endpoints que permiten enviar correos electrónicos con contenido de archivo adjunto o simplemente mensajes de texto.

## Requisitos

- Java 17
- Conexión a internet para el envío de correos electrónicos

## Instalación y Configuración

1. git clone `https://github.com/Angel-Raa/sending-mail-with-spring-boot.git`
2. cd `sending-mail-with-spring-boot`
3. Construya el proyecto `mvn clean install`
4. Ejecute la aplicación Spring Boot: `mvn spring-boot:run`
   

## Uso

1. Ejecuta la aplicación Spring Boot.
2. Utiliza las siguientes rutas para enviar correos electrónicos:
   - `POST /mail/sendEmailFile` para enviar un correo con archivo adjunto.
   - `POST /mail/sendMessage` para enviar un mensaje de correo electrónico.

### Ejemplo de uso con cURL

```bash
# Enviar mensaje de correo electrónico
curl --location 'localhost:2020/mail/sendMessage' \
--header 'Content-Type: application/json' \
--data-raw '{
    "toUser": [
        "ejemplo@gmail.com"
    ],
    "subject": "Prueba de envío de correo",
    "message": "Prueba de correo electrónico utilizando Spring Boot"
}'
```
### Expected Response
```
{
    "message": "Message sent successfully",
    "httpStatus": "OK"
}
```

### Documentación de los Endpoints

#### Endpoint `/mail/sendEmailFile`

Este endpoint envía un correo electrónico con un archivo adjunto.


| Parámetro    | Descripción                          |
|--------------|--------------------------------------|
| `toUser`     | Lista de destinatarios del correo    |
| `subject`    | Asunto del correo electrónico        |
| `message`    | Cuerpo del correo electrónico        |
| `attachment` | Archivo adjunto del correo (opcional)|

**Respuestas:**

- `message`: Mensaje de confirmación de que el correo electrónico se ha enviado correctamente.
- `httpStatus`: Código de estado HTTP de la respuesta.

#### Endpoint `/mail/sendMessage`

Este endpoint envía un mensaje de correo electrónico.

| Parámetro    | Descripción                          |
|--------------|--------------------------------------|
| `toUser`     | Lista de destinatarios del correo    |
| `subject`    | Asunto del correo electrónico        |
| `message`    | Cuerpo del correo electrónico        |

**Respuestas:**

- `message`: Mensaje de confirmación de que el correo electrónico se ha enviado correctamente.
- `httpStatus`: Código de estado HTTP de la respuesta.

## Recursos Adicionales

Aquí hay algunos recursos adicionales que podrían ser útiles:

- [Documentación de  Java Mail Sender](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)
- [Documentacion de open jdk 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Documentacion de Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentacion de Maven](https://maven.apache.org/guides/getting-started/)
