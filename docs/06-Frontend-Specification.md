---
Metadata:
  Title: Frontend Specification
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the frontend architecture, component structure, and routing.
Scope:
  Vue 3 + TypeScript + Element Plus frontend.
Review Cycle:
  Per feature iteration.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - Generate components that follow this structure.
  - The frontend only talks to the Application layer.
---

# Frontend Specification

## Tech Stack

- Vue 3
- TypeScript
- Element Plus
- Vite
- Pinia (state management)
- Axios (HTTP client)

## Project Structure

```text
frontend/
├── src/
│   ├── api/          # HTTP client and API endpoints
│   ├── assets/       # Static assets
│   ├── components/   # Reusable UI components
│   ├── router/       # Vue Router routes
│   ├── stores/       # Pinia stores
│   ├── views/        # Page-level components
│   ├── App.vue
│   └── main.ts
├── index.html
├── package.json
├── tsconfig.json
└── vite.config.ts
```

## Routes

| Route | View | Description |
|-------|------|-------------|
| `/` | Dashboard | Health overview |
| `/record/food` | RecordFood | Record a meal |
| `/record/exercise` | RecordExercise | Record exercise |
| `/ai` | AIAssistant | AI health advice |

## State Management

- `useDashboardStore`: today's summary and trends
- `useRecordStore`: creating food/exercise records
- `useAIStore`: AI assistant state

## API Integration

- All API calls go through `src/api/request.ts`.
- Base URL is configurable via environment variable `VITE_API_BASE_URL`.
- Default: `http://localhost:8080/api`
