# 💎 Compose Glassmorphism UI — Multiplatform Showcase

Welcome to the **Compose Glassmorphism UI** repository! This project showcases a fully animated, glassy, and responsive user interface built with Jetpack Compose for Android, iOS, and Desktop from a single codebase. 

![Desktop GIF](screenshots/preview_desktop.gif)

![Android GIF](screenshots/preview_android.gif)

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Releases](#releases)

## Features

- **Animated Gradient Backgrounds**: Enjoy smooth transitions with vibrant colors.
- **Glassy Cards**: Experience modern UI elements with hover and press effects.
- **Ripple Interactions**: Custom implementation for a polished user experience.
- **AI-Workout Summary Card**: Display workout progress with a shimmer effect.
- **Top Greeting Avatar**: A floating glow effect adds charm to the greeting section.
- **Reusable Components**: Build layouts quickly with atomic elements.
- **Compose Multi-platform**: Share logic across Android, iOS, and Desktop using `commonMain`.

## Architecture

The project follows a clear architecture, making it easy to navigate and extend. Here’s a brief overview of the structure:

```
composeApp/
├── commonMain/
│   └── components/
│   └── sections/
│   └── App.kt
├── androidMain/
├── iosMain/
├── desktopMain/
```

- **`components/`**: Contains reusable elements like cards, avatars, and ripple effects.
- **`sections/`**: Composed layout blocks, such as the top greeting or workout section.
- **`commonMain/App.kt`**: The entry point for all platforms, where the app starts.

## Getting Started

To get started with the Compose Glassmorphism UI, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Peldo432/Compose-Glassmorphism.git
   cd Compose-Glassmorphism
   ```

2. **Open the Project**:
   Open the project in your preferred IDE, such as Android Studio or IntelliJ IDEA.

3. **Install Dependencies**:
   Make sure to install all necessary dependencies. This can usually be done with:
   ```bash
   ./gradlew build
   ```

4. **Run the App**:
   You can run the app on your desired platform by selecting the appropriate configuration in your IDE.

## Usage

Once you have the project set up, you can start customizing it. Here are some ways to get involved:

- **Add New Components**: Explore the `components/` directory and create new reusable UI elements.
- **Modify Existing Sections**: Customize the layout in the `sections/` directory to fit your needs.
- **Experiment with Animations**: Tweak the animations to create unique effects.

## Contributing

We welcome contributions to improve the project. If you would like to contribute, please follow these steps:

1. **Fork the Repository**: Click the fork button on GitHub.
2. **Create a New Branch**: Use a descriptive name for your branch.
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make Changes**: Implement your changes.
4. **Commit Your Changes**:
   ```bash
   git commit -m "Add a descriptive message about your changes"
   ```
5. **Push to Your Fork**:
   ```bash
   git push origin feature/your-feature-name
   ```
6. **Create a Pull Request**: Go to the original repository and create a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Releases

For the latest updates and versions, visit the [Releases section](https://github.com/Peldo432/Compose-Glassmorphism/releases). Here, you can download and execute the latest files.

---

Feel free to explore the code, experiment with features, and make it your own. If you have any questions or suggestions, please open an issue in the repository. Happy coding!