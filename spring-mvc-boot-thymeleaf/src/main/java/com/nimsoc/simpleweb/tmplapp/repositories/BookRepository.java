package com.nimsoc.simpleweb.tmplapp.repositories;

import com.nimsoc.simpleweb.tmplapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
