# RentaEasy 🏠 - Guía Completa de Desarrollo

## 📱 Aplicación de Renta de Departamentos para Estudiantes

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024+-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![License MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

---

## 📋 Tabla de Contenidos

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Características](#-características)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Arquitectura](#-arquitectura)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos](#-requisitos)
- [Instalación](#-instalación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Guía de Desarrollo](#-guía-de-desarrollo-paso-a-paso)
- [Firebase Configuration](#-configuración-de-firebase)
- [Base de Datos](#-base-de-datos-firestore)
- [Componentes Principales](#-componentes-principales)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)
- [Contacto](#-contacto)

---

## 🎯 Descripción del Proyecto

**RentaEasy** es una aplicación móvil Android moderna desarrollada con **Jetpack Compose** y **Firebase** que facilita la búsqueda y publicación de departamentos en renta específicamente diseñada para estudiantes universitarios.

Inspirada en plataformas como **Airbnb** y **Facebook Marketplace**, RentaEasy ofrece una experiencia intuitiva que conecta inquilinos con propietarios de manera eficiente y segura.

### 🎓 Contexto Académico

Este proyecto fue desarrollado como parte del curso de **Desarrollo de Aplicaciones Móviles**, demostrando:
- Implementación completa de arquitectura **MVVM**
- Integración con servicios **Backend-as-a-Service (Firebase)**
- Diseño moderno con **Jetpack Compose**
- Gestión de estados reactiva con **StateFlow**
- Operaciones **CRUD** completas
- Autenticación y autorización de usuarios
- Almacenamiento de imágenes en la nube

---

## ✨ Características

### 🔐 Autenticación y Seguridad
- ✅ Registro de usuarios con email y contraseña
- ✅ Inicio de sesión seguro con Firebase Authentication
- ✅ Validación de formularios en tiempo real
- ✅ Gestión de sesiones persistentes
- ✅ Cierre de sesión seguro

### 🏠 Para Inquilinos
- 🔍 **Búsqueda Inteligente** - Filtra departamentos por ubicación
- ❤️ **Sistema de Favoritos** - Guarda propiedades para revisar después
- 📱 **Navegación Fluida** - Interfaz intuitiva con Material Design 3
- 🖼️ **Galería de Imágenes** - Visualiza múltiples fotos de cada propiedad
- 📍 **Información Detallada** - Precio, ubicación, características y descripción completa

### 🏢 Para Propietarios
- ➕ **Publicación Rápida** - Crea anuncios en minutos
- 📸 **Carga Múltiple de Imágenes** - Hasta varias fotos por propiedad
- ✏️ **Gestión de Publicaciones** - Edita o elimina tus anuncios
- 📊 **Dashboard Personal** - Visualiza todas tus propiedades
- 👤 **Perfil de Propietario** - Gestiona tu información de contacto

### 🎨 Experiencia de Usuario
- 🌙 **Tema Oscuro Moderno** - Reduce fatiga visual y ahorra batería
- 🧭 **Bottom Navigation** - Navegación rápida entre secciones principales
- 🔄 **Estados de Carga** - Feedback visual en todas las operaciones
- ⚡ **Rendimiento Optimizado** - Carga rápida con Coil para imágenes
- 📱 **Responsive Design** - Adaptado a diferentes tamaños de pantalla

---

## 📸 Capturas de Pantalla

### Flujo de Autenticación

#### Splash Screen
![Splash](screenshots/splash.png)
*Pantalla de bienvenida con logo de la aplicación*

#### Login
![Login](screenshots/login.png)
*Inicio de sesión con validación de credenciales*

#### Registro
![Registro](screenshots/register.png)
*Formulario de registro con selección de tipo de usuario*

---

### Pantallas Principales

#### Home - Vista Principal
![Home](screenshots/home.png)
*Listado de todas las propiedades disponibles con búsqueda por ubicación*

#### Detalle de Propiedad
![Detalle](screenshots/detail.png)
*Información completa con galería de imágenes, precio y características*

#### Crear Publicación
![Crear](screenshots/create.png)
*Formulario completo para propietarios con carga de imágenes*

---

### Funcionalidades Adicionales

#### Favoritos
![Favoritos](screenshots/favorites.png)
*Lista de propiedades guardadas para consulta rápida*

#### Perfil de Usuario
![Perfil](screenshots/profile.png)
*Información personal, tipo de usuario y opciones de configuración*

---

## 🏗 Arquitectura

RentaEasy implementa el patrón de arquitectura **MVVM (Model-View-ViewModel)** recomendado por Google para aplicaciones Android modernas con Jetpack Compose.

```
┌─────────────────────────────────────────────────────────┐
│                    UI Layer                             │
│         (Jetpack Compose Screens & Components)          │
│  - LoginScreen, HomeScreen, ProfileScreen, etc.         │
│  - PropertyCard, SearchBar, BottomNav                   │
└─────────────────┬───────────────────────────────────────┘
                  │
                  │ Observa Estados (StateFlow)
                  ▼
┌─────────────────────────────────────────────────────────┐
│                  ViewModel Layer                        │
│      (AuthViewModel, PropertyViewModel, etc.)           │
│  - Gestiona lógica de presentación                      │
│  - Mantiene estados UI con StateFlow                    │
│  - Sobrevive a cambios de configuración                 │
└─────────────────┬───────────────────────────────────────┘
                  │
                  │ Llama Métodos
                  ▼
┌─────────────────────────────────────────────────────────┐
│                 Repository Layer                        │
│   (AuthRepository, PropertyRepository, etc.)            │
│  - Abstracción de fuentes de datos                      │
│  - Lógica de negocio                                    │
│  - Manejo de errores                                    │
└─────────────────┬───────────────────────────────────────┘
                  │
                  │ Operaciones CRUD
                  ▼
┌─────────────────────────────────────────────────────────┐
│               Data Source Layer                         │
│              (Firebase Services)                        │
│  - Firebase Authentication (Auth)                       │
│  - Cloud Firestore (Database)                           │
│  - Firebase Storage (Imágenes)                          │
└─────────────────────────────────────────────────────────┘
```

### Componentes Principales

#### 1. UI Layer (`screens/` + `components/`)
Contiene todas las pantallas de Jetpack Compose y componentes reutilizables:
- **Screens**: Pantallas completas de la aplicación
- **Components**: Componentes reutilizables (cards, barras de búsqueda, etc.)

#### 2. ViewModel Layer (`viewmodels/`)
Gestiona la lógica de presentación y mantiene el estado de la UI:
- Expone `StateFlow` para observación reactiva
- Maneja eventos del usuario
- Coordina operaciones con repositorios

#### 3. Repository Layer (`repository/`)
Capa de abstracción que encapsula el acceso a datos:
- Permite cambiar fuentes de datos sin afectar UI
- Maneja lógica de negocio
- Transforma datos de Firebase a modelos de dominio

#### 4. Data Source Layer (Firebase)
Servicios de backend en la nube:
- **Authentication**: Gestión de usuarios
- **Firestore**: Base de datos NoSQL
- **Storage**: Almacenamiento de archivos

---

## 🛠 Tecnologías Utilizadas

### Core Technologies

| Tecnología | Versión | Descripción |
|-----------|---------|-------------|
| **Kotlin** | 2.0.21 | Lenguaje de programación principal |
| **Android SDK** | API 24+ (7.0 Nougat) | Plataforma Android mínima soportada |
| **Target SDK** | API 34 (Android 14) | Versión objetivo de Android |

### Jetpack Components

| Componente | Versión | Uso |
|-----------|---------|-----|
| **Jetpack Compose** | 1.5.4 | Framework UI moderno y declarativo |
| **Material Design 3** | 1.1.2 | Sistema de diseño y componentes UI |
| **Navigation Compose** | 2.7.5 | Navegación entre pantallas |
| **Lifecycle ViewModel** | 2.7.0 | Gestión de estados UI |
| **Activity Compose** | 1.12.1 | Integración con Activities |

### Firebase Platform

| Servicio | Descripción |
|---------|-------------|
| **Firebase Authentication** | Autenticación de usuarios con email/contraseña |
| **Cloud Firestore** | Base de datos NoSQL en tiempo real |
| **Firebase Storage** | Almacenamiento de imágenes en la nube |
| **Firebase BOM** | 32.7.0 - Gestión de versiones de Firebase |

### Libraries

| Librería | Versión | Propósito |
|---------|---------|-----------|
| **Coil** | 2.5.0 | Carga asíncrona de imágenes |
| **Kotlin Coroutines** | 1.7.3 | Programación asíncrona |
| **StateFlow** | - | Gestión de estados reactiva |

### Build Tools

| Herramienta | Versión |
|------------|---------|
| **Gradle** | 8.2 |
| **Android Gradle Plugin** | 8.1.4 |
| **Kotlin Compiler** | 2.0.21 |

---

## 📦 Requisitos

### Requisitos de Desarrollo

**Herramientas Necesarias:**
- ✅ **Android Studio** Hedgehog (2023.1.1) o superior
- ✅ **JDK** 17 o superior
- ✅ **Gradle** 8.2+
- ✅ **Git** para control de versiones

**Configuración de SDK:**
- SDK Platform: Android 14 (API 34)
- Build Tools: 34.0.0
- Minimum SDK: Android 7.0 (API 24)

**Cuenta de Firebase:**
- Proyecto creado en [Firebase Console](https://console.firebase.google.com/)
- Servicios habilitados: Authentication, Firestore, Storage

### Requisitos del Dispositivo/Emulador

**Especificaciones Mínimas:**
- Android 7.0 (Nougat) - API 24 o superior
- 2 GB RAM mínimo
- 100 MB de espacio libre
- Conexión a Internet

**Permisos Necesarios:**
```xml



```

---

## 🚀 Instalación

### Opción 1: Clonar desde GitHub

```bash
# 1. Clonar el repositorio
git clone https://github.com/oscar1224100523/AppRentaEasyFinal.git

# 2. Navegar al directorio del proyecto
cd AppRentaEasyFinal

# 3. Abrir con Android Studio
# File > Open > Seleccionar carpeta AppRentaEasyFinal

# 4. Esperar sincronización de Gradle
# Android Studio descargará automáticamente todas las dependencias
```

### Opción 2: Descargar ZIP

1. Ve al repositorio: https://github.com/oscar1224100523/AppRentaEasyFinal
2. Click en "Code" > "Download ZIP"
3. Extrae el archivo
4. Abre con Android Studio

### Opción 3: Compilar APK desde Código Fuente

```bash
# Clonar repositorio
git clone https://github.com/oscar1224100523/AppRentaEasyFinal.git
cd AppRentaEasyFinal

# Compilar APK Debug
./gradlew assembleDebug
# El APK se generará en: app/build/outputs/apk/debug/app-debug.apk

# Compilar APK Release (requiere configuración de firma)
./gradlew assembleRelease
```

---

## 🔥 Configuración de Firebase

### Paso 1: Crear Proyecto en Firebase

1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Click en "Agregar proyecto"
3. Nombre del proyecto: `RentaEasy` (o el que prefieras)
4. Sigue el asistente de configuración

### Paso 2: Registrar Aplicación Android

1. En Firebase Console, click en ⚙️ > Configuración del proyecto
2. Click en el icono de Android para agregar app
3. Llena los campos:
   - **Nombre del paquete de Android**: `com.oarj.rentaeasy`
   - **Sobrenombre**: RentaEasy
   - **Certificado de firma SHA-1**: (Opcional para desarrollo)

### Paso 3: Descargar google-services.json

1. Descarga el archivo `google-services.json`
2. Colócalo en: `app/google-services.json`
3. **IMPORTANTE**: Este archivo es específico de tu proyecto

### Paso 4: Habilitar Firebase Authentication

```
1. En Firebase Console > Authentication
2. Click en "Comenzar"
3. Pestaña "Sign-in method"
4. Habilitar "Correo electrónico/Contraseña"
5. Guardar cambios
```

### Paso 5: Configurar Cloud Firestore

```
1. En Firebase Console > Firestore Database
2. Click en "Crear base de datos"
3. Seleccionar ubicación (ej: us-central1)
4. Modo: "Empezar en modo de prueba"
5. Habilitar
```

**Reglas de Seguridad Iniciales:**
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Permitir lectura/escritura solo a usuarios autenticados
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

### Paso 6: Configurar Firebase Storage

```
1. En Firebase Console > Storage
2. Click en "Comenzar"
3. Seleccionar ubicación (misma que Firestore)
4. Modo: "Empezar en modo de prueba"
5. Finalizar
```

**Reglas de Seguridad para Storage:**
```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /properties/{propertyId}/{allPaths=**} {
      // Permitir lectura a todos los autenticados
      allow read: if request.auth != null;
      // Permitir escritura solo al dueño de la propiedad
      allow write: if request.auth != null;
    }
  }
}
```

---

## 📂 Estructura del Proyecto

```
AppRentaEasyFinal/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/oarj/rentaeasy/
│   │   │   │   │
│   │   │   │   ├── 📱 MainActivity.kt
│   │   │   │   │   └── Activity principal que aloja la navegación
│   │   │   │   │
│   │   │   │   ├── 🧭 navigation/
│   │   │   │   │   └── Navigation.kt
│   │   │   │   │       └── Configuración de rutas y navegación con NavHost
│   │   │   │   │
│   │   │   │   ├── 📊 models/
│   │   │   │   │   ├── User.kt
│   │   │   │   │   │   └── Data class: información de usuario (id, email, nombre, tipo)
│   │   │   │   │   │
│   │   │   │   │   ├── Property.kt
│   │   │   │   │   │   └── Data class: propiedad (id, título, precio, ubicación, imágenes, etc.)
│   │   │   │   │   │
│   │   │   │   │   └── Favorite.kt
│   │   │   │   │       └── Data class: favorito (userId, propertyId, timestamp)
│   │   │   │   │
│   │   │   │   ├── 🗄️ repository/
│   │   │   │   │   ├── AuthRepository.kt
│   │   │   │   │   │   └── Gestiona autenticación: registro, login, logout
│   │   │   │   │   │
│   │   │   │   │   ├── PropertyRepository.kt
│   │   │   │   │   │   └── CRUD de propiedades: crear, leer, actualizar, eliminar
│   │   │   │   │   │
│   │   │   │   │   └── FavoriteRepository.kt
│   │   │   │   │       └── Gestiona favoritos: agregar, eliminar, obtener lista
│   │   │   │   │
│   │   │   │   ├── 🎯 viewmodels/
│   │   │   │   │   ├── AuthViewModel.kt
│   │   │   │   │   │   └── ViewModel para autenticación con estados de login/registro
│   │   │   │   │   │
│   │   │   │   │   ├── PropertyViewModel.kt
│   │   │   │   │   │   └── ViewModel para propiedades con estados y operaciones CRUD
│   │   │   │   │   │
│   │   │   │   │   └── FavoriteViewModel.kt
│   │   │   │   │       └── ViewModel para gestión de favoritos
│   │   │   │   │
│   │   │   │   ├── 🖼️ screens/
│   │   │   │   │   │
│   │   │   │   │   ├── login/
│   │   │   │   │   │   └── LoginScreen.kt
│   │   │   │   │   │       └── Pantalla de inicio de sesión con validación
│   │   │   │   │   │
│   │   │   │   │   ├── register/
│   │   │   │   │   │   └── RegisterScreen.kt
│   │   │   │   │   │       └── Formulario de registro (Inquilino/Propietario)
│   │   │   │   │   │
│   │   │   │   │   ├── home/
│   │   │   │   │   │   └── HomeScreen.kt
│   │   │   │   │   │       └── Listado principal de propiedades con búsqueda
│   │   │   │   │   │
│   │   │   │   │   ├── favorites/
│   │   │   │   │   │   └── FavoritesScreen.kt
│   │   │   │   │   │       └── Lista de propiedades guardadas como favoritas
│   │   │   │   │   │
│   │   │   │   │   ├── profile/
│   │   │   │   │   │   └── ProfileScreen.kt
│   │   │   │   │   │       └── Perfil de usuario con información y opciones
│   │   │   │   │   │
│   │   │   │   │   ├── createproperty/
│   │   │   │   │   │   └── CreatePropertyScreen.kt
│   │   │   │   │   │       └── Formulario para crear nuevas publicaciones
│   │   │   │   │   │
│   │   │   │   │   └── propertydetail/
│   │   │   │   │       └── PropertyDetailScreen.kt
│   │   │   │   │           └── Detalle completo de una propiedad con galería
│   │   │   │   │
│   │   │   │   ├── 🧩 components/
│   │   │   │   │   ├── PropertyCard.kt
│   │   │   │   │   │   └── Card reutilizable para mostrar preview de propiedad
│   │   │   │   │   │
│   │   │   │   │   ├── SearchBar.kt
│   │   │   │   │   │   └── Barra de búsqueda con filtro por ubicación
│   │   │   │   │   │
│   │   │   │   │   └── BottomNavigationBar.kt
│   │   │   │   │       └── Barra de navegación inferior (Home/Favorites/Profile)
│   │   │   │   │
│   │   │   │   └── 🎨 ui/theme/
│   │   │   │       ├── Color.kt
│   │   │   │       │   └── Define paleta de colores (Purple, Dark theme)
│   │   │   │       │
│   │   │   │       ├── Type.kt
│   │   │   │       │   └── Define tipografía (Material Design)
│   │   │   │       │
│   │   │   │       └── Theme.kt
│   │   │   │           └── Configuración del tema de la app
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   └── Iconos y recursos gráficos vectoriales
│   │   │   │   │
│   │   │   │   ├── mipmap/
│   │   │   │   │   └── ic_launcher*.png (Iconos de la app)
│   │   │   │   │
│   │   │   │   └── values/
│   │   │   │       ├── strings.xml
│   │   │   │       │   └── Textos y strings de la aplicación
│   │   │   │       │
│   │   │   │       ├── colors.xml
│   │   │   │       │   └── Colores adicionales (si se usan)
│   │   │   │       │
│   │   │   │       └── themes.xml
│   │   │   │           └── Temas XML (para componentes no-Compose)
│   │   │   │
│   │   │   ├── AndroidManifest.xml
│   │   │   │   └── Manifiesto: permisos, Activities, configuración
│   │   │   │
│   │   │   └── google-services.json
│   │   │       └── Configuración de Firebase (DEBE SER GENERADO)
│   │   │
│   │   └── build.gradle.kts
│   │       └── Configuración de dependencias y plugins del módulo app
│   │
│   └── proguard-rules.pro
│       └── Reglas de ofuscación para compilación Release
│
├── screenshots/
│   ├── login.png
│   ├── register.png
│   ├── home.png
│   ├── detail.png
│   ├── create.png
│   ├── favorites.png
│   └── profile.png
│
├── .gitignore
│   └── Archivos ignorados por Git (google-services.json, .idea/, build/)
│
├── build.gradle.kts
│   └── Build script raíz del proyecto
│
├── settings.gradle.kts
│   └── Configuración de módulos del proyecto
│
├── gradle.properties
│   └── Propiedades de Gradle
│
├── gradlew
│   └── Gradle wrapper (Linux/Mac)
│
├── gradlew.bat
│   └── Gradle wrapper (Windows)
│
├── LICENSE
│   └── Licencia MIT
│
└── README.md
    └── Este archivo de documentación
```

---

## 📖 Guía de Desarrollo Paso a Paso

Esta sección te guiará en cómo replicar la aplicación desde cero, explicando qué hace cada archivo sin mostrar todo el código.

### Paso 1: Configuración Inicial del Proyecto

#### 1.1 Crear Proyecto en Android Studio

```
1. File > New > New Project
2. Seleccionar "Empty Activity"
3. Name: RentaEasy
4. Package name: com.oarj.rentaeasy
5. Language: Kotlin
6. Minimum SDK: API 24 (Android 7.0)
7. Build configuration language: Kotlin DSL
```

#### 1.2 Configurar build.gradle.kts (Project Level)

**Ubicación:** `build.gradle.kts` (raíz del proyecto)

**Qué hace este archivo:**
- Define plugins globales del proyecto
- Configura repositorios de dependencias
- Establece versiones de herramientas

**Configuraciones clave:**
```kotlin
// Aquí pegarás tu archivo build.gradle.kts del proyecto raíz
// Define plugins de Kotlin, Android y Google Services
```

#### 1.3 Configurar build.gradle.kts (Module: app)

**Ubicación:** `app/build.gradle.kts`

**Qué hace este archivo:**
- Define todas las dependencias de la app
- Configura opciones de compilación
- Habilita Jetpack Compose

**Dependencias principales que incluye:**
- Jetpack Compose UI
- Firebase (Auth, Firestore, Storage)
- Navigation Compose
- Coil para imágenes
- ViewModel y Lifecycle

```kotlin
// Aquí pegarás tu archivo app/build.gradle.kts
// Incluye todas las dependencias de Firebase, Compose, etc.
```

---

### Paso 2: Configurar Firebase

#### 2.1 google-services.json

**Ubicación:** `app/google-services.json`

**Qué hace:**
- Conecta tu app con tu proyecto de Firebase
- Contiene claves API y configuración
- **DEBE SER GENERADO** desde Firebase Console

**Cómo obtenerlo:**
1. Firebase Console > Configuración del proyecto
2. Agregar app Android
3. Descargar archivo JSON
4. Colocar en carpeta `app/`

---

### Paso 3: Definir Modelos de Datos

#### 3.1 User.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/models/User.kt`

**Qué hace:**
- Define la estructura de datos de un usuario
- Contiene: id, email, nombre, apellido, tipo (INQUILINO/PROPIETARIO)
- Se usa para almacenar y recuperar usuarios de Firestore

```kotlin
// Aquí pegarás tu archivo User.kt
// Data class con propiedades del usuario
```

#### 3.2 Property.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/models/Property.kt`

**Qué hace:**
- Define la estructura de una propiedad/departamento
- Contiene: id, título, descripción, precio, ubicación, imageUrls, ownerId, etc.
- Se serializa/deserializa con Firestore

```kotlin
// Aquí pegarás tu archivo Property.kt
// Data class con todos los atributos de una propiedad
```

#### 3.3 Favorite.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/models/Favorite.kt`

**Qué hace:**
- Representa la relación Usuario-Propiedad favorita
- Contiene: userId, propertyId, timestamp
- Permite gestionar lista de favoritos por usuario

```kotlin
// Aquí pegarás tu archivo Favorite.kt
// Data class simple para la relación favoritos
```

---

### Paso 4: Implementar Repositorios (Acceso a Datos)

#### 4.1 AuthRepository.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/repository/AuthRepository.kt`

**Qué hace:**
- Maneja toda la lógica de autenticación con Firebase Auth
- Métodos principales:
  - `registerUser()`: Registra nuevo usuario
  - `loginUser()`: Inicia sesión
  - `logoutUser()`: Cierra sesión
  - `getCurrentUser()`: Obtiene usuario actual
  - `saveUserToFirestore()`: Guarda datos adicionales en Firestore

**Tecnologías usadas:**
- Firebase Authentication
- Cloud Firestore (para datos de usuario)
- Kotlin Coroutines para operaciones asíncronas

```kotlin
// Aquí pegarás tu archivo AuthRepository.kt
// Clase que encapsula operaciones de Firebase Auth
```

#### 4.2 PropertyRepository.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/repository/PropertyRepository.kt`

**Qué hace:**
- Gestiona todas las operaciones CRUD de propiedades
- Métodos principales:
  - `createProperty()`: Crea nueva propiedad
  - `getAllProperties()`: Obtiene todas las propiedades
  - `getPropertyById()`: Obtiene propiedad específica
  - `updateProperty()`: Actualiza propiedad
  - `deleteProperty()`: Elimina propiedad
  - `getPropertiesByOwner()`: Filtra por dueño
  - `uploadImages()`: Sube imágenes a Storage

**Tecnologías usadas:**
- Cloud Firestore
- Firebase Storage
- StateFlow para observación reactiva

```kotlin
// Aquí pegarás tu archivo PropertyRepository.kt
// Clase con métodos CRUD completos para propiedades
```

#### 4.3 FavoriteRepository.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/repository/FavoriteRepository.kt`

**Qué hace:**
- Maneja operaciones relacionadas con favoritos
- Métodos principales:
  - `addFavorite()`: Agrega propiedad a favoritos
  - `removeFavorite()`: Elimina de favoritos
  - `isFavorite()`: Verifica si es favorito
  - `getUserFavorites()`: Obtiene lista de favoritos del usuario

**Tecnologías usadas:**
- Cloud Firestore
- Flow para datos reactivos

```kotlin
// Aquí pegarás tu archivo FavoriteRepository.kt
// Gestiona la colección 'favorites' en Firestore
```

---

### Paso 5: Crear ViewModels (Lógica de Presentación)

#### 5.1 AuthViewModel.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/viewmodels/AuthViewModel.kt`

**Qué hace:**
- ViewModel que gestiona estados de autenticación
- Expone StateFlow para la UI
- Estados que maneja:
  - Loading (cargando)
  - Success (éxito)
  - Error (mensaje de error)
- Métodos principales:
  - `login()`: Procesa login
  - `register()`: Procesa registro
  - `logout()`: Cierra sesión

**Por qué es importante:**
- Sobrevive a rotaciones de pantalla
- Separa lógica de negocio de UI
- Permite testing más fácil

```kotlin
// Aquí pegarás tu archivo AuthViewModel.kt
// ViewModel con StateFlow para estados de auth
```

#### 5.2 PropertyViewModel.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/viewmodels/PropertyViewModel.kt`

**Qué hace:**
- Gestiona estados y operaciones de propiedades
- Expone listas observables de propiedades
- Métodos principales:
  - `loadAllProperties()`: Carga listado
  - `createProperty()`: Crea nueva
  - `deleteProperty()`: Elimina
  - `searchByLocation()`: Filtra por ubicación
  
**Estados que expone:**
- Lista de propiedades (StateFlow)
- Estado de carga
- Errores

```kotlin
// Aquí pegarás tu archivo PropertyViewModel.kt
// ViewModel con operaciones CRUD y filtrado
```

#### 5.3 FavoriteViewModel.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/viewmodels/FavoriteViewModel.kt`

**Qué hace:**
- Gestiona estado de favoritos del usuario
- Métodos principales:
  - `loadFavorites()`: Carga lista de favoritos
  - `toggleFavorite()`: Agrega/elimina favorito
  - `isFavorite()`: Verifica estado

```kotlin
// Aquí pegarás tu archivo FavoriteViewModel.kt
// ViewModel específico para favoritos
```

---

### Paso 6: Diseñar el Tema de la App

#### 6.1 Color.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/ui/theme/Color.kt`

**Qué hace:**
- Define la paleta de colores de la aplicación
- Colores para tema oscuro (Purple, Pink, etc.)
- Se usan en todo Compose

```kotlin
// Aquí pegarás tu archivo Color.kt
// Definición de colores primarios, secundarios, etc.
```

#### 6.2 Type.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/ui/theme/Type.kt`

**Qué hace:**
- Define la tipografía de Material Design
- Tamaños de fuente (displayLarge, bodyMedium, etc.)
- Estilos de texto consistentes

```kotlin
// Aquí pegarás tu archivo Type.kt
// Configuración de Typography de Material3
```

#### 6.3 Theme.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/ui/theme/Theme.kt`

**Qué hace:**
- Configura el tema completo de la app
- Combina colores, tipografía y formas
- Aplica Material Design 3
- Define tema oscuro

```kotlin
// Aquí pegarás tu archivo Theme.kt
// @Composable que envuelve toda la app con el tema
```

---

### Paso 7: Crear Componentes Reutilizables

#### 7.1 PropertyCard.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/components/PropertyCard.kt`

**Qué hace:**
- Componente Composable reutilizable
- Muestra preview de una propiedad en formato Card
- Incluye: imagen, título, precio, ubicación
- Se usa en HomeScreen y FavoritesScreen
- Maneja click para navegar a detalle

```kotlin
// Aquí pegarás tu archivo PropertyCard.kt
// @Composable Card con toda la info resumida de la propiedad
```

#### 7.2 SearchBar.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/components/SearchBar.kt`

**Qué hace:**
- Barra de búsqueda personalizada
- TextField con ícono de búsqueda
- Filtra propiedades por ubicación
- Callback para cambios de texto

```kotlin
// Aquí pegarás tu archivo SearchBar.kt
// @Composable TextField estilizado para búsqueda
```

#### 7.3 BottomNavigationBar.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/components/BottomNavigationBar.kt`

**Qué hace:**
- Barra de navegación inferior con 3-4 íconos
- Items: Home, Favoritos, Perfil (y opcionalmente Crear)
- Integrada con Navigation Component
- Cambia de pantalla al hacer click

```kotlin
// Aquí pegarás tu archivo BottomNavigationBar.kt
// NavigationBar de Material3 con íconos
```

---

### Paso 8: Implementar Pantallas (Screens)

#### 8.1 LoginScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/login/LoginScreen.kt`

**Qué hace:**
- Pantalla de inicio de sesión
- Formulario con email y contraseña
- Validación de campos
- Botones: Login, Ir a Registro
- Observa estados del AuthViewModel
- Navega a Home si login es exitoso

**Elementos UI:**
- TextFields (email, password)
- Button (Iniciar Sesión)
- TextButton (¿No tienes cuenta?)
- CircularProgressIndicator (loading)
- Alerts para errores

```kotlin
// Aquí pegarás tu archivo LoginScreen.kt
// @Composable completo con formulario de login
```

#### 8.2 RegisterScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/register/RegisterScreen.kt`

**Qué hace:**
- Formulario de registro de usuarios
- Campos: nombre, apellido, email, contraseña, confirmar contraseña
- RadioButtons para seleccionar tipo: INQUILINO o PROPIETARIO
- Validaciones (email válido, contraseñas coinciden, etc.)
- Llama a AuthViewModel para registrar
- Navega a Home tras éxito

```kotlin
// Aquí pegarás tu archivo RegisterScreen.kt
// @Composable con formulario extenso de registro
```

#### 8.3 HomeScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/home/HomeScreen.kt`

**Qué hace:**
- Pantalla principal de la app
- Muestra todas las propiedades disponibles
- SearchBar para filtrar por ubicación
- LazyColumn con PropertyCards
- Botón flotante (+) para crear propiedad (solo propietarios)
- BottomNavigationBar
- Observa PropertyViewModel

**Componentes:**
- TopAppBar con título
- SearchBar
- LazyColumn de PropertyCards
- FloatingActionButton
- BottomNavigationBar

```kotlin
// Aquí pegarás tu archivo HomeScreen.kt
// Listado principal de propiedades con búsqueda
```

#### 8.4 PropertyDetailScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/propertydetail/PropertyDetailScreen.kt`

**Qué hace:**
- Muestra detalle completo de una propiedad
- Galería de imágenes (ViewPager o LazyRow)
- Información: título, precio, ubicación, descripción, características
- Botón de favorito (corazón)
- Información del propietario (nombre, contacto)
- Recibe propertyId como parámetro de navegación

**Elementos:**
- HorizontalPager para galería
- Secciones con Text e Icon
- IconButton para favorito
- Botones de contacto

```kotlin
// Aquí pegarás tu archivo PropertyDetailScreen.kt
// Vista detallada con galería e info completa
```

#### 8.5 CreatePropertyScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/createproperty/CreatePropertyScreen.kt`

**Qué hace:**
- Formulario para crear nueva publicación
- Solo accesible para PROPIETARIOS
- Campos: título, descripción, precio, ubicación, características
- Selector de imágenes (múltiples)
- Validación de campos obligatorios
- Llama a PropertyViewModel.createProperty()
- Navega de regreso al Home tras crear

**Componentes:**
- Multiple TextFields
- Image picker button
- Preview de imágenes seleccionadas
- Button (Publicar)

```kotlin
// Aquí pegarás tu archivo CreatePropertyScreen.kt
// Formulario completo de creación de propiedad
```

#### 8.6 FavoritesScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/favorites/FavoritesScreen.kt`

**Qué hace:**
- Lista de propiedades marcadas como favoritas
- Obtiene datos de FavoriteViewModel
- Muestra PropertyCards
- Permite remover de favoritos
- Si no hay favoritos, muestra mensaje vacío

```kotlin
// Aquí pegarás tu archivo FavoritesScreen.kt
// LazyColumn de favoritos con opción de eliminar
```

#### 8.7 ProfileScreen.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/screens/profile/ProfileScreen.kt`

**Qué hace:**
- Perfil del usuario actual
- Muestra: nombre, email, tipo de usuario
- Botón "Mis Publicaciones" (si es propietario)
- Botón "Cerrar Sesión"
- Navega a Login al hacer logout

**Elementos:**
- Card con info del usuario
- Buttons (Mis Publicaciones, Cerrar Sesión)
- Dialog de confirmación para logout

```kotlin
// Aquí pegarás tu archivo ProfileScreen.kt
// Pantalla de perfil con opciones de usuario
```

---

### Paso 9: Configurar Navegación

#### 9.1 Navigation.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/navigation/Navigation.kt`

**Qué hace:**
- Define todas las rutas de navegación
- Configura NavHost con todos los destinos
- Maneja argumentos de navegación (ej: propertyId)
- Conecta BottomNav con NavController

**Rutas definidas:**
```kotlin
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object PropertyDetail : Screen("property_detail/{propertyId}")
    object CreateProperty : Screen("create_property")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
}
```

**Funcionalidades:**
- startDestination (Login si no autenticado, Home si sí)
- composable() para cada pantalla
- Pasar ViewModels a cada screen

```kotlin
// Aquí pegarás tu archivo Navigation.kt
// NavHost con todas las rutas y sus composables
```

---

### Paso 10: Configurar MainActivity

#### 10.1 MainActivity.kt

**Ubicación:** `app/src/main/java/com/oarj/rentaeasy/MainActivity.kt`

**Qué hace:**
- Activity principal que aloja toda la navegación Compose
- Inicializa NavController
- Inicializa ViewModels
- Aplica el tema RentaEasyTheme
- Configura Surface con NavHost

**Estructura:**
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentaEasyTheme {
                Surface {
                    // NavHost aquí
                    Navigation()
                }
            }
        }
    }
}
```

```kotlin
// Aquí pegarás tu archivo MainActivity.kt
// Punto de entrada de la app con Compose
```

---

### Paso 11: Configurar Recursos

#### 11.1 strings.xml

**Ubicación:** `app/src/main/res/values/strings.xml`

**Qué hace:**
- Define todos los textos de la app en español
- Facilita internacionalización futura
- Centraliza strings reutilizables

```xml


```

#### 11.2 AndroidManifest.xml

**Ubicación:** `app/src/main/AndroidManifest.xml`

**Qué hace:**
- Declara permisos (INTERNET, etc.)
- Define MainActivity como launcher
- Configura tema de la app
- Nombre e ícono de la aplicación

```xml


```

---

## 🗄 Base de Datos (Firestore)

### Estructura de Colecciones

RentaEasy utiliza **Cloud Firestore** como base de datos NoSQL. A continuación se describen las colecciones:

#### 1. Colección: `users`

**Estructura de Documento:**
```json
{
  "id": "user_uid_from_auth",
  "email": "usuario@example.com",
  "firstName": "Juan",
  "lastName": "Pérez",
  "userType": "INQUILINO", // o "PROPIETARIO"
  "createdAt": "2025-01-20T10:30:00Z"
}
```

**Uso:**
- Almacena información adicional de usuarios registrados
- El `id` coincide con el UID de Firebase Authentication
- Se consulta para obtener tipo de usuario

#### 2. Colección: `properties`

**Estructura de Documento:**
```json
{
  "id": "property_auto_generated_id",
  "title": "Departamento Céntrico 2 Habitaciones",
  "description": "Amplio departamento cerca de la universidad...",
  "price": 5000.00,
  "location": "Centro, Pachuca",
  "imageUrls": [
    "https://firebasestorage.../image1.jpg",
    "https://firebasestorage.../image2.jpg"
  ],
  "features": "2 habitaciones, 1 baño, cocina equipada",
  "ownerId": "user_uid_propietario",
  "ownerName": "María López",
  "ownerContact": "maria@example.com",
  "createdAt": "2025-01-22T15:00:00Z"
}
```

**Índices Necesarios:**
- `location` (para búsquedas)
- `ownerId` (para filtrar propiedades del usuario)

#### 3. Colección: `favorites`

**Estructura de Documento:**
```json
{
  "id": "favorite_auto_generated_id",
  "userId": "user_uid_inquilino",
  "propertyId": "property_id",
  "timestamp": "2025-01-23T09:00:00Z"
}
```

**Índices Compuestos:**
- `userId` + `propertyId` (para verificar si ya existe favorito)
- `userId` (para obtener todos los favoritos de un usuario)

### Reglas de Seguridad de Firestore

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Reglas para colección users
    match /users/{userId} {
      // Solo el usuario puede leer/escribir su propio documento
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Reglas para colección properties
    match /properties/{propertyId} {
      // Todos los autenticados pueden leer
      allow read: if request.auth != null;
      // Solo propietarios pueden crear
      allow create: if request.auth != null && 
                       request.resource.data.ownerId == request.auth.uid;
      // Solo el dueño puede actualizar/eliminar
      allow update, delete: if request.auth != null && 
                                resource.data.ownerId == request.auth.uid;
    }
    
    // Reglas para colección favorites
    match /favorites/{favoriteId} {
      // Solo el usuario puede leer sus favoritos
      allow read: if request.auth != null && 
                     resource.data.userId == request.auth.uid;
      // Solo el usuario puede agregar a sus favoritos
      allow create: if request.auth != null && 
                       request.resource.data.userId == request.auth.uid;
      // Solo el usuario puede eliminar sus favoritos
      allow delete: if request.auth != null && 
                       resource.data.userId == request.auth.uid;
    }
  }
}
```

---

## 🎨 Componentes Principales

### PropertyCard Component

**Ubicación:** `components/PropertyCard.kt`

**Descripción:**
Componente reutilizable que muestra un preview de una propiedad en formato de tarjeta (Card).

**Props:**
- `property: Property` - Objeto con los datos de la propiedad
- `onClick: () -> Unit` - Callback cuando se toca la card
- `onFavoriteClick: () -> Unit` - Callback para botón de favorito

**Elementos visuales:**
- Imagen principal de la propiedad (Coil AsyncImage)
- Título de la propiedad
- Precio por mes
- Ubicación con ícono
- Botón de favorito (corazón)

**Uso:**
```kotlin
PropertyCard(
    property = myProperty,
    onClick = { navController.navigate("property_detail/${myProperty.id}") },
    onFavoriteClick = { viewModel.toggleFavorite(myProperty.id) }
)
```

### SearchBar Component

**Ubicación:** `components/SearchBar.kt`

**Descripción:**
Barra de búsqueda personalizada con ícono de lupa.

**Props:**
- `query: String` - Texto de búsqueda actual
- `onQueryChange: (String) -> Unit` - Callback al cambiar texto
- `placeholder: String` - Texto placeholder

**Elementos:**
- OutlinedTextField con forma redondeada
- Icon de búsqueda (Search)
- Icono de limpiar (X) cuando hay texto

### BottomNavigationBar Component

**Ubicación:** `components/BottomNavigationBar.kt`

**Descripción:**
Barra de navegación inferior con íconos para las pantallas principales.

**Props:**
- `navController: NavController` - Controlador de navegación
- `currentRoute: String?` - Ruta actual para resaltar

**Items:**
1. Home (ícono: Home)
2. Favoritos (ícono: Favorite)
3. Perfil (ícono: Person)

**Comportamiento:**
- Resalta el ítem actual
- Navega al tocar un ítem
- Usa Material3 NavigationBar

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Si deseas mejorar RentaEasy:

### Pasos para Contribuir

1. **Fork del Repositorio**
```bash
# Haz clic en "Fork" en GitHub
git clone https://github.com/TU_USUARIO/AppRentaEasyFinal.git
cd AppRentaEasyFinal
```

2. **Crear una Rama**
```bash
# Crea una rama para tu feature o bugfix
git checkout -b feature/nueva-funcionalidad
# O para un bugfix
git checkout -b fix/corregir-bug
```

3. **Hacer Cambios**
- Escribe código limpio y bien documentado
- Sigue las convenciones de Kotlin
- Agrega comentarios para funciones complejas
- Asegúrate de que compile sin errores

4. **Commit y Push**
```bash
# Agrega tus cambios
git add .

# Commit con mensaje descriptivo
git commit -m "feat: agregar sistema de calificaciones"

# Push a tu fork
git push origin feature/nueva-funcionalidad
```

5. **Pull Request**
- Ve a tu fork en GitHub
- Haz clic en "Pull Request"
- Describe tus cambios detalladamente
- Espera revisión y feedback

### Convenciones de Código

**Nombres de Archivos:**
- Screens: `*Screen.kt` (ej: `HomeScreen.kt`)
- ViewModels: `*ViewModel.kt` (ej: `PropertyViewModel.kt`)
- Components: `*Component.kt` o nombre descriptivo
- Repositories: `*Repository.kt`

**Estructura de Código:**
```kotlin
// 1. Imports
import androidx.compose.runtime.*

// 2. Data classes / Models
data class User(...)

// 3. Composables
@Composable
fun MyScreen() {
    // Estados
    var state by remember { mutableStateOf(...) }
    
    // UI
    Column {
        // Contenido
    }
}
```

**Documentación:**
- Documenta funciones públicas con KDoc
- Explica lógica compleja con comentarios
- Usa nombres descriptivos de variables

### Ideas para Contribuir

**Funcionalidades:**
- [ ] Sistema de chat entre inquilino y propietario
- [ ] Integración con Google Maps
- [ ] Sistema de calificaciones y reseñas
- [ ] Notificaciones push para nuevas propiedades
- [ ] Filtros avanzados (rango de precio, habitaciones, etc.)
- [ ] Modo claro además del oscuro
- [ ] Soporte multiidioma (inglés)
- [ ] Sistema de reservas/citas

**Mejoras UI/UX:**
- [ ] Animaciones más fluidas
- [ ] Transiciones entre pantallas
- [ ] Skeleton loading screens
- [ ] Mejor manejo de estados vacíos
- [ ] Splash screen animado

**Testing:**
- [ ] Unit tests para ViewModels
- [ ] UI tests con Compose Testing
- [ ] Integration tests

**Performance:**
- [ ] Paginación en lista de propiedades
- [ ] Caché de imágenes
- [ ] Optimización de consultas Firestore

---

## 📄 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**.

```
MIT License

Copyright (c) 2025 Oscar Ramirez - RentaEasy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📞 Contacto

### 👨‍💻 Desarrollador

**Oscar Ramirez**

- 🌐 GitHub: [@oscar1224100523](https://github.com/oscar1224100523)
- 📧 Email: oscar.ramirez@example.com (actualiza con tu email)
- 📱 Proyecto: [AppRentaEasyFinal](https://github.com/oscar1224100523/AppRentaEasyFinal)

### 🐛 Reportar Bugs

Si encuentras un error, por favor abre un [Issue](https://github.com/oscar1224100523/AppRentaEasyFinal/issues) con:

- **Descripción clara** del problema
- **Pasos para reproducir**
- **Capturas de pantalla** (si aplica)
- **Versión de Android**
- **Modelo de dispositivo**

### 💡 Sugerencias

¿Tienes una idea para mejorar RentaEasy?

- Abre un [Issue](https://github.com/oscar1224100523/AppRentaEasyFinal/issues) con la etiqueta "enhancement"
- Describe tu sugerencia en detalle
- Explica por qué sería útil

---

## 🙏 Agradecimientos

Este proyecto fue posible gracias a:

- **Jetpack Compose Team** - Por el increíble framework UI
- **Firebase** - Por los servicios de backend gratuitos
- **Google Developers** - Por la excelente documentación
- **Material Design** - Por el sistema de diseño moderno
- **Coil** - Por la librería de carga de imágenes
- **Kotlin Community** - Por el soporte y recursos
- **Stack Overflow** - Por resolver dudas técnicas
- **RecetApp Team** (Cristian y David) - Por la inspiración del README

---

## 🌟 ¿Te gustó el proyecto?

Si este proyecto te fue útil o aprendiste algo:

⭐ **Dale una estrella en GitHub** - Ayuda a que más personas lo descubran

🔀 **Haz un Fork** - Crea tu propia versión

📢 **Compártelo** - Ayuda a otros estudiantes

📝 **Contribuye** - Mejora el código y la documentación

---

## 📊 Estadísticas del Proyecto

| Métrica | Valor |
|---------|-------|
| **Lenguaje Principal** | Kotlin 100% |
| **Pantallas Principales** | 7 |
| **Componentes Reutilizables** | 3+ |
| **ViewModels** | 3 |
| **Repositorios** | 3 |
| **Modelos de Datos** | 3 |
| **Servicios Firebase** | 3 (Auth, Firestore, Storage) |
| **Líneas de Código** | ~2500+ |

---

## 🎓 Aprendizajes Clave

Al desarrollar este proyecto, aprenderás:

✅ **Arquitectura MVVM** - Separación de responsabilidades
✅ **Jetpack Compose** - UI declarativa moderna
✅ **Firebase Integration** - Backend como servicio
✅ **State Management** - StateFlow y Compose State
✅ **Navigation Component** - Navegación con argumentos
✅ **CRUD Operations** - Crear, Leer, Actualizar, Eliminar
✅ **Image Handling** - Subida y carga de imágenes
✅ **Form Validation** - Validación de formularios
✅ **User Authentication** - Login y registro seguros
✅ **Material Design 3** - Diseño moderno y consistente

---

## 🚀 Roadmap Futuro

### Versión 2.0 (Planeada)

- [ ] **Sistema de Mensajería** - Chat en tiempo real con Firebase Realtime Database
- [ ] **Google Maps Integration** - Mapa interactivo con ubicación de propiedades
- [ ] **Sistema de Calificaciones** - Reseñas y ratings para propiedades y propietarios
- [ ] **Notificaciones Push** - Alertas de nuevas propiedades y mensajes
- [ ] **Filtros Avanzados** - Por precio, habitaciones, amenidades
- [ ] **Modo Claro** - Tema adicional al oscuro
- [ ] **Multiidioma** - Soporte para inglés
- [ ] **Verificación de Identidad** - Upload de documentos oficiales
- [ ] **Sistema de Pagos** - Integración con Stripe/PayPal
- [ ] **Tours Virtuales 360°** - Fotos panorámicas interactivas

### Versión 2.5

- [ ] **Machine Learning** - Recomendaciones personalizadas
- [ ] **Widget** - Acceso rápido desde home screen
- [ ] **Wear OS** - App para smartwatches
- [ ] **Backend Propio** - Migrar de Firebase a servidor custom
- [ ] **App Web** - Versión para navegadores

---

## 📚 Recursos Adicionales

### Documentación Oficial

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Firebase Android](https://firebase.google.com/docs/android/setup)
- [Material Design 3](https://m3.material.io/)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)

### Tutoriales Recomendados

- [Android Developers Codelabs](https://developer.android.com/codelabs)
- [Compose Pathway](https://developer.android.com/courses/pathways/compose)
- [Firebase Tutorials](https://firebase.google.com/docs/samples)

### Comunidades

- [Kotlin Slack](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up)
- [Android Developers Reddit](https://www.reddit.com/r/androiddev/)
- [Stack Overflow - Android Tag](https://stackoverflow.com/questions/tagged/android)

---

**Desarrollado con ❤️ para estudiantes que buscan su hogar ideal**

🏠 **RentaEasy** - *Encuentra tu lugar perfecto*

---

