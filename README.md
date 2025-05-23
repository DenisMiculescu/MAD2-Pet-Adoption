# Mobile App Development - Pet Adoption App

**Student Name**: Denis Miculescu  
**Student Number**: 20098078

## Project Overview

The Pet Adoption App is a mobile application built in Kotlin using Jetpack Compose. 
It allows users to view, manage, and adopt pets. The app integrates cloud-based storage and authentication via Firebase. 
It also demonstrates swipe-to-delete gestures, search and filtering, and image upload functionality.

---

## Features

- **Pet Listing**: View a list of all pets or just those listed by the user.
- **Search Filter**: Filter pets by breed using a `TextField`.
- **Details View**: View expanded pet details with owner info and metadata.
- **Adoption Management**: Add and remove pets with swipe-to-delete and confirmation.
- **Photo Uploads**: Upload profile pictures using Firebase Storage and display.
- **Navigation**: Compose Navigation and bottom navigation bar implemented.
- **Authentication**: Firebase Auth for email/password login.

---

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/DenisMiculescu/pet-adoption-app.git
    ```
2. Open in Android Studio.
3. Sync Gradle and run on a device or emulator.

---

## APIs & Libraries Used

| Tool/Library                | Purpose                             |
|-----------------------------|-------------------------------------|
| **Firebase Auth**           | User login (email/password)         |
| **Firebase Firestore**      | Store pet data                      |
| **Firebase Storage**        | Store and retrieve profile photos   |
| **Jetpack Compose**         | Modern UI framework                 |
| **Google Maps SDK**         | Google Map view of user & adoptions |
| **Accompanist Permissions** | Location permission handling        |
| **Hilt**                    | Dependency injection                |
| **Material 3**              | UI styling and theming              |

---

## Architecture / DX

- **Architecture**: MVVM pattern used with Hilt ViewModels for separation of concerns.
- **State Management**: `StateFlow`, `mutableStateOf` for real-time updates and recomposition.
- **Code Structure**: Modular separation by screens and components.
- **Developer Experience (DX)**: Compose and Hilt make the codebase more concise and testable.

---

## UX Design

- **Compose-first UI**: Clean and reactive UI built entirely with Compose.
- **Swipe Gestures**: Swipe-to-dismiss implemented using `SwipeToDismiss`.
- **Confirmation Dialogs**: For critical actions like deletions.

---

## Git Strategy

- All code tracked in Git with meaningful commit history.
- Branches:
   - `main` for stable builds
   - `develop` for testing features
   - `firebaseStorage` for storing pictures
   - `firebaseAuth` for authentication features
   - `networking` for networking features (Retrofit)
   - `firebaseDB` for Firestore database features
   - `map` for Google Maps integration
   - `navigation_development` for navigation features
- GitHub: [github.com/DenisMiculescu/pet-adoption-app](https://github.com/DenisMiculescu/pet-adoption-app)

---

## Personal Statement

This app was designed to demonstrate real-world features including authentication, image handling, and reactive UIs using Jetpack Compose. 
I gained significant experience working with modern Android architecture, managing app state, and integrating Firebase services. 
Challenges included managing Compose state across multiple ViewModels.

---

## UML & Class Diagrams

![adoption_model.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Fadoption_model.png)
![uml_diagram.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Fuml_diagram.png)

---

## Screenshots

![listing_screen.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Flisting_screen.png)
![listing_screen_all.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Flisting_screen_all.png)
![map_screen.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Fmap_screen.png)
![login_screen.png](app%2Fsrc%2Fmain%2Fres%2Fscreenshots%2Flogin_screen.png)


## References

- [Firebase Documentation](https://firebase.google.com/docs)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Coil](https://coil-kt.github.io/coil/)
- [Material Design 3](https://m3.material.io/)
- Compose samples, work from class, and other projects.

---

