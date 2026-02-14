# Java Game Backend Simulator

A Spring Boot backend that simulates core systems behind an online competitive game, including matchmaking, Elo-based ranking, persistent player storage, match history, and leaderboards.

This project focuses on backend architecture, service-layer design, database persistence, and realistic multiplayer system logic.

---

## Features

### Player System
- Create players with custom rating
- Retrieve player information
- Persistent storage using H2 (file-based)
- Leaderboard sorted by rating (descending)

### Matchmaking System
- Dynamic rating-based matchmaking range
- Prevents duplicate queue entries
- Active match persistence
- Match completion endpoint

### Competitive Ranking
- Elo rating algorithm (K-factor 32)
- Rating updates based on expected outcome
- Ratings persist across application restarts

### Match History
- Active matches endpoint
- Completed match tracking
- Persistent match records

---

## Tech Stack

- Java 23
- Spring Boot 3
- Spring Data JPA
- Hibernate
- H2 Database (file-based persistence)
- Maven

---

## API Endpoints

### Players

POST `/players?username=&rating=`  
GET `/players?username=`  
GET `/players/all`  
GET `/leaderboard`

### Matchmaking

POST `/matchmaking/join?username=`

### Matches

GET `/matches`  
POST `/matches/{matchId}/complete?winner=`  
GET `/matches/history`  
GET `/matches/history/{username}`  
POST `/matches/reset`

---

## Architecture

Controller → Service → Repository → Database

- **Controllers** handle HTTP requests
- **Services** contain business logic (Elo calculation, matchmaking logic)
- **Repositories** manage database access via Spring Data JPA
- **H2 Database** provides file-based persistent storage

---

## Running the Application

1. Clone the repository
2. Run:

   mvn spring-boot:run

3. Application runs at:
   http://localhost:8080

---

## Database Access

H2 Console (development use only):

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:file:./data/gamedb

---

## Current Capabilities

- Ratings persist across restarts
- Elo-based ranking adjusts correctly
- Leaderboard reflects live database state
- Match records persist in database
- Dynamic matchmaking range prevents dead queues

---

## Future Improvements

- Introduce DTO layer
- Add global exception handling
- Add integration tests
- Implement authentication
- Switch to PostgreSQL
- Dockerize application
