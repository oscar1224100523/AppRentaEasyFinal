# 🏠 RentEasy - Aplicación de Renta de Departamentos para Estudiantes

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

## 📱 Descripción

RentEasy es una aplicación móvil Android diseñada específicamente para facilitar la búsqueda y publicación de departamentos en renta para estudiantes. Inspirada en plataformas como Airbnb y Facebook Marketplace, ofrece una experiencia intuitiva y moderna para conectar inquilinos con propietarios.

## 📸 Capturas de Pantalla

<div align="center">

### Pantalla de Inicio de Sesión
<img src="https://raw.githubusercontent.com/oscar1224100523/AppRentaEasyFinal/main/screenshots/login.png" width="250" alt="Login Screen"/>

*Interfaz limpia y moderna para autenticación de usuarios con validación de credenciales*

---

### Pantalla Principal (Home)
<img src="https://raw.githubusercontent.com/oscar1224100523/AppRentaEasyFinal/main/screenshots/home.png" width="250" alt="Home Screen"/>

*Visualiza todas las propiedades disponibles con búsqueda inteligente por ubicación*

---

### Crear Nueva Publicación
<img src="https://raw.githubusercontent.com/oscar1224100523/AppRentaEasyFinal/main/screenshots/create.png" width="250" alt="Create Property Screen"/>

*Formulario completo para que los propietarios publiquen sus departamentos con múltiples imágenes*

---

### Pantalla de Favoritos
<img src="https://raw.githubusercontent.com/oscar1224100523/AppRentaEasyFinal/main/screenshots/favorites.png" width="250" alt="Favorites Screen"/>

*Acceso rápido a las propiedades guardadas para consulta posterior*

---

### Pantalla de Perfil
<img src="https://raw.githubusercontent.com/oscar1224100523/AppRentaEasyFinal/main/screenshots/profile.png" width="250" alt="Profile Screen"/>

*Gestiona tu información personal, publicaciones y cierra sesión de forma segura*

</div>

## ✨ Características Principales

### Para Inquilinos 🎓
- 🔍 **Búsqueda Inteligente**: Filtra departamentos por ubicación y características
- ❤️ **Sistema de Favoritos**: Guarda tus propiedades favoritas para revisarlas después
- 📱 **Interfaz Moderna**: Navegación intuitiva con Material Design 3
- 🏠 **Detalles Completos**: Visualiza fotos, precios, ubicación y características de cada propiedad
- 👤 **Perfil Personalizado**: Gestiona tu información y preferencias

### Para Propietarios 🏢
- ➕ **Publicación Fácil**: Crea anuncios de propiedades en minutos
- 📸 **Galería de Imágenes**: Sube múltiples fotos de tu propiedad
- ✏️ **Gestión de Publicaciones**: Edita o elimina tus anuncios
- 📊 **Dashboard Personal**: Visualiza todas tus propiedades publicadas

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Kotlin** - Lenguaje de programación principal
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Sistema de diseño moderno
- **Navigation Component** - Navegación entre pantallas
- **Coil** - Carga de imágenes optimizada

### Backend
- **Firebase Authentication** - Autenticación de usuarios
- **Cloud Firestore** - Base de datos NoSQL en tiempo real
- **Firebase Storage** - Almacenamiento de imágenes

### Arquitectura
- **MVVM (Model-View-ViewModel)** - Patrón de arquitectura
- **Repository Pattern** - Capa de abstracción de datos
- **StateFlow** - Gestión de estados reactiva
- **Coroutines** - Programación asíncrona

## 📂 Estructura del Proyecto
```
com.oarj.rentaeasy/
├── 📱 MainActivity.kt
├── 🧭 navigation/
│   └── Navigation.kt
├── 📊 models/
│   ├── User.kt
│   ├── Property.kt
│   └── Favorite.kt
├── 🗄️ repository/
│   ├── AuthRepository.kt
│   ├── PropertyRepository.kt
│   └── FavoriteRepository.kt
├── 🎯 viewmodels/
│   ├── AuthViewModel.kt
│   ├── PropertyViewModel.kt
│   └── FavoriteViewModel.kt
├── 🖼️ screens/
│   ├── login/LoginScreen.kt
│   ├── register/RegisterScreen.kt
│   ├── home/HomeScreen.kt
│   ├── favorites/FavoritesScreen.kt
│   ├── profile/ProfileScreen.kt
│   ├── createproperty/CreatePropertyScreen.kt
│   └── propertydetail/PropertyDetailScreen.kt
├── 🧩 components/
│   ├── PropertyCard.kt
│   ├── SearchBar.kt
│   └── BottomNavigationBar.kt
└── 🎨 ui/theme/
    ├── Color.kt
    ├── Type.kt
    └── Theme.kt
```

