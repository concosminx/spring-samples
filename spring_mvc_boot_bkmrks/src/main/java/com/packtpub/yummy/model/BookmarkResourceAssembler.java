package com.packtpub.yummy.model;

import com.packtpub.yummy.rest.BookmarkController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

@Component
public class BookmarkResourceAssembler implements RepresentationModelAssembler<Bookmark, EntityModel<Bookmark>> {

  @Override
  public EntityModel<Bookmark> toModel(Bookmark entity) {
    return EntityModel.of(entity,
            linkTo(methodOn(BookmarkController.class).getBookmark(entity.getUuid())).withSelfRel());
  }
}
