package es.fpdual.eadmin.eadmin.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.ipl.ImplementacionDeRepositorios;
import es.fpdual.eadmin.eadmin.servicio.ipl.ServicioDocumentoIpl;

public class ServicioDocumentoIplTest {
	
	private static final Integer CODIGO = 5; 
	private static final String NOMBRE= "prueba"; 
	private static final Date FECHACREACION = new Date(); 
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;

	private ServicioDocumento servicioDocumento;
	private ImplementacionDeRepositorios repositorioDocumento;
	private Documento documento = new Documento(CODIGO, NOMBRE, FECHACREACION, PUBLICO, ESTADO);
	
	
	@Before
	public void inicializarEnCadaTest() {
		this.repositorioDocumento = new ImplementacionDeRepositorios();
		this.servicioDocumento = new ServicioDocumentoIpl(repositorioDocumento);
		
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		this.servicioDocumento.altaDocumento(documento);
		
		assertEquals(1, this.repositorioDocumento.getDocumentos().size());
	}
}
