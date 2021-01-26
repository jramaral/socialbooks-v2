package com.socialbooks.jra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   public Docket cursoApi(){
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.socialbooks.jra"))
               .paths(regex("/"))
               .build()
               .apiInfo(metadataInfo());

   }
    private ApiInfo metadataInfo() {
        ApiInfo apiInfo;
        apiInfo = new ApiInfo(
                "Books JRA",
                "Aapi Rest de Cadastro de Books",
                "1.0",
                "Termos of service",
                new Contact("Jose Roberto",
                        "https://github.com/jramaral",
                        "j.robertoamaral@gmail.com"),
                "Apache Licence Version 2.0",
                "https://www.apache.org/licensen.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}
