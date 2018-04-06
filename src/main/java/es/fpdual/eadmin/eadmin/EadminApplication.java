package es.fpdual.eadmin.eadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EadminApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(EadminApplication.class);

	public static void main(String[] args) {
		
logger.info("Esto es una prueba");
		
		//Debug
		logger.debug("Depuración");
		
		//Información
		logger.info("Informacion");
		
		//Error
		logger.error("Error");
		
		//Warning
		logger.warn("Warning");
		
		//Tracer
		logger.trace("Tracer");
		
		logger.info("Inicio run");
		SpringApplication.run(EadminApplication.class, args);
		logger.info("Fin run");
		
		
	}
	
}
