---
Metadata:
  Title: AI Specification
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the role, boundaries, and contracts of the AI subsystem.
Scope:
  AI service abstraction and provider adapters.
Review Cycle:
  Quarterly.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - AI is an Analyst, not a Calculator.
  - Never generate code that uses AI for business-critical computations.
---

# AI Specification

## AI Role

AI is responsible for:
- Food recognition from text and images
- Natural language understanding
- Intent classification
- Health and diet advice

AI is NOT responsible for:
- Calorie calculation
- BMI or TDEE calculation
- Database modification
- Changing business rules

## AI as Analyst

```text
User Input → AI → Structured Understanding → Business System → Deterministic Result
```

## Supported Providers

| Provider | Default | Notes |
|----------|---------|-------|
| DeepSeek | Yes | Cost-effective, good Chinese support |
| OpenAI | No | Strong multi-modal capabilities |

Switching providers only requires changing configuration.

## Contracts

### Food Recognition Output

```json
{
  "foodName": "string",
  "suggestedPortion": 1,
  "suggestedUnit": "string",
  "confidence": 0.95
}
```

### Health Advice Output

```json
{
  "advice": "string",
  "suggestedActions": ["string"]
}
```

### Natural Language Food Record Output

```json
{
  "foodName": "string",
  "portion": 1,
  "unit": "string"
}
```

## Implementation

- `AIService` interface defines all AI operations.
- `DeepSeekAIService` implements the interface using DeepSeek API.
- `OpenAIAIService` implements the interface using OpenAI API.
- All AI calls are wrapped in a resilience layer (retry, timeout, fallback).
