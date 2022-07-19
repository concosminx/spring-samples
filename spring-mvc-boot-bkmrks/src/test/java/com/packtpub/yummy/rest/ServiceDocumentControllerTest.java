package com.packtpub.yummy.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceDocumentControllerTest {

  @Autowired
  MockMvc mvc;
  @Autowired
  ObjectMapper mapper;

  @Test
  public void getServiceDocument() throws Exception {
    String result = mvc.perform(
            MockMvcRequestBuilders.get("/")
                    .accept("application/hal+json;charset=UTF-8")
    ).andDo(print())
            .andExpect(content()
                    .contentTypeCompatibleWith("application/hal+json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString();

    EntityModel<String> value = mapper.readValue(result, new TypeReference<EntityModel<String>>() {});
    assertTrue(value.hasLink("bookmarks"));
  }
}
