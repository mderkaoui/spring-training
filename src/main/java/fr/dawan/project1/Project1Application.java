package fr.dawan.project1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@ComponentScan(basePackages = "") si les fichiers sont placés dans un autre répertoire autre que celui par défaut
public class Project1Application {

	private static Logger myLogger = LoggerFactory.getLogger(Project1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Value("${app.allowedorigin}")
	private String allowedOrigin;

	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						//.allowedOrigins(allowedOrigin)
						.allowedOrigins("*")
						.allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");

				//On peut ajouter autant de règles que l'on souhaite
			}

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				//registry.addInterceptor(myInterceptor);
			}
		};
	}

}
