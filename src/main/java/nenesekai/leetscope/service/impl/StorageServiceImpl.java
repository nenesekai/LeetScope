package nenesekai.leetscope.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import nenesekai.leetscope.config.StorageProperties;
import nenesekai.leetscope.service.StorageService;
import nenesekai.leetscope.util.exception.StorageException;
import nenesekai.leetscope.util.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * src: https://spring.io/guides/gs/uploading-files/
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final Path storageLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {

        if(properties.getRoot().toString().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.storageLocation = properties.storageLocation;
    }

    @Override
    public void store(String prefix, String id, String fileName, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.storageLocation.resolve(
                            Paths.get(prefix, id, fileName))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(destinationFile.getParent());
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.storageLocation, 1)
                    .filter(path -> !path.equals(this.storageLocation))
                    .map(this.storageLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return storageLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(storageLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(storageLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}