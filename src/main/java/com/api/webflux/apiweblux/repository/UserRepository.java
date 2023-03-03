package com.api.webflux.apiweblux.repository;

import com.api.webflux.apiweblux.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
