package com.spring.Resource;

import com.spring.Entities.Mensage;
import com.spring.Entities.User;
import com.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable String email) {
        List<User> users = userService.findByEmail(email);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/filter/email/{email}")
    public ResponseEntity<List<User>> getUserByEmailFilter(@PathVariable String email) {
        try{
            List<User> users = userService.findByLikeEmail(email);
            return ResponseEntity.ok().body(users);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Mensage<User>> createUser(@RequestParam String name, @RequestParam String email,
                                                    @RequestParam String phone,@RequestParam String password) {
        User user = new User(name, email, phone, password);

        if(userService.findByEmail(email).size() > 0) {
            return ResponseEntity.internalServerError().body(new Mensage<User>("E-mail já cadastrado", user));
        }

        try{
            userService.newUser(user);
            return ResponseEntity.ok().body(new Mensage<User>("Usuário cadastrado com sucesso", user));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().body(new Mensage<User>(e.fillInStackTrace().toString(), user));
        }
    }
}
