package com.nimsoc.neo4j.api.node;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
  @Id
  private int cid;
  private String name;
  private String[] address;
  @Relationship(type = "Purchase", direction = Relationship.Direction.INCOMING)
  private List<Product> products;
}