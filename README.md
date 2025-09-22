# DistribuidoraApp

Aplicación móvil desarrollada en Android Studio para una distribuidora de alimentos.  
Permite a los clientes calcular el costo de despacho de sus compras y al administrador monitorear la temperatura del camión en tiempo real.  

---

##  Funcionalidades principales
- Login con Firebase Authentication (correo/contraseña y Google).
- SSO con cuenta Gmail.
- Registro de posición GPS en Firebase Realtime Database al iniciar sesión.
- Cálculo automático de despacho** según reglas de negocio:
  - Compra ≥ $50.000 y ≤ 20 km → Despacho gratis.
  - Compra entre $25.000 y $49.999 → $150/km.
  - Compra < $25.000 → $300/km.
- Monitoreo de temperatura del camión:
  - Si supera 5 °C, muestra alerta en la app.

---

## Tecnologías usadas
- Android Studio (Kotlin)
- Firebase Authentication
- Firebase Realtime Database
- Google Play Services (Location, Maps)
- GitHub para control de versiones y colaboración

---

## Historias de usuario
- Cliente: como cliente quiero registrarme con mi cuenta Gmail para acceder fácilmente.  
- Cliente: como cliente quiero calcular el costo de despacho según mi compra y distancia.  
- Administrador: como administrador quiero monitorear la temperatura del camión y recibir alertas.  
- Administrador: como administrador quiero almacenar la posición GPS de los clientes en Firebase.

---

## 📸 Capturas de pantalla
*(aquí puedes añadir imágenes de tu emulador con la app en ejecución)*

---

## 📂 Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/CamiloSepulvedal/distribuidora-app

  2.Abrir en Android Studio.
  3.Configurar Firebase  (google-services.json) en (app/).
  4.Ejecutar en emulador android (oreo o superior)

  Autores

  Camilo Sepulveda
