# Utiliza la imagen oficial de MySQL
FROM mysql:8.0.34

# Variables de entorno
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=cinebotdb
ENV MYSQL_USER=master
ENV MYSQL_PASSWORD=master

# Copia el script SQL al contenedor
COPY ./cinebotdb.sql /docker-entrypoint-initdb.d/

# Exponer el puerto 3306 (el puerto por defecto de MySQL)
EXPOSE 3306

# Ejecutar el contenedor con MySQL
CMD ["mysqld"]