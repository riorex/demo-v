package com.demo.v.controller;

import com.demo.v.model.Client;
import com.demo.v.model.Document;
import com.demo.v.model.User;
import com.demo.v.repository.UserRepo;
import com.demo.v.service.ClientService;
import com.demo.v.service.DocumentService;
import com.demo.v.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("debug")
public class DebugController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    DocumentService documentService;

    @PostMapping(path = "/user/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("user/getinfo/{userId}")
    public ResponseEntity getInfoUser(@PathVariable @NotNull long userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping("user/get-doc/{userId}")
    public ResponseEntity getUserDoc(@PathVariable @NotNull long userId){
        return ResponseEntity.ok(documentService.getUserDocs(userId));
    }

    @GetMapping("client/getinfo/{clientId}")
    public ResponseEntity getInfoClient(@PathVariable @NotNull long clientId) {
        return ResponseEntity.ok(clientService.findByClientId(clientId));
    }

    @GetMapping("client/delete/{clientId}")
    public ResponseEntity delClient(@PathVariable @NotNull long clientId) {
        return ResponseEntity.ok(clientService.deleteClient(clientId));
    }

    @PostMapping(path = "document/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addDocument(@RequestBody Document doc) {
        return ResponseEntity.ok(documentService.save(doc));
    }

    @PostMapping(path = "client/upsert", consumes = "application/json", produces = "application/json")
    public ResponseEntity addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createOrUpdateClient(client));
    }

    @GetMapping("client/get-by-inn/{inn}")
    public ResponseEntity geClientInn(@PathVariable @NotNull long inn){
        return ResponseEntity.ok(clientService.findByInn(inn));
    }

    @GetMapping("client/get-by-ogrn/{ogrn}")
    public ResponseEntity getClientOgrn(@PathVariable @NotNull long ogrn){
        return ResponseEntity.ok(clientService.findByOgrn(ogrn));
    }

    @GetMapping("client/get-by-crdate/{crDate}")
    public ResponseEntity getClientCrDate(@PathVariable @NotNull Date crDate){
        return ResponseEntity.ok(clientService.findByCrDate(crDate));
    }

    @GetMapping("client/get-by-name/{fullName}")
    public ResponseEntity getClientFullName(@PathVariable @NotNull String fullName){
        return ResponseEntity.ok(clientService.findByFullName(fullName));
    }

}
