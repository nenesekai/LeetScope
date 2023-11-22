package nenesekai.leetscope.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * source: https://spring.io/guides/gs/uploading-files/
 */
@Configuration
public class StorageProperties {
    @Getter
    @Setter
    private String location = "/Users/asiimoviet/Documents/IntelliJ IDEA/LeetScope/temp";
    @Getter
    @Setter
    private String compilerLocation = "/usr/bin/g++";
}
