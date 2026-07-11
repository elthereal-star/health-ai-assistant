---
Metadata:
  Title: System Specification
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the system architecture, layers, and capability boundaries.
Scope:
  Backend and frontend architecture.
Review Cycle:
  Quarterly or after major releases.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - Use this document to determine where a new feature belongs.
  - Always respect layer boundaries.
---

# System Specification

## Architecture Style

**Modular Monolith**

A single deployable unit, but internally organized into independent business capabilities.

## Five-Layer Architecture

```text
Presentation
    ↓
Application
    ↓
Business Capability
    ↓
Supporting Services
    ↓
Infrastructure
```

### Layer 1: Presentation
- Vue 3 + TypeScript + Element Plus
- Pages, components, and stores
- Only consumes Application layer APIs

### Layer 2: Application
- REST controllers
- Request/response DTOs
- Input validation
- Coordinates capability services

### Layer 3: Business Capability
- Identity
- Nutrition
- Exercise
- Analytics

Each capability owns:
- Domain entities and value objects
- Domain services (deterministic calculations)
- Application services
- Infrastructure adapters (repositories, external clients)

### Layer 4: Supporting Services
- AI Service (food recognition, text understanding, advice)
- OCR Service (future)
- Notification Service (future)

### Layer 5: Infrastructure
- MySQL 8
- Redis 7
- MinIO

## Capability Lifecycle

Every capability follows the same lifecycle:

```text
Capture → Process → Analyze → Present
```

Example: Nutrition
```text
Record Food → Standardize → Calculate Nutrition → Dashboard
```

## Data Flow

```text
User
    ↓
Presentation
    ↓
Application
    ↓
AI (optional)
    ↓
Domain
    ↓
Database
    ↓
Analytics
    ↓
Dashboard
```

There is only one data flow in the system.

## Capability Boundaries

| Capability | Responsibility | Key Entities |
|------------|----------------|--------------|
| Identity | User registration, login, profile | User, UserProfile |
| Nutrition | Food record, standardization, nutrition calculation | FoodRecord, FoodItem, NutritionFacts |
| Exercise | Exercise record, calorie consumption | ExerciseRecord, ExerciseType |
| Analytics | Aggregations, trends, dashboard data | DailySummary, Trend |

## Module Dependency Rules

1. Application depends on Capability.
2. Capability depends on Domain and Infrastructure interfaces.
3. Infrastructure implements Capability interfaces.
4. Supporting Services are called by Capability or Application, never by Presentation.
5. No layer depends on Presentation.
