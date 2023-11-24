package nenesekai.leetscope.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * source: https://spring.io/guides/gs/uploading-files/
 */
@Configuration
public class StorageProperties {
    @Getter
    private final Path root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath());
    public Path storageLocation = root.resolve("temp").normalize().toAbsolutePath();
    public Path compilerLocation = Paths.get("/usr/bin/g++");
    public Path shellLocation = Paths.get("/bin/zsh");
}
