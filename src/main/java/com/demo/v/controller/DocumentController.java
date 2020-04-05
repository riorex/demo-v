package com.demo.v.controller;

import com.demo.v.model.Document;
import com.demo.v.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addDocument(@RequestBody Document doc) {
        return ResponseEntity.ok(documentService.save(doc));
    }

    @DeleteMapping(path = "/delete/{documentId}")
    public ResponseEntity delDocument(@PathVariable @NotNull long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-user-docs/{userId}")
    public ResponseEntity getUserDoc(@PathVariable @NotNull long userId) {
        return ResponseEntity.ok(documentService.getUserDocs(userId));
    }
}
