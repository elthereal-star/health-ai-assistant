---
Metadata:
  Title: Deployment
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define how to build, deploy, and run the system.
Scope:
  Local development, Docker, and CI/CD.
Review Cycle:
  Quarterly.
Change Log:
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
- MinIO (optional)

### Backend

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

## Docker Compose

```bash
docker compose up -d
```

This starts:
- MySQL on 3306
- Redis on 6379
- MinIO on 9000 / 9001
- Backend on 8080

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | JDBC URL | jdbc:mysql://localhost:3306/health_ai |
| `SPRING_DATASOURCE_USERNAME` | DB username | root |
| `SPRING_DATASOURCE_PASSWORD` | DB password | root |
| `SPRING_REDIS_HOST` | Redis host | localhost |
| `AI_PROVIDER` | AI provider | deepseek |
| `AI_API_KEY` | AI API key | required |
| `AI_API_BASE_URL` | AI base URL | https://api.deepseek.com |

## CI/CD

GitHub Actions workflow:

- On every push: build and test backend and frontend.
- On release: build Docker images and push to GitHub Container Registry.

## Secrets

Do not commit API keys. Use environment variables or GitHub Secrets for CI/CD.
