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

---

## 🤝 Contributions

PRs are welcome! Feel free to improve UI, add new auth providers, or suggest architecture improvements.

---

## 📬 Contact

Made with ❤️ by Rohan  
[GitHub](https://github.com/your-github-username)
---

## 📄 License

```
## License

MIT License

Copyright (c) 2025 Rohan

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


