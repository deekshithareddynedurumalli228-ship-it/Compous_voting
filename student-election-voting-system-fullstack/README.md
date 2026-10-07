# Student Election Online Voting System (React + Spring Boot + Hibernate)

A full-stack, dispute-free digital voting system designed for student councils. Solves the delays, counting disputes, and geographic barriers between hostel residents and day scholars.

## 🌸 Key Features Included:
- **Admin & Student Login**: Frictionless authentication without tokens/cookies as requested.
- **Admin Election & Post Management**: Configure elections, set start and end date/time, add custom posts.
- **Candidate Profiles**: Add candidates per post with photos, manifestos, department, and Hostel/Day Scholar tag.
- **Single Vote Enforcement**: Students are strictly allowed only 1 vote per post. Duplicate voting is rejected at both frontend and backend database constraints.
- **Timeline-Controlled Voting**: Voting is strictly locked outside the start-to-end election window.
- **Instant Automatic Results**: Vote tallies, winner declaration, runner-up margins, and turnout percentages are calculated in real time once voting closes.
- **Floral Aesthetic UI**: Elegant botanical design with smooth animations.

---

## 🛠️ Step 1: Running in Visual Studio Code (Local Dev)

### Prerequisites:
1. **VS Code** with the **"Extension Pack for Java"** (by Microsoft)
2. **JDK 17 or higher** installed (`java -version`)
3. **Node.js 18+ and npm** installed (`node -v`)
4. **Maven** (bundled inside VS Code Java extension or standalone)

### A. Run Spring Boot Backend (Port 8080):
1. Open the `springboot-backend` folder in VS Code.
2. In VS Code Terminal, run:
   ```bash
   mvn clean spring-boot:run
   ```
   *(Or open `VotingApplication.java` and click **Run** / press **F5**)*
3. Backend starts at: `http://localhost:8080`
4. View the built-in database console at: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:votingdb`
   - User Name: `sa`
   - Password: *(leave blank)*

### B. Run React Frontend (Port 3000 / 5173):
1. Open the `react-frontend` folder in VS Code.
2. Open a new terminal and install dependencies:
   ```bash
   npm install
   ```
3. Start development server:
   ```bash
   npm run dev
   ```
4. Open `http://localhost:3000` in your browser.

---

## 🚀 Step 2: Push to GitHub

1. Initialize Git in the project root:
   ```bash
   git init
   git branch -M main
   git add .
   git commit -m "Initial commit: Student Election Voting System (React + Spring Boot)"
   ```
2. Create a new repository on GitHub (e.g. `student-election-voting-system`).
3. Link and push:
   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/student-election-voting-system.git
   git push -u origin main
   ```

---

## 🌐 Step 3: Deploying Online

### Option A: Deploy Frontend to Vercel or Netlify
1. Go to [Vercel](https://vercel.com) or [Netlify](https://netlify.com).
2. Connect your GitHub repository.
3. Set **Root Directory** to `react-frontend` (or repo root).
4. Set Build Command: `npm run build`
5. Set Output Directory: `dist`
6. Add Environment Variable:
   - `VITE_API_BASE_URL` = Your backend URL (e.g., `https://your-backend.onrender.com`)
7. Click **Deploy**!

### Option B: Deploy Backend to Render or Railway
1. Go to [Render](https://render.com) or [Railway](https://railway.app).
2. Create a **New Web Service** pointing to your repository.
3. Set Root Directory to `springboot-backend`.
4. Environment: **Java** / Docker
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/student-voting-system-1.0.0.jar`
5. Port: `8080`
