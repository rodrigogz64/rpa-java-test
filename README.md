# RPA JAVA Test

Este proyecto implementa una aplicación Spring Boot con Maven para procesar información de clientes desde un archivo de texto plano y realizar operaciones de SQL utilizando JPA.

## Tecnologías

- Java 17
- Spring Boot 3.1.0
- Maven
- H2 Database (para la parte SQL)
- JPA/Hibernate

## Estructura del Proyecto

```
├── src/main/java/com/evaluacion/app
│   ├── ClienteApplication.java (Clase principal)
│   ├── model
│   │   └── Cliente.java (Modelo para procesamiento de archivo)
│   ├── entity
│   │   ├── ClienteEntity.java (Entidad JPA para la base de datos)
│   │   ├── Pedido.java
│   │   ├── Producto.java
│   │   └── DetallePedido.java
│   ├── repository
│   │   ├── ClienteRepository.java
│   │   ├── PedidoRepository.java
│   │   └── ProductoRepository.java
│   ├── service
│   │   ├── ClienteProcessor.java (Lógica de procesamiento de archivos)
│   │   └── SQLService.java (Lógica para operaciones SQL)
│   └── controller
│       └── SQLController.java (RESTful API para operaciones SQL)
├── src/main/resources
│   ├── application.properties
│   └── schema.sql (Esquema de base de datos)
└── pom.xml
```

## Sección I - Resolución de Problemas Java

Esta sección implementa un programa que:

1. **Lee un archivo de texto plano** con datos de clientes en formato: id_cliente, nombre, saldo, ultima_fecha, tipo_cliente
2. **Parsea los datos** para extraer la información de cada cliente
3. **Ordena los clientes por saldo** de forma descendente utilizando el algoritmo de selección
4. **Filtra los clientes** cuya última fecha sea anterior a una fecha dada y sean de un tipo específico
5. **Agrupa a los clientes por tipo** de cliente y calcula el saldo promedio para cada grupo
6. **Genera un nuevo archivo de texto** con los clientes ordenados por saldo
7. **Maneja errores** posibles en el formato del archivo, registrándolos en un archivo de registro

## Sección II - Conocimiento de SQL

Esta sección implementa consultas SQL para un esquema de base de datos con las tablas Cliente, Pedido, Producto y DetallePedido:

1. **Ejercicio SQL 1**: Selección de Clientes Activos
   ```sql
   SELECT nombre, email FROM Cliente WHERE estado = 'activo';
   ```

2. **Ejercicio SQL 2**: Contar Pedidos por Cliente
   ```sql
   SELECT c.nombre, COUNT(p.id_pedido) as total_pedidos
   FROM Cliente c
   LEFT JOIN Pedido p ON c.id_cliente = p.id_cliente
   GROUP BY c.nombre;
   ```

3. **Ejercicio SQL 3**: Encontrar el Producto Más Vendido
   ```sql
   SELECT p.nombre, SUM(dp.cantidad) as total_vendido
   FROM Producto p
   JOIN DetallePedido dp ON p.id_producto = dp.id_producto
   GROUP BY p.nombre
   ORDER BY total_vendido DESC
   LIMIT 1;
   ```

4. **Ejercicio SQL 4**: Actualizar el Estado de Pedidos Antiguos
   ```sql
   UPDATE Pedido
   SET estado = 'archivado'
   WHERE fecha_pedido < '2024-01-01';
   ```

## Ejecución del Proyecto

1. **Requisitos**:
   - JDK 17 o superior
   - Maven 3.6.3 o superior

2. **Clonar el repositorio**:
   ```
   git clone https://github.com/rodrigogz64/rpa-java-test.git
   cd evaluacion-java
   ```

3. **Compilar y ejecutar**:
   ```
   mvn clean install
   mvn spring-boot:run
   ```

4. **Uso de la aplicación**:
   - Al iniciar, la aplicación pedirá la ruta del archivo de clientes
   - Si se presiona Enter sin especificar ruta, usará el archivo de ejemplo (`clientes.txt`)
   - Después de procesar, se mostrarán los resultados en consola
   - Los archivos `clientes_ordenados.txt` y `errores.log` se generarán en el directorio raíz

## API REST

Para las consultas SQL, se exponen los siguientes endpoints:

- `GET /api/clientes/activos`: Devuelve los clientes activos
- `GET /api/clientes/pedidos`: Devuelve el conteo de pedidos por cliente
- `GET /api/productos/mas-vendido`: Devuelve el producto más vendido
- `POST /api/pedidos/actualizar-estado?fecha=2024-01-01`: Actualiza el estado de pedidos antiguos

## Base de Datos

La aplicación utiliza una base de datos H2 en memoria. Los datos de prueba se cargan automáticamente desde el archivo `data.sql` al iniciar la aplicación.

## Entidades Principales

- **Cliente**: Información de los clientes registrados
- **Pedido**: Pedidos realizados por los clientes
- **Producto**: Catálogo de productos disponibles
- **DetallePedido**: Detalles de los productos incluidos en cada pedido