package com.packtpub.yummy.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.yummy.model.Bookmark;
import com.packtpub.yummy.service.BookmarkService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.never;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookmarkControllerTest {

  @Autowired
  MockMvc mvc;
  @SpyBean
  BookmarkService bookmarkService;
  @Autowired
  ObjectMapper mapper;

  @BeforeEach
  public void setup() {
    Mockito.reset(bookmarkService);
  }

  @Test
  public void getABookmark() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    EntityModel<Bookmark> output = getBookmark(location);
    assertNotNull(output.getContent().getUrl());
    assertEquals(input.getUrl(), output.getContent().getUrl());
  }

  @Test
  public void deleteABookmark() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    mvc.perform(
            delete(location).accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
    ).andDo(print()).andExpect(status().isGone());

    mvc.perform(
            get(location).accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
    ).andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  public void deleteABookmarkTwiceYieldsNotModified() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    mvc.perform(
            delete(location).accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
    ).andDo(print()).andExpect(status().isGone());

    mvc.perform(
            delete(location).accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
    ).andDo(print()).andExpect(status().isNotModified());
  }

  @Test
  public void updateABookmark() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    EntityModel<Bookmark> output = getBookmark(location);

    String result = mvc.perform(
            post(output.getLinks().getLink(IanaLinkRelations.SELF).get().getHref())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(output.getContent().withUrl("http://kulinariweb.de")))
    ).andDo(print()).andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    output = mapper.readValue(result, new TypeReference<EntityModel<Bookmark>>() {
    });

    assertEquals("http://kulinariweb.de", output.getContent().getUrl());
  }

  @Test
  public void updateABookmarkFailsBecauseDescriptionIsNull() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    EntityModel<Bookmark> output = getBookmark(location);
    output.getContent().setDescription(null);
    mvc.perform(
            post(output.getLinks().getLink(IanaLinkRelations.SELF).get().getHref())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(output.getContent()))
    ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0].field").value("description"));
    Mockito.verify(bookmarkService, never()).update(Mockito.any(Bookmark.class));

  }

  @Test
  public void updateABookmarkFailsBecauseUrlIsNotValid() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    EntityModel<Bookmark> output = getBookmark(location);
    output.getContent().setUrl("broken://url.me");
    mvc.perform(
            post(output.getLinks().getLink(IanaLinkRelations.SELF).get().getHref())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(output.getContent()))
    ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0].field").value("url"));
    Mockito.verify(bookmarkService, never()).update(Mockito.any(Bookmark.class));

  }

  @Test
  public void updateABookmarkStaleFails() throws Exception {
    Bookmark input = getSimpleBookmark();
    String location = addBookmark(input);

    EntityModel<Bookmark> output = getBookmark(location);
    mvc.perform(
            post(output.getLinks().getLink(IanaLinkRelations.SELF).get().getHref())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(output.getContent().withUrl("http://kulinariweb.de")))
    ).andDo(print()).andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

    mvc.perform(
            post(output.getLinks().getLink(IanaLinkRelations.SELF).get().getHref())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(output.getContent().withUrl("http://kulinariweb2.de")))
    ).andDo(print()).andExpect(status().isConflict());
  }

  @Test
  public void updateABookmarkFailWrongId() throws Exception {
    Bookmark input = getSimpleBookmark();

    mvc.perform(
            post("/bookmark/" + UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
                    .content(mapper.writeValueAsString(input))
    )
            .andDo(print())
            .andExpect(status().isNotFound());
  }

  private Bookmark getSimpleBookmark() {
    return new Bookmark("Packt publishing", "http://packtpub.com");
  }

  private EntityModel<Bookmark> getBookmark(String location) throws Exception {
    String result = mvc.perform(
            get(location)
                    .accept("application/hal+json;charset=UTF-8", "application/json;charset=UTF-8")
    ).andDo(print())
            .andReturn().getResponse().getContentAsString();
    return mapper.readValue(result, new TypeReference<EntityModel<Bookmark>>() {
    });
  }

  private String addBookmark(Bookmark input) throws Exception {
    return mvc.perform(
            post("/bookmarks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(input))
    ).andDo(print()).andExpect(status().isCreated())
            .andReturn().getResponse().getHeader("Location");
  }
}
