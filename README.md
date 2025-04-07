Aquí tienes el README actualizado, tomando en cuenta que ahora trabajas con Maven:

---

# 🖥️ **Compu Rangers - Tienda Virtual**

**Compu Rangers** es un sistema de comercio electrónico diseñado para ofrecer una experiencia de compra en línea fluida y eficiente. Este proyecto se centra en la venta de productos tecnológicos y componentes. Los usuarios pueden navegar por un catálogo de productos, agregar artículos al carrito, realizar pagos seguros y gestionar sus pedidos. La aplicación consta de un **backend** en **Java con Maven** y un **frontend** en **C# con .NET**, conectados mediante **servicios web SOAP**.

---

## 🚀 **Tecnologías Utilizadas**

### **Backend**

- **Lenguaje:** Java 21
- **Gestor de dependencias:** Maven
- **Servicios Web:** SOAP
- **Base de Datos:** MySQL Server

### **Frontend**

- **Lenguaje:** C# 9.0
- **Framework:** .NET Framework
- **Arquitectura:** -

### **Otros**

- **Control de versiones:** Git
- **Gestión de dependencias:** Librerías locales
- **Configuración:** Archivos `.env`

---

## 📌 **Requisitos Previos**

Antes de empezar, asegúrate de tener instalados:

- **Java Development Kit (JDK) 21**
- **Maven**
- **MySQL Server**
- **.NET Framework SDK**
- **Git**

---

## 📌 **Instalación y Configuración**

### **1️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/pedrochgdev/Compu-Rangers.git
cd compu-rangers
```

### **2️⃣ Configurar la Base de Datos**

1. Crea una base de datos en MySQL.
2. Ejecuta el script `database/example.sql` para crear las tablas.
3. (Opcional) Ejecuta `database/seed.sql` para agregar datos de prueba.

### **3️⃣ Configurar el Backend**
```sh
cd backend-java-maven
# Crea y edita .env con las credenciales de tu base de datos
# Edita config/application.properties según el entorno (desarrollo, producción, etc.)
# Compila el backend
mvn clean install
# Ejecuta el servidor
mvn spring-boot:run
```

### **4️⃣ Configurar el Frontend**
```sh
cd frontend-csharp/MiEcommerceFrontend
# Asegúrate de que el backend esté corriendo
# Ejecuta la aplicación
dotnet run
```

---

## 📌 **Ejecutar Pruebas**

### **Backend**
```sh
cd backend-java-maven
mvn test
```

### **Frontend**
```sh
cd frontend-csharp/MiEcommerceFrontend
dotnet test
```

---

## 📌 **Despliegue**

Para un entorno de producción:

1. **Backend**:
   - Compila con `mvn clean install`.
   - Despliega el archivo JAR generado en `target/` en tu servidor.

2. **Frontend**:
   - Publica con `dotnet publish`.
   - Despliega los archivos generados en tu servidor web.

---

## 📌 **Contribuir al Proyecto**

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

## 📌 **Documentación Adicional**

- [Arquitectura del Sistema](docs/arquitectura.md)
- [Guía de Desarrollo](docs/desarrollo.md)

---

## 📌 **Licencia**

Este proyecto está bajo la [Licencia MIT](LICENSE).

---

## 📌 **Contacto**

Para dudas o sugerencias, escríbenos a **gabrielchg6@gmail.com**.