## 🚀 Instalación y Configuración

### Prerrequisitos
- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 8 o superior
- Cuenta de Firebase
- Dispositivo Android con API 24+ (Android 7.0) o emulador

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/oscar1224100523/AppRentaEasyFinal.git
cd AppRentaEasyFinal
```

### Paso 2: Configurar Firebase

1. **Crear Proyecto en Firebase**
   - Ve a [Firebase Console](https://console.firebase.google.com/)
   - Crea un nuevo proyecto o selecciona uno existente
   - Haz clic en "Agregar aplicación" → Android

2. **Registrar la App**
   - Nombre del paquete: `com.oarj.rentaeasy`
   - Descarga el archivo `google-services.json`
   - Coloca el archivo en: `app/google-services.json`

3. **Habilitar Servicios de Firebase**

   **Authentication:**
   - Ve a Authentication → Sign-in method
   - Habilita "Correo electrónico/Contraseña"

   **Firestore Database:**
   - Ve a Firestore Database → Crear base de datos
   - Selecciona modo de prueba (puedes cambiar las reglas después)
   - Reglas de seguridad iniciales:
```javascript
   rules_version = '2';
   service cloud.firestore {
     match /databases/{database}/documents {
       match /{document=**} {
         allow read, write: if request.auth != null;
       }
     }
   }
```

   **Storage:**
   - Ve a Storage → Comenzar
   - Selecciona modo de prueba
   - Reglas de seguridad iniciales:
```javascript
   rules_version = '2';
   service firebase.storage {
     match /b/{bucket}/o {
       match /{allPaths=**} {
         allow read, write: if request.auth != null;
       }
     }
   }
```

### Paso 3: Compilar y Ejecutar

1. Abre el proyecto en Android Studio
2. Espera a que Gradle sincronice las dependencias
3. Conecta un dispositivo Android o inicia un emulador
4. Haz clic en "Run" ▶️ o presiona `Shift + F10`

## 🎯 Funcionalidades CRUD

### Create (Crear) ➕
- Registro de nuevos usuarios
- Creación de publicaciones de propiedades
- Agregar propiedades a favoritos

### Read (Leer) 📖
- Visualización de todas las propiedades
- Búsqueda y filtrado por ubicación
- Consulta de detalles de propiedades
- Lista de favoritos personales

### Update (Actualizar) ✏️
- Actualización de información del perfil
- Modificación de publicaciones (próximamente)

### Delete (Eliminar) 🗑️
- Eliminación de publicaciones propias
- Remover propiedades de favoritos
- Cerrar sesión

## 👥 Tipos de Usuario

### Inquilino
- Buscar departamentos disponibles
- Filtrar por ubicación
- Guardar favoritos
- Ver detalles completos
- Contactar propietarios

### Propietario
- Todas las funciones de inquilino, más:
- Crear nuevas publicaciones
- Subir múltiples imágenes
- Gestionar publicaciones propias
- Ver dashboard de propiedades
- Eliminar publicaciones

## 🎨 Diseño de Interfaz

La aplicación utiliza un esquema de colores moderno con tonos morados y un tema oscuro que:
- ✅ Reduce la fatiga visual
- ✅ Mejora la legibilidad
- ✅ Proporciona una experiencia premium
- ✅ Ahorra batería en dispositivos OLED

## 🔧 Dependencias Principales
```gradle
dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    
    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.12.1")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    
    // Image Loading
    implementation("io.coil-kt:coil-compose:2.5.0")
}
```

## 🔐 Seguridad

- ✅ Autenticación requerida para todas las operaciones
- ✅ Validación de datos en cliente y servidor
- ✅ Reglas de seguridad de Firestore implementadas
- ✅ Almacenamiento seguro de imágenes en Firebase Storage
- ✅ No se almacenan contraseñas en texto plano

## 🐛 Solución de Problemas

### Error: "google-services.json not found"
**Solución:** Asegúrate de haber descargado el archivo de Firebase Console y colocarlo en `app/google-services.json`

### Error: "Failed to authenticate"
**Solución:** Verifica que Authentication esté habilitado en Firebase Console

### Error: "Permission denied" en Firestore
**Solución:** Revisa las reglas de seguridad en Firestore Database

### Error: "PERMISSION_DENIED: Missing or insufficient permissions"
**Solución:** 
1. Verifica que estés autenticado correctamente
2. Revisa los permisos en `AndroidManifest.xml`:
```xml



