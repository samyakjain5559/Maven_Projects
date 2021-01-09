package com.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ShoppingCart.class);
		ctx.refresh();
		
		SpringApplication.run(ShoppingCartApplication.class, args);
		ctx.close();

	}

}
