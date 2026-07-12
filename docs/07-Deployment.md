---
Metadata:
  Title: Deployment
  Version: 1.1.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-12
Purpose:
  Define how to build, deploy, and run the system.
Scope:
  Local development, Docker, and CI/CD.
Review Cycle:
  Quarterly.
Change Log:
  - 2026-07-12 v1.1.0: Add frontend service, JWT/Auth environment variables, Mock AI default
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - Use this document when generating build scripts, Dockerfiles, or CI configuration.
---

# Deployment

## Local Development

### Prerequisites

- Java 21
- Maven 3.9+
- Node.js 20+
- MySQL 8
- Redis 7

### Infrastructure

Start MySQL, Redis, and MinIO with Docker Compose:

```bash
docker compose up -d mysql redis minio
```

### Backend

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile defaults to Mock AI, so the app works without an API key.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Open http://localhost:3000 and register a new account.

## Docker Compose (One-Click)

```bash
docker compose up --build -d
```

This builds the backend JAR inside Docker and the frontend production bundle, then starts:

- MySQL on 3306
- Redis on 6379
- MinIO on 9000 / 9001
- Backend on 8080
- Frontend on 3000

Access the app at http://localhost:3000.

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | JDBC URL | `jdbc:mysql://localhost:3306/health_ai` |
| `SPRING_DATASOURCE_USERNAME` | DB username | `root` |
| `SPRING_DATASOURCE_PASSWORD` | DB password | `root` |
| `SPRING_REDIS_HOST` | Redis host | `localhost` |
| `SPRING_SQL_INIT_MODE` | Run `schema.sql`/`data.sql` | `never` |
| `JWT_SECRET` | JWT signing secret | `health-ai-assistant-local-secret-key` |
| `JWT_EXPIRATION` | Token expiration (ms) | `86400000` |
| `AI_ENABLED` | Enable AI service | `true` |
| `AI_API_KEY` | DeepSeek API key | empty (Mock AI) |
| `AI_API_BASE_URL` | AI base URL | `https://api.deepseek.com` |
| `AI_MODEL` | AI model | `deepseek-chat` |

## AI Configuration

By default, the system runs `MockAIService` with a local food/exercise dictionary. No external key is required.

To use the real DeepSeek model, set the `AI_API_KEY` environment variable before starting the backend or Docker Compose:

```bash
export AI_API_KEY=sk-xxx
docker compose up --build -d
```

AI does not write to the database or modify business rules.

## CI/CD

GitHub Actions workflow:

- On every push: build and test backend and frontend.
- On release: build Docker images and push to GitHub Container Registry.

## Secrets

Do not commit API keys or JWT secrets. Use environment variables or GitHub Secrets for CI/CD.