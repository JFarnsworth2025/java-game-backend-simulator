# Java Game Backend Simulator

A backend service built with Spring Boot that simulates core systems behind an online competitive game, including matchmaking, Elo-based ranking, persistent player storage, and leaderboards.

This project focuses on backend architecture, service-layer design, database integration, and realistic game system logic.

---

## Features

- Player creation and management
- Persistent storage using H2 (file-based)
- Matchmaking system with dynamic rating range
- Match lifecycle (join, active match tracking, completion)
- Elo rating algorithm for competitive ranking
- Leaderboard sorted by rating
- REST API built with Spring Boot
- JPA + Repository pattern architecture

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (file-based persistence)
- Maven

---

## API Endpoints

### Players
- `POST /players?username=&rating=` – Create player
- `GET /players?username=` – Get player by username
- `GET /players/all` – Get all players
- `GET /leaderboard` – Get leaderboard (sorted by rating)

### Matchmaking
- `POST /matchmaking/join?username=` – Join matchmaking queue
- `GET /matches` – View active matches
- `POST /matches/{matchId}/complete?winner=` – Complete match and update ratings

---

## Architecture Overview

Controller → Service → Repository → Database

- Controllers handle HTTP requests
- Services contain business logic (Elo, matchmaking)
- Repositories manage database interaction
- H2 provides persistent storage

---

## Current Capabilities

- Ratings persist across restarts
- Elo-based ranking adjusts based on expected outcome
- Leaderboard reflects live database state
- Dynamic matchmaking range prevents dead queues

---

## Future Improvements

- Persist match history
- Add DTO layer
- Add global exception handling
- Introduce integration tests
- Switch to PostgreSQL
- Dockerize application