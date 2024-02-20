package cgg.springrestapi.springwebapiproj.dao;

import cgg.springrestapi.springwebapiproj.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {}
