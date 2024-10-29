# Bot Notificador de Películas - Modelo de Base de Datos

Este proyecto tiene como objetivo proporcionar notificaciones automáticas a los usuarios sobre las películas que podrían interesarles, basándose en sus géneros preferidos y las películas que están en cartelera en cines cercanos. A continuación, se describe el modelo de base de datos relacional utilizado para almacenar la información.

## Descripción del Modelo

El modelo de base de datos está diseñado para gestionar la información relacionada con los usuarios, las películas, los géneros, y los cines que proyectan las películas. A continuación, se detalla cada una de las tablas que componen este modelo.

## Tablas

### 1. `Usuario`

Esta tabla almacena la información básica de los usuarios del bot notificador.

| Campo           | Tipo de dato      | Descripción                          |
|-----------------|-------------------|--------------------------------------|
| `id`            | INT               | Identificador único del usuario.     |
| `nombre`        | VARCHAR(45)       | Nombre del usuario.                  |
| `apellido`      | VARCHAR(45)       | Nombre del usuario.                  |
| `email`         | VARCHAR(50)       | Correo electrónico del usuario.      |
| `password`      | VARCHAR(50)       | Contraseña del usuario               |
| `telefono`      | VARCHAR(50)       | Numero de telefono.                  |

### 2. `Genero`

La tabla `Genero` contiene los géneros de películas disponibles.

| Campo   | Tipo de dato      | Descripción                              |
|---------|-------------------|------------------------------------------|
| `id`    | INT               | Identificador único del género.          |
| `nombre`| VARCHAR(50)       | Nombre del género (e.g., Acción, Drama). |

### 3. `GeneroXPersona`

Esta tabla intermedia relaciona a los usuarios con los géneros de películas que prefieren.

| Campo       | Tipo de dato  | Descripción                                |
|-------------|---------------|--------------------------------------------|
| `id`        | INT           | Identificador único                        |
| `usuario_id`| INT           | Identificador del usuario (FK a `Usuario`).|
| `genero_id` | INT           | Identificador del género (FK a `Genero`).  |

### 4. `Pelicula`

Esta tabla contiene información sobre las películas disponibles en cartelera.

| Campo         | Tipo de dato     | Descripción                                  |
|---------------|------------------|----------------------------------------------|
| `id`          | INT              | Identificador único de la película.          |
| `nombre`      | VARCHAR(150)     | Título de la película.                       |
| `genero_id`   | INT              | Identificador del género (FK a `Genero`).    |

### 5. `Cartelera`

Esta tabla registra las películas que se están proyectando en un cine en particular.

| Campo        | Tipo de dato      | Descripción                                    |
|--------------|-------------------|------------------------------------------------|
| `id`         | INT               | Identificador único de la película.            |
| `cine_id`    | INT               | Identificador del cine (FK a `Cine`).          |
| `pelicula_id`| INT               | Identificador de la película (FK a `Pelicula`).|
| `fecha `     | DATE              | Fecha de inicio de la proyección.              |
| `hora `      | TIME              | Hora de inicio de la proyección.               |

### 6. `Cine`

La tabla `Cine` almacena la información de los cines que participan en el sistema.

| Campo          | Tipo de dato       | Descripción                            |
|----------------|--------------------|----------------------------------------|
| `id`           | INT                | Identificador único del cine.          |
| `nombre`       | VARCHAR(45)        | Nombre del cine.                       |
| `calle`        | VARCHAR(45)        | Nombre de la calle donde se ubica.     |
| `numero`       | VARCHAR(45)        | Numero                                 |

## Relaciones

- **Usuario** puede tener múltiples géneros favoritos a través de la tabla intermedia `GeneroXPersona`.
- **Genero** se relaciona con **Pelicula**, donde una película puede tener un solo género, pero un género puede estar asociado a varias películas.
- **Pelicula** se relaciona con **Cartelera**, indicando que una película puede estar proyectándose en uno o más cines.
- **Cine** puede tener múltiples entradas en la tabla **Cartelera**, indicando qué películas se proyectan en ese cine.

## Diagrama Relacional

![DB](https://github.com/user-attachments/assets/35bea6e6-10fa-4d61-ade3-e692c4a08ad8)
