package com.nimsoc.simpleweb.tmplapp.repositories;

import com.nimsoc.simpleweb.tmplapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
