package com.mall.autoconfig;


import com.mall.autoconfig.properties.OssProperties;
import com.mall.autoconfig.template.OssTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties({
        OssProperties.class
})
public class MallAutoConfiguration {
    @Bean
    public OssTemplate ossTemplate(OssProperties properties) {
        return new OssTemplate(properties);
    }
}
