package com.api.webflux.apiweblux.service;

import com.api.webflux.apiweblux.document.User;
import com.api.webflux.apiweblux.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Mono<ResponseEntity<Flux<User>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .body(userRepository.findAll())
        );
    }

    public Mono<ResponseEntity<User>> findById(String id) {
        return userRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    public Mono<ResponseEntity<User>> save(User user) {
        return userRepository.save(user)
                .map(person -> ResponseEntity.status(HttpStatus.CREATED).body(user));
    }

    public Mono<ResponseEntity<User>> update(String id, String firstName, String lastName, Long phoneNumber){
        return userRepository.findById(id)
                .flatMap(user -> {
                    if(!StringUtils.isEmpty(firstName)){
                        user.setFisrtName(firstName);
                    }

                    if(!StringUtils.isEmpty(lastName)) {
                        user.setLastName(lastName);
                    }

                    if(!StringUtils.isEmpty(String.valueOf(phoneNumber))) {
                        user.setPhoneNumber(phoneNumber);
                    }

                    return userRepository.save(user);
                }).map(n -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(n));
    }

    public Mono<ResponseEntity<Void>> deleteUser(String id){
        return userRepository.deleteById(id)
                .then(Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
