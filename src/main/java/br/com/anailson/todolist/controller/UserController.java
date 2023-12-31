package br.com.anailson.todolist.controller;

import br.com.anailson.todolist.model.UserModel;
import br.com.anailson.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody  UserModel userModel ){

        var user =  this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
             //mensagem de erro
            //status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuáio já existe");
        }
        var userCreated =   this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