```

### Las imágenes no se cargan
**Solución:** 
1. Verifica que Storage esté configurado en Firebase
2. Revisa las reglas de seguridad de Storage
3. Comprueba la conexión a Internet

## 🚀 Futuras Mejoras

- [ ] Sistema de mensajería entre inquilinos y propietarios
- [ ] Mapa interactivo con ubicación de propiedades
- [ ] Sistema de calificaciones y reseñas
- [ ] Notificaciones push para nuevas propiedades
- [ ] Filtros avanzados (precio, número de habitaciones, etc.)
- [ ] Modo claro adicional al modo oscuro
- [ ] Soporte multiidioma
- [ ] Integración con Google Maps
- [ ] Sistema de reservas y pagos
- [ ] Chat en tiempo real
- [ ] Verificación de identidad de usuarios

## 📝 Versiones

### v1.0.0 (Actual)
- ✅ Sistema de autenticación completo
- ✅ CRUD de propiedades
- ✅ Sistema de favoritos
- ✅ Búsqueda por ubicación
- ✅ Perfiles de usuario (Inquilino/Propietario)
- ✅ Carga de imágenes múltiples
- ✅ Navegación fluida entre pantallas
- ✅ Tema oscuro moderno
- ✅ Validación de formularios
- ✅ Gestión de estados con StateFlow

## 👨‍💻 Autor

**Oscar Ramirez**
- GitHub: [@oscar1224100523](https://github.com/oscar1224100523)
- Proyecto: [AppRentaEasyFinal](https://github.com/oscar1224100523/AppRentaEasyFinal)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add: nueva característica increíble'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📞 Contacto

¿Preguntas o sugerencias? Abre un [Issue](https://github.com/oscar1224100523/AppRentaEasyFinal/issues) en GitHub.

---

**Desarrollado con ❤️ para estudiantes que buscan su hogar ideal**

## 🎓 Contexto Académico

Este proyecto fue desarrollado como parte de un curso de desarrollo móvil, demostrando:
- Implementación de arquitectura MVVM
- Integración con servicios Backend (Firebase)
- Diseño de interfaces modernas con Jetpack Compose
- Gestión de estados con StateFlow
- Programación asíncrona con Coroutines
- Implementación de CRUD completo
- Buenas prácticas de desarrollo Android
- Manejo de permisos y almacenamiento
- Navegación con Jetpack Navigation Component

## 🏆 Características Destacadas

- **Diseño Moderno**: Interfaz intuitiva con Material Design 3
- **Arquitectura Limpia**: Separación clara de responsabilidades
- **Código Mantenible**: Estructura organizada y documentada
- **Experiencia de Usuario**: Navegación fluida y feedback visual
- **Seguridad**: Autenticación y autorización implementadas
- **Escalabilidad**: Preparado para nuevas funcionalidades

---

⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub!

## 📊 Estadísticas del Proyecto

- **Lenguaje Principal:** Kotlin 100%
- **Pantallas:** 7 pantallas principales
- **Componentes Reutilizables:** 3
- **Modelos de Datos:** 3
- **ViewModels:** 3
- **Repositorios:** 3
- **Líneas de Código:** ~2000+

---

### 🌟 Agradecimientos

Gracias por revisar este proyecto. Cualquier feedback es bienvenido para seguir mejorando.

