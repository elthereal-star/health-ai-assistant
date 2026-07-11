---
Metadata:
  Title: Database Design
  Version: 1.0.0
  Author: Health AI Assistant Team
  Last Updated: 2026-07-11
Purpose:
  Define the database schema, relationships, and data constraints.
Scope:
  MySQL database used by the backend.
Review Cycle:
  Per feature iteration.
Change Log:
  - 2026-07-11 v1.0.0: Initial release
AI Context:
  - Generate entity classes and migration scripts based on this schema.
  - Do not invent tables not listed here.
---

# Database Design

## Database

`health_ai`

## Table Overview

| Table | Description |
|-------|-------------|
| `user` | Registered users |
| `user_profile` | User health profile and goals |
| `food_record` | Food intake records |
| `exercise_record` | Exercise records |
| `daily_summary` | Pre-computed daily aggregates |
| `food_dict` | Standardized food reference data |
| `exercise_dict` | Standardized exercise reference data |

## Detailed Schema

### user
```sql
CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE,
  email VARCHAR(128) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### user_profile
```sql
CREATE TABLE user_profile (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL UNIQUE,
  gender VARCHAR(16),
  birth_date DATE,
  height_cm INT,
  weight_kg DECIMAL(5,2),
  daily_calorie_goal INT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES user(id)
);
```

### food_record
```sql
CREATE TABLE food_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  food_name VARCHAR(128) NOT NULL,
  standard_food_code VARCHAR(64),
  portion DECIMAL(10,2) NOT NULL DEFAULT 1,
  unit VARCHAR(32) NOT NULL,
  calories DECIMAL(10,2) NOT NULL,
  protein_g DECIMAL(10,2) NOT NULL,
  carbs_g DECIMAL(10,2) NOT NULL,
  fat_g DECIMAL(10,2) NOT NULL,
  recorded_at DATETIME NOT NULL,
  source VARCHAR(32) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES user(id)
);
```

### exercise_record
```sql
CREATE TABLE exercise_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  exercise_type VARCHAR(64) NOT NULL,
  duration_minutes INT NOT NULL,
  calories_burned DECIMAL(10,2) NOT NULL,
  recorded_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES user(id)
);
```

### daily_summary
```sql
CREATE TABLE daily_summary (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  summary_date DATE NOT NULL,
  total_calories_in DECIMAL(10,2) NOT NULL DEFAULT 0,
  total_calories_out DECIMAL(10,2) NOT NULL DEFAULT 0,
  protein_g DECIMAL(10,2) NOT NULL DEFAULT 0,
  carbs_g DECIMAL(10,2) NOT NULL DEFAULT 0,
  fat_g DECIMAL(10,2) NOT NULL DEFAULT 0,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_user_date (user_id, summary_date),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
```

## Notes

- All monetary or weight values use `DECIMAL` to avoid floating-point errors.
- Calories and nutrition are calculated by the domain layer, not by AI.
- `source` indicates how the record was created: TEXT, IMAGE, or MANUAL.
