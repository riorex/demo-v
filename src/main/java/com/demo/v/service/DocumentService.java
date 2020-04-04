package com.demo.v.service;

import com.demo.v.model.Document;
import com.demo.v.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    DocumentRepo documentRepo;

    public long save(final Document doc) {
        documentRepo.save(doc);
        return doc.getDocumentId();
    }

    public List<Document> getUserDocs(Long userID) {
        return documentRepo.findByUserId(userID);
    }
}
