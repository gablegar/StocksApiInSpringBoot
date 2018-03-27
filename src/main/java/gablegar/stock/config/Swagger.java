package gablegar.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by glegarda on 14/03/18.
 * This class is responsible for Swagger configuration for api documentation
 */
@Configuration
public class Swagger
{

	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("gablegar.stock.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}


	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.license("Apache 2.0")
				.version("Version 1.0 - mw")
				.termsOfServiceUrl("urn:tos")
				.title("API REST for Stock")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("gablegar", null, "gabriel.legarda@gmail.com"))
				.description("This rest API is an example of a secure REST api for stocks.")
				.build();
	}

}