package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;


import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;
import es.fpdual.eadmin.eadmin.util.Utilidades;

public class ModeloBaseAdministracionElectronicaTest extends AbstractoModeloBeanTest<ModeloBaseAdministracionElectronica> {

	private static final Integer CODIGO = 56;
	private static final String NOMBRE = "prueba";
    private static final Date FECHACREACION = Utilidades.asDate(LocalDate.of(2015, 2, 1));
    private static final Date FECHAMODIFICACION = Utilidades.asDate(LocalDate.of(2015, 2, 1));


    class ModeloBaseAdministracionElectronicaFake extends ModeloBaseAdministracionElectronica {

    	public ModeloBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion, Date fechaModificacion) {
    		super(codigo, nombre, fechaCreacion, fechaModificacion);
    	}

    }
    
	@Override
	public void before() {
		this.entityA1=new ModeloBaseAdministracionElectronicaFake(CODIGO, NOMBRE, FECHACREACION, FECHAMODIFICACION);
		this.entityA2=new ModeloBaseAdministracionElectronicaFake(CODIGO, NOMBRE, FECHACREACION, FECHAMODIFICACION);
		this.entityB=new ModeloBaseAdministracionElectronicaFake(1, NOMBRE, FECHACREACION, FECHAMODIFICACION);
		
	}


	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(CODIGO, entityA1.getCodigo());
		assertEquals(NOMBRE, entityA1.getNombre());
		assertEquals(FECHACREACION, entityA1.getFechaCreacion());
		assertEquals(FECHAMODIFICACION, entityA1.getFechaModificacion());
		
	}


}


