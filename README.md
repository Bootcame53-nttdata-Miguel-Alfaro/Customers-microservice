# Customer Microservice

Este microservicio se encarga de la administración de la información de los clientes de un banco. Proporciona diversas operaciones para gestionar los datos personales de los usuarios. Está registrado en un API Gateway y puede ser consumido desde la siguiente dirección: [http://4.152.240.150:8080/](http://4.152.240.150:8080/).

## Descripción del Proyecto

El microservicio de clientes proporciona diversas operaciones para la gestión de la información de los clientes del banco, incluyendo la creación, actualización, eliminación y consulta de datos del cliente. A continuación se detallan los endpoints disponibles y su funcionalidad.

## Endpoints

### Crear un Cliente
- **Descripción**: Añadir un nuevo cliente a la base de datos.
- **Método**: `POST`
- **Ruta**: `http://4.152.240.150:8080/customers`
- **Código de Respuesta**: 201 - Cliente creado exitosamente.

### Obtener Resumen de un Cliente por ID
- **Descripción**: Obtener un resumen consolidado de un cliente con todos los productos que posee en el banco.
- **Método**: `GET`
- **Ruta**: `http://4.152.240.150:8080/customers/{id}/summary`
- **Códigos de Respuesta**: 200 - Operación exitosa, 404 - Cliente no encontrado.

### Actualizar un Cliente
- **Descripción**: Actualizar la información de un cliente existente.
- **Método**: `PUT`
- **Ruta**: `http://4.152.240.150:8080/customers/{id}`
- **Códigos de Respuesta**: 200 - Operación exitosa, 404 - Cliente no encontrado.

### Obtener un Cliente por ID
- **Descripción**: Obtener la información de un cliente específico por su ID.
- **Método**: `GET`
- **Ruta**: `http://4.152.240.150:8080/customers/{id}`
- **Códigos de Respuesta**: 200 - Operación exitosa, 404 - Cliente no encontrado.

### Eliminar un Cliente
- **Descripción**: Eliminar un cliente específico por su ID.
- **Método**: `DELETE`
- **Ruta**: `http://4.152.240.150:8080/customers/{id}`
- **Códigos de Respuesta**: 200 - Eliminación exitosa, 404 - Cliente no encontrado.

## Integración y Despliegue

Este microservicio está integrado dentro de un clúster de AKS (Azure Kubernetes Service) con integración continua. Cada commit se almacena en un registro y el despliegue se realiza de manera automática, garantizando que siempre esté disponible la versión más reciente y funcional del servicio.

## Información Adicional

Para ver más información de las peticiones, tanto body, request y response, revisar el contrato API en el recurso del proyecto.