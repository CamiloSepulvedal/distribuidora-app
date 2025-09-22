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
Capturas de Pantalla
Login con usuario o por google
<img width="490" height="980" alt="1" src="https://github.com/user-attachments/assets/64b8942e-a4d7-45ac-ac11-745995d1495f" />

<img width="509" height="988" alt="2" src="https://github.com/user-attachments/assets/68a27faf-63cb-4fa8-8956-c2c4e15b72bf" />

Al momento de logear te va a pedir la ubicacion GPS
<img width="517" height="1004" alt="3" src="https://github.com/user-attachments/assets/a6642847-6c63-4bf4-ba5f-1284a3ac9903" />

Despues se te dara acceso a la aplicacion con tu ubicacion

<img width="516" height="1001" alt="4" src="https://github.com/user-attachments/assets/1baa1609-efa7-4836-876e-d34c91f4bd33" />
Simulacion de aplicacion
<img width="522" height="1000" alt="5" src="https://github.com/user-attachments/assets/63d5884c-a231-4087-bdbb-4abbcda2b4af" />


## 📂 Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/CamiloSepulvedal/distribuidora-app

  2.Abrir en Android Studio.
  3.Configurar Firebase  (google-services.json) en (app/).
  4.Ejecutar en emulador android (oreo o superior)

  Autores

  Camilo Sepulveda
