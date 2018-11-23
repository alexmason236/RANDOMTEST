package com.zk.main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zk.config.ContextConfig;
import com.zk.service.EditPaticipators;

public class TestMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		@SuppressWarnings("resource")
		ApplicationContext context=new AnnotationConfigApplicationContext(ContextConfig.class);
		EditPaticipators nc=(EditPaticipators) context.getBean(EditPaticipators.class);
		nc.newClient();
	}
}
