# 🔐 Firebase Authentication App – Jetpack Compose

A modern Android app that demonstrates various **Firebase Authentication** methods using **Jetpack Compose**, **Navigation**, and **Clean Architecture**.

---

## ✨ Features

- ✅ OTP Authentication (Phone Number)
- ✅ Email & Password Login & Sign-Up
- ✅ Google Sign-In
- ✅ GitHub OAuth Login
- ✅ Facebook Login *(UI available – functionality pending deployment)*
- 🧼 Clean MVVM architecture with ViewModel & state handling
- 🎯 Animated UI using `AnimatedVisibility`
- 🧭 Navigation 3 for screen transitions

> ⚠️ Microsoft sign-in was supported in an earlier version, but has been removed due to deprecation for general use. To use Microsoft sign-in, a valid enterprise Microsoft account or sandbox eligibility is required.

---

## 🚀 Built With

- Jetpack Compose
- Firebase Authentication
- Google Sign-In SDK
- GitHub OAuth (via Firebase)
- Facebook Login SDK (UI only)
- Kotlin Coroutines
- Clean Architecture (MVVM)

---

## 📸 Demo

Coming Soon — YouTube video or GIF

---

## 🧪 Testing the App

1. Clone the repository
2. Please do add `SHA-1` and `SHA-256`
3. Replace `google-services.json` with your own Firebase config
4. Set up GitHub OAuth and Google Sign-In in the Firebase Console
5. Run the app on a physical or virtual Android device

---

## 🔐 Authentication Providers Setup

- 🔸 **Google Sign-In**: Enabled in Firebase Console  
- 🔸 **Phone (OTP)**: Enabled in Firebase Console  
- 🔸 **GitHub**: Set up OAuth credentials and redirect URI  
- 🔸 **Facebook**: UI integrated, awaiting live deployment  
- 🔸 **Email/Password**: Works for both login and sign-up  

---

## 🧠 Architecture Overview

```
📦 com.example.firebaseauthentication  
┣ 📁 ui                # Compose Screens  
┣ 📁 viewmodel         # MVVM ViewModels  
┣ 📁 auth              # Auth logic (Google, GitHub, OTP etc.)  
┣ 📁 navigation        # Compose Navigation  
┗ 📜 MainActivity.kt   # App Entry Point  
```

---

## 📄 License

```
Apache License 2.0

Copyright 2025 Rohan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
```

---

## 🤝 Contributions

PRs are welcome! Feel free to improve UI, add new auth providers, or suggest architecture improvements.

---

## 📬 Contact

Made with ❤️ by Rohan  
[GitHub](https://github.com/your-github-username)
---
