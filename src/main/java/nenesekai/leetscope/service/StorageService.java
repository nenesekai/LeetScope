package nenesekai.leetscope.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * source: https://spring.io/guides/gs/uploading-files/
 */
public interface StorageService {

    void init();

    void store(String prefix, String id, String fileName, MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}