package cgg.springrestapi.springwebapiproj.services;

import cgg.springrestapi.springwebapiproj.dao.BookRepository;
import cgg.springrestapi.springwebapiproj.entities.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  // private static List<Book> books = new ArrayList<>();
  @Autowired
  private BookRepository bookrepo;

  // static {
  //   books.add(new Book(5, "Java", "k.rajan"));
  //   books.add(new Book(1, "I see you", "John "));
  //   books.add(new Book(2, "Think ", "Kane "));
  // }

  // public Optional<Book> getBookById(int id) {
  //   Optional<Book> book = books
  //     .stream()
  //     .filter(b -> b.getBook_id() == id)
  //     .findFirst();
  //   return book;
  // }

  // public Book addBook(Book b) {
  //   books.add(b);
  //   return b;
  // }

  // public boolean updateBook(Book bk, int id) {
  //   if (
  //     books.stream().filter(b -> b.getBook_id() == id).findAny().isPresent()
  //   ) {
  //     books =
  //       books
  //         .stream()
  //         .map(b -> {
  //           if (b.getBook_id() == id) {
  //             b.setTitle(bk.getTitle());
  //             b.setAuthor(bk.getAuthor());
  //           }
  //           return b;
  //         })
  //         .collect(Collectors.toList());
  //     return true;
  //   }
  //   return false;
  // }

  // public void deleteBook(int id) {
  //   books = books.stream().filter(b -> b.getBook_id() != id).toList();
  // }

  // public void deleteAll() {
  //   books.removeAll(books);
  // }

  //data jpa
  public List<Book> getBooks() {
    return (List<Book>) bookrepo.findAll();
  }

  public Optional<Book> getBookById(int id) {
    Optional<Book> book = bookrepo.findById(id);
    return book;
  }

  public Book addBook(Book b) {
    return bookrepo.save(b);
  }

  public void updateBook(Book bk, int id) {
    Book book = bookrepo.findById(id).get();
    book.setAuthor(bk.getAuthor());
    book.setTitle(bk.getTitle());
    bookrepo.save(book);
  }

  public void deleteBook(int id) {
    bookrepo.deleteById(id);
  }

  public void deleteAll() {
    bookrepo.deleteAll();
  }
}
