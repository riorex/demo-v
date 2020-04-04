package com.demo.v.controller;

import com.demo.v.model.Client;
import com.demo.v.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/getinfo/{clientId}")
    public ResponseEntity getInfoClient(@PathVariable @NotNull long clientId) {
        return ResponseEntity.ok(clientService.findByClientId(clientId));
    }

    @GetMapping("/delete/{clientId}")
    public ResponseEntity delClient(@PathVariable @NotNull long clientId) {
        return ResponseEntity.ok(clientService.deleteClient(clientId));
    }

    @PostMapping(path = "/upsert", consumes = "application/json", produces = "application/json")
    public ResponseEntity addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createOrUpdateClient(client));
    }

    @GetMapping("/get-by-inn/{inn}")
    public ResponseEntity geClientInn(@PathVariable @NotNull long inn){
        return ResponseEntity.ok(clientService.findByInn(inn));
    }

    @GetMapping("/get-by-ogrn/{ogrn}")
    public ResponseEntity getClientOgrn(@PathVariable @NotNull long ogrn){
        return ResponseEntity.ok(clientService.findByOgrn(ogrn));
    }

    @GetMapping("/get-by-crdate/{crDate}")
    public ResponseEntity getClientCrDate(@PathVariable @NotNull Date crDate){
        return ResponseEntity.ok(clientService.findByCrDate(crDate));
    }

    @GetMapping("/get-by-name/{fullName}")
    public ResponseEntity getClientFullName(@PathVariable @NotNull String fullName){
        return ResponseEntity.ok(clientService.findByFullName(fullName));
    }
}
