package com.example.webclients;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class DeprecatedTemplateOperationsFamily extends TestWithMockServer {

  public DeprecatedTemplateOperationsFamily(MockServerClient mockServer) {
    super(mockServer);
  }

  RestTemplate restTemplateClassIsNotDeprecated;


  @Test
  void sampleUsageOfRestTemplate() {
    var restTemplate = new RestTemplate();

    String url = "http://localhost:1090/some-endpoint";
    SampleResponseModel getResponse = restTemplate.getForObject(url, SampleResponseModel.class);
    assertThat(getResponse).isEqualTo(new SampleResponseModel("John", 25));
  }

  @Test
  void sampleUsageOfSettingHeaders() throws Exception {
    // example of setting cookies / headers
    String url = "http://localhost:1090/some-endpoint-with-cookies";

    mockServer.when(
        org.mockserver.model.HttpRequest.request()
            .withMethod("GET")
            .withHeader("Cookie", "name=value")
            .withHeader("Authorization", "Bearer token")
    ).respond(
        org.mockserver.model.HttpResponse.response()
            .withStatusCode(200)
            .withBody("{\"name\":\"John\",\"age\":25}")
            .withHeader("Content-Type", "application/json")
            .withHeader("Set-Cookie", "name=value")
    );


    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Cookie", "name=value");
    httpHeaders.add("Authorization", "Bearer token");

    RequestEntity<?> request = new RequestEntity<>(
        httpHeaders, HttpMethod.GET, new URI(url)
    );

    ResponseEntity<SampleResponseModel> response = new RestTemplate().exchange(request, SampleResponseModel.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().name()).isEqualTo("John");
    assertThat(response.getBody().age()).isEqualTo(25);
    assertThat(response.getHeaders().get("Set-Cookie")).contains("name=value");
  }

  @Test
  void sampleUsageOfMakingAPostWithPathVariables() {
    RestTemplate restTemplate = new RestTemplate();
    mockServer.when(
        org.mockserver.model.HttpRequest.request()
            .withMethod("POST")
            .withPath("/some-endpoint/John")
            .withQueryStringParameter("age", "25")
    ).respond(
        org.mockserver.model.HttpResponse.response()
            .withStatusCode(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"name\":\"John\",\"age\":25}")
    );

    String urkWithTemplate = "http://localhost:1090/some-endpoint/{name}?age={age}";
    SampleResponseModel someResponse = restTemplate.postForObject(
        urkWithTemplate, new SampleResponseModel("John", 25),
        SampleResponseModel.class,
        "John", 25
    );
    assertThat(someResponse).isEqualTo(new SampleResponseModel("John", 25));
  }

  RestClient theNewInterfaceForMakingHttpRequestsFromSpring6;

  @Test
  void sampleUsageOfRestClientInterface() {
    // the way to adapt your existing RestTemplate to RestClient
    RestClient restClient = RestClient.builder(new RestTemplate()).build();

    mockServer.when(
        org.mockserver.model.HttpRequest.request()
            .withMethod("GET")
            .withHeader("Accept", "application/json")
            .withPath("/some-endpoint/John")
            .withQueryStringParameter("age", "25")
    ).respond(
        org.mockserver.model.HttpResponse.response()
            .withStatusCode(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"name\":\"John\",\"age\":25}")
    );

    // now we can use the new interface
    String url = "http://localhost:1090/some-endpoint/{name}?age={age}";
    var getResponse = restClient.get()
        .uri(url, "John", 25)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().body(SampleResponseModel.class);

    assertThat(getResponse).isEqualTo(new SampleResponseModel("John", 25));
  }

  @Test
  void usingRestClientWithInterfacedDefinedProxy() {

    interface RemoteServiceAsInterface {
      @org.springframework.web.service.annotation.GetExchange("/some-endpoint/{name}")
      SampleResponseModel getSampleResponseModel(
          @org.springframework.web.bind.annotation.PathVariable String name,
          @org.springframework.web.bind.annotation.RequestParam Integer age
      );
    }

    mockServer.when(
        org.mockserver.model.HttpRequest.request()
            .withMethod("GET")
            .withPath("/some-endpoint/John")
            .withQueryStringParameter("age", "25")
    ).respond(
        org.mockserver.model.HttpResponse.response()
            .withStatusCode(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"name\":\"John\",\"age\":25}")
    );


    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
        // supports both RestTemplate and RestClient
        /*.exchangeAdapter(RestTemplateAdapter.create(new RestTemplateBuilder()
                .rootUri("http://localhost:1090").build()
        ))*/
        .exchangeAdapter(RestClientAdapter.create(RestClient.builder()
            .baseUrl("http://localhost:1090")
            .build()))
        .build();

    RemoteServiceAsInterface remoteServiceProxy = factory.createClient(RemoteServiceAsInterface.class);

    SampleResponseModel responseModel = remoteServiceProxy.getSampleResponseModel("John", 25);

    assertThat(responseModel).isEqualTo(new SampleResponseModel("John", 25));
  }


  /**
   * YYYTemplate and YYYOperations are common names for classes that encapsulate the operations
   * with a specific service. For example:
   */
  RestOperations restOperationsIsAnInterfaceForMakingHttpRequests;


  /**
   * In data-jpa world,
   */
  JdbcOperations jdbcOperations;

  NamedParameterJdbcOperations namedParameterJdbcOperations;

  JdbcTemplate jdbcTemplate;

  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /**
   * Alternative to {@link org.springframework.transaction.annotation.Transactional}
   */
  TransactionOperations transactionOperations;

  TransactionTemplate transactionTemplate;

  /**
   * Mongo and redis have their own templates.
   */

  MongoOperations mongoOperations;

  MongoTemplate mongoTemplate;

  /**
   * <K> – the Redis key type against which the template works (usually a String)
   * <V> – the Redis value type against which the template works
   */
  RedisOperations<String, String> redisOperations;

  RedisTemplate<String, String> redisTemplate;

}
