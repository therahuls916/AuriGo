# 🚗 AuriGO - Ride-Sharing App Prototype
> **Project for Android Development Learning & Portfolio**

| Project Info      | Details                   |
|----------------------|---------------------------|
| **Developer Name**             | Rahul Salunke             |
| **Project**        | AuriGO - Ride-Sharing App |
| **Domain**           | Android Development       |
| **Guidance By**    | Neela Santhosh Kumar      |

---
### 📸 Screenshots

*This section showcases the app's user flow from login to live ride tracking.*

![Register and login Screen](https://github.com/user-attachments/assets/d45b2bcb-8633-4b3a-b2e4-5ff5930e6073)
![Location Permission](https://github.com/user-attachments/assets/12468e23-5f5f-4b37-8363-049407696579)
![select Driver](https://github.com/user-attachments/assets/df8c81df-ae3a-41e9-9ebc-1c0c2fa86ef4)
![Confirm Driver](https://github.com/user-attachments/assets/07cd19c5-21c4-4d74-8611-73e48ae77ffc)
![Live update](https://github.com/user-attachments/assets/9ed5066f-df69-466c-aee0-1e571a4d80db)

AuriGO is a modern, functional prototype for a ride-sharing application, built with Kotlin and Jetpack Compose. It demonstrates a complete, end-to-end user flow from logging in, viewing a live map, requesting a ride by selecting a driver, and tracking the driver's simulated journey in real-time.

---
## 🚀 Features

| Feature | Description |
|--------|-------------|
| 🔐 **Firebase Authentication** | Secure user login and session persistence using Firebase Auth's Phone Number + Password method. Users stay logged in between app launches. |
| 🗺️ **Live Map & User Location** | Integrates the **Google Maps SDK** to display a real-time map. Automatically requests location permissions and centers the map on the user's current location. |
| 🚕 **Ride Request System** | Allows users to select a dummy driver and confirm a ride. The ride request, including user ID and dummy locations, is saved as a new document in **Firebase Firestore**. |
| 📍 **Simulated Live Tracking** | After a ride is confirmed, a **simulated driver** appears on the map and follows a pre-defined route, demonstrating the core location tracking feature of a ride-sharing app. |
| ✨ **Modern UI/UX** | Built entirely with **Jetpack Compose & Material 3**, featuring a clean, component-based architecture and a user-friendly interface. |
| 🔄 **State Management** | Uses Compose's state management (`remember`, `mutableStateOf`) to create a reactive UI that responds to user input and loading states. |
| 🛠️ **Professional Project Structure** | Organized into a scalable, feature-based package structure (`auth`, `home`, `ride_details`) to promote clean code and maintainability. |
| 👆 **Interactive UI Components** | Includes loading indicators, disabled buttons during network requests, and a re-center FAB, providing a professional user experience. |

---
## 🛠 Tech Stack

- **Kotlin** (Primary Language)
- **Jetpack Compose** (Modern UI Toolkit)
- **MVVM-like Architecture** (Separation of UI and Logic)
- **Kotlin Coroutines** (For asynchronous operations like delays and navigation)
- **Firebase**
    - **Authentication** (For user login and sessions)
    - **Cloud Firestore** (Backend database for storing ride requests)
- **Google Maps SDK for Compose** (For all map functionalities)
- **Google Play Services** (For Fused Location Provider)
- **Jetpack Navigation Compose** (For screen navigation)
- **Accompanist Permissions** (For streamlined permission handling)
- **Material 3 Components**

---
## 🔧 Installation
```bash
# Clone the repository
git clone https://github.com/therahuls916/AuriGo.git
cd AuriGo
Open the project in Android Studio, let Gradle sync, and click ▶️ Run. You will need to add your own google-services.json file from Firebase and a Google Maps API key in the AndroidManifest.xml to run the project.

📂 Folder Structure
app/src/main/java/com/rahul/auric/aurigo/
├── MainActivity.kt
├── components/               // Globally reusable UI components (e.g., SocialLoginButton)
├── features/                 // Code grouped by application feature
│   ├── auth/                 // LoginScreen and related auth UI
│   ├── home/                 // HomeScreen and its specific UI and logic
│   │   ├── components/       // Components used only in the Home feature (e.g., DriverCard)
│   │   └── location/         // Location-related utilities (e.g., getUserLocation)
│   ├── ride_details/
│   └── ride_in_progress/
├── navigation/               // Navigation graph (AppNavigation.kt) and routes (AppRoutes.kt)
└── ui/
    └── theme/                // App theme, colors, and typography


---

### **Step 6: Permissions and "How It Works"**

This part explains the technical details of your app's architecture and why it needs certain permissions.

**Add this code below the previous section:**
```markdown
## 🔐 Permissions Used
| Permission | Reason |
|--------|-------------|
| `ACCESS_FINE_LOCATION` | Required to get the user's precise GPS location to display on the map. |
| `ACCESS_COARSE_LOCATION` | Provides a fallback to network-based location if GPS is unavailable. |
| `INTERNET` | Essential for communicating with Firebase services and loading Google Maps tiles. |

---
## 🧠 How It Works
* **UI Layer:** The entire UI is built with Jetpack Compose. Screens are organized into feature packages. The UI is designed to be stateless where possible, reacting to state changes.
* **State Management:** Simple state management is handled within each Composable using `remember { mutableStateOf(...) }`. Logic that affects the UI, like loading indicators, is controlled by this state.
* **Backend Layer:** **Firebase** serves as the backend. **Firebase Auth** handles user creation and login. **Cloud Firestore** is used to store ride requests, fulfilling the core backend requirement of the prototype.
* **Map & Location Layer:** The app uses the **Google Maps SDK for Compose** for rendering maps. It gets the user's location via the **Fused Location Provider** and uses the **Accompanist library** to handle runtime location permissions gracefully.
* **Simulation:** The "live tracking" feature is a client-side simulation. A `LaunchedEffect` coroutine in the `RideInProgressScreen` animates a marker along a hardcoded list of `LatLng` coordinates to demonstrate the visual effect of a moving vehicle.

---
## ✅ Planned Features (Future Enhancements)

* [ ] 🔍 Implement a real location search using Google Places API.
* [ ] 📲 Create an OTP screen to complete the phone number authentication flow.
* [ ] 📍 Use Firebase Realtime Database for true real-time location updates from a driver.
* [ ] 👤 Build a Profile screen with a "Logout" button.
* [ ] 📜 Build the Ride History screen by fetching data from Firestore.
* [ ] 🎨 Add a dark theme and allow the user to switch.

---

## 🤝 Contributing
This is a portfolio project, but feel free to fork this repo, create a new branch, and open a PR with suggestions or improvements.

---

## 📄 License
This project is licensed under the MIT License - see the `LICENSE` file for details.

---

## 🙌 Acknowledgements
* [Material Design Components](https://m3.material.io/) by Google
* [Firebase](https://firebase.google.com/) and [Google Maps Platform](https://mapsplatform.google.com/) documentation

---

## 👨‍💻 Developer
**Rahul Salunke**
[GitHub](https://github.com/therahuls916) | [LinkedIn](https://www.linkedin.com/in/rahulasalunke/)
