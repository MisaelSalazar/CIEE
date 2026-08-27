# AGENTS.md - Restricciones y Permisos del Proyecto

## Proyecto
- **Nombre**: ControlIncidenciasEventos
- **Tipo**: Aplicación de escritorio Java Swing (NetBeans)
- **Base de datos**: SQLite (integrada en el proyecto)
- **Propósito**: Sistema de gestión de incidencias y eventos escolares

## Convenciones de nomenclatura
- **Clases, métodos, variables y elementos**: Deben seguir la convención **CamelCase** (ej: `LoginFrame`, `userRepository`, `getUserName()`)

## Permisos de la IA

### Libre (sin restricciones)
- **Maquetación de UI**: Diseñar y modificar componentes gráficos (JFrame, JPanel, botones, tablas, campos de texto, layouts, etc.)
- **Recomendar** enfoques de diseño visual y adaptabilidad de interfaces

### Requiere autorización explícita del usuario
- **Lógica de negocio**: No se crea ni modifica sin autorización directa del usuario
- **Base de datos**: No se puede modificar nada relacionado con la BD (conexiones, queries, esquemas) sin autorización previa del usuario

### Prohibido
- **Git**: No ejecutar ningún comando git (add, commit, switch, branch, push, pull, merge, rebase, etc.)
- **Sin modificaciones autónomas**: No crear funcionalidad no solicitada

## Reglas de trabajo
1. El usuario dirige el desarrollo y define las prioridades
2. La IA solo implementa lo que el usuario solicita explícitamente
3. La interfaz gráfica debe ser lo más responsive/adaptable posible dentro de las limitaciones de Java Swing
4. El usuario tiene el modelo Entidad-Relación creado; la IA ayudará con la BD solo cuando se le indique
