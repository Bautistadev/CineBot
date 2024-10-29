FROM node:18

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo package.json y package-lock.json si existe
COPY package*.json ./

# Instala las dependencias de npm
RUN npm install

# Copia el resto de los archivos de la aplicación al contenedor
COPY . .

# Expone el puerto que utiliza la aplicación (cambiar si es necesario)
EXPOSE 3000

# Comando para ejecutar el programa en modo desarrollo
CMD [ "npm", "run", "dev" ]
