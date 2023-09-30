# Backend para aplicación de TODO List 📆✅

## Descripción

Este proyecto corresponde al backend para una aplicación de TODO List. La aplicación consta de múltiples funcionalidades que permiten al usuario gestionar tareas creadas por él mismo.

## Funcionalidad

**1. Creación de tareas:** La aplicación permite al usuario crear nuevas tareas pendientes por defecto y asignarles una fecha límite y una etiqueta, para identificar el tipo de tarea.

**2. Listado de tareas:** La aplicación permite al usuario listar todas las tareas pendientes y completadas, con las especificaciones sobre sus fechas límite, etiquetas y estados.

**3. Conclusión de tareas:** La aplicación permite al usuario marcar tareas como completadas y registrar la fecha de conclusión de las mismas.

**4. Seguridad:** La aplicación permite al usuario realizar un proceso de autenticación para poder acceder a las funcionalidades de la aplicación.

**5. Gestión de etiquetas:** La aplicación permite al usuario crear nuevas etiquetas, modificarlas y eliminarlas.

## Arquitectura

El software tiene una arquitectura en capas:

 - api: Capa de controladores. Todas las clases que son controladores REST están en este paquete.
 - bl: Capa de lógica de negocio. Todas las clases que constan de lógica de negocio están en este paquete.
 - dao: Capa de acceso a datos. Todas las clases que tienen acceso a datos están en este paquete.
 - dto: Capa de transferencia de datos. Todas las clases que son utilizadas para transferir datos entre la capa de controladores y la capa de lógica de negocio están en este paquete.
 - entity: Capa de entidades. Todas las clases que son entidades de la base de datos están en este paquete.

## Requerimientos

Para compilar y ejecutar este proyecto, es necesario tener instalado:

 - Docker Engine 24.0.6
 - Java 17
 - Maven 3.8.7

## Instalación de la base de datos

Para instalar la base de datos, seguir los siguientes pasos:

1. Descargar la imagen de Docker de PostgreSQL 16:

```
docker pull postgres:16
```

2. Crear una instancia Docker con PostgreSQL 16:

```
docker run --name pg-todoapp -e POSTGRES_PASSWORD=pg123456 -p 15432:5432 -d postgres:16
```

3. Acceder a la base de datos por defecto del contenedor creado anteriormente:

```
docker exec -it <CONTAINER ID> psql -U postgres
```

4. Ejecutar el script init.sql del directorio 'database'.


## Compilación de la aplicación

Para compilar la aplicación, ejecutar el siguiente comando:

```
mvn clean package
```

## Ejecución de la aplicación

Para ejecutar la aplicación, ejecutar el siguiente comando:

``` 
mvn spring-boot:run
```
