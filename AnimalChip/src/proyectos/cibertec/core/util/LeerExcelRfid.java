package proyectos.cibertec.core.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import proyectos.cibertec.core.entity.Rfid;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LeerExcelRfid {
	
	private File inputFile;
	
	public File copyExcelToServer(UploadedFile uploadedFile) throws IOException{
		ExternalContext external = 	FacesContext.getCurrentInstance().getExternalContext();   		
		
		// Obtenemos la extension y el nombre temporal a usar.
		String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
		String filename = UUID.randomUUID().toString();
		
		// Obtenemos el path a usar 
		// TODO: cambiar el string "/photocam" para que se lea de un properties
		String path = external.getRealPath("/photocam") ;
		
		// Obtenemos el stream
		InputStream input = uploadedFile.getInputstream();
		
		// OBtenemos el directorio y lo creamos en caso no exista
		File directory = new File(path);
		
		if(!directory.exists()){
			directory.mkdir();
		}
		
		
		// Creamos el objeto File con la ruta 
		File file = new File(path, filename +"."+ extension);
		System.out.println(directory.getPath());
		System.out.println(file.getPath());
		  	
		// Copiamos el archivo
		FileOutputStream output = new FileOutputStream(file);
				
		try {
		    IOUtils.copy(input, output);
		    this.inputFile = file;
		    return file;
		} finally {
		    IOUtils.closeQuietly(output);
		    IOUtils.closeQuietly(input);
		}		
	}

	// El metodo debe leer el File en inputFile y cargar los datos del RFID en una lista, devolviendo la lsita
	public List<Rfid> readRfid() throws IOException, IllegalStateException, BiffException {
		
		// Comprobamos si existe el file
		if(inputFile==null){		
			throw new IllegalStateException("No se definio un objeto File para leer.");
		}
		if(!inputFile.exists()){
			throw new IllegalStateException("El archivo " + inputFile.getName() + "no existe.");
		}		
		
		// Creamos la lista
		List<Rfid> lista = new ArrayList<Rfid>();
		
		Workbook w = Workbook.getWorkbook(inputFile);
		// Obtenemos el workbook y la hoja			
		Sheet sheet = w.getSheet(0);
	  
		// Leemos el excel
		for(int i = 1; i< sheet.getRows(); i++){
			Rfid oRfid = new Rfid();
			oRfid.setFechaIngreso(new Date());		
			Cell cell1  = sheet.getCell(5, i);
			CellType type = cell1.getType();
			if (type == CellType.LABEL) {
				oRfid.setCodInterno(cell1.getContents());    		  
				oRfid.setCorrelativo(i);    
			}
			lista.add(oRfid); 
		}
		w.close();
		return lista;  
	}	

	//====== Getters & Setters ======

	public File getInputFile() {
		return inputFile;
	}
	
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

} 