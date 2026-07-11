# Health AI Assistant

[![CI](https://github.com/{username}/health-ai-assistant/actions/workflows/ci.yml/badge.svg)](https://github.com/{username}/health-ai-assistant/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> An AI-Enhanced Personal Health Management Platform.

Health AI Assistant is a **modular monolith** that helps users record diet and exercise through text and images, and generates sustainable health analytics and personalized advice.

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
| AI | DeepSeek (OpenAI compatible) |
| Build | Maven + GitHub Actions |
| Deploy | Docker Compose |

## Quick Start

```bash
# 1. Clone the repo
git clone https://github.com/{username}/health-ai-assistant.git
cd health-ai-assistant

# 2. Configure AI service
cp backend/src/main/resources/application.yml backend/src/main/resources/application-local.yml
# Edit application-local.yml and set your DeepSeek API key

# 3. Start infrastructure
docker compose up -d mysql redis minio

# 4. Start backend
cd backend
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# 5. Start frontend
cd ../frontend
npm install
npm run dev
```

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
