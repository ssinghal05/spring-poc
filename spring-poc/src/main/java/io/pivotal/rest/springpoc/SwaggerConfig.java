package io.pivotal.rest.springpoc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Sonali", "https://github.com/ssinghalpvtl", "xxx@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Spring boot API", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "www.example.com", Arrays.asList());


    public static final Set<String> DEFAULT_MEDIA_TYPES = new HashSet<String>(
            Arrays.asList(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_XML.getType()));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_MEDIA_TYPES).consumes(DEFAULT_MEDIA_TYPES);
    }
}
