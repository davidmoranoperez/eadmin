package es.fpdual.eadmin.eadmin.servicio;

import java.util.Date;

import org.junit.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ipl.ServicioDocumentoIpl;
import static org.mockito.Mockito.*;

public class ServicioDocumentoIplTest {

	private static final Integer CODIGO = 5;
	private static final String NOMBRE = "prueba";
	private static final Date FECHACREACION = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;

	private ServicioDocumento servicioDocumento;
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	private Documento documento = mock(Documento.class);

	@Before
	public void inicializarEnCadaTest() {
		this.servicioDocumento = new ServicioDocumentoIpl(repositorioDocumento);

	}

	@Test
	public void deberiaAlmacenarUnDocumento() {
		this.servicioDocumento.altaDocumento(documento);

		verify(this.repositorioDocumento).altaDocumento(new Documento(CODIGO, NOMBRE, FECHACREACION, PUBLICO, ESTADO));
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		this.servicioDocumento.modificarDocumento(documento);

		verify(this.repositorioDocumento).modificarDocumento(new Documento(CODIGO, NOMBRE, FECHACREACION, PUBLICO, ESTADO));
	}
	
	@Test
	public void deberiaEliminarUnDocumento() {
		when(documento.getCodigo()).thenReturn(CODIGO);
		this.servicioDocumento.eliminarDocumento(documento.getCodigo());
		verify(this.repositorioDocumento).eliminarDocumento(CODIGO);
	}
}
