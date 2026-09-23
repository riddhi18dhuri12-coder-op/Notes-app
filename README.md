# Notes Application - CRUD Web App

A full-stack notes application with Java backend (Spring Boot) and HTML/CSS/JavaScript frontend.

## Features

✅ **Create** - Add new notes with title and content  
✅ **Read** - Display all notes with timestamps  
✅ **Update** - Edit existing notes  
✅ **Delete** - Remove notes with confirmation  
✅ **Responsive Design** - Works on desktop and mobile  
✅ **Real-time Updates** - Instant UI updates  
✅ **Timestamps** - Track creation and modification times  

## Project Structure

```
notes-app/
├── NotesApplication.java    # Backend (Spring Boot REST API)
├── pom.xml                  # Maven configuration
├── index.html               # Frontend (HTML/CSS/JavaScript)
└── README.md                # This file
```

## Prerequisites

- **Java 11 or higher**
- **Maven 3.6 or higher**
- **Modern web browser**
- **Port 8080 available**

## Installation & Setup

### 1. Install Java
Make sure you have Java 11+ installed:
```bash
java -version
```

### 2. Install Maven
Download from [maven.apache.org](https://maven.apache.org/download.cgi) or use package manager:

**On Ubuntu/Debian:**
```bash
sudo apt-get install maven
```

**On macOS (with Homebrew):**
```bash
brew install maven
```

**On Windows:** Download and follow the installer

## Running the Application

### Step 1: Start the Backend Server

Navigate to the project directory and run:

```bash
mvn spring-boot:run
```

Or build and run the JAR:
```bash
mvn clean package
java -jar target/notes-app-1.0.0.jar
```

You should see output like:
```
Started NotesApplication in X.XXX seconds (JVM running for X.XXX)
```

The server will run on: `http://localhost:8080`

### Step 2: Open the Frontend

Open `index.html` in your web browser:
- **Double-click** the `index.html` file, or
- Use a local server for better experience:

```bash
# Using Python 3
python -m http.server 8000

# Using Node.js (http-server)
npx http-server
```

Then open: `http://localhost:8000` (or the port shown)

## API Endpoints

### Base URL: `http://localhost:8080/api/notes`

| Method | Endpoint | Description |
|--------|----------|-------------|
| **POST** | `/api/notes` | Create a new note |
| **GET** | `/api/notes` | Get all notes |
| **GET** | `/api/notes/{id}` | Get a specific note |
| **PUT** | `/api/notes/{id}` | Update a note |
| **DELETE** | `/api/notes/{id}` | Delete a note |

### Example Requests

**Create a Note:**
```bash
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Note",
    "content": "This is the content of my note"
  }'
```

**Get All Notes:**
```bash
curl http://localhost:8080/api/notes
```

**Update a Note:**
```bash
curl -X PUT http://localhost:8080/api/notes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Title",
    "content": "Updated content"
  }'
```

**Delete a Note:**
```bash
curl -X DELETE http://localhost:8080/api/notes/1
```

## Frontend Features

### Create Note
1. Fill in the **Title** field
2. Enter your **Content**
3. Click **Save Note** button
4. New note appears in the grid

### View Notes
- All notes display in a responsive card grid
- Shows creation and update timestamps
- Preview of note content

### Edit Note
1. Click **Edit** button on any note card
2. Form fills with current note data
3. Form title changes to "Edit Note"
4. Make your changes
5. Click **Update Note** button

### Delete Note
1. Click **Delete** button on any note card
2. Confirm deletion in the popup
3. Note is removed from the list

## Troubleshooting

### "Connection Refused" Error
- Ensure backend is running on port 8080
- Check with: `lsof -i :8080` (macOS/Linux)

### "Failed to load notes" Error
- Verify the API URL in the browser console
- Make sure CORS is enabled (already configured)
- Check backend logs for errors

### Port 8080 Already in Use
- Change the port in `application.properties` (create if needed):
  ```properties
  server.port=8081
  ```
- Update the API URL in `index.html`

### Maven Build Issues
```bash
# Clear cache and rebuild
mvn clean install
```

## Technology Stack

### Backend
- **Spring Boot 3.1.5** - REST API framework
- **Java 11** - Programming language
- **Maven** - Build tool

### Frontend
- **HTML5** - Structure
- **CSS3** - Styling with gradients and animations
- **Vanilla JavaScript** - No dependencies
- **Fetch API** - HTTP requests

## Data Storage

Currently, notes are stored **in-memory**. To persist data:

1. Add Spring Data JPA and H2 Database to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. Convert `Note` to a JPA entity and use `JpaRepository`

## Future Enhancements

- [ ] Database persistence (MySQL, PostgreSQL)
- [ ] User authentication & authorization
- [ ] Note categories/tags
- [ ] Search functionality
- [ ] Rich text editor
- [ ] Sharing & collaboration
- [ ] Cloud synchronization
- [ ] Mobile app

## License

This project is open source and available under the MIT License.

## Support

For issues or questions:
1. Check the Troubleshooting section
2. Review browser console for errors
3. Check backend logs for API errors

---

**Happy Note-Taking! 📝**
