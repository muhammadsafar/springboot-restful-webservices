package com.baharuddinfamily.learn.springbootrestfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootRestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);

		System.out.println("hello from main class");
	}

	@Bean
	public LocaleResolver localeResolver() {

		// version 1
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver;

		// version 2
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();

		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

//  can be changed with set in properties application
//	@Bean
//	public ResourceBundleMessageSource messageSource() { // name must messageSource()
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

}
