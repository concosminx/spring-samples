package com.packtpub.yummy.rest;

import com.packtpub.yummy.model.Bookmark;
import com.packtpub.yummy.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;

@RestController
@RequestMapping(value = "bookmarks", produces = "application/hal+json;charset=UTF-8")
@PreAuthorize("hasRole('USER')")
public class BookmarksController {

  @Autowired
  BookmarkService bookmarkService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> addBookmark(@RequestBody @Valid Bookmark bookmark) {
    UUID uuid = bookmarkService.addBookmark(bookmark);
    return ResponseEntity.created(
            BasicLinkBuilder.linkToCurrentMapping()
                    .slash("bookmark")
                    .slash(uuid)
                    .toUri())
            .build();
  }

  @GetMapping
  public CollectionModel<Bookmark> findAllBookmarks() {
    return CollectionModel.of(bookmarkService.findAll(),
            BasicLinkBuilder.linkToCurrentMapping()
                    .slash("bookmarks").withSelfRel()
    );
  }
}
