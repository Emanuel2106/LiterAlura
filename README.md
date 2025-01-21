# 📚 LiterAlura - Catálogo de Libros

**LiterAlura** es una aplicación desarrollada en **Java** con **Spring Boot** que permite buscar libros en la API pública de **Gutendex**, almacenar la información en una base de datos **PostgreSQL**, e interactuar con los datos de libros y autores desde la consola.

## 🚀 Tecnologías utilizadas

- **Java 17**  
- **Spring Boot 3.2.3**  
  - Spring Data JPA  
- **PostgreSQL 16**  
- **Maven 4**  
- **Jackson** (para manejo de JSON)  
- **IntelliJ IDEA** (IDE recomendado)  

---

## ⚙️ Configuración del proyecto

### 1. Prerrequisitos

Asegúrate de tener instalados los siguientes programas:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven 4+](https://maven.apache.org/download.cgi)
- [PostgreSQL 16+](https://www.postgresql.org/download/)
- [IntelliJ IDEA (opcional)](https://www.jetbrains.com/idea/download/)

---

### 2. Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/literalura.git
   cd literalura
   ```
2. Configura la base de datos en el archivo ```src/main/resources/application.properties```:
  ```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  ```

3. Compila e inicia la aplicación:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

## 🖥️ Cómo usar la aplicación

Una vez ejecutada la aplicación, se mostrará el siguiente menú en la consola:
  ```bash
📚 *** LiterAlura - Catálogo de Libros *** 📚
1. Buscar libro por título
2. Listar libros guardados
3. Listar autores guardados
4. Buscar autores vivos en un año
5. Contar libros por idioma
6. Salir
  ```
Ingresa la opción correspondiente y sigue las instrucciones.

