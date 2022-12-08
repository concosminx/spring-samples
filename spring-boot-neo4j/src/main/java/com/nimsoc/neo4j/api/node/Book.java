package com.nimsoc.neo4j.api.node;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id
  private String id;
  private String name;
  private String author;
}
