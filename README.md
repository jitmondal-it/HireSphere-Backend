# HireSphere — Job Portal Backend

HireSphere is a full-stack job portal application that connects **job seekers and recruiters** on a single platform.

This repository contains the **backend of HireSphere**, developed using **Java and Spring Boot**. It provides REST APIs for authentication, user profiles, job posting, job applications, saved jobs, notifications, and recruiter operations.

The backend is responsible for handling the application's business logic, data management, authentication, and communication with the MongoDB database.

---

## ✨ Features

### 👨‍💻 Job Seeker

- Register and login
- Manage user profile
- Browse available jobs
- View detailed job information
- Apply for jobs
- Prevent duplicate applications
- Track application status
- Save/bookmark jobs
- View application history
- Receive notifications

### 🏢 Recruiter

- Create job postings
- Update job postings
- View posted jobs
- View applicants
- Manage application status
- Schedule interviews
- Receive notifications

### 🔐 Authentication & Security

- JWT-based authentication
- Protected API endpoints
- User authentication and authorization
- Token-based request validation
- Role-based access control

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Backend programming |
| Spring Boot | Backend framework |
| Spring Web | REST API development |
| Spring Data MongoDB | Database integration |
| MongoDB | Data storage |
| Spring Security | Authentication and security |
| JWT | Token-based authentication |
| Maven | Dependency management |
| Lombok | Reduce boilerplate code |

---

## 🏗️ Backend Architecture

The project follows a layered architecture.

```text
                    ┌──────────────────────┐
                    │       Frontend       │
                    │     React + Vite     │
                    └──────────┬───────────┘
                               │
                               │ REST API
                               ▼
                    ┌──────────────────────┐
                    │      Controllers     │
                    │      /REST API       │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │       Services       │
                    │    Business Logic    │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     Repositories     │
                    │   Data Access Layer  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │       MongoDB        │
                    │      Database        │
                    └──────────────────────┘
