package pers.dlx.selfchat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oss")
public class OSSConfig {
    @Getter
    @Setter
    private String bucketName;
    @Getter
    @Setter
    private String endpoint;
    @Getter
    @Setter
    private String accessKeyId;
    @Getter
    @Setter
    private String accessKeySecret;
}
