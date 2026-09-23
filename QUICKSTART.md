# Quick Start Guide

Get the Notes App running in 5 minutes!

## Prerequisites Check

Make sure you have Java and Maven installed:

```bash
java -version    # Should show Java 11 or higher
mvn -version     # Should show Maven 3.6 or higher
```

If not installed, visit:
- Java: https://www.oracle.com/java/technologies/downloads/
- Maven: https://maven.apache.org/download.cgi

## 1. Start the Backend Server

```bash
# Navigate to your project directory
cd /path/to/notes-app

# Run the Spring Boot application
mvn spring-boot:run
```

**Wait for this message:**
```
Started NotesApplication in X.XXX seconds
```

✅ Server is now running on `http://localhost:8080`

## 2. Open the Frontend

Choose one of these options:

### Option A: Direct File (Simplest)
```bash
# Simply open the file in your browser
open index.html  # macOS
start index.html # Windows
# Or double-click the file
```

### Option B: Local Web Server (Recommended)

**Using Python 3:**
```bash
python -m http.server 8000
```

**Using Node.js:**
```bash
npx http-server
```

Then open your browser to: `http://localhost:8000`

## 3. Start Using the App! 🎉

### Create a Note
1. Enter a title in the "Title" field
2. Type your content in the "Content" field
3. Click "Save Note"
4. See it appear in the grid!

### Edit a Note
1. Click "Edit" on any note card
2. Modify the content
3. Click "Update Note"

### Delete a Note
1. Click "Delete" on any note card
2. Confirm deletion
3. Note is removed

## Common Issues & Fixes

| Issue | Solution |
|-------|----------|
| **"Connection refused" error** | Make sure `mvn spring-boot:run` is running |
| **No notes loading** | Check browser console (F12) for errors |
| **Port 8080 in use** | Kill existing process or use different port |
| **Maven not found** | Add Maven to your system PATH |

## Quick API Test (Optional)

Test the backend API directly:

```bash
# Get all notes
curl http://localhost:8080/api/notes

# Create a note
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","content":"Hello World"}'
```

## Project Files Explained

| File | Purpose |
|------|---------|
| **NotesApplication.java** | Backend REST API |
| **pom.xml** | Maven dependencies |
| **index.html** | Frontend UI (HTML/CSS/JS) |
| **README.md** | Full documentation |
| **QUICKSTART.md** | This file |

## Architecture Overview

```
┌─────────────────────────────────────────┐
│         index.html (Browser)            │
│    HTML/CSS/JavaScript Frontend         │
└────────────┬────────────────────────────┘
             │ HTTP/JSON
             │ (Fetch API)
             ▼
┌─────────────────────────────────────────┐
│    http://localhost:8080/api/notes      │
│      Spring Boot REST API                │
│  (NotesApplication.java Backend)        │
└─────────────────────────────────────────┘
             │
             ▼
    ┌────────────────┐
    │ In-Memory Data │
    │   (HashMap)    │
    └────────────────┘
```

## What's Happening Under the Hood

1. **Frontend (index.html)**: Shows the UI and handles user interactions
2. **JavaScript**: Makes HTTP requests to the backend using Fetch API
3. **Spring Boot**: Processes requests and manages notes data
4. **HTTP Responses**: Return JSON data to update the frontend

## Next Steps

After getting the basic app running:

1. ✅ Test all CRUD operations
2. ✅ Try editing and deleting notes
3. ✅ Check browser DevTools (F12) to see network requests
4. ✅ Read README.md for advanced features
5. ✅ Customize the UI (colors, fonts, layout)
6. ✅ Add database persistence (see README.md)

## Customize the App

### Change the Title
Edit `index.html`, find `<h1>📝 My Notes</h1>`, change to whatever you want

### Change Colors
Edit `index.html`, find the CSS `background: linear-gradient(...)` section and modify colors

### Change Port (if 8080 is in use)
1. Create `src/main/resources/application.properties`
2. Add: `server.port=8081`
3. Update the API URL in `index.html` to match

## Stopping the Server

Press **Ctrl+C** in the terminal where `mvn spring-boot:run` is running

---

**Enjoy your Notes App! 📝✨**

For more details, see `README.md`
