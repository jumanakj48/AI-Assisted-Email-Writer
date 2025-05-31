# Smart Email Assistant

**Smart Email Assistant** is an AI-powered email reply generator that allows users to create professional, friendly, or casual responses to emails with just one click. This project integrates OpenAI's **Gemini API** with a modern web application built using **Spring Boot (Java)** for the backend and **React + Vite + Material UI** for the frontend. A **Chrome Extension** is also provided to seamlessly integrate this feature directly into Gmail.

---

## 🚀 Features

- ✉️ **AI-generated email replies** using Gemini API
- 🌐 **Spring Boot backend** with WebClient (WebFlux) for non-blocking HTTP calls
- ⚛️ **React (Vite)** frontend with a sleek Material UI design
- 🧩 **Chrome Extension** that injects an "AI Reply" button directly into Gmail
- 🔄 Real-time DOM monitoring with `MutationObserver`
- 📤 Copy-to-clipboard functionality for generated replies
- 🌍 CORS enabled for frontend-backend communication

---

## 🛠️ Tech Stack

| Layer         | Technology                     |
|--------------|---------------------------------|
| Backend       | Spring Boot (Java)             |
| Frontend      | React, Vite, Material UI       |
| Chrome Plugin | JavaScript, MutationObserver   |
| API           | Gemini API (via WebClient)     |
| Build Tool    | Maven                          |
| API Testing   | Postman                        |

---

## 🧠 How It Works

### 📦 Backend (Spring Boot)
1. **Initialize Project**  
   - Use [Spring Initializr](https://start.spring.io/)
   - Add dependencies: `Spring Web`, `Lombok`, `Spring Recation Web`

2. **Model Class**
   - Create `EmailRequest` with:
     ```java
     private String emailContent;
     private String tone;
     ```

3. **Controller**
   - Endpoint: `POST /api/email/generate`
   - Accepts `EmailRequest` and calls the service method

4. **Service Logic**
   - Step 1: Build the prompt using the email content and tone
   - Step 2: Construct a JSON body (Map format)
   - Step 3: Send POST request to Gemini API using WebClient
   - Step 4: Extract the generated reply from the JSON response

---

### 💻 Frontend (React + Vite + MUI)
- Text area to input email
- Dropdown to choose tone (friendly, professional, casual)
- Button to generate reply
- Axios is used to send data to the backend and retrieve the reply
- Reply is shown in a read-only field with a copy button

---

### 🧩 Chrome Extension
- Uses `MutationObserver` to detect Gmail compose window
- Injects an **"AI Reply"** button into the Gmail UI
- On click, fetches the last email thread content
- Sends a request to the same backend API
- Inserts generated reply into Gmail's compose box

---

## 🌐 API Integration: Gemini

- Generate an API key from Gemini
- Test API using Postman by importing the `curl` request
- Securely call the API via WebClient in Spring Boot

