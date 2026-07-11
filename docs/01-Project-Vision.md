---
Metadata:
  Title: Project Vision
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the project positioning, goals, and high-level product vision.
Scope:
  Product and engineering direction.
Review Cycle:
  Quarterly.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - This document defines the "why" behind the project.
  - Use it to align generated features and architecture.
---

# Project Vision

## Project Name

Health AI Assistant

## One-Sentence Intro

An AI-enhanced personal health management platform that helps users record diet and exercise through text and images, and generates sustainable health analytics and personalized advice.

## Core Goal

We are building an **AI-enhanced** health management platform, not a "system that can call AI".

Traditional AI demo:
```
User → AI → Answer
```

Our project:
```
User → Business System → {Rule Engine, AI Enhancement} → Health Analysis Result
```

AI is one of the capabilities, not the entire system.

## Product Features (MVP)

### 1. Diet Record
- Text input: "I had a bowl of beef noodles today."
- Image upload: AI recognizes food automatically.
- Manual entry: select food and portion.
- The system standardizes food, calculates calories and nutrition, saves data, and updates the dashboard.

### 2. Exercise Record
- Text input: "I ran 5km today."
- Manual entry: select exercise type and duration.
- The system recognizes exercise type, calculates calorie consumption, and saves data.

### 3. Dashboard
- Today intake / burn / net calories
- Protein / carbs / fat
- Last 7 days trend
- Daily goal completion rate
- The dashboard only displays data; it does not compute.

### 4. AI Assistant
- Food recognition from images
- Natural language understanding
- Health and diet advice
- AI never modifies the database, calculates calories, or changes business rules.

## Design Philosophy

- Data is the source of truth.
- AI provides enhancement, not logic.
- Deterministic calculations are testable and reproducible.
- The system is organized by business capabilities.
- One-way data flow keeps the system predictable.
- Contracts define boundaries.
- Architecture is sufficient, not excessive.
