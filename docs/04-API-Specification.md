---
Metadata:
  Title: API Specification
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define all REST API contracts, endpoints, and response formats.
Scope:
  Backend HTTP API.
Review Cycle:
  Per feature iteration.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - Generate controllers and DTOs based on these contracts.
  - Never change response structure without updating this document.
---

# API Specification

## Base URL

```
http://localhost:8080/api
```

## Response Format

All responses use the same envelope:

```json
{
  "code": 0,
  "message": "ok",
  "data": {}
}
```

## Error Codes

| Code | Meaning |
|------|---------|
| 0 | Success |
| 40001 | Bad request / validation error |
| 40002 | AI service unavailable |
| 50001 | Internal server error |

## Endpoints

### Diet

#### POST /nutrition/food-records

Create a food record.

**Request:**
```json
{
  "foodName": "beef noodles",
  "portion": 1,
  "unit": "bowl",
  "recordedAt": "2026-07-11T12:00:00"
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "id": 1,
    "foodName": "beef noodles",
    "calories": 550,
    "proteinG": 25,
    "carbsG": 65,
    "fatG": 18,
    "recordedAt": "2026-07-11T12:00:00"
  }
}
```

#### GET /nutrition/food-records?date=2026-07-11

List food records for a user on a given date.

#### POST /nutrition/food-records/recognize

Recognize food from text or image.

**Request:**
```json
{
  "text": "I had a bowl of beef noodles today.",
  "imageUrl": null
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "foodName": "beef noodles",
    "suggestedPortion": 1,
    "suggestedUnit": "bowl"
  }
}
```

### Exercise

#### POST /exercise/exercise-records

Create an exercise record.

**Request:**
```json
{
  "exerciseType": "running",
  "durationMinutes": 30,
  "recordedAt": "2026-07-11T18:00:00"
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "id": 1,
    "exerciseType": "running",
    "durationMinutes": 30,
    "caloriesBurned": 300,
    "recordedAt": "2026-07-11T18:00:00"
  }
}
```

### Dashboard

#### GET /analytics/dashboard

Get today's dashboard data.

**Response:**
```json
{
  "code": 0,
  "data": {
    "caloriesIn": 1650,
    "caloriesOut": 450,
    "netCalories": 1200,
    "goalCalories": 2000,
    "goalCompletionRate": 0.6,
    "proteinG": 80,
    "carbsG": 180,
    "fatG": 55,
    "last7Days": [
      {"date": "2026-07-05", "netCalories": 1300}
    ]
  }
}
```

### AI Assistant

#### POST /ai/health-advice

Get health advice based on recent records.

**Request:**
```json
{
  "context": "recent diet and exercise"
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "advice": "Your protein intake is good today. Consider adding more vegetables."
  }
}
```
