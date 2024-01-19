package com.person;

import com.person.platform.CommonUrl;
import com.person.user.api.request.CreateUserRequest;
import com.person.user.port.UserUrl;
import com.person.utils.CaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@CaseTest
class UserCaseTests {

    @Autowired
    private WebTestClient webTestClient;

    private final CreateUserRequest createUserRequest = new CreateUserRequest("Ivan", "11", "1");

    private final CreateUserRequest updateUserRequest = new CreateUserRequest("John", "12", "2");

    @Test
    @Sql(config = @SqlConfig(encoding = "UTF-8"), scripts = "classpath:db/insert-default-user.sql")
    void should_get_users_true() {
        webTestClient.method(HttpMethod.GET)
                .uri(CommonUrl.API + UserUrl.USERS)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4);
    }

    @Test
    void should_create_user_true() {
        Long userId = webTestClient.method(HttpMethod.POST)
                .uri(CommonUrl.API + UserUrl.USERS)
                .body(Mono.just(createUserRequest), CreateUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody().blockFirst();

        assert userId != null;
        webTestClient.method(HttpMethod.GET)
                .uri(CommonUrl.API + UserUrl.USERS + "/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(userId)
                .jsonPath("$.name").isEqualTo("Ivan")
                .jsonPath("$.address").isEqualTo("11")
                .jsonPath("$.description").isEqualTo("1");
    }

    @Test
    void should_update_user_true() {
        Long userId = webTestClient.method(HttpMethod.POST)
                .uri(CommonUrl.API + UserUrl.USERS)
                .body(Mono.just(createUserRequest), CreateUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody().blockFirst();

        assert userId != null;
        webTestClient.method(HttpMethod.PUT)
                .uri(CommonUrl.API + UserUrl.USERS + "/" + userId)
                .body(Mono.just(updateUserRequest), CreateUserRequest.class)
                .exchange()
                .expectStatus().isOk();

        webTestClient.method(HttpMethod.GET)
                .uri(CommonUrl.API + UserUrl.USERS + "/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(userId)
                .jsonPath("$.name").isEqualTo("John")
                .jsonPath("$.address").isEqualTo("12")
                .jsonPath("$.description").isEqualTo("2");
    }

    @Test
    void should_delete_user_true() {
        Long userId = webTestClient.method(HttpMethod.POST)
                .uri(CommonUrl.API + UserUrl.USERS)
                .body(Mono.just(createUserRequest), CreateUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody().blockFirst();

        assert userId != null;
        webTestClient.method(HttpMethod.DELETE)
                .uri(CommonUrl.API + UserUrl.USERS + "/" + userId)
                .body(Mono.just(updateUserRequest), CreateUserRequest.class)
                .exchange()
                .expectStatus().isOk();

        webTestClient.method(HttpMethod.GET)
                .uri(CommonUrl.API + UserUrl.USERS)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(0);
    }

}
