# 🏦 eBank - Modern Banking Platform

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.0-green) ![React](https://img.shields.io/badge/React-19-blue) ![Vite](https://img.shields.io/badge/Vite-7-purple) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue) ![License](https://img.shields.io/badge/License-MIT-yellow)

## 🌟 Project Overview

**eBank** is a robust, full-stack banking application designed to simulate real-world financial operations. Built with a **Spring Boot** backend and a **React** frontend, it offers a secure and intuitive interface for both Bank Agents and Clients.

The project demonstrates a modern micro-level architecture with strict separation of concerns, secure **JWT authentication**, and a polished user interface. It handles complex business logic such as account management, inter-account transfers with validation, and role-based access control.

## ✨ Key Features

### 🔐 Security & Authentication
*   **JWT (JSON Web Token)** based stateless authentication.
*   **Role-Based Access Control (RBAC)**: Distinct portals for `ADMIN`, `AGENT`, and `CLIENT` (Data Model supports Roles).
*   **Secure Password Handling**: BCrypt hashing.
*   **Custom Exception Handling**: Global error management for a smooth UX.

### 👨‍💼 For Bank Agents
*   **Client Management**: Create and manage client profiles.
*   **Account Management**: Open new bank accounts (Current/Savings) for clients.
*   **Operations View**: unique interface to view client operation history.

### 👤 For Clients
*   **Dashboard**: Real-time view of account balances and recent activities.
*   **Transfers**: Secure fund transfers between accounts with insufficient balance checks.
*   **Transaction History**: Paginated view of all previous operations (Debit/Credit).
*   **Profile Management**: View personal details.

## 🛠️ Technology Stack

### Backend (ebank-backend)
*   **Framework**: Spring Boot 3.3.0
*   **Language**: Java 17
*   **Data Access**: Spring Data JPA, Hibernate
*   **Database**: MySQL 8.0
*   **Security**: Spring Security 6, JWT (jjwt 0.11.5)
*   **Mapping**: MapStruct 1.5.5
*   **Validation**: Bean Validation (Hibernate Validator), Custom RIB Validators
*   **Tools**: Lombok, Flyway (Database Migration), Maven

### Frontend (ebank-front)
*   **Framework**: React 19
*   **Build Tool**: Vite 7
*   **Styling**: TailwindCSS (inferred from modern stack norms) / CSS Modules
*   **State Management**: React Context API
*   **Routing**: React Router DOM 7
*   **HTTP Client**: Axios
*   **Forms**: React Hook Form

## 📂 Project Structure

```bash
eBank/
├── ebank-backend/       # Spring Boot Server
│   ├── src/main/java/com/khalil/ebank
│   │   ├── config/      # Security & App Config
│   │   ├── controllers/ # REST APIs
│   │   ├── services/    # Business Logic
│   │   ├── entities/    # JPA Data Models
│   │   └── repositories/# Data Access Layer
│   └── pom.xml
│
└── ebank-front/         # React Client
    ├── src/
    │   ├── components/  # Reusable UI Components
    │   ├── pages/       # Application Views (Login, Dashboard)
    │   ├── context/     # Auth Context
    │   └── services/    # API Connectors
    └── package.json
```

## 🚀 Getting Started

### Prerequisites
*   **Java 17** SDK installed.
*   **Node.js** (v18+) and npm.
*   **MySQL** Database installed and running.

### 1️⃣ Database Setup
Create a MySQL database named `ebank`:
```sql
CREATE DATABASE ebank;
```
*Note: The application is configured to automatically create tables (update mode) or use Flyway migrations.*

### 2️⃣ Backend Setup
1.  Navigate to the backend directory:
    ```bash
    cd ebank-backend
    ```
2.  Configure database credentials in `src/main/resources/application.properties` (if not default `root`/``).
3.  Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

### 3️⃣ Frontend Setup
1.  Navigate to the frontend directory:
    ```bash
    cd ebank-front
    ```
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```
    The application will be available at `http://localhost:5173`.

## 📸 Usage Scenarios

1.  **Login**: Use the default credentials (or create one directly in DB/Postman for first setup).
2.  **Create Client (Agent)**: Go to the "Clients" tab and fill in the details.
3.  **Transfer (Client)**: Select a source account, enter destination RIB, and amount.

## 🤝 Contact

**Khalil Ghouddan**
*   [GitHub Profile](https://github.com/khalilghouddan)
*   [Email](mailto:contact@khalil.com)

---
*Built with ❤️ for generic banking simulations.*
