# Key Features

SQL_Orb is a lightweight SQL syntax checker for basic `SELECT` query validation.

## Core capabilities

- Tokenizes an input SQL query into manageable tokens.
- Detects key SQL clauses:
  - `SELECT`
  - `FROM`
  - `WHERE`
  - `GROUP BY`
  - `HAVING`
  - `ORDER BY`
- Validates mandatory clause presence (`SELECT` and `FROM`).
- Validates clause ordering (must follow: `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`).
- Detects missing commas between selected columns in the `SELECT` list.
- Prints structured, human-readable error messages with explanation and (when available) suggested fixes.

## Project structure highlights

- `/main` — application entry point and rule execution flow.
- `/lexer` — query tokenization.
- `/parser` — clause detection and positioning.
- `/rules` — syntax rule checks.
- `/error` — reusable SQL error model and formatter.
