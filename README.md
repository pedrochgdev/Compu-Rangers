# 🛍️ April Boutique - Tienda Virtual

**April Boutique** es un sistema de comercio electrónico diseñado para ofrecer una experiencia de compra en línea fluida y eficiente. Permite a los usuarios navegar por un catálogo de productos, agregar artículos al carrito, realizar pagos seguros y gestionar sus pedidos. La aplicación consta de un **backend** en **Java con Apache Ant** y un **frontend** en **C# con .NET**, conectados mediante **servicios web SOAP**.

---

## 🚀 Tecnologías Utilizadas

### **Backend**

- **Lenguaje:** Java 11
- **Build Tool:** Apache Ant 1.10.12
- **Servicios Web:** SOAP (JAX-WS)
- **Base de Datos:** Oracle 19c

### **Frontend**

- **Lenguaje:** C# 9.0
- **Framework:** .NET 5.0
- **Arquitectura:** MVVM

### **Otros**

- **Control de versiones:** Git
- **Gestión de dependencias:** Librerías locales
- **Configuración:** Archivos `.properties`

---

## 📌 Requisitos Previos

Antes de empezar, asegúrate de tener instalados:

- **Java Development Kit (JDK) 11**
- **Apache Ant 1.10.12**
- **Oracle Database 19c**
- **.NET 5.0 SDK**
- **Git**

---

## 📌 Instalación y Configuración

### **1️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/pedrochgdev/april-boutique.git
cd april-boutique
```

### **2️⃣ Configurar la Base de Datos**

1. Crea una base de datos en Oracle.
2. Ejecuta el script `database/schema.sql` para crear las tablas.
3. (Opcional) Ejecuta `database/seed.sql` para agregar datos de prueba.

### **3️⃣ Configurar el Backend**
```sh
cd backend-java-ant
# Edita config/database.properties con las credenciales de tu base de datos
# Edita config/application.properties según el entorno (desarrollo, producción, etc.)
# Compila el backend
ant compile
# Ejecuta el servidor
ant run
```

### **4️⃣ Configurar el Frontend**
```sh
cd frontend-csharp/MiEcommerceFrontend
# Asegúrate de que el backend esté corriendo
# Ejecuta la aplicación
dotnet run
```

---

## 📌 Ejecutar Pruebas

### **Backend**
```sh
cd backend-java-ant
ant test
```

### **Frontend**
```sh
cd frontend-csharp/MiEcommerceFrontend
dotnet test
```

---

## 📌 Despliegue

Para un entorno de producción:

1. **Backend**:
   - Compila con `ant build`.
   - Despliega el archivo JAR en `dist/` en tu servidor.

2. **Frontend**:
   - Publica con `dotnet publish`.
   - Despliega los archivos generados en tu servidor web.

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

- [Arquitectura del Sistema](docs/arquitectura.md)
- [Guía de Desarrollo](docs/desarrollo.md)

---

## 📌 Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).

---

## 📌 Contacto

Para dudas o sugerencias, escríbenos a **gabrielchg6@gmail.com**.

