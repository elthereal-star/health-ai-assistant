---
Metadata:
  Title: Engineering Rules
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the engineering principles and coding standards for the Health AI Assistant project.
Scope:
  All backend, frontend, and infrastructure code.
Review Cycle:
  Quarterly or after major architecture changes.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - When generating code, follow these rules strictly.
  - Prefer deterministic logic over AI inference for business rules.
  - Never place business-critical calculations inside AI prompts.
---

# Engineering Rules

## 1. Core Principles

### Data First
- Data is the single source of truth.
- All business logic must be derived from persisted data, not from assumptions or LLM outputs.

### AI Enhanced
- AI is a supporting service, not a business controller.
- AI handles understanding, OCR, intent classification, and advice; it never modifies the database or computes core business values.

### Deterministic Logic
- All calculations (calories, nutrition, BMI, TDEE) must be reproducible and testable.
- Business rules must be expressed as code with clear unit tests, not as part of an LLM prompt.

### Capability Driven
- The codebase is organized around business capabilities, not technical layers.
- Capabilities: Identity, Nutrition, Exercise, Analytics.
- Each capability follows the lifecycle: Capture → Process → Analyze → Present.

### One-Way Data Flow
```
User → Presentation → Application → AI (optional) → Domain → Database → Analytics → Dashboard
```
- Data flows in one direction. Avoid circular dependencies.

### Contract First
- APIs, events, and module boundaries are defined by contracts before implementation.
- Contracts include request/response schemas, error codes, and behavioral invariants.

### Just Enough Architecture
- Use the right amount of architecture to solve the current problem.
- Avoid premature abstraction or microservices decomposition.

## 2. Project Rules

- Never commit secrets or API keys.
- Use `application-local.yml` for local overrides; never commit it.
- Keep functions small and testable.
- Write unit tests for domain logic and integration tests for API contracts.
- All public APIs must be documented in the API specification.
- Use English for identifiers and code comments; Chinese is allowed in user-facing text and documentation.
