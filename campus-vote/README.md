# CampusVote — Java Full Stack Student Election System

React + Vite frontend, Java 17 + Spring Boot backend, Spring Data JPA/Hibernate and PostgreSQL. Intentionally simple: no Spring Security, JWT, OAuth or tokens.

## Features
- Admin and student login
- Admin creates election, posts, start/end time
- Admin adds candidates with department/year/manifesto/photo URL
- Student sees upcoming/live/closed elections
- Voting allowed only during the configured period
- Exactly one candidate per post
- Database unique constraint prevents duplicate student/post votes
- Automatic result display after election closes
- Responsive floral/pastel UI and small animations

## Demo accounts
Admin: `ADMIN001` / `admin123`
Student: `STU001` / `student123`
Student: `STU002` / `student123`

## Requirements
Java 17+, Maven 3.9+, Node.js 20+, PostgreSQL 14+, VS Code.

## Local database
Create a PostgreSQL database:
```sql
CREATE DATABASE campus_vote;
```
Default local backend values are postgres/postgres. If your password differs, set `DB_USERNAME` and `DB_PASSWORD` environment variables.

## Run backend
```bash
cd backend
mvn spring-boot:run
```
Backend: `http://localhost:8080`

## Run frontend
Open a second terminal:
```bash
cd frontend
npm install
```
Create `frontend/.env`:
```env
VITE_API_URL=http://localhost:8080/api
```
Then:
```bash
npm run dev
```
Open `http://localhost:5173`.

## Full test flow
1. Login as ADMIN001/admin123.
2. Admin Studio → create an election with start/end times and posts.
3. Add at least two candidates to each post.
4. Set the start time to now/past and end time to a future time.
5. Login as STU001/student123 in another browser/incognito window.
6. Select one candidate for every post and submit.
7. A second attempt is blocked.
8. After the election end time, open the election again to see results.

## GitHub
From project root:
```bash
git init
git add .
git commit -m "Initial CampusVote application"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/campus-vote.git
git push -u origin main
```
Do not commit real production passwords.

## Render backend
This repo contains `render.yaml`. Connect the GitHub repo to Render as a Blueprint. It creates the Spring Boot web service and PostgreSQL database. After deployment, set:
```text
CORS_ORIGINS=https://YOUR-VERCEL-DOMAIN.vercel.app
```
The API will be similar to `https://campus-vote-api.onrender.com/api`.

## Vercel frontend
Import the GitHub repository into Vercel. Set Root Directory to `frontend`.
Build command: `npm run build`
Output: `dist`
Environment variable:
```text
VITE_API_URL=https://YOUR-RENDER-BACKEND.onrender.com/api
```
The included `vercel.json` supports SPA routing.

## Netlify alternative
Base directory: `frontend`
Build command: `npm run build`
Publish directory: `dist`
Set the same `VITE_API_URL` environment variable.

## Deployment order
PostgreSQL → Render Spring Boot API → copy API URL → Vercel/Netlify React frontend → set `VITE_API_URL` → set Render `CORS_ORIGINS` to the frontend URL.

## Architecture
React/Vite → REST API → Spring Boot → Spring Data JPA/Hibernate → PostgreSQL.

## Important project limitation
This is intentionally a college-project/demo authentication design because you requested no security/tokens. A real election should add institutional identity verification, stronger authentication, privacy controls, encryption, auditability, accessibility and independent election oversight.
