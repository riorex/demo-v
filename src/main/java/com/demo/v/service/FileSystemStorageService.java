package com.demo.v.service;

import com.demo.v.config.StorageConfiguration;
import com.demo.v.exception.FileNotFoundException;
import com.demo.v.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path pathToStorage;

    @Autowired
    public FileSystemStorageService(StorageConfiguration storageConfiguration) {
        this.pathToStorage = Paths.get(storageConfiguration.getUploadDir());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.pathToStorage.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.pathToStorage, 1)
                    .filter(path -> !path.equals(this.pathToStorage))
                    .map(path -> this.pathToStorage.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return pathToStorage.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        FileSystemUtils.deleteRecursively(pathToStorage.resolve(fileName).toFile());
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(pathToStorage.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(pathToStorage);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}