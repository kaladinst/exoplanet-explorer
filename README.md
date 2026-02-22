# 🪐 Exoplanet Explorer

![Java](https://img.shields.io/badge/Java-23-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen)
![React](https://img.shields.io/badge/React-Vite-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon-blueviolet)

**Live Demo:** [Click here to view the live application](https://exoplanet-frontend.onrender.com/)
<img width="1919" height="946" alt="Screenshot 2026-02-22 151546" src="https://github.com/user-attachments/assets/84211dab-f5a5-4195-b64d-8a46195496f4" />

## 📖 Overview
Exoplanet Explorer is a full-stack web application designed to fetch, process, and visualize real-time astronomical data. By integrating directly with the **NASA/Caltech Exoplanet Archive API**, the application processes over 6,000 discovered celestial bodies, filtering them based on their physical characteristics to highlight potentially habitable worlds.

The application calculates and filters planets based on their **Earth Similarity Index (ESI)**, giving users a sleek, responsive interface to explore planets categorized as "Earth-Like," "Potential," or "Hostile."

## ✨ Key Technical Features
* **Automated Data Pipeline:** Built a smart synchronization service that checks database state on startup and automatically fetches missing data from the external NASA TAP API.
* **Optimized Persistence:** Implemented **Spring Boot JDBC Batching** to efficiently process and save massive JSON payloads to a remote cloud database without triggering memory timeouts or connection drops.
* **Relational Data Integrity:** Utilized Hibernate/JPA to enforce unique constraints, preventing duplicate records during subsequent API fetches.
* **RESTful Architecture:** Designed clean, cross-origin resource sharing (CORS) compliant endpoints to serve complex filtered queries to the frontend.
* **Modern UI/UX:** Developed a responsive, component-based frontend using React and Vite, featuring dynamic state management for immediate data filtering.

## 🛠️ Tech Stack
### Backend (`/exoplanet-explorer`)
* **Core:** Java 23, Spring Boot
* **Data Access:** Spring Data JPA, Hibernate
* **Database:** PostgreSQL (Hosted on Neon)
* **Build/Deploy:** Maven, Docker, Render Web Services

### Frontend (`/exoplanet-frontend`)
* **Core:** React.js, Vite
* **Styling:** Custom CSS (Dark Theme, Responsive Grid)
* **Deploy:** Render Static Sites

## 📡 API Reference
The backend exposes the following REST endpoints to serve planetary data:

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/planets/all` | Retrieves the complete database of valid exoplanets. |
| `GET` | `/api/planets/top-10` | Returns the 10 planets with the highest ESI scores. |
| `GET` | `/api/planets/between?min={val}&max={val}` | Filters and returns planets within a specific ESI range. |

## 💻 Local Installation & Setup

### 1. Database Configuration
To run this project locally, you will need a PostgreSQL database. Set the following environment variables on your machine or in your IDE:
* `DB_URL` (e.g., `jdbc:postgresql://localhost:5432/exoplanets`)
* `DB_USERNAME`
* `DB_PASSWORD`

### 2. Running the Backend
Navigate to the Spring Boot directory and run the Maven wrapper:
```bash
cd exoplanet-explorer
./mvnw spring-boot:run
