# Sistema de Reservas - API REST (Proyecto Integrador)

Este proyecto es una API REST construida con **Spring Boot** para gestionar un sistema de reservas, aplicando una arquitectura de separación por capas (Controller, Service, Domain) para garantizar un bajo acoplamiento y alta cohesión.

## Requisitos
- Java 21
- Maven
- Git

## Cómo ejecutar la aplicación
1. Abre una terminal en la carpeta raíz del proyecto.
2. Levanta el servidor ejecutando:
   `mvn spring-boot:run`
3. La API estará disponible y puedes verificar su estado inicial en:
   `GET http://localhost:8080/api/reservas/salud`

## Cómo ejecutar las pruebas automatizadas y cobertura
Para verificar que el sistema funciona correctamente y no hay regresiones, ejecuta la suite de pruebas (JUnit 5 y Mockito) con:
```bash
mvn clean test