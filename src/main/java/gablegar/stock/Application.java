package gablegar.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by glegarda on 13/03/18.
 */
@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String... args)
	{
		SpringApplication.run(Application.class, args);
	}
}
