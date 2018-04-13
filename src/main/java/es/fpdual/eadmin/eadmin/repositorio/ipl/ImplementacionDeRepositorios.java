package es.fpdual.eadmin.eadmin.repositorio.ipl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class ImplementacionDeRepositorios implements RepositorioDocumento {

	private DocumentoMapper mapper;
	
	@Autowired
	public ImplementacionDeRepositorios(DocumentoMapper mapper) {
		this.mapper= mapper;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ImplementacionDeRepositorios.class);

	PrintWriter pw = null;
	FileWriter file = null;
	String archivoAlta = "Alta.txt";
	String archivoLista = "Listado.txt";
	String archivoEliminar = "Eliminar.txt";
	String archivoModificado = "Modificar.txt";
	String excel = "Documentos.xls";
 
	@Override
	public void altaDocumento(Documento documento) {
//		logger.info("Comienza el método de altaDocumento");
		//exportExcel("Alta", documento, excel);	
		this.mapper.insertarDocumento(documento);	
//		logger.info("Saliendo del exportar excel");
//		altaDocumentoFichero(documento);
//		logger.info(toString() + documento.getCodigo() + " se ha creado correctamente");
//
//		logger.info("Saliendo del método de altaDocumento");
	}

	public static void exportExcel(String nombreHoja, Documento documento, String fileName) {

		try {
			FileInputStream inputStream = new FileInputStream(new File(fileName));
			Workbook workbook = WorkbookFactory.create(inputStream);
			CellStyle style = workbook.createCellStyle();
			
			
			// Sets the cell background
			 style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		 
			// Styles
			HSSFCellStyle headerStyle = (HSSFCellStyle) workbook.createCellStyle();
			headerStyle.setFillForegroundColor(HSSFColor.CORAL.index);
			headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFCellStyle oddRowStyle;
			
			HSSFCellStyle evenRowStyle = (HSSFCellStyle) workbook.createCellStyle();
			evenRowStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			evenRowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// Integer to store the index of the next row			

			int numeroHoja;
			if (nombreHoja.equals("Alta")) {
				numeroHoja = 0;
			} else if (nombreHoja.equals("Modificar")) {
				numeroHoja = 1;
			} else if (nombreHoja.equals("Eliminar")) {
				numeroHoja = 2;
			} else {
				numeroHoja = 3;
			}

			Sheet sheet = workbook.getSheetAt(numeroHoja);

			Object[] bookData = { documento.getCodigo(), documento.getNombre(), documento.getFechaCreacion().toString(),
					documento.getEstado().toString() };

			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(++rowCount);
			

			int columnCount = 0;

			Cell cell = row.createCell(columnCount);
			cell.setCellValue(rowCount);
			

			for (Object field : bookData) {
				cell = row.createCell(++columnCount);
				
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
				if(rowCount%2==0) {
					cell.setCellStyle(evenRowStyle);
				}
				
			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String[]> importExcel(String fileName, int numColums, String hoja) {

		// ArrayList donde guardaremos todos los datos del excel
		ArrayList<String[]> data = new ArrayList<>();
		XSSFSheet sheet = null;

		try {
			// Acceso al fichero xlsx
			FileInputStream file = new FileInputStream(new File(fileName));

			// Creamos la referencia al libro del directorio dado
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Obtenemos la primera hoja
			switch (hoja) {
			case "Alta":
				sheet = workbook.getSheetAt(0);
				break;
			case "Modificar":
				sheet = workbook.getSheetAt(1);
				break;
			case "Eliminar":
				sheet = workbook.getSheetAt(2);
				break;
			case "TodosDocumentos":
				sheet = workbook.getSheetAt(3);
				break;
			}

			// Iterador de filas
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// Iterador de celdas
				Iterator<Cell> cellIterator = row.cellIterator();
				// contador para el array donde guardamos los datos de cada fila
				int contador = 0;
				// Array para guardar los datos de cada fila
				// y añadirlo al ArrayList
				String[] fila = new String[numColums];
				// iteramos las celdas de la fila
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Guardamos los datos de la celda segun su tipo
					switch (cell.getCellType()) {
					// si es numerico
					case Cell.CELL_TYPE_NUMERIC:
						fila[contador] = (int) cell.getNumericCellValue() + "";
						break;
					// si es cadena de texto
					case Cell.CELL_TYPE_STRING:
						fila[contador] = cell.getStringCellValue() + "";
						break;
					}
					// Si hemos terminado con la ultima celda de la fila
					if ((contador + 1) % numColums == 0) {
						// Añadimos la fila al ArrayList con todos los datos
						data.add(fila);
					}
					// Incrementamos el contador
					// con cada fila terminada al redeclarar arriba el contador,
					// no obtenemos excepciones de ArrayIndexOfBounds
					contador++;
				}
			}
			// Cerramos el fichero y workbook
			file.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Excel importado correctamente\n");

		return data;
	}

	private void altaDocumentoFichero(Documento documento) {
		try {
			file = new FileWriter(archivoAlta, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: " + documento.getCodigo());
			pw.println("Nombre: " + documento.getNombre());
			pw.println("Fecha: " + documento.getFechaCreacion());
			pw.println("Estado: " + documento.getEstado());
			pw.println("Fecha modificacion: " + documento.getFechaModificacion());
			pw.println("Publico: " + documento.getPublico() + "\n");

			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modificarDocumento(Documento documento) {
//		logger.info("Comienza el método de modificarDocumento");
//		logger.info("El documento a modificar es: " + documento.getNombre() + ", " + documento.getCodigo() + ", "
//				+ "Fecha: " + documento.getFechaCreacion());
//		
		this.mapper.modificarDocumento(documento);
		modificarDocumentoFichero(documento);
//
//		logger.info("El documento modificado se queda: " + documento.getNombre() + ", " + documento.getCodigo() + ", "
//				+ "Fecha: " + documento.getFechaCreacion());
//		documentos.set(documentos.indexOf(documento), documento);
		//exportExcel("Modificar", documento, excel);
//		logger.info("Saliendo del método de modificarDocumento");
	}

	private void modificarDocumentoFichero(Documento documento) {
		try {
			file = new FileWriter(archivoModificado, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: " + documento.getCodigo());
			pw.println("Nombre: " + documento.getNombre());
			pw.println("Fecha: " + documento.getFechaCreacion());
			;
			pw.println("Estado: " + documento.getEstado());
			pw.println("Fecha modificacion: " + documento.getFechaModificacion());
			pw.println("Publico: " + documento.getPublico() + "\n");

			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarDocumento(Documento documento) {
//		logger.info("Comienza el método de eliminarDocumento");
//		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> d.getCodigo().equals(codigo))
//				.findFirst();
//
//		if (documentoEncontrado.isPresent()) {
			this.mapper.eliminarDocumento(documento);
//			eliminarDocumentoFichero(documentoEncontrado);

//			documentos.remove(documentoEncontrado.get());
			//exportExcel("Eliminar", documentoEncontrado.get(), excel);
//			logger.info("El documento con codigo: " + documentoEncontrado.get().getCodigo()
//					+ ", ha sido eliminado correctamente");
//		}
//		logger.info("Saliendo del método de eliminarDocumento");
	}

	private void eliminarDocumentoFichero(Optional<Documento> documentoEncontrado) {
		try {
			file = new FileWriter(archivoEliminar, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: " + documentoEncontrado.get().getCodigo());
			pw.println("Nombre: " + documentoEncontrado.get().getNombre());
			pw.println("Fecha: " + documentoEncontrado.get().getFechaCreacion());
			;
			pw.println("Estado: " + documentoEncontrado.get().getEstado());
			pw.println("Fecha modificacion: " + documentoEncontrado.get().getFechaModificacion());
			pw.println("Publico: " + documentoEncontrado.get().getPublico() + "\n");

			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
//		logger.info("Comienza el método de eliminar documento");
//		for (Documento doc : documentos) {
//			logger.info("***********");
//			logger.info("Documento: " + doc.getCodigo());
//			logger.info("Nombre: " + doc.getNombre());
//			logger.info("Fecha: " + doc.getFechaCreacion());
//			//exportExcel("TodosDocumentos", doc, excel);
//			logger.info("****************");
		}
		logger.info("Saliendo del método de obtenerTodosLosDocumento");
		return getDocumentos();
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
//		logger.info("Entrando en el método de obtenerDocumentoPorCodigo");
//		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> d.getCodigo().equals(codigo))
//				.findFirst();
//		if (documentoEncontrado.isPresent()) {
//			logger.info("Saliendo del método de obtenerDocumentoPorCodigo");
//			return documentoEncontrado.get();
//		}
		this.mapper.consultarDocumento(codigo);
//		logger.info("Saliendo del método de obtenerDocumentoPorCodigo");
//		return null;
	}

	public void escribeLista() {
		logger.info("Comienza la escritura en la lista");
		try {
			file = new FileWriter(archivoLista, true);
			pw = new PrintWriter(file);

			for (Documento doc : documentos) {
				pw.println("*********************");
				pw.println("Documento: " + doc.getCodigo());
				pw.println("Nombre: " + doc.getNombre());
				pw.println("Fecha: " + doc.getFechaCreacion());
				;
				pw.println("Estado: " + doc.getEstado());
				pw.println("Fecha modificacion: " + doc.getFechaModificacion());
				pw.println("Publico: " + doc.getPublico());

			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Saliendo del método de escribir lista");
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public String toString() {
		return "El documento con codigo ";
	}

}
