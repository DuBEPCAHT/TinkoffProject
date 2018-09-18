package com.dyarvoy.TinkoffProject;

import com.dyarvoy.TinkoffProject.service.FileService;
import com.dyarvoy.TinkoffProject.service.exception.FileCreateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Start point in app
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@SpringBootApplication
public class TinkoffProjectApplication {

	private static FileService fileService = new FileService();

	public static void main(String[] args) throws FileCreateException {
		//fileService.createInstanseFiles(120_000_000); //Generated 20 .txt files (1GB size)
		SpringApplication.run(TinkoffProjectApplication.class, args);
	}
}
