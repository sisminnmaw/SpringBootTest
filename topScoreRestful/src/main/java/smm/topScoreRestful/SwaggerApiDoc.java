package smm.topScoreRestful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * This class wrote for API doc
 * no need in Production
 * This class used Swagger Tools https://swagger.io/
 */

@Configuration
@EnableSwagger2
public class SwaggerApiDoc {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}

}
	