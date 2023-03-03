package com.api.webflux.apiweblux.service;

import com.api.webflux.apiweblux.document.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServiceInterface {

    Flux<User> findAll();
    Mono<ResponseEntity<User>> findUserById(String id);
    Mono<ResponseEntity<User>> save(User user);
}
