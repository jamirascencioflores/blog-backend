## 🗄️ Configuración de la Base de Datos

1. Crea la base de datos en MySQL: `CREATE DATABASE blog_db;`
2. Ejecuta el proyecto para que Hibernate cree las tablas automáticamente.
3. Inserta el usuario administrador inicial para probar las funciones protegidas:

```sql
INSERT INTO usuarios (username, email, password, es_admin)
VALUES (
  'admin',
  'admin@blog.com',
  '$2a$10$TtCMpP5mxe8qrDHY6V5luei01JH7lZ/HFnIqm4HTO1dN/y.0qM4Vi', 
  1
);
-- Nota: La contraseña es 'admin123'
