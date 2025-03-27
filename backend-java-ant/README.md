# 🛠️ April Boutique - Backend

**April Boutique Backend** es el componente del servidor de la tienda virtual, desarrollado en **Java 21** con **Apache Ant** y utilizando **servicios web SOAP** para la comunicación con el frontend. Gestiona la lógica de negocio, el acceso a datos y la seguridad del sistema.

---

## 🚀 Tecnologías Utilizadas

- **Lenguaje:** Java 21
- **Build Tool:** Apache Ant
- **Servicios Web:** SOAP
- **Base de Datos:** MySQL Server
- **Configuración:** Archivos `.env`

---

## 📌 Requisitos Previos

Antes de empezar, asegúrate de tener instalados:

- **Java Development Kit (JDK) 21**
- **Apache Ant**
- **MySQL Server**
- **Git**

---

## 📌 Instalación y Configuración

### **1️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/pedrochgdev/april-boutique.git
cd april-boutique/backend-java-ant
```

### **2️⃣ Configurar la Base de Datos**

1. Crea una base de datos en MySQL.
2. Ejecuta el script `database/example.sql` para crear las tablas.
3. (Opcional) Ejecuta `database/seed.sql` para agregar datos de prueba.

### **3️⃣ Configurar el Backend**
```sh
# Crea y edita .env con las credenciales de tu base de datos
# Edita config/application.properties según el entorno (desarrollo, producción, etc.)

# Compila el backend
ant compile

# Ejecuta el servidor
ant run
```

---

## 📌 Ejecutar Pruebas

Ejecuta las pruebas unitarias y de integración con:
```sh
ant test
```

---

## 📌 Despliegue

Para un entorno de producción:

```sh
# Compila el proyecto
ant build

# El JAR generado estará en dist/
# Despliégalo en tu servidor
```

---

## 📌 API y Endpoints

El backend expone servicios web SOAP en la siguiente URL:

```
http://localhost:8080/AprilBoutique/Service?wsdl
```

Algunos de los servicios disponibles:

- `UsuarioService` → Registro y autenticación de usuarios.
- `ProductoService` → Gestión de productos.
- `PedidoService` → Procesamiento de órdenes.

---

## 📌 Contribuir al Proyecto

1. **Haz un Fork** del repositorio.
2. Crea una nueva rama:
   ```sh
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz commit:
   ```sh
   git commit -m "Descripción de los cambios"
   ```
4. Sube tu rama:
   ```sh
   git push origin feature/nueva-funcionalidad
   ```
5. Crea un **Pull Request** en GitHub.

---

## 📌 Documentación Adicional

- [Guía de Desarrollo](../docs/backend-desarrollo.md)
- [Estructura de Directorios](../docs/backend-estructura.md)

---

## 📌 Licencia

Este proyecto está bajo la [Licencia MIT](../LICENSE).

