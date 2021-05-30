package com.nimsoc.simpleweb.tmplapp.bootstrap;

import com.nimsoc.simpleweb.tmplapp.model.Author;
import com.nimsoc.simpleweb.tmplapp.model.Book;
import com.nimsoc.simpleweb.tmplapp.model.Publisher;
import com.nimsoc.simpleweb.tmplapp.repositories.AuthorRepository;
import com.nimsoc.simpleweb.tmplapp.repositories.BookRepository;
import com.nimsoc.simpleweb.tmplapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultLoader implements ApplicationListener<ContextRefreshedEvent> {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;

  public DefaultLoader(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  public void initDat() {

    Publisher p1 = new Publisher("Knopf");
    publisherRepository.save(p1);

    Author palacio = new Author("R.J.", "Palacio");
    Book wonder = new Book("WONDER", "1241213", p1);
    palacio.getBooks().add(wonder);
    wonder.getAuthors().add(palacio);

    authorRepository.save(palacio);
    bookRepository.save(wonder);

    Publisher p2 = new Publisher("Packt");
    publisherRepository.save(p2);

    Author aly = new Author("Aly", "Saleh");
    Author murat = new Author("Murat", "Karslioglu");
    Book kbnts = new Book("Kubernetes in Production Best Practices", "123455", p2);
    kbnts.getAuthors().add(aly);
    kbnts.getAuthors().add(murat);
    aly.getBooks().add(kbnts);
    murat.getBooks().add(kbnts);

    authorRepository.save(aly);
    authorRepository.save(murat);
    bookRepository.save(kbnts);

  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent e) {
    initDat();
  }

}
