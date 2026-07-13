# Health AI Assistant

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> An AI-Enhanced Personal Health Management Platform.

Health AI Assistant is a **modular monolith** that helps users record diet and exercise through text, and generates sustainable health analytics and personalized advice.

**Core Principle:** AI is the Analyst, not the Calculator.

## Architecture

```text
Presentation (Vue 3 + TS)
    ↓
Application (REST API)
    ↓
Business Capability (Identity / Nutrition / Exercise / Analytics)
    ↓
Supporting Services (AI / OCR / Notification)
    ↓
Infrastructure (MySQL / Redis / MinIO)
```

## Tech Stack

| Layer | Tech |
|-------|------|
| Frontend | Vue 3 + TypeScript + Element Plus + Vite |
| Backend | Spring Boot 3 + Java 21 + Maven |
| Database | MySQL 8 + Redis 7 |
| ORM | MyBatis-Plus |
| Object Storage | MinIO |
| AI | DeepSeek (OpenAI compatible) + Local Mock fallback |
| Build | Maven + GitHub Actions |
| Deploy | Docker Compose |

## Quick Start (H2 / No Docker)

For local development without Docker, WSL, MySQL, or Redis:

### 1. Prerequisites
- Java 21 (Eclipse Temurin recommended)
- Maven 3.9+
- Node.js 18+

### 2. Build the Backend
```bash
cd backend
mvn clean package -DskipTests
```

### 3. Start the Backend
```bash
cd backend
java -jar target/health-ai-assistant-0.0.1-SNAPSHOT.jar --spring.profiles.active=local,h2
```
The backend will start on `http://localhost:8080/api` using an in-memory H2 database.

### 4. Start the Frontend
```bash
cd frontend
npm install
npm run dev
```
The frontend dev server will start on `http://localhost:3000` and proxy `/api` to the backend.

### 5. Try the App
Open `http://localhost:3000`, register a new account, then record food, exercise, and view the dashboard.

> The H2 profile uses Mock AI by default. To use DeepSeek, set `AI_API_KEY` in your environment or configure `application-local.yml`. The H2 database is in-memory, so data is reset when the backend stops.

## Quick Start (MySQL + Nginx)

For production-like deployment with your existing MySQL and Nginx (no Docker needed):

### 1. Prerequisites
- Java 21 (Eclipse Temurin recommended)
- Maven 3.9+
- Node.js 18+
- MySQL 8 running on localhost:3306
- Nginx installed

### 2. Create Database
\\sql
CREATE DATABASE IF NOT EXISTS health_ai CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
\
### 3. Build
\\ash
cd backend
mvn clean package -DskipTests

cd ../frontend
npm install
npm run build
\
### 4. Start Backend
\\ash
cd backend
java -jar target/health-ai-assistant-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
\The backend starts on http://localhost:8080/api (context path /api).

### 5. Configure Nginx
Edit the included ginx.conf\ to match your paths, then:
\\ash
nginx -c D:/项目/nginx.conf
\
### 6. Open the App
Visit http://localhost, register a new account, then record food, exercise, and view the dashboard.

> The local profile uses Mock AI by default (no API key needed). To use DeepSeek, set AI_API_KEY environment variable.


## Docker Quick Start

### Option 1: Docker Compose (recommended)

No local Java/Node required. The backend Dockerfile builds the JAR inside Docker, and the frontend Dockerfile builds the static bundle.

```bash
# 1. Clone the repo
git clone https://github.com/elthereal-star/health-ai-assistant.git
cd health-ai-assistant

# 2. Optional: set AI key to use real DeepSeek instead of local Mock
#    If you skip this, the system runs fully offline with the Mock AI.
# export AI_API_KEY=sk-xxx

# 3. One-click start
docker compose up --build -d

# 4. Open the app
# http://localhost:3000
# Register a new account, then log in to see the dashboard.
```

This starts:
- MySQL on 3306
- Redis on 6379
- MinIO on 9000 / 9001
- Backend on 8080
- Frontend on 3000

### Option 2: Local Development

**Prerequisites**
- Java 21
- Maven 3.9+
- Node.js 20+
- MySQL 8
- Redis 7

```bash
# 1. Start infrastructure
docker compose up -d mysql redis minio

# 2. Start backend (uses Mock AI by default)
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 3. Start frontend
cd ../frontend
npm install
npm run dev
# Open http://localhost:3000
```

The backend `application-local.yml` defaults to Mock AI. To switch to DeepSeek, set the `AI_API_KEY` environment variable or add it to `application-local.yml`:

```yaml
ai:
  api-key: sk-xxx
```

## Authentication

All protected endpoints require a `Bearer` token in the `Authorization` header. The token is obtained from `POST /api/identity/login` or `POST /api/identity/register`.

The frontend stores the token in `localStorage` and injects it automatically via Axios interceptors.

## AI Strategy

- By default, `MockAIService` runs locally with no API key. It uses the built-in food/exercise dictionaries to recognize common Chinese and Western foods and give rule-based health advice.
- When `AI_API_KEY` is set, the system automatically switches to `DeepSeekAIService`.
- AI never touches the database, calculates calories, or changes business rules.

## Documentation

See the [docs](./docs) directory:

- [00-Engineering-Rules](./docs/00-Engineering-Rules.md)
- [01-Project-Vision](./docs/01-Project-Vision.md)
- [02-System-Specification](./docs/02-System-Specification.md)
- [03-Database-Design](./docs/03-Database-Design.md)
- [04-API-Specification](./docs/04-API-Specification.md)
- [05-AI-Specification](./docs/05-AI-Specification.md)
- [06-Frontend-Specification](./docs/06-Frontend-Specification.md)
- [07-Deployment](./docs/07-Deployment.md)

## License

MIT