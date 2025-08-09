# 🔐 Firebase Authentication App – Jetpack Compose

A modern Android app that demonstrates various **Firebase Authentication** methods using **Jetpack Compose**, **Navigation**, and **Clean Architecture**.

---

## ✨ Features

- ✅ OTP Authentication (Phone Number)
- ✅ Email & Password Login & Sign-Up
- ✅ Google Sign-In
- ✅ GitHub OAuth Login
- ✅ Facebook Login *(UI available – functionality pending deployment)*
- 🧭 Animated Splash Screen
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

-> Create and Account with Email, Passward and DisplayName

<br><Br><Br><br>

![emailandpasswardCreateAc](https://github.com/user-attachments/assets/4667de0b-2dbf-47fb-8b79-d48e5f505218)

<br><Br><Br><br>

-> Sign into an account with email and passward

<br><Br><Br><br>

![signInWithEmailAndPass](https://github.com/user-attachments/assets/1877d198-f44e-4aea-9667-860af07e6f74)

<br><Br><Br><br>

-> Sign in with your google account

<br><Br><Br><br>

![signInWIthGoogle](https://github.com/user-attachments/assets/1b19927c-cde2-44cb-91a3-d954c6e34ad0)
<br><Br><Br><br>

-> Sign in with facebook account (since this is not an deployed application facebook dont allow for login and give error in their OAuth)
<br><Br><Br><br>
![facebookLogin](https://github.com/user-attachments/assets/14ab85fd-a3f1-4449-aa41-041613f767af)

<br><Br><Br><br>
-> Sign In with you GitHub Account

<br><Br><Br><br>

![githubLogin](https://github.com/user-attachments/assets/54bf4e0b-62a3-4663-ab22-e85a812462f1)
<br><Br><Br><br>
-> Sign in with OTP/Phone Authintication

<br><Br><Br><br>

![signInWithOTP](https://github.com/user-attachments/assets/d51a73d8-162c-415c-ab3b-8c4992f237b2)

<br><Br><Br><br>

-> SplashScreen of the Applicaion which checks if USer Logged in or not and Navigates to Login Screen if not

<br><Br><Br><br>

![splashScreen](https://github.com/user-attachments/assets/fc8de27e-950c-402b-9be9-6784ffb22aa6)

<br><Br><Br><br>
-> If you dont want to give you Account or create one You can use Anonomous Login 
![anonomosly](https://github.com/user-attachments/assets/1e93b1cb-78b4-458a-b130-4225fe4da583)

<br><Br><Br><br>

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
┣ 📁 Firebase          # GoogleSignInClient
┣ 📁 Utlis             # OTPTextField, LoginButton, PhoneLogins etc
┣ 📁 navigation        # Compose Navigation  
┣ 📁 Screens           # All types of screen used in this APK
┣ 📜 AuthViewModel.kt  # ViewModel
┣ 📁 MainActivity.kt   # Main File
┗ 📁 MyApplication     # Application file to prevent UI Freezing 


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


