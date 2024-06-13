# DTComposeMultiplatform

This is a Compose Multiplatform application developed as part of a diploma thesis. The application leverages modern Kotlin Multiplatform capabilities to create a shared codebase for Android and iOS, utilizing Jetpack Compose for UI.

## Technologies Used

- **Kotlin Multiplatform**: Share common business logic across Android and iOS.
- **Jetpack Compose**: Modern UI toolkit for building native UI on Android and beyond.
- **Koin**: Dependency injection framework for Kotlin.
- **Coroutines and Flow**: For asynchronous programming.
- **Detekt**: Static code analysis tool for Kotlin.
- **MockK**: Mocking library for Kotlin.

## Application Structure

### Modules

The application is structured into multiple modules to separate concerns and enhance modularity.

1. **composeApp**: The main application module.
2. **database**: Manages data persistence.
3. **design**: Contains design system components and styles.
4. **domain**: Contains domain models and business logic.
5. **favorites**: Manages the favorites feature.
6. **gallery**: Handles gallery-related functionality.
7. **home**: The home screen module.
8. **introduction**: Manages introductory screens and data.
9. **iosApp**: The iOS-specific application module.
10. **navigationcomposables**: Navigation components using Compose.
11. **productdetail**: Manages product details.
12. **repository**: Data repositories for fetching and caching data.
13. **splashscreen**: The splash screen module.
14. **datastore**: Manages data storage using Jetpack DataStore.
15. **booking**: Handles booking-related functionality.


```
DTComposeMultiplatform/
├── composeApp/ # The main application module
├── database/ # Manages data persistence
├── design/ # Contains design system components and styles
├── domain/ # Domain models and business logic
├── favorites/ # Manages the favorites feature
├── gallery/ # Handles gallery-related functionality
├── home/ # The home screen module
├── introduction/ # Manages introductory screens and data
├── iosApp/ # The iOS-specific application module
├── navigationcomposables/ # Navigation components using Compose
├── productdetail/ # Manages product details
├── repository/ # Data repositories for fetching and caching data
├── splashscreen/ # The splash screen module
├── datastore/ # Manages data storage using Jetpack DataStore
└── booking/ # Handles booking-related functionality
```

## Technologies Used

- **Kotlin Multiplatform**: Share common business logic across Android and iOS.
- **Compose Multiplatform**: Share UI across Android and iOS.
- **Coil**: To show images.
- **Jetpack Compose**: Modern UI toolkit for building native UI on Android and beyond.
- **Koin**: Dependency injection framework for Kotlin.
- **Coroutines and Flow**: For asynchronous programming.
- **Ktor**: For Network actions.
- **Room**: To save local database.
- **Datastore**: To store data locally.
- **Firebase**: For remote real time database.
- **Cupertino (3rd Party Library)**: Make navigation composable look native for example, top/bottom navigation
