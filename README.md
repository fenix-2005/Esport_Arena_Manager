#  Esport Manager - Arquitectura de Microservicios

##  Contexto y Dominio del Proyecto
**Esport Manager** es una plataforma integral de gestión de torneos y competiciones de deportes electrónicos (Esports). El sistema está diseñado bajo una arquitectura distribuida de microservicios robusta y escalable. Permite a organizadores, jugadores y equipos administrar todo el ciclo de vida competitivo: desde la configuración de videojuegos y torneos, pasando por la inscripción de escuadras y el emparejamiento (fixtures), hasta el registro de resultados, cálculo automatizado de rankings, adjudicación de premios y la aplicación de sanciones disciplinarias.

##  Integrantes del Equipo
* Javier Guzmán
* Bastian Figueroa

## Arquitectura y Tecnologías
* **Backend:** Java 17+, Spring Boot 3.x
* **Patrón de Diseño:** CSR (Controller - Service - Repository)
* **Comunicación Interna:** Spring Cloud OpenFeign
* **Service Discovery:** Netflix Eureka
* **Enrutamiento:** Spring Cloud Gateway
* **Documentación API:** SpringDoc OpenAPI (Swagger)
* **Testing:** JUnit 5, Mockito, JaCoCo (Cobertura > 80%)
* **Base de Datos:** H2 Database (In-Memory para desarrollo)

## Listado de Microservicios Implementados

1. **Eureka Server** (`8761`): Registro y descubrimiento de servicios.
2. **API Gateway** (`8080`): Punto de entrada único y balanceo de carga.
3. **Auth-service** (`8081`): Gestión de credenciales y seguridad.
4. **User-Service**: Perfiles, roles e información de competidores.
5. **Team-Service**: Gestión de clubes, escuadras y roles tácticos.
6. **Game-service**: Catálogo de videojuegos y modalidades.
7. **Tournament-Service**: Configuración de ligas, copas y cronogramas.
8. **Registration-Service**: Control de inscripciones y cupos.
9. **Match-Service**: Fixtures, llaves y programación de enfrentamientos.
10. **Result-Service**: Actas oficiales de juego y marcadores.
11. **Ranking-Service**: Tablas de posiciones y cálculos estadísticos.
12. **Prize-Service**: Pozos de recompensa y adjudicaciones.
13. **Sanction-Service**: Tribunal de disciplina y bloqueos.
14. **Notification-Service**: Alertas y buzón de mensajería interna.

##  Rutas Principales del Gateway
Todo el tráfico externo es interceptado y enrutado dinámicamente por el API Gateway en el puerto `8080`.
Formato base: `http://localhost:8080/api/v1/{recurso}`

* **Cuentas de Acceso:** `/api/v1/cuentas/**`
* **Usuarios:** `/api/v1/usuarios/**`
* **Equipos:** `/api/v1/equipos/**`
* **Juegos:** `/api/v1/juegos/**`
* **Torneos:** `/api/v1/torneos/**`
* **Inscripciones:** `/api/v1/inscripciones/**`
* **Partidas:** `/api/v1/partidas/**`
* **Resultados:** `/api/v1/resultados/**`
* **Rankings:** `/api/v1/rankings/**`
* **Premios:** `/api/v1/premios/**`
* **Sanciones:** `/api/v1/sanciones/**`
* **Notificaciones:** `/api/v1/notificaciones/**`

##  Documentación de APIs (Swagger)
La documentación técnica de todos los microservicios se ha unificado magistralmente a través del Gateway. No es necesario acceder a cada puerto de forma individual.
* **Acceso a la interfaz unificada:** [http://localhost:8080/docs/swagger-ui.html](http://localhost:8080/docs/swagger-ui.html)
  *(Utilice el menú desplegable en la esquina superior derecha para navegar entre la documentación de los distintos microservicios).*

##  Instrucciones de Ejecución

### Entorno Local (Maven)
Para levantar el ecosistema correctamente, se debe respetar el siguiente orden estricto de arranque:
1. Iniciar **Eureka Server** (`Discovery-Service`). Esperar a que la consola confirme el inicio (Puerto `8761`).
2. Iniciar **API Gateway**. Esperar a que se registre exitosamente en Eureka (Puerto `8080`).
3. Iniciar los **Microservicios de Negocio** (Auth, User, Team, etc.) en cualquier orden.
4. Acceder a [http://localhost:8761](http://localhost:8761) para verificar que las 13 instancias (Gateway + 12 servicios) aparezcan con el status `UP`.

### Entorno con Docker (Contenedores)
Si se dispone de Docker y Docker Compose instalados:
1. Abrir una terminal en la raíz del proyecto.
2. Ejecutar el comando para construir las imágenes y levantar los contenedores:
   ```bash
   docker-compose up --build -d

## ☁Despliegue en Producción (Servidor Linux / OCI)

El ecosistema esta hecho para poder usar docker en un servidor, garantizando su portabilidad a cualquier servidor VPS (Oracle Linux 9, Ubuntu, etc.).

### Requisitos en el Servidor
- **Docker** (v24+) y **Docker Compose plugin**.
- Tráfico habilitado en el Firewall (Ingress Rules) para los puertos de acceso público (ej. el puerto del API Gateway y SSH).

### Pasos de Despliegue

1. **Acceder al servidor mediante SSH:**
   ```bash
   ssh -i "ruta/a/tu/llave.key" opc@<IP_PUBLICA_DEL_SERVIDOR>
   ```

2. **Obtener el código fuente:**
   ```bash
   git clone [https://github.com/tu-usuario/Esport_Arena_Manager.git](https://github.com/tu-usuario/Esport_Arena_Manager.git)
   cd Esport_Arena_Manager
   ```

3. **Construir y levantar la infraestructura:**
   Ejecuta el orquestador en modo *detached* (segundo plano) para mantener los servicios vivos tras cerrar la sesión SSH:
   ```bash
   docker compose up --build -d
   ```

4. **Monitoreo y Verificación:**
   Puedes revisar el estado de los contenedores y leer los registros en tiempo real:
   ```bash
   # Ver todos los microservicios en ejecución
   docker ps

   # Revisar los logs del servidor de descubrimiento (Eureka)
   docker logs msvc-eureka
   
   # Revisar los logs del Gateway
   docker logs msvc-gateway
   ```