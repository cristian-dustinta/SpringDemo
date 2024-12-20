package demo.controller;

import demo.model.Library;
import demo.repository.LibraryRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    // Create a new Library
    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        Library savedLibrary = libraryRepository.save(library);
        return ResponseEntity.ok(savedLibrary);
    }

    // Get all Libraries
    @GetMapping
    public ResponseEntity<List<Library>> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        return ResponseEntity.ok(libraries);
    }

    // Get Library by ID
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "404", description = "No library with the given id")
    @ApiResponse(responseCode = "200", description = "Get the library with the given id")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        return libraryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}