package com.api.webflux.apiweblux.controllers;


import com.api.webflux.apiweblux.document.User;
import com.api.webflux.apiweblux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping
    public Mono<ResponseEntity<Flux<User>>> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping()
    public Mono<ResponseEntity<User>> save(@Validated @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        return userService
                .update(id,
                        user.getFisrtName(),
                        user.getLastName(),
                        user.getPhoneNumber()
                );
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }
}
