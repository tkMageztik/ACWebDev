package proyectos.cibertec.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import proyectos.cibertec.core.entity.TipoEntidad;
import proyectos.cibertec.core.service.TipoEntidadService;

@FacesConverter(value="tipocomprobanteConverter")
public class TipoEntidadConverter implements Converter {

	@Autowired
	private TipoEntidadService service;
	
	@Override
	public Object  getAsObject(FacesContext context, UIComponent component, String value) {
		  if(value==null|| value.equals("")){
	            return null;
	        }
	                
            int id = -1;
            
            //Trycatch?
            try{
                    id = Integer.parseInt(value);
            }
            catch (NumberFormatException pex){
                    id = -1;
            }             
            
            try {
				return service.obtenerPorId(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 if(value==null|| !(value instanceof TipoEntidad)){
	            return null;
	        }
	                
		 TipoEntidad gpActual = (TipoEntidad) value;
	                return String.valueOf(gpActual.getId());                        
	        }

}
