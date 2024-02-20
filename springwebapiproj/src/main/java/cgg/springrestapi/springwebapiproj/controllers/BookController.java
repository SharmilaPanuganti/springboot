package cgg.springrestapi.springwebapiproj.controllers;

import cgg.springrestapi.springwebapiproj.entities.Book;
import cgg.springrestapi.springwebapiproj.services.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// @ResponseBody
@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  //   @GetMapping("/books")
  //   public List<Book> getBooks() {
  //     return bookService.getBooks();
  //   }

  //   @GetMapping("/books/{bookId}")
  //   public Optional<Book> getBookById(@PathVariable("bookId") int id) {
  //     return bookService.getBookById(id);
  //   }
  //   @PostMapping("/books")
  //   public Book addBook(@RequestBody Book b) {
  //     return bookService.addBook(b);
  //   }
  //   @PutMapping("books/{id}")
  //   public void updateBook(@RequestBody Book b, @PathVariable int id) {
  //     bookService.updateBook(b, id);
  //   }
  //responseENtity
  @GetMapping("/books")
  public ResponseEntity<List<Book>> getBooks() {
    List<Book> books = bookService.getBooks();
    if (books.size() <= 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.of(Optional.of(books));
  }

  @GetMapping("/books/{bookId}")
  public ResponseEntity<Book> getBookById(@PathVariable("bookId") int id) {
    Optional<Book> book = null;

    book = bookService.getBookById(id);
    if (book == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.of(book);
  }

  @PostMapping("/books")
  public ResponseEntity<Book> addBook(@RequestBody Book b) {
    Book book = null;

    try {
      book = bookService.addBook(b);
      return ResponseEntity.status(HttpStatus.CREATED).body(book);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("books/{id}")
  public ResponseEntity<Book> updateBook(
    @RequestBody Book b,
    @PathVariable int id
  ) {
    try {
      bookService.updateBook(b, id);
      return ResponseEntity.ok().body(b);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  //   @DeleteMapping("books/{id}")
  //   public void deleteBook(@PathVariable int id) {
  //     bookService.deleteBook(id);
  //   }
  @DeleteMapping("/books/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable int id) {
    try {
      bookService.deleteBook(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/books")
  public ResponseEntity<Void> deleteAll() {
    try {
      bookService.deleteAll();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  //   @DeleteMapping("/books")
  //   public void deleteAll() {
  //     bookService.deleteAll();
  //   }
}
