package com.packtpub.yummy.rest;

import com.packtpub.yummy.model.ServiceDoc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;

@RestController
@RequestMapping(produces = "application/hal+json;charset=UTF-8")
public class ServiceDocumentController {

  @GetMapping("/")
  public EntityModel<ServiceDoc> getServiceDocument() throws URISyntaxException {
    return EntityModel.of(new ServiceDoc("Yummy is the best service"),
            BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
            linkTo(methodOn(BookmarksController.class).addBookmark(null)).withRel("bookmarks")
    );
  }
}
