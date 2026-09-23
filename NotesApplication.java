import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class NotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }
}

// Note Model
class Note {
    private String id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    public Note() {}

    public Note(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = now.format(formatter);
        this.updatedAt = now.format(formatter);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}

// REST Controller
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
class NoteController {
    private Map<String, Note> notesDatabase = new HashMap<>();
    private int noteCounter = 0;

    // CREATE - Add a new note
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        String id = String.valueOf(++noteCounter);
        note.setId(id);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        note.setCreatedAt(now.format(formatter));
        note.setUpdatedAt(now.format(formatter));
        notesDatabase.put(id, note);
        return note;
    }

    // READ - Get all notes
    @GetMapping
    public Collection<Note> getAllNotes() {
        return notesDatabase.values();
    }

    // READ - Get a specific note by ID
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        Note note = notesDatabase.get(id);
        if (note == null) {
            throw new NoteNotFoundException("Note with ID " + id + " not found");
        }
        return note;
    }

    // UPDATE - Update an existing note
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note updatedNote) {
        Note existingNote = notesDatabase.get(id);
        if (existingNote == null) {
            throw new NoteNotFoundException("Note with ID " + id + " not found");
        }
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        existingNote.setUpdatedAt(now.format(formatter));
        notesDatabase.put(id, existingNote);
        return existingNote;
    }

    // DELETE - Delete a note
    @DeleteMapping("/{id}")
    public Map<String, String> deleteNote(@PathVariable String id) {
        Note note = notesDatabase.get(id);
        if (note == null) {
            throw new NoteNotFoundException("Note with ID " + id + " not found");
        }
        notesDatabase.remove(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Note deleted successfully");
        response.put("id", id);
        return response;
    }
}

// Exception Handler
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public Map<String, String> handleNoteNotFound(NoteNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }
}

// Custom Exception
class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String message) {
        super(message);
    }
}
