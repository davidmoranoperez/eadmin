package es.fpdual.eadmin.eadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EadminApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(EadminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EadminApplication.class, args);
		logger.info("Fin run");
		
		
	}
	
}
