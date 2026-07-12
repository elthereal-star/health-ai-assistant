---
Metadata:
  Title: API Specification
  Version: 1.1.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-12
Purpose:
  Define all REST API contracts, endpoints, and response formats.
Scope:
  Backend HTTP API.
Review Cycle:
  Per feature iteration.
Change Log:
  - 2026-07-12 v1.1.0: Add Identity endpoints and authentication
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
| 40101 | Unauthorized / invalid token |
| 50001 | Internal server error |

## Authentication

All endpoints except `POST /identity/register` and `POST /identity/login` require a `Bearer` token in the `Authorization` header:

```http
Authorization: Bearer <token>
```

The token is returned after registration or login. The default expiration time is 24 hours.

## Endpoints

### Identity

#### POST /identity/register

Register a new user.

**Request:**
```json
{
  "username": "alice",
  "email": "alice@example.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "tokenType": "Bearer",
    "userId": 1,
    "username": "alice"
  }
}
```

#### POST /identity/login

Log in with username and password.

**Request:**
```json
{
  "username": "alice",
  "password": "123456"
}
```

**Response:**
```json
{
  "code": 0,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "tokenType": "Bearer",
    "userId": 1,
    "username": "alice"
  }
}
```

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

List food records for the current user on a given date. If `date` is omitted, all records for the user are returned.

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
    "suggestedUnit": "bowl",
    "confidence": 0.95
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

#### GET /exercise/exercise-records?date=2026-07-11

List exercise records for the current user on a given date.

### Dashboard

#### GET /analytics/dashboard

Get today's dashboard data for the current user.

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
    "advice": "Your protein intake is good today. Consider adding more vegetables.",
    "suggestedActions": ["add vegetables", "drink more water"]
  }
}
```