package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.AdministracionDAO;
import mx.edu.itspa.dto.Administracion;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="administracionBO")
@SessionScoped
public class AdministracionBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Administracion administracion;
	private AdministracionDAO administracionDAO;
	
	public AdministracionBO() {
		administracion = new Administracion();
		administracionDAO = new AdministracionDAO();
	}
	
	public List<Administracion> getAdministradores(){
		try {
			return administracionDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String codigo) {
		try {
			administracion = administracionDAO.obtener(codigo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entro a edit");
		
		return "anadir_administradores";
	}
	
	public String save() {
		try {
			if(administracion.getId_admin() == null || administracion.getId_admin() ==0) {
				System.out.println("Entro a insertar");
				administracionDAO.insertar(administracion);
			}else {
				System.out.println("Entro a modi");
				administracionDAO.modificar(administracion);
			}
			administracion = new Administracion();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "administracion";
	}
	
	public String eliminar(String codigo) {
		try {
			administracion = administracionDAO.obtener(codigo);
			administracionDAO.eliminar(administracion);
			administracion = new Administracion();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "administracion";
	}

	public Administracion getAdministracion() {
		return administracion;
	}

	public void setAdministracion(Administracion administracion) {
		this.administracion = administracion;
	}
	
}
