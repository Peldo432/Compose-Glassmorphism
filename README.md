# 💎 Compose Glassmorphism UI — Multiplatform Showcase

> A fully animated, glassy, and responsive Compose Multi-platform UI built with care for Android, iOS, and Desktop — from a single codebase.

![Desktop GIF](screenshots/preview_desktop.gif)

![Android GIF](screenshots/preview_android.gif)

---

### Features

- Animated Gradient Backgrounds
- Glassy Cards with Hover and Press Effects
- Ripple Interactions (Custom Implementation)
- AI-Workout Summary Card with Progress Shimmer
- Top Greeting Avatar with Floating Glow
- Reusable Components and Sections
- Compose Multi-platform — Android, iOS, Desktop from one `commonMain` logic

---

### Architecture

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

- `components/` — atomic reusable elements like cards, avatars, and ripple effects
- `sections/` — composed layout blocks like the top greeting or workout section
- `commonMain/App.kt` — entry point for all platforms
- `Platform.kt` — expect/actual abstraction for target-specific code

---

### Getting Started

Pre-requisites:

- Kotlin Multiplatform plugin
- Android Studio (Giraffe or above)
- Xcode with Cocoapods installed (for iOS)

```bash
# Clone the repo
git clone https://github.com/MRTHAKER/Compose-Glassmorphism.git
cd Compose-Glassmorphism

# Run Desktop
./gradlew :composeApp:run

# Run Android or iOS from Android Studio or Xcode respectively
```

---

### Tech Stack

- JetBrains Compose Multi-platform
- Jetpack Compose UI
- Kotlin Multiplatform
- Custom Indication + Interaction APIs
- Infinite Transition Animations

---

### Target Platforms

| Platform | Status |
|----------|--------|
| Android  | ✅ Fully Working |
| Desktop  | ✅ Fully Working |
| iOS      | 🚧 iOS build ready, working on simulator fixes |

---

### 🧵 Story Behind This Project

I took on the Compose-UI Challenge by [Akshay Nandwana 🇮🇳](https://github.com/anandwana001)  
which featured a stunning, visually rich design with Glassmorphism and backdrop blur effects all implemented using Jetpack Compose Multiplatform.

Special thanks to [Akshay Nandwana 🇮🇳](https://github.com/anandwana001) for the challenge  
and the Android Engineers community — those blog posts came in clutch when I was stuck.

---

### Acknowledgements

- JetBrains for Compose Multi-platform
- Android Developers Blog
- Akshay Nandwana and the Compose UI Challenge series
---

### License

MIT License — free to use, modify, and build on. Please credit if shared publicly.
