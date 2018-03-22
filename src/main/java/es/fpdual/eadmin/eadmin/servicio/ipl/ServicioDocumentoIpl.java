package es.fpdual.eadmin.eadmin.servicio.ipl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

@Service
public class ServicioDocumentoIpl implements ServicioDocumento{
	
	RepositorioDocumento repositorioDocumento;
	
	@Autowired
	public ServicioDocumentoIpl(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}

	@Override
	public void altaDocumento(Documento documento) {
		
		repositorioDocumento.altaDocumento(documento);
		
	}

}
