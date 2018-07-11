package co.com.foundation.intersoft.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "co.com.foundation.intersoft.api", "co.com.foundation.intersoft.repository",
		"co.com.foundation.intersoft.persistence" })
public class ApplicationConfiguration {

}
