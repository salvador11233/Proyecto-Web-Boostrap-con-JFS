package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.SuministracionDAO;
import mx.edu.itspa.dto.Suministracion;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="suministracionBO")
@SessionScoped
public class SuministracionBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Suministracion suministracion;
	private SuministracionDAO suministracionDAO;
	
	public SuministracionBO() {
		suministracion = new Suministracion();
		suministracionDAO = new SuministracionDAO();
	}
	
	public List<Suministracion> getSuministraciones(){
		try {
			return suministracionDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String codigo) {
		try {
			suministracion = suministracionDAO.obtener(codigo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entro a edit");
		
		return "anadir_suministraciones";
	}
	
	public String save() {
		try {
			if(suministracion.getId_sum() == null || suministracion.getId_sum()==0) {
				System.out.println("Entro a insertar");
				suministracionDAO.insertar(suministracion);
			}else {
				System.out.println("Entro a modi");
				suministracionDAO.modificar(suministracion);
			}
			suministracion = new Suministracion();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "suministracion";
	}
	
	public String eliminar(String codigo) {
		try {
			suministracion = suministracionDAO.obtener(codigo);
			suministracionDAO.eliminar(suministracion);
			suministracion = new Suministracion();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "suministracion";
	}

	public Suministracion getSuministracion() {
		return suministracion;
	}

	public void setSuministracion(Suministracion suministracion) {
		this.suministracion = suministracion;
	}
	
}
