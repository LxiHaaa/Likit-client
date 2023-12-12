package com.likit.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: LXY
 * @create: 2023-12-06 19:50
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "likit")
public class LikitProperties {

    public String localhost;

    public Integer port;

}
