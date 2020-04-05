package com.demo.v.service;

import com.demo.v.model.File;
import com.demo.v.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    FileRepo fileRepo;

    long save(final File file) {
        fileRepo.save(file);
        return file.getFileId();
    }

}
