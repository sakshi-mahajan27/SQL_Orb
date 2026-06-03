# Quick Start

Use this guide to run SQL_Orb locally.

## Prerequisites

- Java (JDK 8 or newer)

## Run locally

1. Open a terminal and navigate to the repository root directory:
   `cd SQL_Orb`
2. Compile source files:
   `find . -name "*.java" -print0 | xargs -0 javac`
   (No output usually means compilation succeeded.)
3. Run the program:
   `java main.Main`
4. Enter a SQL query when prompted.

## Example

Note: This query intentionally contains an error to demonstrate checker output.

Input:
`SELECT id name FROM users;`

Expected behavior:
- SQL_Orb reports a `SELECT Clause Error` for missing comma between `id` and `name`.
